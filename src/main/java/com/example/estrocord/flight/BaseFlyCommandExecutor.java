package com.example.estrocord.flight;

import com.example.estrocord.EstrocordPlugin;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BaseFlyCommandExecutor implements CommandExecutor {

    private final BaseFlightMain flightMain;
    private final EstrocordPlugin estrocordPlugin;

    public BaseFlyCommandExecutor(BaseFlightMain flightMain, EstrocordPlugin estrocordPlugin) {
        this.flightMain = flightMain;
        this.estrocordPlugin = estrocordPlugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;
        UUID playerUUID = player.getUniqueId();

        if (!estrocordPlugin.hasBase(playerUUID)) {
            player.sendMessage(ChatColor.RED + "You have not set a base!");
            return true;
        }

        Location baseLocation = estrocordPlugin.getBaseLocation(playerUUID);
        if (baseLocation == null || !baseLocation.getWorld().equals(player.getWorld())) {
            player.sendMessage(ChatColor.RED + "You are not in the same world as your base!");
            return true;
        }

        double distance = baseLocation.distance(player.getLocation());
        boolean withinRadius = distance <= 100;

        if (!withinRadius) {
            player.sendMessage(ChatColor.RED + "You must be within your base radius to toggle flight!");
            return true;
        }

        if (args.length != 1 || (!args[0].equalsIgnoreCase("on") && !args[0].equalsIgnoreCase("off"))) {
            player.sendMessage(ChatColor.RED + "Usage: /basefly <on|off>");
            return true;
        }

        boolean enableFlight = args[0].equalsIgnoreCase("on");
        flightMain.getFlightToggles().put(playerUUID, enableFlight);

        if (enableFlight) {
            player.setAllowFlight(true);
            player.sendMessage(ChatColor.GREEN + "Flight enabled while you are within your base.");
        } else {
            player.setAllowFlight(false);
            player.sendMessage(ChatColor.YELLOW + "Flight disabled.");
        }
        return true;
    }
}
