package com.creamint.practice.arena;

import com.creamint.practice.PracticePvP;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ArenaManager {
    private final PracticePvP plugin;
    private final Map<String, Arena> arenas;
    private final File arenaFile;
    private final FileConfiguration arenaConfig;

    public ArenaManager(PracticePvP plugin) {
        this.plugin = plugin;
        this.arenas = new HashMap<>();
        this.arenaFile = new File(plugin.getDataFolder(), "arenas.yml");
        this.arenaConfig = YamlConfiguration.loadConfiguration(arenaFile);
        loadArenas();
    }

    public void createArena(String name) {
        arenas.put(name, new Arena(name));
    }

    public void deleteArena(String name) {
        arenas.remove(name);
        saveArenas();
    }

    public Arena getArena(String name) {
        return arenas.get(name);
    }

    public void setArenaPosition(String name, Location pos1, Location pos2) {
        Arena arena = getArena(name);
        if (arena != null) {
            arena.setPos1(pos1);
            arena.setPos2(pos2);
        }
    }

    public void setArenaSpawn(String name, Location spawn1, Location spawn2) {
        Arena arena = getArena(name);
        if (arena != null) {
            arena.setSpawn1(spawn1);
            arena.setSpawn2(spawn2);
        }
    }

    public void setArenaKits(String name, List<String> kits) {
        Arena arena = getArena(name);
        if (arena != null) {
            arena.setKits(kits);
        }
    }

    public void clearArenaKits(String name) {
        Arena arena = getArena(name);
        if (arena != null) {
            arena.clearKits();
        }
    }

    public void setResetAfterMatch(String name, boolean reset) {
        Arena arena = getArena(name);
        if (arena != null) {
            arena.setResetAfterMatch(reset);
        }
    }

    public void setArenaIcon(String name, ItemStack icon) {
        Arena arena = getArena(name);
        if (arena != null) {
            arena.setIcon(icon);
        }
    }

    public void saveArenas() {
        for (Arena arena : arenas.values()) {
            String path = "arenas." + arena.getName();
            arenaConfig.set(path + ".pos1", arena.getPos1());
            arenaConfig.set(path + ".pos2", arena.getPos2());
            arenaConfig.set(path + ".spawn1", arena.getSpawn1());
            arenaConfig.set(path + ".spawn2", arena.getSpawn2());
            arenaConfig.set(path + ".kits", arena.getKits());
            arenaConfig.set(path + ".resetAfterMatch", arena.isResetAfterMatch());
            arenaConfig.set(path + ".icon", arena.getIcon());
        }
        try {
            arenaConfig.save(arenaFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadArenas() {
        if (arenaConfig.contains("arenas")) {
            for (String name : arenaConfig.getConfigurationSection("arenas").getKeys(false)) {
                Arena arena = new Arena(name);
                arena.setPos1((Location) arenaConfig.get("arenas." + name + ".pos1"));
                arena.setPos2((Location) arenaConfig.get("arenas." + name + ".pos2"));
                arena.setSpawn1((Location) arenaConfig.get("arenas." + name + ".spawn1"));
                arena.setSpawn2((Location) arenaConfig.get("arenas." + name + ".spawn2"));
                arena.setKits((List<String>) arenaConfig.get("arenas." + name + ".kits"));
                arena.setResetAfterMatch(arenaConfig.getBoolean("arenas." + name + ".resetAfterMatch"));
                arena.setIcon((ItemStack) arenaConfig.get("arenas." + name + ".icon"));
                arenas.put(name, arena);
            }
        }
    }
}