/*
 * Copyright (c) 2025 Clove Nytrix Doughmination Twilight
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package uk.co.clovetwilight3.estrocord.listeners;

import uk.co.clovetwilight3.estrocord.EstrocordPlugin;
import uk.co.clovetwilight3.estrocord.commands.veinminerCommandExecutor;
import uk.co.clovetwilight3.estrocord.veinminer.handleOreVeinminer;
import uk.co.clovetwilight3.estrocord.veinminer.handleTreeVeinminer;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import java.util.UUID;

public class blockVeinminerListener implements Listener {
    private final EstrocordPlugin plugin;
    private final handleOreVeinminer oreVeinminer;
    private final handleTreeVeinminer treeVeinminer;
    private final veinminerCommandExecutor veinMinerExecutor;

    public blockVeinminerListener(EstrocordPlugin plugin, veinminerCommandExecutor veinMinerExecutor) {
        this.plugin = plugin;
        this.oreVeinminer = new handleOreVeinminer(plugin);
        this.treeVeinminer = new handleTreeVeinminer(plugin);
        this.veinMinerExecutor = veinMinerExecutor; // Assign the passed executor
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();
        Block block = event.getBlock();

        // Get the veinminer toggles
        CommandExecutor executor = plugin.getCommand("veinminer").getExecutor();
        if (executor instanceof veinminerCommandExecutor veinMinerExecutor) {
            // Check if the player has vein-mining enabled for ores
            if (veinMinerExecutor.isOreVeinMinerEnabled(playerUUID) && isOre(block.getType())) {
                oreVeinminer.handleOreBreak(player, block);
                event.setCancelled(true); // Cancel the default block break
                return;
            }

            // Check if the player has vein-mining enabled for trees
            if (veinMinerExecutor.isTreeVeinMinerEnabled(playerUUID) && isLog(block.getType())) {
                treeVeinminer.handleTreeBreak(player, block);
                event.setCancelled(true); // Cancel the default block break
            }
        } else {
            plugin.getLogger().warning("The veinminer command executor is not properly set!");
        }
    }

    private boolean isOre(Material material) {
        return material.name().endsWith("_ORE") || material.name().endsWith("_DEBRIS");
    }

    private boolean isLog(Material material) {
        return material.name().endsWith("_LOG");
    }
}
