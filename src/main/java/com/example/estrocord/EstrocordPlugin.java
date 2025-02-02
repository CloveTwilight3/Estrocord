/*
 * Copyright (c) 2025 Mazey-Jessica Emily Twilight
 * Copyright (c) 2025 UnifiedGaming Systems Ltd (Company Number: 16108983)
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.example.estrocord;

import com.example.estrocord.UpdateChecker;
import com.example.estrocord.spawneggs.*;
import com.example.estrocord.flight.*;
import com.example.estrocord.listeners.*;
import com.example.estrocord.commands.*;
import com.org.clovelib.CloveLib;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Location;

public class EstrocordPlugin extends JavaPlugin {

    // Variables to store player data like playtime and bases
    private final Map<UUID, Long> playtimeMap = new HashMap<>();
    private final Map<UUID, Long> loginTimestamps = new HashMap<>();
    private final Map<UUID, Location> bases = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        String startupMessage =
                ChatColor.AQUA + "Es" +
                ChatColor.LIGHT_PURPLE + "tro" +
                ChatColor.WHITE + "cor" +
                ChatColor.LIGHT_PURPLE + "dPl" +
                ChatColor.AQUA + "ugin" +
                ChatColor.WHITE + " is starting up...";
        getLogger().info(startupMessage);

        if (CloveLib.getInstance() == null) {
            getLogger().severe("CloveLib is not initialized! Ensure it is installed and loaded.");
            getServer().getPluginManager().disablePlugin(this); // Disable plugin if critical
            return;
        }

        // Load the config file
        saveDefaultConfig();

        // Register all your commands
        registerCommands();

        RecipeManager.registerRecipes(this);

        // Load bases from config (if saved)
        loadBases();

        // Check for updates
        String githubApiUrl = "https://api.github.com/repos/CloveTwilight3/EstrocordPlugin/releases/latest";
        new UpdateChecker(this, githubApiUrl).checkForUpdates();

        // Flight
        BaseFlightMain baseFlightMain = new BaseFlightMain(this);
        baseFlightMain.onEnable();
        getServer().getPluginManager().registerEvents(new FlightListener(baseFlightMain, this), this);
        new FlightCheckTask(baseFlightMain, this).runTaskTimer(this, 20L, 20L);

        // Veinminer
        veinminerCommandExecutor veinMinerExecutor = new veinminerCommandExecutor(this);
        getServer().getPluginManager().registerEvents(new blockVeinminerListener(this, veinMinerExecutor), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        String shutdownMessage =
                ChatColor.AQUA + "Es" +
                        ChatColor.LIGHT_PURPLE + "tro" +
                        ChatColor.WHITE + "cor" +
                        ChatColor.LIGHT_PURPLE + "dPl" +
                        ChatColor.AQUA + "ugin" +
                        ChatColor.WHITE + " is shutting down...";

        getLogger().info(shutdownMessage);

        // Save all player bases to config
        saveBases();

        // Additional cleanup if necessary
    }

    private void registerCommands() {
        // Register command executors
        getCommand("setspawn").setExecutor(new setSpawnCommandExecutor(this));
        getCommand("spawn").setExecutor(new spawnCommandExecutor(this));
        getCommand("tpask").setExecutor(new tpAskCommandExecutor(this));
        getCommand("tpaccept").setExecutor(new tpAcceptCommandExecutor(this));
        getCommand("tpdeny").setExecutor(new tpDenyCommandExecutor(this));
        getCommand("setbase").setExecutor(new setBaseCommandExecutor(this));
        getCommand("visitbase").setExecutor(new visitBaseCommandExecutor(this));
        getCommand("base").setExecutor(new baseCommandExecutor(this));
        getCommand("kitty").setExecutor(new kittyCommandExecutor(this));
        getCommand("kiss").setExecutor(new kissCommandExecutor(this));
        getCommand("playtime").setExecutor(new playtimeCommandExecutor(this));
        getCommand("veinminer").setExecutor(new veinminerCommandExecutor(this));
        getCommand("visitbase").setTabCompleter(new visitBaseCommandExecutor(this));
        getCommand("kiss").setTabCompleter(new kissCommandExecutor(this));
        getCommand("estrocord").setExecutor(new EstrocordCommandExecutor(this));
        getCommand("version").setExecutor(new VersionCommandExecutor(this));
        getCommand("estrocordreload").setExecutor(new ReloadCommandExecutor(this));
        getCommand("spawnbook").setExecutor(new SpawnBookCommand());
    }

    public Map<UUID, Long> getPlaytimeMap() {
        return playtimeMap;
    }

    public Map<UUID, Long> getLoginTimestamps() {
        return loginTimestamps;
    }

    public Map<UUID, Location> getBases() {
        return bases;
    }

    // Add and retrieve jailed status for players
    public boolean isPlayerJailed(Player player) {
        CloveLib cloveLib = CloveLib.getInstance();
        return cloveLib != null && cloveLib.isPlayerJailed(player.getUniqueId());
    }

    public Location getBaseLocation(UUID playerUUID) {
        return bases.get(playerUUID); // Retrieve the base location from the map
    }

    public boolean hasBase(UUID playerUUID) {
        return bases.containsKey(playerUUID);
    }

    private void loadBases() {
        if (!getConfig().contains("bases")) return;

        for (String key : getConfig().getConfigurationSection("bases").getKeys(false)) {
            try {
                UUID playerUUID = UUID.fromString(key);
                Location baseLocation = Location.deserialize(getConfig().getConfigurationSection("bases." + key).getValues(false));

                bases.put(playerUUID, baseLocation);
            } catch (Exception e) {
                getLogger().warning("Failed to load base for player UUID: " + key);
            }
        }
    }

    private void saveBases() {
        for (Map.Entry<UUID, Location> entry : bases.entrySet()) {
            UUID playerUUID = entry.getKey();
            Location location = entry.getValue();

            getConfig().set("bases." + playerUUID, location.serialize());
        }

        saveConfig();
    }
}
