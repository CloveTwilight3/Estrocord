package com.example.estrocord.commands;

import com.example.estrocord.EstrocordPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ConditionalCommandExecutor implements CommandExecutor {
    private final EstrocordPlugin plugin;
    private final String commandType;

    public ConditionalCommandExecutor(EstrocordPlugin plugin, String commandType) {
        this.plugin = plugin;
        this.commandType = commandType;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        if (!plugin.canExecuteCommand(player, commandType)) {
            return true;
        }

        switch (commandType) {
            case "setspawn":
                Location spawnLocation = player.getLocation();
                plugin.getConfig().set("spawn.world", spawnLocation.getWorld().getName());
                plugin.getConfig().set("spawn.x", spawnLocation.getX());
                plugin.getConfig().set("spawn.y", spawnLocation.getY());
                plugin.getConfig().set("spawn.z", spawnLocation.getZ());
                plugin.getConfig().set("spawn.yaw", spawnLocation.getYaw());
                plugin.getConfig().set("spawn.pitch", spawnLocation.getPitch());
                plugin.saveConfig();
                player.sendMessage(ChatColor.GREEN + "Spawn location set!");
                break;

            case "spawn":
                Location savedSpawn = getSavedSpawnLocation(player);
                if (savedSpawn != null) {
                    player.teleport(savedSpawn);
                    player.sendMessage(ChatColor.GREEN + "Teleported to spawn!");
                } else {
                    player.sendMessage(ChatColor.RED + "Spawn location has not been set!");
                }
                break;

            case "setbase":
                Location baseLocation = player.getLocation();
                plugin.getBases().put(player.getUniqueId(), baseLocation);
                player.sendMessage(ChatColor.GREEN + "Base location set!");
                plugin.saveConfig();
                break;

            case "base":
                Location playerBase = plugin.getBaseLocation(player.getUniqueId());
                if (playerBase != null) {
                    player.teleport(playerBase);
                    player.sendMessage(ChatColor.GREEN + "Teleported to your base!");
                } else {
                    player.sendMessage(ChatColor.RED + "You have not set a base location!");
                }
                break;
        }

        return true;
    }

    private Location getSavedSpawnLocation(Player player) {
        if (!plugin.getConfig().contains("spawn")) return null;

        String worldName = plugin.getConfig().getString("spawn.world");
        double x = plugin.getConfig().getDouble("spawn.x");
        double y = plugin.getConfig().getDouble("spawn.y");
        double z = plugin.getConfig().getDouble("spawn.z");
        float yaw = (float) plugin.getConfig().getDouble("spawn.yaw");
        float pitch = (float) plugin.getConfig().getDouble("spawn.pitch");

        return new Location(
                plugin.getServer().getWorld(worldName),
                x, y, z,
                yaw, pitch
        );
    }
}