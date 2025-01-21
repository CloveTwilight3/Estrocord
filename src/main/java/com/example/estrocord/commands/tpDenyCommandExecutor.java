package com.example.estrocord.commands;

import com.example.estrocord.EstrocordPlugin;
import com.example.estrocord.commands.tpAskCommandExecutor.TeleportRequest;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class tpDenyCommandExecutor implements CommandExecutor {

    private final EstrocordPlugin plugin;

    public tpDenyCommandExecutor(EstrocordPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player target)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        tpAskCommandExecutor tpAskExecutor = (tpAskCommandExecutor) plugin.getCommand("tpask").getExecutor();

        UUID targetUUID = target.getUniqueId();
        if (!tpAskExecutor.hasRequest(targetUUID)) {
            target.sendMessage(ChatColor.RED + "You have no pending teleport requests to deny.");
            return true;
        }

        // Fetch the teleport request details
        TeleportRequest request = tpAskExecutor.getRequest(targetUUID);
        Player requester = plugin.getServer().getPlayer(request.getRequesterUUID());

        // Notify both players
        if (requester != null && requester.isOnline()) {
            requester.sendMessage(ChatColor.RED + "Your teleport request to " + ChatColor.AQUA + target.getName() + ChatColor.RED + " was denied.");
        }

        target.sendMessage(ChatColor.YELLOW + "You have denied the teleport request.");
        tpAskExecutor.removeRequest(targetUUID); // Clean up the denied request
        return true;
    }
}