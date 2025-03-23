package com.creamint.practice.commands;

import com.creamint.practice.PracticePvP;
import com.creamint.practice.arena.ArenaManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Location;

public class ArenaCommand implements CommandExecutor {
    private final PracticePvP plugin;
    private final ArenaManager arenaManager;

    public ArenaCommand(PracticePvP plugin) {
        this.plugin = plugin;
        this.arenaManager = plugin.getArenaManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by players.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 2) {
            player.sendMessage("Usage: /arena <create|setpos|setspawn> <arenaname>");
            return true;
        }

        String subCommand = args[0];
        String arenaName = args[1];

        switch (subCommand.toLowerCase()) {
            case "create":
                arenaManager.createArena(arenaName);
                player.sendMessage("Arena " + arenaName + " created.");
                break;
            case "setpos":
                if (args.length < 3) {
                    player.sendMessage("Usage: /arena setpos <arenaname> <pos1|pos2>");
                    return true;
                }
                String pos = args[2];
                Location location = player.getLocation();
                if (pos.equalsIgnoreCase("pos1")) {
                    arenaManager.setArenaPosition(arenaName, location, null);
                    player.sendMessage("Position 1 set for arena " + arenaName);
                } else if (pos.equalsIgnoreCase("pos2")) {
                    arenaManager.setArenaPosition(arenaName, null, location);
                    player.sendMessage("Position 2 set for arena " + arenaName);
                } else {
                    player.sendMessage("Usage: /arena setpos <arenaname> <pos1|pos2>");
                }
                break;
            case "setspawn":
                if (args.length < 3) {
                    player.sendMessage("Usage: /arena setspawn <arenaname> <spawn1|spawn2>");
                    return true;
                }
                String spawn = args[2];
                location = player.getLocation();
                if (spawn.equalsIgnoreCase("spawn1")) {
                    arenaManager.setArenaSpawn(arenaName, location, null);
                    player.sendMessage("Spawn 1 set for arena " + arenaName);
                } else if (spawn.equalsIgnoreCase("spawn2")) {
                    arenaManager.setArenaSpawn(arenaName, null, location);
                    player.sendMessage("Spawn 2 set for arena " + arenaName);
                } else {
                    player.sendMessage("Usage: /arena setspawn <arenaname> <spawn1|spawn2>");
                }
                break;
            default:
                player.sendMessage("Usage: /arena <create|setpos|setspawn> <arenaname>");
                break;
        }

        return true;
    }
}