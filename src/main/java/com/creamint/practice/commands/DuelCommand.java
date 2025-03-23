package com.creamint.practice.commands;

import com.creamint.practice.PracticePvP;
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
            // Duelリクエストを送るロジック
            player.sendMessage("Duel command executed.");
            return true;
        }
        return false;
    }
}