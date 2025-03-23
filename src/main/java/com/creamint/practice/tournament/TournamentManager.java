package com.creamint.practice.tournament;

import com.creamint.practice.PracticePvP;
import com.creamint.practice.game.GameMode;
import com.creamint.practice.utils.Utils;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TournamentManager {
    private final PracticePvP plugin;
    private final Map<String, Tournament> tournaments;
    private final Map<UUID, String> playerTournaments;

    public TournamentManager(PracticePvP plugin) {
        this.plugin = plugin;
        this.tournaments = new ConcurrentHashMap<>();
        this.playerTournaments = new ConcurrentHashMap<>();
    }

    public Tournament createTournament(Player host, String name, GameMode gameMode, int maxPlayers) {
        // トーナメント名の重複チェック
        if (tournaments.containsKey(name)) {
            host.sendMessage(Utils.color("&cA tournament with that name already exists!"));
            return null;
        }

        // プレイヤー数のバリデーション
        if (maxPlayers < 2 || maxPlayers > 32) {
            host.sendMessage(Utils.color("&cPlayer count must be between 2 and 32!"));
            return null;
        }

        // 権限チェック
        if (!host.hasPermission("practice.tournament.host")) {
            host.sendMessage(Utils.color("&cYou don't have permission to host tournaments!"));
            return null;
        }

        Tournament tournament = new Tournament(plugin, name, gameMode);
        tournaments.put(name, tournament);

        // トーナメント作成のアナウンス
        plugin.getServer().broadcastMessage(Utils.color(
                "&8[&b&lTournament&8] &7A new tournament has been created!"
        ));
        plugin.getServer().broadcastMessage(Utils.color(
                "&8» &7Name: &b" + name
        ));
        plugin.getServer().broadcastMessage(Utils.color(
                "&8» &7Mode: &b" + gameMode.getName()
        ));
        plugin.getServer().broadcastMessage(Utils.color(
                "&8» &7Players: &b" + maxPlayers
        ));
        plugin.getServer().broadcastMessage(Utils.color(
                "&8» &7Type &b/tournament join " + name + " &7to participate!"
        ));

        return tournament;
    }

    public void joinTournament(Player player, String tournamentName) {
        // プレイヤーが既に別のトーナメントに参加しているかチェック
        if (playerTournaments.containsKey(player.getUniqueId())) {
            player.sendMessage(Utils.color("&cYou are already in a tournament!"));
            return;
        }

        Tournament tournament = tournaments.get(tournamentName);
        if (tournament == null) {
            player.sendMessage(Utils.color("&cThat tournament doesn't exist!"));
            return;
        }

        if (tournament.getState() != TournamentState.WAITING) {
            player.sendMessage(Utils.color("&cYou can't join a tournament that is not in the waiting state!"));
            return;
        }

        tournament.addPlayer(player);
        playerTournaments.put(player.getUniqueId(), tournamentName);
    }

    public void leaveTournament(Player player) {
        String tournamentName = playerTournaments.get(player.getUniqueId());
        if (tournamentName == null) {
            player.sendMessage(Utils.color("&cYou are not in a tournament!"));
            return;
        }

        Tournament tournament = tournaments.get(tournamentName);
        if (tournament != null) {
            tournament.removePlayer(player);
            playerTournaments.remove(player.getUniqueId());
        }
    }

    public Tournament getTournament(String name) {
        return tournaments.get(name);
    }

    public Tournament getPlayerTournament(UUID playerId) {
        String tournamentName = playerTournaments.get(playerId);
        return tournamentName == null ? null : tournaments.get(tournamentName);
    }

    public List<Tournament> getActiveTournaments() {
        return new ArrayList<>(tournaments.values());
    }
}