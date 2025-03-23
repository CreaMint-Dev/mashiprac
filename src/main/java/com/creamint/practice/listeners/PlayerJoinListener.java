package com.creamint.practice.listeners;

import com.creamint.practice.PracticePvP;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private final PracticePvP plugin;

    public PlayerJoinListener(PracticePvP plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage("Welcome to the PracticePvP server!");
    }
}