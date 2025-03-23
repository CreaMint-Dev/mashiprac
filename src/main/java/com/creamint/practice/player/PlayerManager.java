package com.creamint.practice.player;

import com.creamint.practice.PracticePvP;
import org.bukkit.entity.Player;

import java.util.*;

public class PlayerManager {
    private final PracticePvP plugin;
    private final Map<UUID, PlayerData> playerDataMap;

    public PlayerManager(PracticePvP plugin) {
        this.plugin = plugin;
        this.playerDataMap = new HashMap<>();
    }

    public PlayerData getPlayerData(UUID playerId) {
        return playerDataMap.get(playerId);
    }

    public void setState(UUID playerId, PlayerState state) {
        PlayerData playerData = playerDataMap.get(playerId);
        if (playerData != null) {
            playerData.setState(state);
        }
    }

    public void addPlayer(Player player) {
        playerDataMap.put(player.getUniqueId(), new PlayerData(player));
    }

    public void removePlayer(Player player) {
        playerDataMap.remove(player.getUniqueId());
    }
}