/*
 * Copyright (c) 2025 Mazey-Jessica Emily Twilight
 * Copyright (c) 2025 UnifiedGaming Systems Ltd (Company Number: 16108983)
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.example.estrocord.veinminer;

import com.example.estrocord.EstrocordPlugin;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Particle;
import org.bukkit.Sound;
import java.util.*;

public class handleOreVeinminer {

    private final EstrocordPlugin plugin;

    public handleOreVeinminer(EstrocordPlugin plugin) {
        this.plugin = plugin;
    }


    public void handleOreBreak(Player player, Block block) {
        Material blocktype = block.getType();
        if (isOre(blocktype)) {
            Location start = block.getLocation();
            Set<Location> oreBlocks = new HashSet<>();
            findOreBlocks(start, blocktype, oreBlocks);

            for (Location loc : oreBlocks) {
                Material dropMaterial = getOreDrop(blocktype); // Get the correct drop material
                loc.getBlock().setType(Material.AIR);
                if (dropMaterial != null) {
                    loc.getWorld().dropItemNaturally(loc, new ItemStack(dropMaterial));
                }
            }

            player.playSound(player.getLocation(), Sound.BLOCK_NETHER_ORE_BREAK, 1.0f, 1.0f);
            player.spawnParticle(Particle.HAPPY_VILLAGER, start, 20, 1.0, 1.0, 1.0, 0.1);
        }
    }

    // Map ore types to their corresponding drops
    private Material getOreDrop(Material oreType) {
        return switch (oreType) {
            case ANCIENT_DEBRIS -> Material.ANCIENT_DEBRIS;
            case GOLD_ORE, DEEPSLATE_GOLD_ORE -> Material.RAW_GOLD;
            case IRON_ORE, DEEPSLATE_IRON_ORE -> Material.RAW_IRON;
            case DIAMOND_ORE, DEEPSLATE_DIAMOND_ORE -> Material.DIAMOND;
            case COAL_ORE, DEEPSLATE_COAL_ORE -> Material.COAL;
            case EMERALD_ORE, DEEPSLATE_EMERALD_ORE -> Material.EMERALD;
            case COPPER_ORE, DEEPSLATE_COPPER_ORE -> Material.RAW_COPPER;
            case REDSTONE_ORE, DEEPSLATE_REDSTONE_ORE -> Material.REDSTONE;
            case LAPIS_ORE, DEEPSLATE_LAPIS_ORE -> Material.LAPIS_LAZULI;
            case NETHER_QUARTZ_ORE -> Material.QUARTZ;
            case NETHER_GOLD_ORE -> Material.GOLD_NUGGET;
            default -> null; // For unsupported ores, return null
        };
    }


    private boolean isOre(Material material) {
        return material.name().endsWith("_ORE") || material.name().endsWith("_DEBRIS");
    }

    private void findOreBlocks(Location start, Material blockType, Set<Location> foundBlocks) {
        int maxBlocks = plugin.getConfig().getInt("ore-remover.max-blocks", 100);
        int maxHeight = plugin.getConfig().getInt("ore-remover.max-height", 30);
        int startY = start.getBlockY();

        Queue<Location> toCheck = new LinkedList<>();
        toCheck.add(start);

        while (!toCheck.isEmpty() && foundBlocks.size() < maxBlocks) {
            Location current = toCheck.poll();

            if (current.getBlockY() < startY) continue;

            if (!foundBlocks.contains(current) && current.getBlock().getType() == blockType) {
                foundBlocks.add(current);
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dz = -1; dz <= 1; dz++){
                        toCheck.add(current.clone().add(dx, 1, dz));
                        toCheck.add(current.clone().add(dx, 0, dz));
                    }
                }
            }
        }
    }
}