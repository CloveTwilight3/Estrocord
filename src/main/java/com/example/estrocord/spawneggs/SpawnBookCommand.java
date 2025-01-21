package com.example.estrocord.spawneggs;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
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

        // Add contents page
        meta.addPage(generateContentsPage());

        // Add pages for each mob recipe
        for (SpawnEggRecipes recipe : SpawnEggRecipes.values()) {
            meta.addPage(generateRecipePage(recipe));
        }

        // Set the book metadata
        book.setItemMeta(meta);

        // Give the book to the player
        player.getInventory().addItem(book);
        player.sendMessage("§aYou have been given the §6Spawn Egg Recipe Book!");

        return true;
    }

    private String generateContentsPage() {
        StringBuilder contents = new StringBuilder("§l§6Spawn Egg Recipes\n\n§r");

        for (SpawnEggRecipes recipe : SpawnEggRecipes.values()) {
            contents.append("§n§e").append(recipe.name()).append("§r\n")
                    .append("§a[Click to see recipe]\n\n");
        }
        return contents.toString();
    }

    private String generateRecipePage(SpawnEggRecipes mobRecipe) {
        StringBuilder page = new StringBuilder();

        page.append("§l§6").append(mobRecipe.name()).append(" Recipe\n\n§r");

        String imageUrl = getImageUrl(mobRecipe.name());
        if (imageUrl == null) {
            page.append("§cI haven't got a craft yet!§r\n\n");
        } else {
            page.append("§a[Click to view full recipe]\n\n");
            TextComponent link = new TextComponent("§b§nView Image");
            link.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, imageUrl));
            page.append(link.toLegacyText());
        }

        page.append("\n§a[Return to Contents]\n");

        return page.toString();
    }

    private String getImageUrl(String mobName) {
        return "https://mazeymoos.com/estrocord/crafts/" + mobName.toLowerCase() + "_craft.png";
    }
}
