package com.creamint.practice.tournament;

import com.creamint.practice.PracticePvP;
import com.creamint.practice.game.GameMode;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.*;

public class Tournament {
    private final PracticePvP plugin;
    private final String name;
    private final GameMode gameMode;
    private final Set<UUID> players;
    private TournamentState state;

    public Tournament(PracticePvP plugin, String name, GameMode gameMode) {
        this.plugin = plugin;
        this.name = name;
        this.gameMode = gameMode;
        this.players = new HashSet<>();
        this.state = TournamentState.WAITING;
    }

    public void broadcast(String message) {
        getOnlinePlayers().forEach(player ->
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', message)));
    }

    public void addPlayer(Player player) {
        players.add(player.getUniqueId());
        broadcast("&b" + player.getName() + " &7has joined the tournament!");
    }

    public void removePlayer(Player player) {
        players.remove(player.getUniqueId());
        broadcast("&b" + player.getName() + " &7has left the tournament!");
    }

    public List<Player> getOnlinePlayers() {
        List<Player> onlinePlayers = new ArrayList<>();
        for (UUID uuid : players) {
            Player player = plugin.getServer().getPlayer(uuid);
            if (player != null && player.isOnline()) {
                onlinePlayers.add(player);
            }
        }
        return onlinePlayers;
    }

    public String getName() { return name; }
    public GameMode getGameMode() { return gameMode; }
    public TournamentState getState() { return state; }
    public int getPlayerCount() { return players.size(); }
}