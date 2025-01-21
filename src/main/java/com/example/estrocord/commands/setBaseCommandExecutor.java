// src/main/java/com/example/cutiecord/commands/SetBaseCommandExecutor.java
package com.example.estrocord.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.example.estrocord.EstrocordPlugin;
import java.util.UUID;

public class setBaseCommandExecutor implements CommandExecutor {
    private final EstrocordPlugin plugin;

    public setBaseCommandExecutor(EstrocordPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        UUID playerUUID = player.getUniqueId();

        // Check if the player is jailed
        if (plugin.isPlayerJailed(player)) {
            player.sendMessage(ChatColor.RED + "You cannot setbase in Jail!");
            return true;
        }

        // Cooldown Check
        long lastSetTime = plugin.getConfig().getLong("base.cooldowns." + playerUUID, 0);
        long cooldownHours = plugin.getConfig().getInt("base-teleport.cooldown-hours", 1);
        long currentTime = System.currentTimeMillis();

        if ((currentTime - lastSetTime) < cooldownHours * 3600 * 1000) {
            long remainingTime = cooldownHours - ((currentTime - lastSetTime) / (3600 * 1000));

            player.sendMessage(ChatColor.RED + "You must wait " +
                    ChatColor.YELLOW + remainingTime +
                    ChatColor.RED + " more hours before setting a new base.");

            return true;
        }

        // Set Base Location
        Location baseLocation = player.getLocation();
        plugin.getBases().put(playerUUID, baseLocation);

        // Save to config
        plugin.getConfig().set("bases." + playerUUID, baseLocation.serialize());
        plugin.getConfig().set("base.cooldowns." + playerUUID, currentTime);
        plugin.saveConfig();

        player.sendMessage(ChatColor.GREEN + "Your base was set!");
        return true;
    }
}