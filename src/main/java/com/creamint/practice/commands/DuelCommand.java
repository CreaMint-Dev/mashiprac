package com.creamint.practice.commands;

import com.creamint.practice.PracticePvP;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DuelCommand implements CommandExecutor {
    private final PracticePvP plugin;

    public DuelCommand(PracticePvP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    // Duel request logic here
                    player.sendMessage("Duel request sent to " + target.getName());
                    target.sendMessage("Duel request received from " + player.getName() + ". Use /duel accept to accept.");
                } else {
                    player.sendMessage("The specified player is not online.");
                }
            } else {
                player.sendMessage("Usage: /duel <player>");
            }
            return true;
        }
        return false;
    }
}