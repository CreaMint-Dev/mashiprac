package com.creamint.practice.kit;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class KitManager {
    private final Map<String, Kit> kits;
    private final File kitFile;
    private final FileConfiguration kitConfig;

    public KitManager(File dataFolder) {
        this.kits = new HashMap<>();
        this.kitFile = new File(dataFolder, "kits.yml");
        if (!kitFile.exists()) {
            try {
                kitFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.kitConfig = YamlConfiguration.loadConfiguration(kitFile);
        loadKits();
    }

    public void createKit(String name, ItemStack item) {
        Kit kit = new Kit(name, item);
        kits.put(name, kit);
        saveKits();
    }

    public void deleteKit(String name) {
        kits.remove(name);
        saveKits();
    }

    public void setKit(String name, ItemStack item) {
        Kit kit = kits.get(name);
        if (kit != null) {
            kit.setIcon(item);
            saveKits();
        }
    }

    public void setKitInventory(String name, PlayerInventory inventory) {
        Kit kit = kits.get(name);
        if (kit != null) {
            kit.setInventory(inventory);
            saveKits();
        }
    }

    public void loadKitInventory(String name, Player player) {
        Kit kit = kits.get(name);
        if (kit != null) {
            kit.loadInventory(player);
        }
    }

    public Kit getKit(String name) {
        return kits.get(name);
    }

    public Map<String, Kit> getKits() {
        return kits;
    }

    public void saveKits() {
        for (Kit kit : kits.values()) {
            String path = "kits." + kit.getName();
            kitConfig.set(path + ".type", kit.getIcon().getType().name());
            kitConfig.set(path + ".amount", kit.getIcon().getAmount());
            kitConfig.set(path + ".name", kit.getIcon().getItemMeta().getDisplayName());
            kitConfig.set(path + ".inventory", kit.getInventory());
        }
        try {
            kitConfig.save(kitFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadKits() {
        if (kitConfig.contains("kits")) {
            for (String name : kitConfig.getConfigurationSection("kits").getKeys(false)) {
                Material type = Material.valueOf(kitConfig.getString("kits." + name + ".type"));
                int amount = kitConfig.getInt("kits." + name + ".amount");
                String displayName = kitConfig.getString("kits." + name + ".name");

                ItemStack item = new ItemStack(type, amount);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(displayName);
                item.setItemMeta(meta);

                Kit kit = new Kit(name, item);
                kit.setInventory((PlayerInventory) kitConfig.get("kits." + name + ".inventory"));
                kits.put(name, kit);
            }
        }
    }
}