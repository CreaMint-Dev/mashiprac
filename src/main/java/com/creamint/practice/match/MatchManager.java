package com.creamint.practice.match;

import com.creamint.practice.PracticePvP;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class MatchManager {
    private final PracticePvP plugin;
    private final Map<Player, Match> activeMatches;

    public MatchManager(PracticePvP plugin) {
        this.plugin = plugin;
        this.activeMatches = new HashMap<>();
    }

    public void startMatch(Player player1, Player player2, String kitName) {
        Match match = new Match(player1, player2, kitName);
        activeMatches.put(player1, match);
        activeMatches.put(player2, match);

        // マッチ開始のロジック
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            match.start();
            // プレイヤーをアリーナにテレポートするロジック
            player1.sendMessage("Match started with " + player2.getName());
            player2.sendMessage("Match started with " + player1.getName());

            // カウントダウンのロジック
            startCountdown(match);
        }, 100L); // 5秒後に開始
    }

    public void endMatch(Match match) {
        match.end();
        activeMatches.remove(match.getPlayer1());
        activeMatches.remove(match.getPlayer2());

        // マッチ終了のロジック
        match.getPlayer1().sendMessage("Match ended.");
        match.getPlayer2().sendMessage("Match ended.");

        // 4秒後にロビースポーンにテレポートし、スポーンアイテムを持たせるロジック
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            Location lobbySpawn = plugin.getServer().getWorld("world").getSpawnLocation(); // ロビーのスポーン地点を取得
            match.getPlayer1().teleport(lobbySpawn);
            match.getPlayer2().teleport(lobbySpawn);

            // スポーンアイテムを持たせる
            giveSpawnItems(match.getPlayer1());
            giveSpawnItems(match.getPlayer2());
        }, 80L); // 4秒後に実行（20L = 1秒）
    }

    public Match getMatch(Player player) {
        return activeMatches.get(player);
    }

    private void startCountdown(Match match) {
        // 5秒のカウントダウンロジック
        for (int i = 5; i > 0; i--) {
            final int count = i;
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                match.getPlayer1().sendMessage("Starting in " + count + " seconds...");
                match.getPlayer2().sendMessage("Starting in " + count + " seconds...");
            }, (5 - i) * 20L);
        }

        // カウントダウン終了後に試合開始
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            match.getPlayer1().sendMessage("Fight!");
            match.getPlayer2().sendMessage("Fight!");
            // プレイヤーを攻撃可能にするロジック
        }, 100L);
    }

    private void giveSpawnItems(Player player) {
        // スポーンアイテムを持たせるロジックを実装
        // 例：player.getInventory().addItem(new ItemStack(Material.COMPASS));
    }
}