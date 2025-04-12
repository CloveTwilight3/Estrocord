/*
 * Copyright (c) 2025 Mazey-Jessica Emily Twilight
 * Copyright (c) 2025 UnifiedGaming Systems Ltd (Company Number: 16108983)
 * Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package uk.co.clovetwilight3.estrocord.spawneggs;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public enum SpawnEggRecipes {

    ALLAY("allay_spawn_egg", Material.ALLAY_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.DIAMOND, Material.AMETHYST_SHARD, Material.DIAMOND},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    ARMADILLO("armadillo_spawn_egg", Material.ARMADILLO_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.ARMADILLO_SCUTE, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    AXOLOTL("axolotl_spawn_egg", Material.AXOLOTL_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.DIAMOND, Material.TROPICAL_FISH, Material.DIAMOND},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    BAT("bat_spawn_egg", Material.BAT_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.POTION, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    BEE("bee_spawn_egg", Material.BEE_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.HONEY_BOTTLE, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    BLAZE("blaze_spawn_egg", Material.BLAZE_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.BLAZE_POWDER, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    BOGGED("bogged_spawn_egg", Material.BOGGED_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.BROWN_MUSHROOM, Material.SKELETON_SPAWN_EGG, Material.RED_MUSHROOM},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    BREEZE("breeze_spawn_egg", Material.BREEZE_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.BREEZE_ROD, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    CAMEL("camel_spawn_egg", Material.CAMEL_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.SAND, Material.HORSE_SPAWN_EGG, Material.SAND},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    CAT("cat_spawn_egg", Material.CAT_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.MILK_BUCKET, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    CAVE_SPIDER("cave_spider_spawn_egg", Material.CAVE_SPIDER_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.FERMENTED_SPIDER_EYE, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    CHICKEN("chicken_spawn_egg", Material.CHICKEN_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.FEATHER, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    COD("cod_spawn_egg", Material.COD_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.COD, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    COW("cow_spawn_egg", Material.COW_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.LEATHER, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    CREEPER("creeper_spawn_egg", Material.CREEPER_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.GUNPOWDER, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    DOLPHIN("dolphin_spawn_egg", Material.DOLPHIN_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.HEART_OF_THE_SEA, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    DONKEY("donkey_spawn_egg", Material.DONKEY_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.CHEST, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    DROWNED("drowned_spawn_egg", Material.DROWNED_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.WATER_BUCKET, Material.ZOMBIE_SPAWN_EGG, Material.WATER_BUCKET},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    ELDER_GUARDIAN("elder_guardian_spawn_egg", Material.ELDER_GUARDIAN_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.PRISMARINE_SHARD, Material.GUARDIAN_SPAWN_EGG, Material.PRISMARINE_SHARD},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    ENDER_DRAGON("ender_dragon_spawn_egg", Material.ENDER_DRAGON_SPAWN_EGG, new Material[][]{
            {Material.NETHER_STAR, Material.DRAGON_HEAD, Material.NETHER_STAR},
            {Material.END_CRYSTAL, Material.ENDERMAN_SPAWN_EGG, Material.END_CRYSTAL},
            {Material.BREEZE_ROD, Material.ELYTRA, Material.WIND_CHARGE}
    }),
    ENDERMAN("enderman_spawn_egg", Material.ENDERMAN_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.ENDER_PEARL, Material.ENDER_EYE, Material.ENDER_PEARL},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    ENDERMITE("endermite_spawn_egg", Material.ENDERMITE_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.ENDER_PEARL, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    EVOKER("evoker_spawn_egg", Material.EVOKER_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.TOTEM_OF_UNDYING, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    FOX("fox_spawn_egg", Material.FOX_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    FROG("frog_spawn_egg", Material.FROG_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.KELP, Material.TADPOLE_SPAWN_EGG, Material.KELP},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    GHAST("ghast_spawn_egg", Material.GHAST_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.GHAST_TEAR, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    GLOW_SQUID("glow_squid_spawn_egg", Material.GLOW_SQUID_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.GLOW_INK_SAC, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    GOAT("goat_spawn_egg", Material.GOAT_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.LEATHER_BOOTS, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    GUARDIAN("guardian_spawn_egg", Material.GUARDIAN_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.PRISMARINE_SHARD, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    HOGLIN("hoglin_spawn_egg", Material.HOGLIN_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.NETHERRACK, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    HORSE("horse_spawn_egg", Material.HORSE_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.SADDLE, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    HUSK("husk_spawn_egg", Material.HUSK_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.SAND, Material.ZOMBIE_SPAWN_EGG, Material.SAND},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    IRON_GOLEM("iron_golem_spawn_egg", Material.IRON_GOLEM_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.IRON_INGOT, Material.POPPY, Material.IRON_INGOT},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    LLAMA("llama_spawn_egg", Material.LLAMA_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.WHITE_CARPET, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    MAGMA_CUBE("magma_cube_spawn_egg", Material.MAGMA_CUBE_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.MAGMA_CREAM, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    MOOSHROOM("mooshroom_spawn_egg", Material.MOOSHROOM_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.RED_MUSHROOM, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    MULE("mule_spawn_egg", Material.MULE_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.WHEAT, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    OCELOT("ocelot_spawn_egg", Material.OCELOT_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.ORANGE_DYE, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    PANDA("panda_spawn_egg", Material.PANDA_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.BAMBOO, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    PARROT("parrot_spawn_egg", Material.PARROT_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.WHEAT_SEEDS, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    PHANTOM("phantom_spawn_egg", Material.PHANTOM_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.PHANTOM_MEMBRANE, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    PIG("pig_spawn_egg", Material.PIG_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.CARROT, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    PIGLIN("piglin_spawn_egg", Material.PIGLIN_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.GOLD_INGOT, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    PIGLIN_BRUTE("piglin_brute_spawn_egg", Material.PIGLIN_BRUTE_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.GOLDEN_SWORD, Material.PIGLIN_SPAWN_EGG, Material.GOLDEN_SWORD},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    PILLAGER("pillager_spawn_egg", Material.PILLAGER_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.CROSSBOW, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    POLAR_BEAR("polar_bear_spawn_egg", Material.POLAR_BEAR_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.PACKED_ICE, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    PUFFERFISH("pufferfish_spawn_egg", Material.PUFFERFISH_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.PUFFERFISH, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    RABBIT("rabbit_spawn_egg", Material.RABBIT_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.RABBIT_HIDE, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    RAVAGER("ravager_spawn_egg", Material.RAVAGER_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.SADDLE, Material.HORSE_SPAWN_EGG, Material.CROSSBOW},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    SALMON("salmon_spawn_egg", Material.SALMON_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.SALMON, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    SHEEP("sheep_spawn_egg", Material.SHEEP_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.MUTTON, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    SHULKER("shulker_spawn_egg", Material.SHULKER_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.END_STONE, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    SILVERFISH("silverfish_spawn_egg", Material.SILVERFISH_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.CRACKED_STONE_BRICKS, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    SKELETON("skeleton_spawn_egg", Material.SKELETON_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.ARROW, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    SKELETON_HORSE("skeleton_horse_spawn_egg", Material.SKELETON_HORSE_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.BONE, Material.HORSE_SPAWN_EGG, Material.BONE},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    SLIME("slime_spawn_egg", Material.SLIME_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.SLIME_BALL, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    SNIFFER("sniffer_spawn_egg", Material.SNIFFER_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.PITCHER_PLANT, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    SNOW_GOLEM("snow_golem_spawn_egg", Material.SNOW_GOLEM_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.SNOW_BLOCK, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    SPIDER("spider_spawn_egg", Material.SPIDER_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.SPIDER_EYE, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    SQUID("squid_spawn_egg", Material.SQUID_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.INK_SAC, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    STRAY("stray_spawn_egg", Material.STRAY_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.SAND, Material.SKELETON_SPAWN_EGG, Material.SAND},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    STRIDER("strider_spawn_egg", Material.STRIDER_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.NETHERRACK, Material.LAVA_BUCKET, Material.NETHERRACK},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    TADPOLE("tadpole_spawn_egg", Material.TADPOLE_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.FROGSPAWN, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    TRADER_LLAMA("trader_llama_spawn_egg", Material.TRADER_LLAMA_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.LEAD, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    TROPICAL_FISH("tropical_fish_spawn_egg", Material.TROPICAL_FISH_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.TROPICAL_FISH, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    TURTLE("turtle_spawn_egg", Material.TURTLE_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.TURTLE_SCUTE, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    VEX("vex_spawn_egg", Material.VEX_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.EVOKER_SPAWN_EGG, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    VILLAGER("villager_spawn_egg", Material.VILLAGER_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.EMERALD, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    VINDICATOR("vindicator_spawn_egg", Material.VINDICATOR_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.IRON_AXE, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    WANDERING_TRADER("wandering_trader_spawn_egg", Material.WANDERING_TRADER_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.LEAD, Material.VILLAGER_SPAWN_EGG, Material.LEAD},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    WARDEN("warden_spawn_egg", Material.WARDEN_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.SCULK_SENSOR, Material.SCULK_SHRIEKER, Material.SCULK_SENSOR},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    WITCH("witch_spawn_egg", Material.WITCH_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.SUGAR, Material.REDSTONE, Material.GLOWSTONE_DUST},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    WITHER_SKELETON("wither_skeleton_spawn_egg", Material.WITHER_SKELETON_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.DIAMOND_SWORD, Material.COAL, Material.DIAMOND_SWORD},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    WITHER("wither_spawn_egg", Material.WITHER_SPAWN_EGG, new Material[][]{
            {Material.NETHERITE_INGOT, Material.STRING, Material.NETHERITE_INGOT},
            {Material.WITHER_SKELETON_SPAWN_EGG, Material.WITHER_SKELETON_SPAWN_EGG, Material.WITHER_SKELETON_SPAWN_EGG},
            {Material.STRING, Material.SOUL_SAND, Material.STRING}
    }),
    WOLF("wolf_spawn_egg", Material.WOLF_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.BONE, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    ZOGLIN("zoglin_spawn_egg", Material.ZOGLIN_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.ROTTEN_FLESH, Material.HOGLIN_SPAWN_EGG, Material.ROTTEN_FLESH},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    ZOMBIE("zombie_spawn_egg", Material.ZOMBIE_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.ROTTEN_FLESH, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    ZOMBIE_HORSE("zombie_horse_spawn_egg", Material.ZOMBIE_HORSE_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.ROTTEN_FLESH, Material.HORSE_SPAWN_EGG, Material.ROTTEN_FLESH},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    ZOMBIFIED_PIGLIN("zombified_piglin_spawn_egg", Material.ZOMBIFIED_PIGLIN_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.STRING, Material.GOLD_NUGGET, Material.STRING},
            {Material.STRING, Material.STRING, Material.STRING}
    }),
    ZOMBIE_VILLAGER("zombie_villager_spawn_egg", Material.ZOMBIE_VILLAGER_SPAWN_EGG, new Material[][]{
            {Material.STRING, Material.STRING, Material.STRING},
            {Material.ROTTEN_FLESH, Material.VILLAGER_SPAWN_EGG, Material.ROTTEN_FLESH},
            {Material.STRING, Material.STRING, Material.STRING}
    });

    private static final Map<Material, Character> symbolMap = new HashMap<>();
    private static char nextSymbol = 'A';
    private final String key;
    private final Material resultMaterial;
    private final Material[][] materials;

    SpawnEggRecipes(String key, Material resultMaterial, Material[][] materials) {
        this.key = key;
        this.resultMaterial = resultMaterial;
        this.materials = materials;
    }

    public String getKey() {
        return key;
    }

    public Material getResultMaterial() {
        return resultMaterial;
    }

    public Material[][] getMaterials() {
        return materials;
    }

    public String[] getShape() {
        String[] shape = new String[materials.length];
        for (int row = 0; row < materials.length; row++) {
            StringBuilder rowShape = new StringBuilder();
            for (Material mat : materials[row]) {
                if (mat == null) {
                    rowShape.append(" "); // Empty slot
                } else {
                    rowShape.append(getSymbolForMaterial(mat)); // Use the assigned symbol
                }
            }
            shape[row] = rowShape.toString();
        }
        return shape;
    }

    // Helper method to assign unique symbols to materials
    private char getSymbolForMaterial(Material material) {
        // Predefine symbols for unique materials
        if (!symbolMap.containsKey(material)) {
            symbolMap.put(material, nextSymbol++);
        }
        return symbolMap.get(material);
    }

}
