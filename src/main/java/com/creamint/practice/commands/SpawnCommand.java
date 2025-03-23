package com.creamint.practice.commands;

import com.creamint.practice.PracticePvP;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    private final PracticePvP plugin;

    public SpawnCommand(PracticePvP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("mashiprac.admin")) {
                Location spawnLocation = (Location) plugin.getConfig().get("spawn");
                if (spawnLocation != null) {
                    player.teleport(spawnLocation);
                    player.sendMessage("Teleported to the lobby spawn.");
                } else {
                    player.sendMessage("Lobby spawn location is not set.");
                }
                return true;
            } else {
                player.sendMessage("You do not have permission to execute this command.");
            }
        }
        return false;
    }
}