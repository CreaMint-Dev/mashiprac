package com.creamint.practice.player;

import org.bukkit.entity.Player;

public class PlayerData {
    private final Player player;
    private PlayerState state;

    public PlayerData(Player player) {
        this.player = player;
        this.state = PlayerState.LOBBY;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerState getState() {
        return state;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }
}