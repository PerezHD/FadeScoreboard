/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fadecloud.scoreboard.utils;

import org.arkhamnetwork.rankup.RankupPlugin;
import org.arkhamnetwork.rankup.manager.PlayerManager;
import org.arkhamnetwork.rankup.struct.Rank;
import org.bukkit.entity.Player;

/**
 *
 * @author devan_000
 */
public class RankupUtils {

    private static RankupPlugin plugin = RankupPlugin.get();

    public static Rank getPlayerNextRank(Player player) {
        if (!plugin.rankMap.containsKey(PlayerManager.getPlayerRankID(player))) {
            Rank next = plugin.rankMap.get(1);
            return next;
        }

        if (PlayerManager.getPlayerRankID(player) == plugin.rankMap.size()) {
            return null;
        }
        
        return plugin.rankMap.get(PlayerManager.getPlayerRankID(player) + 1);
    }

}
