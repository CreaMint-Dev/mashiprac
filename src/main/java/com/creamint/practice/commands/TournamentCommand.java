package com.creamint.practice.commands;

import com.creamint.practice.PracticePvP;
import com.creamint.practice.game.GameMode;
import com.creamint.practice.tournament.Tournament;
import com.creamint.practice.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TournamentCommand implements CommandExecutor, TabCompleter {
    private final PracticePvP plugin;

    public TournamentCommand(PracticePvP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Utils.color("&cOnly players can use this command!"));
            return true;
        }

        if (args.length == 0) {
            sendHelpMessage(player);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "create" -> handleCreate(player, args);
            case "join" -> handleJoin(player, args);
            case "leave" -> handleLeave(player);
            case "info" -> handleInfo(player, args);
            case "list" -> handleList(player);
            default -> sendHelpMessage(player);
        }

        return true;
    }

    private void handleCreate(Player player, String[] args) {
        if (!player.hasPermission("practice.tournament.host")) {
            player.sendMessage(Utils.color("&cYou don't have permission to host tournaments!"));
            return;
        }

        if (args.length < 4) {
            player.sendMessage(Utils.color("&cUsage: /tournament create <name> <gamemode> <maxPlayers>"));
            return;
        }

        String name = args[1];
        GameMode gameMode;
        try {
            gameMode = GameMode.valueOf(args[2].toUpperCase());
        } catch (IllegalArgumentException e) {
            player.sendMessage(Utils.color("&cInvalid gamemode! Available modes:"));
            for (GameMode mode : GameMode.values()) {
                player.sendMessage(Utils.color("&8» &b" + mode.name()));
            }
            return;
        }

        int maxPlayers;
        try {
            maxPlayers = Integer.parseInt(args[3]);
        } catch (NumberFormatException e) {
            player.sendMessage(Utils.color("&cMaxPlayers must be a number!"));
            return;
        }

        plugin.getTournamentManager().createTournament(player, name, gameMode, maxPlayers);
    }

    private void handleJoin(Player player, String[] args) {
        if (args.length < 2) {
            player.sendMessage(Utils.color("&cUsage: /tournament join <name>"));
            return;
        }

        String tournamentName = args[1];
        plugin.getTournamentManager().joinTournament(player, tournamentName);
    }

    private void handleLeave(Player player) {
        plugin.getTournamentManager().leaveTournament(player);
    }

    private void handleInfo(Player player, String[] args) {
        if (args.length < 2) {
            // プレイヤーが参加中のトーナメント情報を表示
            Tournament tournament = plugin.getTournamentManager()
                    .getPlayerTournament(player.getUniqueId());
            if (tournament == null) {
                player.sendMessage(Utils.color("&cUsage: /tournament info <name>"));
                return;
            }
            displayTournamentInfo(player, tournament);
            return;
        }

        String tournamentName = args[1];
        Tournament tournament = plugin.getTournamentManager().getTournament(tournamentName);
        if (tournament == null) {
            player.sendMessage(Utils.color("&cThat tournament doesn't exist!"));
            return;
        }

        displayTournamentInfo(player, tournament);
    }

    private void handleList(Player player) {
        List<Tournament> tournaments = plugin.getTournamentManager().getActiveTournaments();
        if (tournaments.isEmpty()) {
            player.sendMessage(Utils.color("&cThere are no active tournaments!"));
            return;
        }

        player.sendMessage(Utils.color("&8&m------------------------------------"));
        player.sendMessage(Utils.color("&b&lActive Tournaments"));
        player.sendMessage("");

        for (Tournament tournament : tournaments) {
            player.sendMessage(Utils.color("&8» &b" + tournament.getName()));
            player.sendMessage(Utils.color("  &8• &7Mode: &b" + tournament.getGameMode().getName()));
            player.sendMessage(Utils.color("  &8• &7Players: &b" + tournament.getPlayerCount() +
                    "/" + tournament.getMaxPlayers()));
            player.sendMessage(Utils.color("  &8• &7Status: &b" + tournament.getStatus()));
            player.sendMessage("");
        }

        player.sendMessage(Utils.color("&8&m------------------------------------"));
    }

    private void displayTournamentInfo(Player player, Tournament tournament) {
        player.sendMessage(Utils.color("&8&m------------------------------------"));
        player.sendMessage(Utils.color("&b&lTournament Info: &7" + tournament.getName()));
        player.sendMessage("");
        player.sendMessage(Utils.color("&8» &7Mode: &b" + tournament.getGameMode().getName()));
        player.sendMessage(Utils.color("&8» &7Players: &b" + tournament.getPlayerCount() +
                "/" + tournament.getMaxPlayers()));
        player.sendMessage(Utils.color("&8» &7Status: &b" + tournament.getStatus()));
        if (tournament.getStatus() == com.creamint.practice.tournament.TournamentState.IN_PROGRESS) {
            player.sendMessage(Utils.color("&8» &7Current Round: &b" + tournament.getCurrentRound()));
        }
        player.sendMessage("");
        player.sendMessage(Utils.color("&8&m------------------------------------"));
    }

    private void sendHelpMessage(Player player) {
        player.sendMessage(Utils.color("&8&m------------------------------------"));
        player.sendMessage(Utils.color("&b&lTournament Commands"));
        player.sendMessage("");
        if (player.hasPermission("practice.tournament.host")) {
            player.sendMessage(Utils.color("&8» &b/tournament create <name> <gamemode> <maxPlayers>"));
        }
        player.sendMessage(Utils.color("&8» &b/tournament join <name>"));
        player.sendMessage(Utils.color("&8» &b/tournament leave"));
        player.sendMessage(Utils.color("&8» &b/tournament info [name]"));
        player.sendMessage(Utils.color("&8» &b/tournament list"));
        player.sendMessage(Utils.color("&8&m------------------------------------"));
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            String[] commands = sender.hasPermission("practice.tournament.host") ?
                    new String[]{"create", "join", "leave", "info", "list"} :
                    new String[]{"join", "leave", "info", "list"};
            return filterCompletions(args[0], commands);
        }

        if (args.length == 2) {
            switch (args[0].toLowerCase()) {
                case "join", "info" -> {
                    return filterCompletions(args[1],
                            plugin.getTournamentManager().getActiveTournaments().stream()
                                    .map(Tournament::getName)
                                    .toArray(String[]::new));
                }
                case "create" -> {
                    if (sender.hasPermission("practice.tournament.host")) {
                        return List.of("<name>");
                    }
                }
            }
        }

        if (args.length == 3 && args[0].equalsIgnoreCase("create")) {
            return filterCompletions(args[2],
                    Stream.of(GameMode.values())
                            .map(GameMode::name)
                            .toArray(String[]::new));
        }

        if (args.length == 4 && args[0].equalsIgnoreCase("create")) {
            return List.of("4", "8", "16", "32");
        }

        return completions;
    }

    private List<String> filterCompletions(String input, String[] possibilities) {
        List<String> completions = new ArrayList<>();
        for (String possibility : possibilities) {
            if (possibility.toLowerCase().startsWith(input.toLowerCase())) {
                completions.add(possibility);
            }
        }
        return completions;
    }
}