package com.creamint.practice.commands;

import com.creamint.practice.PracticePvP;
import com.creamint.practice.kit.KitManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class KitCommand implements CommandExecutor {
    private final PracticePvP plugin;
    private final KitManager kitManager;

    public KitCommand(PracticePvP plugin) {
        this.plugin = plugin;
        this.kitManager = plugin.getKitManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by players.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 2) {
            player.sendMessage("Usage: /kit <create|delete|set|list> <kitname>");
            return true;
        }

        String subCommand = args[0];
        String kitName = args[1];

        switch (subCommand.toLowerCase()) {
            case "create":
                ItemStack itemInHand = player.getInventory().getItemInHand();
                if (itemInHand.getType() == Material.AIR) {
                    player.sendMessage("You must hold an item to create a kit.");
                    return true;
                }
                kitManager.createKit(kitName, itemInHand);
                player.sendMessage("Kit " + kitName + " created.");
                break;
            case "delete":
                kitManager.deleteKit(kitName);
                player.sendMessage("Kit " + kitName + " deleted.");
                break;
            case "set":
                itemInHand = player.getInventory().getItemInHand();
                if (itemInHand.getType() == Material.AIR) {
                    player.sendMessage("You must hold an item to set a kit.");
                    return true;
                }
                kitManager.setKit(kitName, itemInHand);
                player.sendMessage("Kit " + kitName + " set.");
                break;
            case "list":
                player.sendMessage("Available kits:");
                for (String name : kitManager.getKits().keySet()) {
                    player.sendMessage("- " + name);
                }
                break;
            default:
                player.sendMessage("Usage: /kit <create|delete|set|list> <kitname>");
                break;
        }

        return true;
    }
}