package com.example.estrocord.commands;

import com.example.estrocord.EstrocordPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class EstrocordCommandExecutor implements CommandExecutor {

    private final EstrocordPlugin plugin;
    private final List<String> helpPages = new ArrayList<>();

    public EstrocordCommandExecutor(EstrocordPlugin plugin) {
        this.plugin = plugin;
        initializeHelpPages();
    }

    private void initializeHelpPages() {
        // Page 1
        helpPages.add(ChatColor.GOLD + "======== " + ChatColor.GREEN + "Estrocord Help (Page 1/2)" + ChatColor.GOLD + " ========\n"
                + ChatColor.YELLOW + "/setspawn" + ChatColor.WHITE + " - Set the server spawn location.\n"
                + ChatColor.YELLOW + "/spawn" + ChatColor.WHITE + " - Teleport to the server spawn.\n"
                + ChatColor.YELLOW + "/setbase" + ChatColor.WHITE + " - Set your personal base location.\n"
                + ChatColor.YELLOW + "/base" + ChatColor.WHITE + " - Teleport to your saved base location.\n"
                + ChatColor.YELLOW + "/visitbase <player>" + ChatColor.WHITE + " - Visit another player's base.\n"
                + ChatColor.YELLOW + "/playtime" + ChatColor.WHITE + " - Check your total playtime on the server.\n"
                + ChatColor.YELLOW + "/kitty" + ChatColor.WHITE + " - Send a cute kitty message.\n"
                + ChatColor.YELLOW + "/kiss <player>" + ChatColor.WHITE + " - Send a kiss to another player.\n"
                + ChatColor.YELLOW + "/veinminer <ores|trees>" + ChatColor.WHITE + " - Toggle vein mining for ores or trees.\n");

        // Page 2
        helpPages.add(ChatColor.GOLD + "======== " + ChatColor.GREEN + "Estrocord Help (Page 2/2)" + ChatColor.GOLD + " ========\n"
                + ChatColor.YELLOW + "/basefly <on|off>" + ChatColor.WHITE + " - Toggle flight within your base radius.\n"
                + ChatColor.YELLOW + "/flyzone <x1> <y1> <z1> <x2> <y2> <z2> <name>" + ChatColor.WHITE + " - Create a communal fly zone.\n"
                + ChatColor.YELLOW + "/rmflyzone <name>" + ChatColor.WHITE + " - Remove a communal fly zone.\n"
                + ChatColor.YELLOW + "/tpask <player>" + ChatColor.WHITE + " - Request to teleport to another player.\n"
                + ChatColor.YELLOW + "/tpaccept" + ChatColor.WHITE + " - Accept a teleport request.\n"
                + ChatColor.YELLOW + "/tpdeny" + ChatColor.WHITE + " - Deny a teleport request.\n"
                + ChatColor.YELLOW + "/estrocordreload" + ChatColor.WHITE + " - Reload the plugin configuration.\n"
                + ChatColor.YELLOW + "/version" + ChatColor.WHITE + " - Shows the current version of the plugin.\n"
                + ChatColor.YELLOW + "/spawnbook" + ChatColor.WHITE + " - Spawns in the spawn egg book.\n"
        );
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        int page = 1;

        if (args.length > 0) {
            try {
                page = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                sender.sendMessage(ChatColor.RED + "Invalid page number. Use /cutiecord <page>.");
                return true;
            }
        }

        if (page < 1 || page > helpPages.size()) {
            sender.sendMessage(ChatColor.RED + "Invalid page number. There are " + helpPages.size() + " pages available.");
            return true;
        }

        sender.sendMessage(helpPages.get(page - 1));
        return true;
    }
}
