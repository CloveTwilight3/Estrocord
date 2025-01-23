package com.example.estrocord;

import com.example.estrocord.UpdateChecker;
import com.example.estrocord.spawneggs.*;
import com.example.estrocord.flight.*;
import com.example.estrocord.listeners.*;
import com.example.estrocord.commands.*;
import com.org.clovelib.CloveLib;
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
    private final Map<UUID, Boolean> jailedStatus = new HashMap<>(); // Example for jailed status

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Estrocord plugin is starting...");

        if (com.org.clovelib.CloveLib.getInstance() == null) {
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
        String githubApiUrl = "https://api.github.com/repos/MazeyMoos0022/EstrocordPlugin/releases/latest";
        new UpdateChecker(this, githubApiUrl).checkForUpdates();

        // Flight
        baseFlightMain = new BaseFlightMain(this);
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
        getLogger().info("Estrocord plugin is shutting down...");

        // Save all player bases to config
        saveBases();

        // Additional cleanup if necessary
    }

    private void registerCommands() {
        // Register command executors
        getCommand("setspawn").setExecutor(new ConditionalCommandExecutor(this, "setspawn"));
        getCommand("spawn").setExecutor(new ConditionalCommandExecutor(this, "spawn"));
        getCommand("tpask").setExecutor(new tpAskCommandExecutor(this));
        getCommand("tpaccept").setExecutor(new tpAcceptCommandExecutor(this));
        getCommand("tpdeny").setExecutor(new tpDenyCommandExecutor(this));
        getCommand("setbase").setExecutor(new ConditionalCommandExecutor(this, "setbase"));
        getCommand("visitbase").setExecutor(new visitBaseCommandExecutor(this));
        getCommand("base").setExecutor(new ConditionalCommandExecutor(this, "base"));
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

    private BaseFlightMain baseFlightMain;

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

    public boolean canExecuteCommand(Player player, String command) {
        CloveLib cloveLib = CloveLib.getInstance();
        if (cloveLib != null && cloveLib.isPlayerJailed(player.getUniqueId())) {
            switch (command) {
                case "spawn":
                case "base":
                case "setbase":
                case "baseflight":
                    player.sendMessage("You cannot execute this command while jailed.");
                    return false;
            }
        }
        return true;
    }

    public Location getBaseLocation(UUID playerUUID) {
        return bases.get(playerUUID); // Retrieve the base location from the map
    }

    public boolean hasBase(UUID playerUUID) {
        return bases.containsKey(playerUUID);
    }

    public boolean isAllFlightEnabled() {
        return getConfig().getBoolean("allflight", false);
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
