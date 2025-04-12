/*
 * Copyright (c) 2025 Mazey-Jessica Emily Twilight
 * Copyright (c) 2025 UnifiedGaming Systems Ltd (Company Number: 16108983)
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package uk.co.clovetwilight3.estrocord.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import uk.co.clovetwilight3.estrocord.EstrocordPlugin;
import java.util.UUID;

public class playtimeCommandExecutor implements CommandExecutor {
    private final EstrocordPlugin plugin;

    public playtimeCommandExecutor(EstrocordPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        UUID playerUUID = player.getUniqueId();
        long totalPlaytime = plugin.getPlaytimeMap().getOrDefault(playerUUID, 0L);

        // Add current session time if player is online
        if (plugin.getLoginTimestamps().containsKey(playerUUID)) {
            totalPlaytime += System.currentTimeMillis() - plugin.getLoginTimestamps().get(playerUUID);
        }

        String formattedTime = formatTime(totalPlaytime);
        player.sendMessage(ChatColor.WHITE + "Your total playtime " + formattedTime);
        return true;
    }

    private String formatTime(long milliseconds) {
        long seconds = milliseconds / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        return String.format("%02d hours, %02d minutes, %02d seconds",
                hours,
                minutes % 60,
                seconds % 60
        );
    }
}