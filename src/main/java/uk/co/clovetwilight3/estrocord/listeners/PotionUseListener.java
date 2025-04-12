package uk.co.clovetwilight3.estrocord.listeners;

import uk.co.clovetwilight3.estrocord.EstrocordPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

public class PotionUseListener implements Listener {

    private final NamespacedKey potionTypeKey;
    private final EstrocordPlugin plugin;

    public PotionUseListener(EstrocordPlugin plugin) {
        this.plugin = plugin;
        potionTypeKey = new NamespacedKey(plugin, "potionType");
    }

    @EventHandler
    public void onPotionConsume(PlayerItemConsumeEvent event) {
        ItemMeta meta = event.getItem().getItemMeta();
        if (meta == null || !meta.getPersistentDataContainer().has(potionTypeKey, PersistentDataType.STRING)) {
            return;
        }
        String type = meta.getPersistentDataContainer().get(potionTypeKey, PersistentDataType.STRING);
        Player player = event.getPlayer();
        if ("growth".equals(type)) {
            player.sendMessage(ChatColor.GOLD + "You feel yourself growing larger!");
            // Dispatch a command to set scale to 1.6
            graduallyChangeScale(player, 1.0, 1.6, 20L);
            // Schedule a reset to default scale (600 ticks ~ 30 secs)
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                graduallyChangeScale(player, 1.6, 1.0, 20L);
                player.sendMessage(ChatColor.YELLOW + "Your size has been returned to normal.");
            }, 600L);
        } else if ("shrink".equals(type)) {
            player.sendMessage(ChatColor.AQUA + "You feel yourself shrinking smaller!");
            // Dispatch a command to set scale to 0.4
            graduallyChangeScale(player, 1.0, 0.4, 20L);
            // Schedule a reset to default scale (600 ticks ~ 30 secs)
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                graduallyChangeScale(player, 0.4, 1.0, 20L);
                player.sendMessage(ChatColor.YELLOW + "Your size has been returned to normal.");
            }, 600L);
        }
    }

    /**
     * Gradually changes the player's scale from a start value to an end value over a specified duration.
     *
     * @param player       The player whose scale will be changed.
     * @param start        The starting scale value.
     * @param end          The target scale value.
     * @param durationTicks The duration of the transition in ticks.
     */

    private void graduallyChangeScale(Player player, double start, double end, long durationTicks) {
        double difference = end - start;
        int steps = (int) durationTicks;
        double stepChange = difference / steps;
        new BukkitRunnable() {
            int currentStep = 0;
            double currentScale = start;

            @Override
            public void run() {
                if (currentStep >= steps){
                    // Ensure the final scale is set exactly to the target
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "attribute " + player.getName() + " minecraft:scale base set " + end);
                    cancel();
                    return;
                }
                currentScale += stepChange;
                String scaleFormatted = String.format("%.2f", currentScale);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "attribute " + player.getName() + " minecraft:scale base set " + scaleFormatted);
                currentStep++;
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

}
