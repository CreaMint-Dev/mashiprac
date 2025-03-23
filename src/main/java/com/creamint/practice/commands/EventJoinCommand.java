package com.creamint.practice.commands;

import com.creamint.practice.PracticePvP;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EventJoinCommand implements CommandExecutor {
    private final PracticePvP plugin;

    public EventJoinCommand(PracticePvP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            // イベントに参加するロジック
            player.sendMessage("Event join command executed.");
            return true;
        }
        return false;
    }
}