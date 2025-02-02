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

public class handleTreeVeinminer {
    private final EstrocordPlugin plugin;

    public handleTreeVeinminer(EstrocordPlugin plugin) {
        this.plugin = plugin;
    }

    public void handleTreeBreak(Player player, Block block) {
        Material blockType = block.getType();
        if (isLog(blockType)) {
            Location start = block.getLocation();
            Set<Location> treeBlocks = new HashSet<>();
            findTreeBlocks(start, blockType, treeBlocks);

            for (Location loc : treeBlocks) {
                loc.getBlock().setType(Material.AIR);
                loc.getWorld().dropItemNaturally(loc, new ItemStack(blockType));
            }

            player.playSound(player.getLocation(), Sound.BLOCK_WOOD_BREAK, 1.0f, 1.0f);
            player.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, start, 20, 1.0, 1.0, 1.0, 0.1);
        }
    }

    private boolean isLog(Material material) {
        return material.name().endsWith("_LOG");
    }

    private void findTreeBlocks(Location start, Material blockType, Set<Location> foundBlocks) {
        int maxBlocks = plugin.getConfig().getInt("tree-remover.max-blocks", 100);
        int maxHeight = plugin.getConfig().getInt("tree-remover.max-height", 30);
        int startY = start.getBlockY();

        Queue<Location> toCheck = new LinkedList<>();
        toCheck.add(start);

        while (!toCheck.isEmpty() && foundBlocks.size() < maxBlocks) {
            Location current = toCheck.poll();

            if (current.getBlockY() < startY) continue;

            if (!foundBlocks.contains(current) && current.getBlock().getType() == blockType) {
                foundBlocks.add(current);
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dz = -1; dz <= 1; dz++) {
                        toCheck.add(current.clone().add(dx, 1, dz));
                        toCheck.add(current.clone().add(dx, 0, dz));
                    }
                }
            }
        }
    }
}