package com.creamint.practice.commands;

import com.creamint.practice.PracticePvP;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCommand implements CommandExecutor {
    private final PracticePvP plugin;

    public StatsCommand(PracticePvP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            // Implement stats logic here
            if (args.length > 0) {
                // Display stats for the specified player
                Player target = plugin.getServer().getPlayer(args[0]);
                if (target != null) {
                    player.sendMessage("Displaying stats for " + target.getName());
                } else {
                    player.sendMessage("The specified player is not online.");
                }
            } else {
                // Display stats for the player using the command
                player.sendMessage("Displaying your stats.");
            }
            return true;
        }
        return false;
    }
}