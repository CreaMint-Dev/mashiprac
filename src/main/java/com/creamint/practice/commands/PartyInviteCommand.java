package com.creamint.practice.commands;

import com.creamint.practice.PracticePvP;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PartyInviteCommand implements CommandExecutor {
    private final PracticePvP plugin;

    public PartyInviteCommand(PracticePvP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    // Party invite logic here
                    player.sendMessage("Party invite sent to " + target.getName());
                    target.sendMessage("You have been invited to a party by " + player.getName());
                } else {
                    player.sendMessage("The specified player is not online.");
                }
            } else {
                player.sendMessage("Usage: /party invite <player>");
            }
            return true;
        }
        return false;
    }
}