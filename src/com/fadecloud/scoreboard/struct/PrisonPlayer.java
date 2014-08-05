/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fadecloud.scoreboard.struct;

import com.fadecloud.dropparty.FadeDropParty;
import com.fadecloud.scoreboard.FadeScoreboard;
import com.fadecloud.scoreboard.scoreboard.PrisonScoreboard;
import com.fadecloud.scoreboard.utils.NumberUtils;
import com.fadecloud.scoreboard.utils.RankupUtils;
import java.util.UUID;
import lombok.Getter;
import net.minecraft.server.v1_7_R4.MinecraftServer;
import org.arkhamnetwork.rankup.struct.Rank;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;

/**
 *
 * @author devan_000
 */
public class PrisonPlayer {

      @Getter
      private Player bukkitPlayer;
      @Getter
      private UUID uuid;
      @Getter
      private PrisonScoreboard currentScoreboard;

      public PrisonPlayer(Player bukkitPlayer) {
            this.bukkitPlayer = bukkitPlayer;
            this.uuid = bukkitPlayer.getUniqueId();
            this.currentScoreboard = new PrisonScoreboard();
            this.currentScoreboard.setSlot(DisplaySlot.SIDEBAR);
            this.currentScoreboard.setName(ChatColor.AQUA + "" + ChatColor.BOLD + "Fade" + ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Prison");
      }

      public synchronized void updateScoreboard() {
            if (bukkitPlayer.getScoreboard().getObjective("Arcade") == null) {
                  currentScoreboard.setForPlayer(bukkitPlayer);
            }

            if (!currentScoreboard.hasLine(12)) {
                  currentScoreboard.addLine(12, ChatColor.AQUA + "" + ChatColor.BOLD + "Online", 12);
            }

            if (!currentScoreboard.hasLine(11)) {
                  currentScoreboard.addLine(11, ChatColor.WHITE + String.valueOf(FadeScoreboard.getPlugin().getServer().getOnlinePlayers().length) + "    ", 11);
            } else {
                  currentScoreboard.updateLine(11, ChatColor.WHITE + String.valueOf(FadeScoreboard.getPlugin().getServer().getOnlinePlayers().length) + "    ", 11);
            }

            if (!currentScoreboard.hasLine(10)) {
                  currentScoreboard.addLine(10, ChatColor.AQUA + "" + ChatColor.BOLD + "Balance", 10);
            }

            if (!currentScoreboard.hasLine(9)) {
                  currentScoreboard.addLine(9, ChatColor.WHITE + NumberUtils.formatMoney((long) FadeScoreboard.getEcon().getBalance(bukkitPlayer.getName())) + ChatColor.AQUA, 9);
            } else {
                  currentScoreboard.updateLine(9, ChatColor.WHITE + NumberUtils.formatMoney((long) FadeScoreboard.getEcon().getBalance(bukkitPlayer.getName())) + ChatColor.AQUA, 9);
            }

            if (!currentScoreboard.hasLine(8)) {
                  currentScoreboard.addLine(8, ChatColor.AQUA + "" + ChatColor.BOLD + "Votes for DP", 8);
            }

            if (!currentScoreboard.hasLine(7)) {
                  currentScoreboard.addLine(7, ChatColor.WHITE + String.valueOf(FadeDropParty.getCurrentVotes()) + "   ", 7);
            } else {
                  currentScoreboard.updateLine(7, ChatColor.WHITE + String.valueOf(FadeDropParty.getCurrentVotes()) + "   ", 7);
            }

            if (!currentScoreboard.hasLine(6)) {
                  currentScoreboard.addLine(6, ChatColor.AQUA + "" + ChatColor.BOLD + "Next Rank", 6);
            }

            Rank nextRank = RankupUtils.getPlayerNextRank(bukkitPlayer);

            String name;
            if (nextRank != null) {
                  name = nextRank.getGroup();
            } else {
                  name = "None";
            }

            if (!currentScoreboard.hasLine(5)) {
                  currentScoreboard.addLine(5, ChatColor.WHITE + name, 5);
            } else {
                  currentScoreboard.updateLine(5, ChatColor.WHITE + name, 5);
            }

            if (!currentScoreboard.hasLine(4)) {
                  currentScoreboard.addLine(4, ChatColor.AQUA + "" + ChatColor.BOLD + "Rank Cost", 4);
            }

            long cost = 0;

            if (nextRank != null) {
                  cost = (long) nextRank.getCost();
            }

            if (!currentScoreboard.hasLine(3)) {
                  currentScoreboard.addLine(3, ChatColor.WHITE + NumberUtils.formatMoney(cost) + " ", 3);
            } else {
                  currentScoreboard.updateLine(3, ChatColor.WHITE + NumberUtils.formatMoney(cost) + " ", 3);
            }

            if (!currentScoreboard.hasLine(2)) {
                  currentScoreboard.addLine(2, ChatColor.AQUA + "" + ChatColor.BOLD + "Ticks", 2);
            }

            if (!currentScoreboard.hasLine(1)) {
                  currentScoreboard.addLine(1, ChatColor.WHITE + String.valueOf(Math.min(Math.round(MinecraftServer.getServer().recentTps[0] * 100.00) / 100.00, 20.00)) + ChatColor.BLUE + " ", 1);
            } else {
                  currentScoreboard.updateLine(1, ChatColor.WHITE + String.valueOf(Math.min(Math.round(MinecraftServer.getServer().recentTps[0] * 100.00) / 100.00, 20.00)) + ChatColor.BLUE + " ", 1);
            }

      }
}
