package com.creamint.practice.commands;

import com.creamint.practice.PracticePvP;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PartySetLimitCommand implements CommandExecutor {
    private final PracticePvP plugin;

    public PartySetLimitCommand(PracticePvP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                try {
                    int limit = Integer.parseInt(args[0]);
                    // Set party limit logic here
                    player.sendMessage("Party limit set to " + limit);
                } catch (NumberFormatException e) {
                    player.sendMessage("Invalid limit. Please enter a number.");
                }
            } else {
                player.sendMessage("Usage: /party setlimit <limit>");
            }
            return true;
        }
        return false;
    }
}