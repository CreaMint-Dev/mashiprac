package com.creamint.practice.tournament;

import com.creamint.practice.PracticePvP;
import com.creamint.practice.game.Match;
import com.creamint.practice.game.GameMode;
import org.bukkit.entity.Player;

import java.util.*;

public class TournamentBracket {
    private final PracticePvP plugin;
    private final Tournament tournament;
    private final List<Match> matches;
    private final Queue<Player> playersQueue;

    public TournamentBracket(PracticePvP plugin, Tournament tournament) {
        this.plugin = plugin;
        this.tournament = tournament;
        this.matches = new ArrayList<>();
        this.playersQueue = new LinkedList<>(tournament.getOnlinePlayers());
        generateBracket();
    }

    private void generateBracket() {
        while (playersQueue.size() >= 2) {
            Player player1 = playersQueue.poll();
            Player player2 = playersQueue.poll();
            if (player1 != null && player2 != null) {
                Match match = new Match(player1, player2, tournament.getGameMode());
                matches.add(match);
            }
        }
    }

    public void startNextRound() {
        for (Match match : matches) {
            if (match.getState() == MatchState.WAITING) {
                match.start();
            }
        }
    }

    public void endMatch(Match match, Player winner) {
        match.end(winner);
        playersQueue.offer(winner);

        if (isRoundComplete()) {
            if (playersQueue.size() == 1) {
                announceWinner(playersQueue.poll());
            } else {
                generateBracket();
                startNextRound();
            }
        }
    }

    private boolean isRoundComplete() {
        for (Match match : matches) {
            if (match.getState() != MatchState.ENDED) {
                return false;
            }
        }
        return true;
    }

    private void announceWinner(Player winner) {
        plugin.getServer().broadcastMessage(
                winner.getName() + " has won the " + tournament.getName() + " tournament!"
        );
        tournament.setState(TournamentState.ENDED);
    }

    public List<Match> getMatches() {
        return matches;
    }
}