package com.creamint.practice.kit;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class KitManager {
    private final Map<String, ItemStack> kits;
    private final File kitFile;
    private final FileConfiguration kitConfig;

    public KitManager(File dataFolder) {
        this.kits = new HashMap<>();
        this.kitFile = new File(dataFolder, "kits.yml");
        this.kitConfig = YamlConfiguration.loadConfiguration(kitFile);
        loadKits();
    }

    public void createKit(String name, ItemStack item) {
        kits.put(name, item);
        saveKits();
    }

    public void deleteKit(String name) {
        kits.remove(name);
        saveKits();
    }

    public void setKit(String name, ItemStack item) {
        kits.put(name, item);
        saveKits();
    }

    public ItemStack getKit(String name) {
        return kits.get(name);
    }

    public Map<String, ItemStack> getKits() {
        return kits;
    }

    private void saveKits() {
        for (Map.Entry<String, ItemStack> entry : kits.entrySet()) {
            String path = "kits." + entry.getKey();
            ItemStack item = entry.getValue();
            kitConfig.set(path + ".type", item.getType().name());
            kitConfig.set(path + ".amount", item.getAmount());
            kitConfig.set(path + ".name", item.getItemMeta().getDisplayName());
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

                kits.put(name, item);
            }
        }
    }
}