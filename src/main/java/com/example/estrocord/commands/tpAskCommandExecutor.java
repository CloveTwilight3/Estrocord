package com.example.estrocord.commands;

import com.example.estrocord.EstrocordPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class tpAskCommandExecutor implements CommandExecutor {

    private final EstrocordPlugin plugin;
    private final HashMap<UUID, TeleportRequest> teleportRequests; // Stores pending teleport requests

    public tpAskCommandExecutor(EstrocordPlugin plugin) {
        this.plugin = plugin;
        this.teleportRequests = new HashMap<>(); // Initialize the map to store requests
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player requester)) { // Only a player can execute this command
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        if (args.length != 1) { // Require exactly one argument
            requester.sendMessage(ChatColor.RED + "Usage: /tpask <player>");
            return true;
        }

        Player target = plugin.getServer().getPlayer(args[0]); // Fetch the target player
        if (target == null || !target.isOnline()) { // Check if the target player is online
            requester.sendMessage(ChatColor.RED + "Player not found or not online!");
            return true;
        }

        if (target.equals(requester)) { // Prevent requesting teleportation to self
            requester.sendMessage(ChatColor.RED + "You cannot teleport to yourself!");
            return true;
        }

        UUID targetUUID = target.getUniqueId();

        // Check if there's already a pending request for this player
        if (teleportRequests.containsKey(targetUUID)) {
            requester.sendMessage(ChatColor.YELLOW + "This player already has a pending teleport request.");
            return true;
        }

        // Create a new teleport request
        teleportRequests.put(targetUUID, new TeleportRequest(requester.getUniqueId(), System.currentTimeMillis()));

        // Send notification to the target player
        target.sendMessage(ChatColor.AQUA + requester.getName() + ChatColor.YELLOW + " wants to teleport to you!");
        target.sendMessage(ChatColor.GREEN + "Type " + ChatColor.AQUA + "/tpaccept" + ChatColor.GREEN + " to accept or " +
                ChatColor.AQUA + "/tpdeny" + ChatColor.GREEN + " to deny.");

        // Notify the requester
        requester.sendMessage(ChatColor.GREEN + "Teleport request sent to " + ChatColor.AQUA + target.getName() + ChatColor.GREEN + ".");
        return true;
    }

    public TeleportRequest getRequest(UUID targetUUID) {
        return teleportRequests.get(targetUUID); // Fetch an existing request
    }

    public TeleportRequest removeRequest(UUID targetUUID) {
        return teleportRequests.remove(targetUUID); // Remove the request once it's accepted/denied
    }

    public boolean hasRequest(UUID targetUUID) {
        return teleportRequests.containsKey(targetUUID); // Check if a request exists
    }

    // Nested class to store teleport request details
    public static class TeleportRequest {
        private final UUID requesterUUID;
        private final long timestamp;

        public TeleportRequest(UUID requesterUUID, long timestamp) {
            this.requesterUUID = requesterUUID;
            this.timestamp = timestamp;
        }

        public UUID getRequesterUUID() {
            return requesterUUID;
        }

        public long getTimestamp() {
            return timestamp;
        }
    }
}