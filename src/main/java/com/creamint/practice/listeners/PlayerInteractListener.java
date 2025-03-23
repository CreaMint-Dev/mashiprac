package com.creamint.practice.listeners;

import com.creamint.practice.PracticePvP;
import com.creamint.practice.queue.QueueManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerInteractListener implements Listener {
    private final PracticePvP plugin;
    private final QueueManager queueManager;

    public PlayerInteractListener(PracticePvP plugin, QueueManager queueManager) {
        this.plugin = plugin;
        this.queueManager = queueManager;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item != null && item.getType() == Material.DIAMOND_SWORD) {
            ItemMeta meta = item.getItemMeta();
            if (meta != null && "§8Ranked Queue §7(Right-Click)".equals(meta.getDisplayName())) {
                event.setCancelled(true);
                // Queueに参加するロジック
                String kitName = "default"; // Kit名を設定するロジックを追加する
                queueManager.addPlayerToQueue(player, kitName);
                player.sendMessage("You have joined the queue for kit: " + kitName);
            }
        } else if (item != null && item.getType() == Material.BOOK) {
            ItemMeta meta = item.getItemMeta();
            if (meta != null && "§8Edit Kits §7(Right-Click)".equals(meta.getDisplayName())) {
                event.setCancelled(true);
                // KitEditorを開くロジック
                player.sendMessage("Opening Kit Editor...");
            }
        }
    }
}