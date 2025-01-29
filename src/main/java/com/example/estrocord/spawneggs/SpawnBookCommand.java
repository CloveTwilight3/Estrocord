package com.example.estrocord.spawneggs;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.List;

public class SpawnBookCommand implements org.bukkit.command.CommandExecutor {

    private static final String BASE_IMAGE_URL = "https://raw.githubusercontent.com/CloveTwilight3/EstrocordPlugin/main/spawn_egg_crafts/";
    private static final int FIRST_PAGE_MOBS = 10;  // First page can fit 10 mobs
    private static final int OTHER_PAGE_MOBS = 12;  // Other pages can fit 12 mobs

    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cThis command can only be used by players!");
            return true;
        }

        Player player = (Player) sender;
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();

        if (meta == null) {
            player.sendMessage("§cError creating the recipe book!");
            return false;
        }

        meta.setTitle("§5Spawn Egg Recipes");
        meta.setAuthor("§dCloveTwilight3");

        // Add contents pages
        List<TextComponent[]> contentsPages = generateContentsPages();
        for (TextComponent[] page : contentsPages) {
            meta.spigot().addPage(page);
        }

        // Add recipe pages
        for (SpawnEggRecipes recipe : SpawnEggRecipes.values()) {
            meta.spigot().addPage(generateRecipePage(recipe, contentsPages.size()));
        }

        book.setItemMeta(meta);
        player.getInventory().addItem(book);
        player.sendMessage("§dRecipe book given!");

        return true;
    }

    private List<TextComponent[]> generateContentsPages() {
        List<TextComponent[]> pages = new ArrayList<>();
        SpawnEggRecipes[] recipes = SpawnEggRecipes.values();

        // Calculate total pages needed
        int remainingMobs = recipes.length - FIRST_PAGE_MOBS;
        int additionalPages = (int) Math.ceil((double) remainingMobs / OTHER_PAGE_MOBS);
        int totalPages = 1 + additionalPages;

        int currentMob = 0;

        // Generate all pages
        for (int pageNum = 0; pageNum < totalPages; pageNum++) {
            TextComponent page = new TextComponent();

            // Header only on first page
            if (pageNum == 0) {
                page.addExtra("§5§lSpawn Egg Recipes§r\n\n");
            }

            // Calculate how many mobs to show on this page
            int mobsOnThisPage = (pageNum == 0) ? FIRST_PAGE_MOBS : OTHER_PAGE_MOBS;
            int endIndex = Math.min(currentMob + mobsOnThisPage, recipes.length);

            // Add mob names
            for (int i = currentMob; i < endIndex; i++) {
                SpawnEggRecipes recipe = recipes[i];
                TextComponent recipeLink = new TextComponent("§d" + formatMobName(recipe.name()) + "§r\n");
                recipeLink.setClickEvent(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, String.valueOf(getRecipePageNumber(recipe, totalPages))));
                page.addExtra(recipeLink);
            }

            currentMob = endIndex;

            // Navigation buttons
            if (totalPages > 1) {
                page.addExtra("\n");
                if (pageNum > 0) {
                    TextComponent prevButton = new TextComponent("§c«");
                    prevButton.setClickEvent(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, String.valueOf(pageNum)));
                    page.addExtra(prevButton);
                }

                page.addExtra(" §5" + (pageNum + 1) + "/" + totalPages + " ");

                if (pageNum < totalPages - 1) {
                    TextComponent nextButton = new TextComponent("§c»");
                    nextButton.setClickEvent(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, String.valueOf(pageNum + 2)));
                    page.addExtra(nextButton);
                }
            }

            pages.add(new TextComponent[] { page });
        }

        return pages;
    }

    private TextComponent[] generateRecipePage(SpawnEggRecipes mobRecipe, int contentsPageCount) {
        TextComponent page = new TextComponent();

        // Title
        page.addExtra("§5§l" + formatMobName(mobRecipe.name()) + "§r\n\n");

        // Recipe link
        String imageUrl = getImageUrl(mobRecipe.name());
        TextComponent imageLink = new TextComponent("§c[VIEW RECIPE]");
        imageLink.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, imageUrl));
        page.addExtra(imageLink);

        // Back button
        page.addExtra("\n\n");
        TextComponent backLink = new TextComponent("§c« §dBack");
        backLink.setClickEvent(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, "1"));
        page.addExtra(backLink);

        return new TextComponent[] { page };
    }

    private int getRecipePageNumber(SpawnEggRecipes recipe, int contentsPageCount) {
        return recipe.ordinal() + contentsPageCount + 1;
    }

    private String getImageUrl(String mobName) {
        return BASE_IMAGE_URL + mobName.toLowerCase() + "_craft.png";
    }

    private String formatMobName(String mobName) {
        String[] words = mobName.split("_");
        StringBuilder formatted = new StringBuilder();

        for (String word : words) {
            if (formatted.length() > 0) {
                formatted.append(" ");
            }
            formatted.append(word.substring(0, 1).toUpperCase())
                    .append(word.substring(1).toLowerCase());
        }

        return formatted.toString();
    }
}