package com.creamint.practice.arena;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private final String name;
    private Location pos1;
    private Location pos2;
    private Location spawn1;
    private Location spawn2;
    private List<String> kits;
    private boolean resetAfterMatch;
    private ItemStack icon;

    public Arena(String name) {
        this.name = name;
        this.kits = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Location getPos1() {
        return pos1;
    }

    public void setPos1(Location pos1) {
        this.pos1 = pos1;
    }

    public Location getPos2() {
        return pos2;
    }

    public void setPos2(Location pos2) {
        this.pos2 = pos2;
    }

    public Location getSpawn1() {
        return spawn1;
    }

    public void setSpawn1(Location spawn1) {
        this.spawn1 = spawn1;
    }

    public Location getSpawn2() {
        return spawn2;
    }

    public void setSpawn2(Location spawn2) {
        this.spawn2 = spawn2;
    }

    public List<String> getKits() {
        return kits;
    }

    public void setKits(List<String> kits) {
        this.kits = kits;
    }

    public void clearKits() {
        this.kits.clear();
    }

    public boolean isResetAfterMatch() {
        return resetAfterMatch;
    }

    public void setResetAfterMatch(boolean resetAfterMatch) {
        this.resetAfterMatch = resetAfterMatch;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public void setIcon(ItemStack icon) {
        this.icon = icon;
    }
}