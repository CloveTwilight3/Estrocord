package com.example.estrocord.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.example.estrocord.EstrocordPlugin;

public class ReloadCommandExecutor implements CommandExecutor {

    private final EstrocordPlugin plugin;

    public ReloadCommandExecutor(EstrocordPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("cutiecord.reload")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to reload the plugin!");
            return true;
        }

        sender.sendMessage(ChatColor.YELLOW + "Reloading Cutiecord configuration...");

        try {
            plugin.reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "Cutiecord configuration reloaded successfully!");
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "An error occurred while reloading the configuration.");
            plugin.getLogger().severe("Failed to reload Cutiecord configuration: " + e.getMessage());
            e.printStackTrace();
        }

        return true;
    }
}
