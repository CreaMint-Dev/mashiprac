package com.creamint.practice;

import com.creamint.practice.commands.*;
import com.creamint.practice.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

public class PracticePvP extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("PracticePvP has been enabled!");

        // Register commands
        getCommand("mashiprac").setExecutor(new MashipracCommand(this));
        getCommand("event").setExecutor(new EventCommand(this));
        getCommand("duel").setExecutor(new DuelCommand(this));
        getCommand("spec").setExecutor(new SpecCommand(this));
        getCommand("stats").setExecutor(new StatsCommand(this));
        getCommand("l").setExecutor(new LeaveCommand(this));
        getCommand("event join").setExecutor(new EventJoinCommand(this));

        // Register party commands
        getCommand("party create").setExecutor(new PartyCreateCommand(this));
        getCommand("party invite").setExecutor(new PartyInviteCommand(this));
        getCommand("party setlimit").setExecutor(new PartySetLimitCommand(this));
        getCommand("party promote").setExecutor(new PartyPromoteCommand(this));

        // Register event listeners
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("PracticePvP has been disabled!");
    }
}