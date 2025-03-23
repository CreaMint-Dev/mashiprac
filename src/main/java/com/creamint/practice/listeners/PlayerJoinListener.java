package com.creamint.practice.listeners;

import com.creamint.practice.PracticePvP;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerJoinListener implements Listener {
    private final PracticePvP plugin;

    public PlayerJoinListener(PracticePvP plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Location spawnLocation = (Location) plugin.getConfig().get("spawn");
        player.teleport(spawnLocation);

        // スポーンアイテムの設定
        ItemStack queueItem = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta queueMeta = queueItem.getItemMeta();
        queueMeta.setDisplayName("§8Ranked Queue §7(Right-Click)");
        queueItem.setItemMeta(queueMeta);

        ItemStack kitEditorItem = new ItemStack(Material.BOOK);
        ItemMeta kitEditorMeta = kitEditorItem.getItemMeta();
        kitEditorMeta.setDisplayName("§8Edit Kits §7(Right-Click)");
        kitEditorItem.setItemMeta(kitEditorMeta);

        player.getInventory().setItem(0, queueItem);
        player.getInventory().setItem(8, kitEditorItem);
    }
}