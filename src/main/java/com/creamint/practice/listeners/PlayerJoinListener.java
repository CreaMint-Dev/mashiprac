package com.creamint.practice.listeners;

import com.creamint.practice.PracticePvP;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.Location;

public class PlayerJoinListener implements Listener {
    private final PracticePvP plugin;

    public PlayerJoinListener(PracticePvP plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Location spawnLocation = (Location) plugin.getConfig().get("spawn");
        event.getPlayer().teleport(spawnLocation);
        // スポーンアイテムを渡すロジック
    }
}