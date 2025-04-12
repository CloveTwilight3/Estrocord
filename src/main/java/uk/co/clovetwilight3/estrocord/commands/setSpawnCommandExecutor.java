/*
 * Copyright (c) 2025 Mazey-Jessica Emily Twilight
 * Copyright (c) 2025 UnifiedGaming Systems Ltd (Company Number: 16108983)
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package uk.co.clovetwilight3.estrocord.commands;

import uk.co.clovetwilight3.estrocord.EstrocordPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setSpawnCommandExecutor implements CommandExecutor {

    private final EstrocordPlugin plugin;

    public setSpawnCommandExecutor(EstrocordPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        if (!player.hasPermission("cutiecord.setspawn")) {
            player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        Location location = player.getLocation();
        World world = location.getWorld();

        if (world == null) {
            player.sendMessage(ChatColor.RED + "Failed to set spawn. Invalid world.");
            return true;
        }

        plugin.getConfig().set("spawn.world", world.getName());
        plugin.getConfig().set("spawn.x", location.getX());
        plugin.getConfig().set("spawn.y", location.getY());
        plugin.getConfig().set("spawn.z", location.getZ());
        plugin.getConfig().set("spawn.yaw", location.getYaw());
        plugin.getConfig().set("spawn.pitch", location.getPitch());
        plugin.saveConfig();

        world.setSpawnLocation(location);

        player.sendMessage(ChatColor.GREEN + "Server spawn has been set to your current location: " +
                ChatColor.AQUA + formatLocation(location));
        Bukkit.getLogger().info("Server spawn has been updated by " + player.getName() + " at " + formatLocation(location));
        return true;
    }

    private String formatLocation(Location location) {
        return String.format("World: %s, X: %.2f, Y: %.2f, Z: %.2f, Yaw: %.2f, Pitch: %.2f",
                location.getWorld().getName(),
                location.getX(),
                location.getY(),
                location.getZ(),
                location.getYaw(),
                location.getPitch());
    }
}
