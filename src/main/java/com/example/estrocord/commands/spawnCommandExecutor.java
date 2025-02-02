/*
 * Copyright (c) 2025 Mazey-Jessica Emily Twilight
 * Copyright (c) 2025 UnifiedGaming Systems Ltd (Company Number: 16108983)
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.example.estrocord.commands;

import com.example.estrocord.EstrocordPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.org.clovelib.CloveLib; // Import CloveLib

public class spawnCommandExecutor implements CommandExecutor {

    private final EstrocordPlugin plugin;

    public spawnCommandExecutor(EstrocordPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        // Check with CloveLib if the player is allowed to use the spawn command
        if (!CloveLib.getInstance().canUseCommand(player, "spawn")) {
            player.sendMessage(ChatColor.RED + "You cannot use this command while jailed!");
            return true;
        }

        Location spawnLocation = getSpawnLocation();
        if (spawnLocation == null) {
            player.sendMessage(ChatColor.RED + "The server spawn location is not set!");
            return true;
        }

        player.teleport(spawnLocation);
        player.sendMessage(ChatColor.GREEN + "You have been teleported to the server spawn!");
        return true;
    }

    private Location getSpawnLocation() {
        if (!plugin.getConfig().contains("spawn.world")) {
            Bukkit.getLogger().warning("Spawn location not found in config!");
            return null;
        }

        try {
            String worldName = plugin.getConfig().getString("spawn.world");
            double x = plugin.getConfig().getDouble("spawn.x");
            double y = plugin.getConfig().getDouble("spawn.y");
            double z = plugin.getConfig().getDouble("spawn.z");
            float yaw = (float) plugin.getConfig().getDouble("spawn.yaw");
            float pitch = (float) plugin.getConfig().getDouble("spawn.pitch");

            World world = Bukkit.getWorld(worldName);
            if (world == null) {
                Bukkit.getLogger().severe("Invalid spawn world: " + worldName);
                return null;
            }

            return new Location(world, x, y, z, yaw, pitch);
        } catch (Exception e) {
            Bukkit.getLogger().severe("Failed to load spawn location from config: " + e.getMessage());
            return null;
        }
    }
}