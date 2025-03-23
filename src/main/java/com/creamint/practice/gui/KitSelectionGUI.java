package com.creamint.practice.gui;

import com.creamint.practice.PracticePvP;
import com.creamint.practice.kit.KitManager;
import com.creamint.practice.queue.QueueManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KitSelectionGUI implements Listener {
    private final KitManager kitManager;
    private final QueueManager queueManager;
    private final PracticePvP plugin;

    public KitSelectionGUI(KitManager kitManager, QueueManager queueManager, PracticePvP plugin) {
        this.kitManager = kitManager;
        this.queueManager = queueManager;
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public void openKitSelection(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 27, "Select Your Kit");
        int slot = 0;
        for (Map.Entry<String, ItemStack> entry : kitManager.getKits().entrySet()) {
            ItemStack item = entry.getValue().clone();
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(entry.getKey());
            item.setItemMeta(meta);
            inventory.setItem(slot++, item);
        }
        player.openInventory(inventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals("Select Your Kit")) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack item = event.getCurrentItem();
            if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
                String kitName = item.getItemMeta().getDisplayName();
                player.closeInventory();
                queueManager.addPlayerToQueue(player, kitName);
                player.sendMessage("You have joined the queue for kit: " + kitName);
            }
        }
    }
}