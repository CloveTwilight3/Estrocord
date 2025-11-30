/*
 * Copyright (c) 2025 Clove Twilight
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package uk.co.clovetwilight3.estrocord.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import uk.co.clovetwilight3.CloveLib;
import uk.co.clovetwilight3.clovelib.BanData;
import uk.co.clovetwilight3.estrocord.EstrocordPlugin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class BanlistCommandExecutor implements CommandExecutor {

    private final EstrocordPlugin plugin;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public BanlistCommandExecutor(EstrocordPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("estrocord.banlist")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
            return true;
        }

        CloveLib cloveLib = CloveLib.getInstance();
        Map<UUID, BanData> bans = cloveLib.getAllBans();

        if (bans.isEmpty()) {
            sender.sendMessage(ChatColor.GREEN + "There are no banned players.");
            return true;
        }

        sender.sendMessage(ChatColor.GOLD + "======== " + ChatColor.RED + "Banned Players" +
                ChatColor.GOLD + " (" + bans.size() + ") ========");

        for (BanData banData : bans.values()) {
            String banDate = dateFormat.format(new Date(banData.getBannedAt()));

            sender.sendMessage(ChatColor.RED + "• " + ChatColor.WHITE + banData.getPlayerName());
            sender.sendMessage(ChatColor.GRAY + "  Reason: " + banData.getReason());
            sender.sendMessage(ChatColor.GRAY + "  Banned by: " + banData.getBannedBy() + " on " + banDate);
        }

        sender.sendMessage(ChatColor.GOLD + "================================");

        return true;
    }
}