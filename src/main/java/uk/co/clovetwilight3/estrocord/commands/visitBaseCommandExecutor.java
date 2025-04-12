/*
 * Copyright (c) 2025 Mazey-Jessica Emily Twilight
 * Copyright (c) 2025 UnifiedGaming Systems Ltd (Company Number: 16108983)
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package uk.co.clovetwilight3.estrocord.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import uk.co.clovetwilight3.estrocord.EstrocordPlugin;
import uk.co.clovetwilight3.CloveLib;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class visitBaseCommandExecutor implements CommandExecutor, TabCompleter {
    private final EstrocordPlugin plugin;

    public visitBaseCommandExecutor(EstrocordPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player visitor)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        if (!CloveLib.getInstance().canUseCommand(visitor, "visitbase")) {
            visitor.sendMessage(ChatColor.RED + "You cannot use this command while jailed!");
            return true;
        }

        if (args.length != 1) {
            visitor.sendMessage(ChatColor.AQUA + "Usage: /visitbase <player>");
            return true;
        }

        Player target = plugin.getServer().getPlayer(args[0]);

        if (target == null || !target.isOnline()) {
            visitor.sendMessage(ChatColor.RED + "The target could not be found!");
            return true;
        }

        UUID targetUUID = target.getUniqueId();
        if (!plugin.getBases().containsKey(targetUUID)) {
            visitor.sendMessage(ChatColor.RED + "The target has no base!");
            return true;
        }

        Location targetBase = plugin.getBases().get(targetUUID);
        if (visitor.teleport(targetBase)) {
            visitor.sendMessage(ChatColor.GREEN + "Teleported to " +
                    ChatColor.YELLOW + target.getName() +
                    ChatColor.GREEN + "'s base!");
        } else {
            visitor.sendMessage(ChatColor.RED + "Failed to teleport to " +
                    ChatColor.YELLOW + target.getName() + ChatColor.RED + "'s base. Please try again later!");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (sender instanceof Player && sender.hasPermission("cutiecord.visitbase")) {
            if (args.length == 1) {
                String partialName = args[0].toLowerCase();
                for (Player player : plugin.getServer().getOnlinePlayers()) {
                    if (plugin.getBases().containsKey(player.getUniqueId()) &&
                            player.getName().toLowerCase().startsWith(partialName)) {
                        completions.add(player.getName());
                    }
                }
            }
        }

        return completions;
    }
}
