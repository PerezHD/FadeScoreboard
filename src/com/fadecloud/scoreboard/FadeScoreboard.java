/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fadecloud.scoreboard;

import com.fadecloud.scoreboard.listener.PlayerListener;
import com.fadecloud.scoreboard.struct.PrisonPlayer;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import lombok.Getter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author devan_000
 */
public class FadeScoreboard extends JavaPlugin {

      @Getter
      private static FadeScoreboard plugin;
      @Getter
      private static Economy econ = null;
      @Getter
      private ConcurrentHashMap<UUID, PrisonPlayer> players = new ConcurrentHashMap<>();

      @Override
      public void onEnable() {
            plugin = this;

            log("==[ Plugin version " + getDescription().getVersion() + " starting ]==");

            if (!setupEconomy()) {
                  log("Plugin will not work as no economy plugin found.");
                  return;
            }

            getServer().getPluginManager().registerEvents(new PlayerListener(), this);

            final Player[] onlinePlayers = getServer().getOnlinePlayers();
            for (Player player : onlinePlayers) {
                  players.put(player.getUniqueId(), new PrisonPlayer(player));
                  player.setScoreboard(getServer().getScoreboardManager().getNewScoreboard());
            }

            getServer().getScheduler().runTaskTimerAsynchronously(plugin, new Runnable() {
                  public synchronized void run() {
                        players.values().stream().forEach((player) -> {
                              try {
                                    player.updateScoreboard();
                              } catch (Exception ex) {
                                    ex.printStackTrace();
                              }
                        });
                  }
            }, 40L, 40L);

            log("==[ Plugin version " + getDescription().getVersion() + " started ]==");
      }

      @Override
      public void onDisable() {
            log("==[ Plugin version " + getDescription().getVersion() + " stopping ]==");

            log("==[ Plugin version " + getDescription().getVersion() + " shutdown ]==");
      }

      private void log(String message) {
            getLogger().info(message);
      }

      private boolean setupEconomy() {
            RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
            if (rsp == null) {
                  return false;
            }
            econ = rsp.getProvider();
            return econ != null;
      }

}
