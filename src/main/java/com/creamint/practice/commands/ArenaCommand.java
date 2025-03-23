package com.creamint.practice.commands;

import com.creamint.practice.PracticePvP;
import com.creamint.practice.arena.ArenaManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

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
            player.sendMessage("Usage: /arena <create|delete|setpos|setspawn|teleport|kits|clearkits|resetaftermatch|pos1|pos2|c1|c2|list|seticon> <arenaname>");
            return true;
        }

        String subCommand = args[0];
        String arenaName = args[1];

        switch (subCommand.toLowerCase()) {
            case "create":
                arenaManager.createArena(arenaName);
                player.sendMessage("Arena " + arenaName + " created.");
                break;
            case "delete":
                arenaManager.deleteArena(arenaName);
                player.sendMessage("Arena " + arenaName + " deleted.");
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
            case "teleport":
                Arena arena = arenaManager.getArena(arenaName);
                if (arena != null && arena.getPos1() != null) {
                    player.teleport(arena.getPos1());
                    player.sendMessage("Teleported to position 1 of arena " + arenaName);
                } else {
                    player.sendMessage("Arena or position 1 not set.");
                }
                break;
            case "kits":
                if (args.length < 3) {
                    player.sendMessage("Usage: /arena kits <arenaname> <kits>");
                    return true;
                }
                String[] kits = Arrays.copyOfRange(args, 2, args.length);
                arenaManager.setArenaKits(arenaName, Arrays.asList(kits));
                player.sendMessage("Kits set for arena " + arenaName);
                break;
            case "clearkits":
                arenaManager.clearArenaKits(arenaName);
                player.sendMessage("Kits cleared for arena " + arenaName);
                break;
            case "resetaftermatch":
                if (args.length < 3) {
                    player.sendMessage("Usage: /arena resetaftermatch <arenaname> <true|false>");
                    return true;
                }
                boolean reset = Boolean.parseBoolean(args[2]);
                arenaManager.setResetAfterMatch(arenaName, reset);
                player.sendMessage("Reset after match set to " + reset + " for arena " + arenaName);
                break;
            case "pos1":
                location = player.getLocation();
                arenaManager.setArenaPosition(arenaName, location, null);
                player.sendMessage("Position 1 set for arena " + arenaName);
                break;
            case "pos2":
                location = player.getLocation();
                arenaManager.setArenaPosition(arenaName, null, location);
                player.sendMessage("Position 2 set for arena " + arenaName);
                break;
            case "c1":
                location = player.getLocation();
                arenaManager.setArenaPosition(arenaName, location, null);
                player.sendMessage("Corner 1 set for arena " + arenaName);
                break;
            case "c2":
                location = player.getLocation();
                arenaManager.setArenaPosition(arenaName, null, location);
                player.sendMessage("Corner 2 set for arena " + arenaName);
                break;
            case "list":
                player.sendMessage("Available arenas:");
                for (String name : arenaManager.getArenas().keySet()) {
                    player.sendMessage("- " + name);
                }
                break;
            case "seticon":
                ItemStack itemInHand = player.getInventory().getItemInHand();
                if (itemInHand.getType() == Material.AIR) {
                    player.sendMessage("You must hold an item to set an icon.");
                    return true;
                }
                arenaManager.setArenaIcon(arenaName, itemInHand);
                player.sendMessage("Icon for arena " + arenaName + " set.");
                break;
            default:
                player.sendMessage("Usage: /arena <create|delete|setpos|setspawn|teleport|kits|clearkits|resetaftermatch|pos1|pos2|c1|c2|list|seticon> <arenaname>");
                break;
        }

        return true;
    }
}