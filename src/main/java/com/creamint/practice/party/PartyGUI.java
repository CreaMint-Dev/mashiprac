package com.creamint.practice.party;

import com.creamint.practice.PracticePvP;
import com.creamint.practice.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PartyGUI {
    private final PracticePvP plugin;
    private final Party party;
    private final Player viewer;

    public PartyGUI(PracticePvP plugin, Party party, Player viewer) {
        this.plugin = plugin;
        this.party = party;
        this.viewer = viewer;
    }

    public void openMainMenu() {
        Inventory inv = Bukkit.createInventory(null, 27,
                Utils.color("&8» &bParty Menu"));

        // パーティー情報
        inv.setItem(4, Utils.createItem(
                Material.NETHER_STAR,
                "&b&lParty Info",
                "",
                "&7Leader: &b" + party.getLeader().getName(),
                "&7Members: &b" + party.getSize() + "/" + party.getMaxSize(),
                "&7Status: " + (party.isOpen() ? "&aOpen" : "&cClosed")
        ));

        // メンバーリスト
        inv.setItem(10, Utils.createItem(
                Material.PLAYER_HEAD,
                "&b&lMembers",
                "",
                "&7Click to view members!"
        ));

        // パーティー設定（リーダーのみ）
        if (party.isLeader(viewer.getUniqueId())) {
            inv.setItem(12, Utils.createItem(
                    Material.REDSTONE_COMPARATOR,
                    "&b&lParty Settings",
                    "",
                    "&7Click to change settings!"
            ));
        }

        // パーティー戦
        inv.setItem(14, Utils.createItem(
                Material.DIAMOND_SWORD,
                "&b&lParty Fight",
                "",
                "&7Click to start a party fight!"
        ));

        // パーティー解散/退出
        if (party.isLeader(viewer.getUniqueId())) {
            inv.setItem(16, Utils.createItem(
                    Material.BARRIER,
                    "&c&lDisband Party",
                    "",
                    "&7Click to disband the party!"
            ));
        } else {
            inv.setItem(16, Utils.createItem(
                    Material.BARRIER,
                    "&c&lLeave Party",
                    "",
                    "&7Click to leave the party!"
            ));
        }

        viewer.openInventory(inv);
    }

    public void openMemberList() {
        Inventory inv = Bukkit.createInventory(null, 27,
                Utils.color("&8» &bParty Members"));

        int slot = 10;
        for (Player member : party.getOnlineMembers()) {
            inv.setItem(slot++, Utils.createItem(
                    Material.PLAYER_HEAD,
                    "&b" + member.getName(),
                    "",
                    party.isLeader(member.getUniqueId()) ? "&6Party Leader" : "&7Member",
                    "",
                    party.isLeader(viewer.getUniqueId()) && !member.equals(viewer) ?
                            "&cClick to kick!" : ""
            ));
        }

        viewer.openInventory(inv);
    }

    public void openSettings() {
        if (!party.isLeader(viewer.getUniqueId())) {
            viewer.sendMessage(Utils.color("&cOnly the party leader can access settings!"));
            return;
        }

        Inventory inv = Bukkit.createInventory(null, 27,
                Utils.color("&8» &bParty Settings"));

        // パーティー公開設定
        inv.setItem(11, Utils.createItem(
                Material.IRON_DOOR,
                "&b&lParty Privacy",
                "",
                "&7Current: " + (party.isOpen() ? "&aOpen" : "&cClosed"),
                "",
                "&eClick to toggle!"
        ));

        // サイズ制限設定
        inv.setItem(13, Utils.createItem(
                Material.PLAYER_HEAD,
                "&b&lParty Size",
                "",
                "&7Current: &b" + party.getMaxSize(),
                "",
                "&eClick to change!"
        ));

        // その他の設定...

        viewer.openInventory(inv);
    }
}