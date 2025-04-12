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

import java.util.List;
import java.util.Random;

public class kittyCommandExecutor implements CommandExecutor {
    private final EstrocordPlugin plugin;
    private final Random random;

    public kittyCommandExecutor(EstrocordPlugin plugin) {
        this.plugin = plugin;
        this.random = new Random();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        // Fetch kitty messages from the config file
        List<String> kittyMessages = plugin.getConfig().getStringList("kitty-command.messages");

        if (kittyMessages.isEmpty()) {
            player.sendMessage(ChatColor.RED + "No kitty messages are set in the config!");
            return true;
        }

        // Pick a random message
        String randomMessage = kittyMessages.get(random.nextInt(kittyMessages.size()));

        // Construct the full formatted message
        String formattedMessage = ChatColor.LIGHT_PURPLE + player.getName() + " says " + randomMessage;

        // Send the message to all players online
        plugin.getServer().broadcastMessage(formattedMessage);

        return true;
    }
}