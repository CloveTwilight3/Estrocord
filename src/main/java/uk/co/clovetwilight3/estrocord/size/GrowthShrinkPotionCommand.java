/*
 * Copyright (c) 2025 Clove Nytrix Doughmination Twilight
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package uk.co.clovetwilight3.estrocord.size;

import uk.co.clovetwilight3.estrocord.EstrocordPlugin;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class GrowthShrinkPotionCommand implements CommandExecutor {

    private final EstrocordPlugin plugin;
    private final NamespacedKey potionTypeKey;

    public GrowthShrinkPotionCommand(EstrocordPlugin plugin) {
        this.plugin = plugin;
        // Create a new namespaced key for custom potion types
        potionTypeKey = new NamespacedKey(plugin, "potionType");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can get potions!");
            return true;
        }
        Player player = (Player) sender;
        String cmdName = command.getName().toLowerCase();
        ItemStack potion = new ItemStack(Material.POTION, 1);
        ItemMeta meta = potion.getItemMeta();
        if (meta == null) return true;

        if (cmdName.equals("growthpotion")) {
            meta.setDisplayName(ChatColor.GOLD + "Growth Potion");
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "Drink to grow larger!");
            meta.setLore(lore);
            meta.getPersistentDataContainer().set(potionTypeKey, PersistentDataType.STRING, "growth");
        } else if (cmdName.equals("shrinkpotion")) {
            meta.setDisplayName(ChatColor.AQUA + "Shrink Potion");
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "Drink to grow smaller!");
            meta.setLore(lore);
            meta.getPersistentDataContainer().set(potionTypeKey, PersistentDataType.STRING, "shrink");
        } else {
            player.sendMessage(ChatColor.RED + "Unknown potion command.");
            return true;
        }
        potion.setItemMeta(meta);
        player.getInventory().addItem(potion);
        player.sendMessage(ChatColor.GREEN + "You have received a " + meta.getDisplayName() + ChatColor.GREEN + "!");
        return true;
    }

}
