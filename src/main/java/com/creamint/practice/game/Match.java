package com.creamint.practice.game;

import org.bukkit.entity.Player;

public class Match {
    private final Player player1;
    private final Player player2;
    private final GameMode gameMode;
    private MatchState state;

    public Match(Player player1, Player player2, GameMode gameMode) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameMode = gameMode;
        this.state = MatchState.WAITING;
    }

    public void start() {
        this.state = MatchState.IN_PROGRESS;
        // 試合開始のロジック
    }

    public void end(Player winner) {
        this.state = MatchState.ENDED;
        // 終了処理
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public MatchState getState() {
        return state;
    }
}