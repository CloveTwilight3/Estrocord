package com.example.estrocord.commands;

import com.example.estrocord.EstrocordPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class veinminerCommandExecutor implements CommandExecutor {
    private final EstrocordPlugin plugin;
    private final Map<UUID, Boolean> treeVeinMinerEnabled = new HashMap<>();
    private final Map<UUID, Boolean> oreVeinMinerEnabled = new HashMap<>();

    public veinminerCommandExecutor(EstrocordPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage(ChatColor.AQUA + "Usage: /veinminer <ores|trees>");
            return true;
        }

        UUID playerUUID = player.getUniqueId();
        String option = args[0].toLowerCase();

        switch (option) {
            case "ores" -> {
                boolean isEnabled = oreVeinMinerEnabled.getOrDefault(playerUUID, false);
                oreVeinMinerEnabled.put(playerUUID, !isEnabled);
                player.sendMessage(ChatColor.GREEN + "VeinMining for ores is now " +
                        (isEnabled ? ChatColor.RED + "disabled" : ChatColor.GREEN + "enabled") + ChatColor.GREEN + ".");
                return true;
            }
            case "trees" -> {
                boolean isEnabled = treeVeinMinerEnabled.getOrDefault(playerUUID, false);
                treeVeinMinerEnabled.put(playerUUID, !isEnabled);
                player.sendMessage(ChatColor.GREEN + "VeinMining for trees is now " +
                        (isEnabled ? ChatColor.RED + "disabled" : ChatColor.GREEN + "enabled") + ChatColor.GREEN + ".");
                return true;
            }
            default -> {
                player.sendMessage(ChatColor.RED + "Invalid option. Use /veinminer <ores|trees>");
                return true;
            }
        }
    }

    public boolean isOreVeinMinerEnabled(UUID playerUUID) {
        return oreVeinMinerEnabled.getOrDefault(playerUUID, false);
    }

    public boolean isTreeVeinMinerEnabled(UUID playerUUID) {
        return treeVeinMinerEnabled.getOrDefault(playerUUID, false);
    }
}