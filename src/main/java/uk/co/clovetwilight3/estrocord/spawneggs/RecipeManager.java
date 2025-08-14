/*
 * Copyright (c) 2025 Clove Nytrix Doughmination Twilight
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package uk.co.clovetwilight3.estrocord.spawneggs;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.util.Map;

public class RecipeManager {

    public static void registerRecipes(JavaPlugin plugin) {
        for (SpawnEggRecipes recipeData : SpawnEggRecipes.values()) {
            ItemStack result = new ItemStack(recipeData.getResultMaterial());
            NamespacedKey key = new NamespacedKey(plugin, recipeData.getKey());

            ShapedRecipe recipe = new ShapedRecipe(key, result);
            recipe.shape(recipeData.getShape());

            // Map symbols from the shape to materials
            Material[][] materials = recipeData.getMaterials();
            String[] shape = recipeData.getShape();
            Map<Character, Material> ingredientMap = new HashMap<>();

            for (int row = 0; row < materials.length; row++) {
                char[] shapeRow = shape[row].toCharArray();
                for (int col = 0; col < shapeRow.length; col++) {
                    char symbol = shapeRow[col];
                    Material material = materials[row][col];

                    // Skip spaces and null materials
                    if (symbol != ' ' && material != null && !ingredientMap.containsKey(symbol)) {
                        ingredientMap.put(symbol, material);
                    }
                }
            }

            // Assign materials to symbols in the recipe
            for (Map.Entry<Character, Material> entry : ingredientMap.entrySet()) {
                recipe.setIngredient(entry.getKey(), entry.getValue());
            }

            // Register the recipe
            Bukkit.addRecipe(recipe);
        }
    }
}
