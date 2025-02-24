package com.example.estrocord.listeners;

import com.example.estrocord.EstrocordPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class PotionUseListener implements Listener {

    private final NamespacedKey potionTypeKey;
    private final EstrocordPlugin plugin;

    public PotionUseListener(EstrocordPlugin plugin) {
        this.plugin = plugin;
        potionTypeKey = new NamespacedKey(plugin, "potionType");
    }

    @EventHandler
    public void onPotionConsume(PlayerItemConsumeEvent event) {
        ItemMeta meta = event.getItem().getItemMeta();
        if (meta == null || !meta.getPersistentDataContainer().has(potionTypeKey, PersistentDataType.STRING)) {
            return;
        }
        String type = meta.getPersistentDataContainer().get(potionTypeKey, PersistentDataType.STRING);
        Player player = event.getPlayer();
        if ("growth".equals(type)) {
            player.sendMessage(ChatColor.GOLD + "You feel yourself growing larger!");
            // Dispatch a command to set scale to 1.6
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "attribute " + player.getName() + " minecraft:scale base set 1.6");
            // Schedule a reset to default scale (600 ticks ~ 30 secs)
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "attribute " + player.getName() + " minecraft:scale base set 1");
                player.sendMessage(ChatColor.YELLOW + "Your size has been returned to normal.");
            }, 600L);
        } else if ("shrink".equals(type)) {
            player.sendMessage(ChatColor.AQUA + "You feel yourself shrinking smaller!");
            // Dispatch a command to set scale to 0.4
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "attribute " + player.getName() + " minecraft:scale base set 0.4");
            // Schedule a reset to default scale (600 ticks ~ 30 secs)
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "attribute " + player.getName() + " minecraft:scale base set 1");
                player.sendMessage(ChatColor.YELLOW + "Your size has been returned to normal.");
            }, 600L);
        }
    }
}
