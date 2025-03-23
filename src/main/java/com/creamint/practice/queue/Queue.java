package com.creamint.practice.queue;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Queue {
    private final List<Player> players;

    public Queue() {
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        if (!players.contains(player)) {
            players.add(player);
        }
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public boolean isPlayerInQueue(Player player) {
        return players.contains(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean hasEnoughPlayers() {
        return players.size() >= 2;
    }

    public void clearQueue() {
        players.clear();
    }
}