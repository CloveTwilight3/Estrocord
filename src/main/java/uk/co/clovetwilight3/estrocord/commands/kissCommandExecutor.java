/*
 * Copyright (c) 2025 Mazey-Jessica Emily Twilight
 * Copyright (c) 2025 UnifiedGaming Systems Ltd (Company Number: 16108983)
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */


package uk.co.clovetwilight3.estrocord.commands;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import uk.co.clovetwilight3.estrocord.EstrocordPlugin;
import java.util.ArrayList;
import java.util.List;

public class kissCommandExecutor implements CommandExecutor, TabCompleter {
    private final EstrocordPlugin plugin;

    public kissCommandExecutor(EstrocordPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player senderPlayer)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        if (args.length != 1) {
            senderPlayer.sendMessage(ChatColor.AQUA + "Usage: /kiss <player>");
            return true;
        }

        Player targetPlayer = plugin.getServer().getPlayer(args[0]);
        if (targetPlayer == null) {
            senderPlayer.sendMessage(ChatColor.RED + "Player not found!");
            return true;
        }

        if (senderPlayer.equals(targetPlayer)) {
            senderPlayer.sendMessage(ChatColor.RED + "You can't kiss yourself!");
            return true;
        }

        // Broadcast the kiss message
        plugin.getServer().broadcastMessage(
                ChatColor.LIGHT_PURPLE + senderPlayer.getName() +
                        ChatColor.WHITE + " kisses " +
                        ChatColor.LIGHT_PURPLE + targetPlayer.getName() +
                        ChatColor.WHITE + "!"
        );

        // Play level up sound for the sender
        senderPlayer.playSound(senderPlayer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            String partialName = args[0].toLowerCase();
            for (Player player : plugin.getServer().getOnlinePlayers()) {
                if (player.getName().toLowerCase().startsWith(partialName)) {
                    completions.add(player.getName());
                }
            }
        }

        return completions;
    }
}