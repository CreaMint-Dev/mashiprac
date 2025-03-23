package com.creamint.practice;

import com.creamint.practice.commands.PartyCommand;
import com.creamint.practice.commands.TournamentCommand;
import com.creamint.practice.game.GameManager;
import com.creamint.practice.party.PartyManager;
import com.creamint.practice.player.PlayerManager;
import com.creamint.practice.tournament.TournamentManager;
import com.creamint.practice.listeners.PartyListener;
import com.creamint.practice.listeners.TournamentListener;
import org.bukkit.plugin.java.JavaPlugin;

public class PracticePvP extends JavaPlugin {
    private GameManager gameManager;
    private PlayerManager playerManager;
    private PartyManager partyManager;
    private TournamentManager tournamentManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        this.gameManager = new GameManager(this);
        this.playerManager = new PlayerManager(this);
        this.partyManager = new PartyManager(this);
        this.tournamentManager = new TournamentManager(this);

        // コマンド登録
        getCommand("party").setExecutor(new PartyCommand(this));
        getCommand("tournament").setExecutor(new TournamentCommand(this));

        // リスナー登録
        getServer().getPluginManager().registerEvents(new PartyListener(this), this);
        getServer().getPluginManager().registerEvents(new TournamentListener(this), this);
    }

    // Getters
    public GameManager getGameManager() { return gameManager; }
    public PlayerManager getPlayerManager() { return playerManager; }
    public PartyManager getPartyManager() { return partyManager; }
    public TournamentManager getTournamentManager() { return tournamentManager; }
}