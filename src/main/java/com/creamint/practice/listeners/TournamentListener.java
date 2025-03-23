package com.creamint.practice.listeners;

import com.creamint.practice.PracticePvP;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.EventHandler;

public class TournamentListener implements Listener {
    private final PracticePvP plugin;

    public TournamentListener(PracticePvP plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // プレイヤーが退出したときの処理
    }
}