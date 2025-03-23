package com.creamint.practice.party;

import com.creamint.practice.PracticePvP;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.*;

public class Party {
    private final PracticePvP plugin;
    private final UUID leaderId;
    private final Set<UUID> members;
    private final Map<UUID, Long> invites;

    public Party(PracticePvP plugin, Player leader) {
        this.plugin = plugin;
        this.leaderId = leader.getUniqueId();
        this.members = new HashSet<>();
        this.invites = new HashMap<>();
        this.members.add(leaderId);
    }

    public void broadcast(String message) {
        getOnlineMembers().forEach(player ->
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', message)));
    }

    public void addMember(Player player) {
        members.add(player.getUniqueId());
        broadcast("&b" + player.getName() + " &7has joined the party!");
    }

    public void removeMember(Player player) {
        members.remove(player.getUniqueId());
        broadcast("&b" + player.getName() + " &7has left the party!");
    }

    public List<Player> getOnlineMembers() {
        List<Player> onlinePlayers = new ArrayList<>();
        for (UUID uuid : members) {
            Player player = plugin.getServer().getPlayer(uuid);
            if (player != null && player.isOnline()) {
                onlinePlayers.add(player);
            }
        }
        return onlinePlayers;
    }

    public boolean isLeader(UUID uuid) {
        return uuid.equals(leaderId);
    }

    public boolean isMember(UUID uuid) {
        return members.contains(uuid);
    }

    public Player getLeader() {
        return plugin.getServer().getPlayer(leaderId);
    }
}