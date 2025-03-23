package com.creamint.practice.commands;

import com.creamint.practice.PracticePvP;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PartyPromoteCommand implements CommandExecutor {
    private final PracticePvP plugin;

    public PartyPromoteCommand(PracticePvP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    // Promote player to party leader logic here
                    player.sendMessage("Promoted " + target.getName() + " to party leader.");
                    target.sendMessage("You have been promoted to party leader by " + player.getName());
                } else {
                    player.sendMessage("The specified player is not online.");
                }
            } else {
                player.sendMessage("Usage: /party promote <player>");
            }
            return true;
        }
        return false;
    }
}