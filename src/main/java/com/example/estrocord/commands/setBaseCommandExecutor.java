package com.example.estrocord.commands;

import com.example.estrocord.EstrocordPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.org.clovelib.CloveLib;

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