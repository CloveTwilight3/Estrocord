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
import uk.co.clovetwilight3.estrocord.EstrocordPlugin;

public class VersionCommandExecutor implements CommandExecutor {

    private final EstrocordPlugin plugin;

    public VersionCommandExecutor(EstrocordPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String commonMessage =
                ChatColor.AQUA + "Es" +
                ChatColor.LIGHT_PURPLE + "tro" +
                ChatColor.WHITE + "cor" +
                ChatColor.LIGHT_PURPLE + "dPl" +
                ChatColor.AQUA + "ugin";

        String version = plugin.getDescription().getVersion();
        String authors = String.join(", ", plugin.getDescription().getAuthors());
        String website = plugin.getDescription().getWebsite();

        sender.sendMessage(ChatColor.GOLD + "======== " + commonMessage + ChatColor.GREEN + " Plugin Info" + ChatColor.GOLD + " ========");
        sender.sendMessage(ChatColor.YELLOW + "Version: " + ChatColor.WHITE + version);
        sender.sendMessage(ChatColor.YELLOW + "Authors: " + ChatColor.WHITE + authors);
        if (website != null && !website.isEmpty()) {
            sender.sendMessage(ChatColor.YELLOW + "Website: " + ChatColor.WHITE + website);
        }
        sender.sendMessage(ChatColor.GOLD + "================================");

        return true;
    }
}
