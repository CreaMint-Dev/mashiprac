package com.creamint.practice.commands;

import com.creamint.practice.PracticePvP;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpecCommand implements CommandExecutor {
    private final PracticePvP plugin;

    public SpecCommand(PracticePvP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > 0) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    // Spec logic here
                    player.teleport(target.getLocation());
                    player.sendMessage("You are now spectating " + target.getName());
                } else {
                    player.sendMessage("The specified player is not online.");
                }
            } else {
                player.sendMessage("Usage: /spec <player>");
            }
            return true;
        }
        return false;
    }
}