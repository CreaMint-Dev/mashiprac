package com.creamint.practice.kit;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class KitManager {
    private final Map<String, ItemStack> kits;

    public KitManager() {
        this.kits = new HashMap<>();
        // デフォルトのKitを追加
        kits.put("default", new ItemStack(Material.DIAMOND_SWORD));
        kits.put("archer", new ItemStack(Material.BOW));
        // 必要に応じて他のKitを追加
    }

    public ItemStack getKit(String kitName) {
        return kits.get(kitName);
    }

    public Map<String, ItemStack> getKits() {
        return kits;
    }
}