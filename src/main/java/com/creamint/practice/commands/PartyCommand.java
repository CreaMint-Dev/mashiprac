package com.creamint.practice.commands;

import com.creamint.practice.PracticePvP;
import com.creamint.practice.party.PartyManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PartyCommand implements CommandExecutor {
    private final PracticePvP plugin;

    public PartyCommand(PracticePvP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        if (args.length == 0) {
            sendHelp(player);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "create" -> plugin.getPartyManager().createParty(player);
            case "leave" -> plugin.getPartyManager().leaveParty(player);
            case "invite" -> {
                if (args.length < 2) {
                    player.sendMessage(ChatColor.RED + "Usage: /party invite <player>");
                    return true;
                }
                Player target = plugin.getServer().getPlayer(args[1]);
                if (target != null) {
                    plugin.getPartyManager().invitePlayer(player, target);
                }
            }
            default -> sendHelp(player);
        }

        return true;
    }

    private void sendHelp(Player player) {
        player.sendMessage(ChatColor.GRAY + "Party Commands:");
        player.sendMessage(ChatColor.BLUE + "/party create");
        player.sendMessage(ChatColor.BLUE + "/party invite <player>");
        player.sendMessage(ChatColor.BLUE + "/party leave");
    }
}