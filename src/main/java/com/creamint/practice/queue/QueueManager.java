package com.creamint.practice.queue;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class QueueManager {
    private final Map<String, Queue> queues;

    public QueueManager() {
        this.queues = new HashMap<>();
    }

    public void createQueue(String kitName) {
        queues.put(kitName, new Queue());
    }

    public void addPlayerToQueue(Player player, String kitName) {
        Queue queue = queues.get(kitName);
        if (queue != null) {
            queue.addPlayer(player);
            if (queue.hasEnoughPlayers()) {
                startMatch(queue, kitName);
            }
        }
    }

    public void removePlayerFromQueue(Player player, String kitName) {
        Queue queue = queues.get(kitName);
        if (queue != null) {
            queue.removePlayer(player);
        }
    }

    private void startMatch(Queue queue, String kitName) {
        // マッチ開始のロジックを実装
        List<Player> players = queue.getPlayers();
        Player player1 = players.get(0);
        Player player2 = players.get(1);

        // アリーナにテレポートするロジック
        // 例：Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "tp " + player1.getName() + " arena1_pos1");
        // 例：Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "tp " + player2.getName() + " arena1_pos2");

        player1.sendMessage("You are matched with " + player2.getName() + " in kit " + kitName);
        player2.sendMessage("You are matched with " + player1.getName() + " in kit " + kitName);

        queue.clearQueue();
    }
}