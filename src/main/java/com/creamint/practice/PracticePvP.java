package com.creamint.practice;

import com.creamint.practice.commands.*;
import com.creamint.practice.listeners.*;
import com.creamint.practice.queue.QueueManager;
import com.creamint.practice.match.MatchManager;
import com.creamint.practice.kit.KitManager;
import com.creamint.practice.gui.KitSelectionGUI;
import org.bukkit.plugin.java.JavaPlugin;

public class PracticePvP extends JavaPlugin {
    private QueueManager queueManager;
    private MatchManager matchManager;
    private KitManager kitManager;
    private KitSelectionGUI kitSelectionGUI;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        queueManager = new QueueManager();
        matchManager = new MatchManager(this);
        kitManager = new KitManager();
        kitSelectionGUI = new KitSelectionGUI(kitManager, queueManager);

        // コマンドの登録
        getCommand("mashiprac").setExecutor(new MashipracCommand(this));
        getCommand("event").setExecutor(new EventCommand(this));
        getCommand("duel").setExecutor(new DuelCommand(this));
        getCommand("spec").setExecutor(new SpecCommand(this));
        getCommand("stats").setExecutor(new StatsCommand(this));
        getCommand("l").setExecutor(new LeaveCommand(this));
        getCommand("event join").setExecutor(new EventJoinCommand(this));

        // リスナーの登録
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(kitSelectionGUI), this);

        // Queueの初期化（必要に応じてKit名を設定）
        queueManager.createQueue("default");
    }

    @Override
    public void onDisable() {
        // プラグインが無効化されたときの処理
    }

    public QueueManager getQueueManager() {
        return queueManager;
    }

    public MatchManager getMatchManager() {
        return matchManager;
    }

    public KitManager getKitManager() {
        return kitManager;
    }
}