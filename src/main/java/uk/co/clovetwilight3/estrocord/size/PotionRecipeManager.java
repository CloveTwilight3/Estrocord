/*
 * Copyright (c) 2025 Clove Nytrix Doughmination Twilight
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package uk.co.clovetwilight3.estrocord.size;

import uk.co.clovetwilight3.estrocord.EstrocordPlugin;
import org.bukkit.NamespacedKey;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class PotionRecipeManager {

    public static void registerRecipes(EstrocordPlugin plugin) {
        // --- Growth Potion Recipe ---
        // Create the custom Growth Potion item (sticky piston used to represent "stretch")
        ItemStack growthPotion = new ItemStack(Material.POTION);
        ItemMeta growthMeta = growthPotion.getItemMeta();
        if (growthMeta != null) {
            growthMeta.setDisplayName("§eGrowth Potion");
            List<String> lore = new ArrayList<>();
            lore.add("Drink to grow larger!");
            growthMeta.setLore(lore);
            // Store custom data to identify the potion type
            growthMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "potionType"), PersistentDataType.STRING, "growth");
            growthPotion.setItemMeta(growthMeta);
        }

        // Create a shapeless recipe with any potion and a sticky piston
        ShapelessRecipe growthRecipe = new ShapelessRecipe(new NamespacedKey(plugin, "growth_potion"), growthPotion);
        growthRecipe.addIngredient(Material.POTION);          // Accepts any potion type
        growthRecipe.addIngredient(Material.STICKY_PISTON);     // Sticky piston signifies "stretch"
        plugin.getServer().addRecipe(growthRecipe);

        // --- Shrink Potion Recipe ---
        // Create the custom Shrink Potion item (piston represents compression)
        ItemStack shrinkPotion = new ItemStack(Material.POTION);
        ItemMeta shrinkMeta = shrinkPotion.getItemMeta();
        if (shrinkMeta != null) {
            shrinkMeta.setDisplayName("§bShrink Potion");
            List<String> lore = new ArrayList<>();
            lore.add("Drink to shrink smaller!");
            shrinkMeta.setLore(lore);
            shrinkMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, "potionType"), PersistentDataType.STRING, "shrink");
            shrinkPotion.setItemMeta(shrinkMeta);
        }

        // Create a shapeless recipe with any potion and a regular piston
        ShapelessRecipe shrinkRecipe = new ShapelessRecipe(new NamespacedKey(plugin, "shrink_potion"), shrinkPotion);
        shrinkRecipe.addIngredient(Material.POTION);
        shrinkRecipe.addIngredient(Material.PISTON);
        plugin.getServer().addRecipe(shrinkRecipe);
    }
}
