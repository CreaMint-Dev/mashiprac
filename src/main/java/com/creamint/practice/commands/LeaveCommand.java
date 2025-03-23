package com.creamint.practice.commands;

import com.creamint.practice.PracticePvP;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LeaveCommand implements CommandExecutor {
    private final PracticePvP plugin;

    public LeaveCommand(PracticePvP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            // Implement leave logic here
            player.sendMessage("Returning to the lobby spawn.");
            // Teleport to lobby spawn
            Location spawnLocation = (Location) plugin.getConfig().get("spawn");
            if (spawnLocation != null) {
                player.teleport(spawnLocation);
            } else {
                player.sendMessage("Lobby spawn location is not set.");
            }
            return true;
        }
        return false;
    }
}