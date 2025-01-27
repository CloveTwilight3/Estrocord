package com.example.estrocord.spawneggs;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class SpawnBookCommand implements org.bukkit.command.CommandExecutor {

    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players!");
            return true;
        }

        Player player = (Player) sender;

        // Create the book
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();

        if (meta == null) return false;

        meta.setTitle("§6Spawn Egg Recipes");
        meta.setAuthor("§bEstrocord");

        // Create contents page with clickable links
        TextComponent contentsPage = new TextComponent("§l§6Spawn Egg Recipes\n\n§r");
        for (SpawnEggRecipes recipe : SpawnEggRecipes.values()) {
            TextComponent recipeLink = new TextComponent("§n§e" + recipe.name() + "§r\n");
            recipeLink.setClickEvent(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, String.valueOf(getRecipePageNumber(recipe))));
            recipeLink.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder("Click to view " + recipe.name() + " recipe").create()));
            contentsPage.addExtra(recipeLink);
            contentsPage.addExtra("\n");
        }

        // Add contents page
        meta.spigot().addPage(new TextComponent[] { contentsPage });

        // Add pages for each mob recipe
        for (SpawnEggRecipes recipe : SpawnEggRecipes.values()) {
            meta.spigot().addPage(generateRecipePage(recipe));
        }

        // Set the book metadata
        book.setItemMeta(meta);

        // Give the book to the player
        player.getInventory().addItem(book);
        player.sendMessage("§aYou have been given the §6Spawn Egg Recipe Book!");

        return true;
    }

    private int getRecipePageNumber(SpawnEggRecipes recipe) {
        // Add 1 to account for the contents page
        return SpawnEggRecipes.valueOf(recipe.name()).ordinal() + 2;
    }

    private TextComponent[] generateRecipePage(SpawnEggRecipes mobRecipe) {
        TextComponent page = new TextComponent();
        page.addExtra("§l§6" + mobRecipe.name() + " Recipe\n\n§r");

        String imageUrl = getImageUrl(mobRecipe.name());
        if (imageUrl == null) {
            page.addExtra("§cI haven't got a craft yet!§r\n\n");
        } else {
            TextComponent imageLink = new TextComponent("§b§nView Image");
            imageLink.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, imageUrl));
            imageLink.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder("Click to view full recipe image").create()));
            page.addExtra("§a[Click to view full recipe]\n\n");
            page.addExtra(imageLink);
            page.addExtra("\n");
        }

        TextComponent contentsLink = new TextComponent("\n§a[Return to Contents]");
        contentsLink.setClickEvent(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, "1"));
        contentsLink.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                new ComponentBuilder("Return to Contents page").create()));
        page.addExtra(contentsLink);

        return new TextComponent[] { page };
    }

    private String getImageUrl(String mobName) {
        return "https://mazeymoos.com/estrocord/crafts/" + mobName.toLowerCase() + "_craft.png";
    }
}