/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fadecloud.scoreboard.listener;

import com.fadecloud.scoreboard.FadeScoreboard;
import com.fadecloud.scoreboard.struct.PrisonPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 *
 * @author devan_000
 */
public class PlayerListener implements Listener {

    private FadeScoreboard plugin = FadeScoreboard.getPlugin();

    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin(PlayerJoinEvent event) {
        plugin.getPlayers().put(event.getPlayer().getUniqueId(), new PrisonPlayer(event.getPlayer()));
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
        event.getPlayer().setScoreboard(plugin.getServer().getScoreboardManager().getNewScoreboard());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onKick(PlayerKickEvent event) {
        plugin.getPlayers().remove(event.getPlayer().getUniqueId());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onQuit(PlayerQuitEvent event) {
        plugin.getPlayers().remove(event.getPlayer().getUniqueId());
    }
}
