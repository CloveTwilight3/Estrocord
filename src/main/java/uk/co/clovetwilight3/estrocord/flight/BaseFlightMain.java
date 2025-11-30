/*
 * Copyright (c) 2025 Clove Twilight
 * Licensed under the MIT Licence
 */

package uk.co.clovetwilight3.estrocord.flight;

import uk.co.clovetwilight3.estrocord.EstrocordPlugin;
import org.bukkit.Bukkit;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BaseFlightMain {

    private final EstrocordPlugin estrocordPlugin;
    private final Map<UUID, Boolean> flightToggles;
    private final Map<String, FlyZone> communalFlyZones;

    public BaseFlightMain(EstrocordPlugin plugin) {
        this.estrocordPlugin = plugin;
        this.flightToggles = new HashMap<>();
        this.communalFlyZones = new HashMap<>();
    }

    public void onEnable() {
        // Register commands and events
        Bukkit.getPluginManager().registerEvents(new FlightListener(this, estrocordPlugin), estrocordPlugin);

        estrocordPlugin.getCommand("basefly").setExecutor(new BaseFlyCommandExecutor(this, estrocordPlugin));
        estrocordPlugin.getCommand("flyzone").setExecutor(new FlyZoneCommandExecutor(this));
        estrocordPlugin.getCommand("rmflyzone").setExecutor(new RemoveFlyZoneCommandExecutor(this));

        // Start flight zone checks
        new FlightCheckTask(this, estrocordPlugin).runTaskTimer(estrocordPlugin, 20L, 20L);
    }

    public Map<UUID, Boolean> getFlightToggles() {
        return flightToggles;
    }

    public Map<String, FlyZone> getCommunalFlyZones() {
        return communalFlyZones;
    }

    public static class FlyZone {
        private final String name;
        private final org.bukkit.Location corner1;
        private final org.bukkit.Location corner2;

        public FlyZone(String name, org.bukkit.Location corner1, org.bukkit.Location corner2) {
            this.name = name;
            this.corner1 = corner1;
            this.corner2 = corner2;
        }

        public boolean isWithinZone(org.bukkit.Location location) {
            double minX = Math.min(corner1.getX(), corner2.getX());
            double maxX = Math.max(corner1.getX(), corner2.getX());
            double minY = Math.min(corner1.getY(), corner2.getY());
            double maxY = Math.max(corner1.getY(), corner2.getY());
            double minZ = Math.min(corner1.getZ(), corner2.getZ());
            double maxZ = Math.max(corner1.getZ(), corner2.getZ());

            return location.getX() >= minX && location.getX() <= maxX &&
                    location.getY() >= minY && location.getY() <= maxY &&
                    location.getZ() >= minZ && location.getZ() <= maxZ;
        }

        public String getName() {
            return name;
        }
    }
}
