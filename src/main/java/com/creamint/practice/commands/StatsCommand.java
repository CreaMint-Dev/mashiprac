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
            // Stats表示ロジック
            player.sendMessage("Stats command executed.");
            return true;
        }
        return false;
    }
}