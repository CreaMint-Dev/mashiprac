package com.creamint.practice.kit;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.entity.Player;

public class Kit {
    private final String name;
    private ItemStack icon;
    private PlayerInventory inventory;

    public Kit(String name, ItemStack icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public void setIcon(ItemStack icon) {
        this.icon = icon;
    }

    public PlayerInventory getInventory() {
        return inventory;
    }

    public void setInventory(PlayerInventory inventory) {
        this.inventory = inventory;
    }

    public void loadInventory(Player player) {
        player.getInventory().setContents(inventory.getContents());
        player.getInventory().setArmorContents(inventory.getArmorContents());
    }
}