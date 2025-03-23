package com.creamint.practice.commands;

import com.creamint.practice.PracticePvP;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArenasWorldCommand implements CommandExecutor {
    private final PracticePvP plugin;

    public ArenasWorldCommand(PracticePvP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("mashiprac.admin")) {
                Location arenasWorldLocation = new Location(player.getWorld(), 0, 100, 0);
                player.teleport(arenasWorldLocation);
                player.sendMessage("Teleported to the Maps world.");
                return true;
            } else {
                player.sendMessage("You do not have permission to execute this command.");
            }
        }
        return false;
    }
}