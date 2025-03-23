package com.creamint.practice.party;

import com.creamint.practice.PracticePvP;
import org.bukkit.entity.Player;

import java.util.*;

public class PartyManager {
    private final PracticePvP plugin;
    private final Map<UUID, Party> parties;
    private final Map<UUID, Party> playerParties;

    public PartyManager(PracticePvP plugin) {
        this.plugin = plugin;
        this.parties = new HashMap<>();
        this.playerParties = new HashMap<>();
    }

    public void createParty(Player leader) {
        Party party = new Party(plugin, leader);
        parties.put(leader.getUniqueId(), party);
        playerParties.put(leader.getUniqueId(), party);
        leader.sendMessage("&aParty created!");
    }

    public void invitePlayer(Player leader, Player target) {
        Party party = parties.get(leader.getUniqueId());
        if (party == null) {
            leader.sendMessage("&cYou are not the leader of a party!");
            return;
        }

        if (party.isMember(target.getUniqueId())) {
            leader.sendMessage("&cThat player is already in your party!");
            return;
        }

        party.invitePlayer(target);
        target.sendMessage("&aYou have been invited to join " + leader.getName() + "'s party!");
    }

    public void joinParty(Player player, Party party) {
        if (playerParties.containsKey(player.getUniqueId())) {
            player.sendMessage("&cYou are already in a party!");
            return;
        }

        party.addMember(player);
        playerParties.put(player.getUniqueId(), party);
    }

    public void leaveParty(Player player) {
        Party party = playerParties.get(player.getUniqueId());
        if (party == null) {
            player.sendMessage("&cYou are not in a party!");
            return;
        }

        party.removeMember(player);
        playerParties.remove(player.getUniqueId());
    }

    public Party getParty(UUID leaderId) {
        return parties.get(leaderId);
    }

    public Party getPlayerParty(UUID playerId) {
        return playerParties.get(playerId);
    }
}