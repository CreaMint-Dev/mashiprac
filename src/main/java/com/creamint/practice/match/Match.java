package com.creamint.practice.match;

import org.bukkit.entity.Player;

public class Match {
    private final Player player1;
    private final Player player2;
    private final String kitName;
    private boolean isStarted;

    public Match(Player player1, Player player2, String kitName) {
        this.player1 = player1;
        this.player2 = player2;
        this.kitName = kitName;
        this.isStarted = false;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public String getKitName() {
        return kitName;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void start() {
        this.isStarted = true;
    }

    public void end() {
        this.isStarted = false;
    }
}