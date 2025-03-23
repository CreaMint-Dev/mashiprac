package com.creamint.practice.commands;

import com.creamint.practice.PracticePvP;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

public class EventCommand implements CommandExecutor {
    private final PracticePvP plugin;

    public EventCommand(PracticePvP plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("mashiprac.event")) {
                Inventory eventInventory = plugin.getServer().createInventory(null, 9, "Select Event");

                ItemStack tntTag = new ItemStack(Material.TNT);
                ItemMeta tntTagMeta = tntTag.getItemMeta();
                tntTagMeta.setDisplayName("TNT Tag");
                tntTag.setItemMeta(tntTagMeta);

                eventInventory.setItem(0, tntTag);

                player.openInventory(eventInventory);
                return true;
            } else {
                player.sendMessage("You do not have permission to execute this command.");
            }
        }
        return false;
    }
}