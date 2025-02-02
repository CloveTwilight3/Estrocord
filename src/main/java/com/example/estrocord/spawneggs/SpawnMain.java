/*
 * Copyright (c) 2025 Mazey-Jessica Emily Twilight
 * Copyright (c) 2025 UnifiedGaming Systems Ltd (Company Number: 16108983)
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.example.estrocord.spawneggs;

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
