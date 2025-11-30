/*
 * Copyright (c) 2025 Clove Twilight
 * Licensed under the MIT Licence
 */

package uk.co.clovetwilight3.estrocord.flight;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RemoveFlyZoneCommandExecutor implements CommandExecutor {

    private final BaseFlightMain flightMain;

    public RemoveFlyZoneCommandExecutor(BaseFlightMain flightMain) {
        this.flightMain = flightMain;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        if (!player.hasPermission("estrocord.flyzone")) {
            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /rmflyzone <name>");
            return true;
        }

        String zoneName = args[0];
        if (flightMain.getCommunalFlyZones().remove(zoneName) != null) {
            sender.sendMessage(ChatColor.GREEN + "Fly zone '" + zoneName + "' has been removed.");
        } else {
            sender.sendMessage(ChatColor.RED + "Fly zone '" + zoneName + "' does not exist.");
        }

        return true;
    }
}
