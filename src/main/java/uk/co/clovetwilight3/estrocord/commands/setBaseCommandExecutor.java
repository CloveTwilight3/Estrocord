/*
 * Copyright (c) 2025 Mazey-Jessica Emily Twilight
 * Copyright (c) 2025 UnifiedGaming Systems Ltd (Company Number: 16108983)
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package uk.co.clovetwilight3.estrocord.commands;

import uk.co.clovetwilight3.estrocord.EstrocordPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.co.clovetwilight3.CloveLib;

import java.util.UUID;

public class setBaseCommandExecutor implements CommandExecutor {

    private final EstrocordPlugin plugin;

    public setBaseCommandExecutor(EstrocordPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        if (!CloveLib.getInstance().canUseCommand(player, "setbase")) {
            player.sendMessage(ChatColor.RED + "You cannot use this command while jailed!");
            return true;
        }

        UUID playerUUID = player.getUniqueId();
        Location location = player.getLocation();

        plugin.getBases().put(playerUUID, location);
        plugin.getConfig().set("bases." + playerUUID, location.serialize());
        plugin.saveConfig();

        player.sendMessage(ChatColor.GREEN + "Your base location has been set!");
        return true;
    }
}