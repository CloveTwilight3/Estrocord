/*
 * Copyright (c) 2025 Clove Twilight
 * Licensed under the MIT Licence
 */

package uk.co.clovetwilight3.estrocord.spawneggs;

import org.bukkit.plugin.java.JavaPlugin;

public class SpawnMain extends JavaPlugin {

    @Override
    public void onEnable() {
        RecipeManager.registerRecipes(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
