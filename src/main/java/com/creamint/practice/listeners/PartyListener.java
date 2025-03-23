package com.creamint.practice.listeners;

import com.creamint.practice.PracticePvP;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;

public class PartyListener implements Listener {
    private final PracticePvP plugin;

    public PartyListener(PracticePvP plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // プレイヤーが参加したときの処理
    }
}