/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fadecloud.scoreboard.scoreboard;

import com.fadecloud.scoreboard.FadeScoreboard;
import com.fadecloud.scoreboard.utils.MessageUtils;
import java.util.concurrent.ConcurrentHashMap;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;

/**
 *
 * @author devan_000
 */
public class PrisonScoreboard {

    private Scoreboard scoreboard;
    private ConcurrentHashMap<Integer, String> storedLines = new ConcurrentHashMap<>();

    public PrisonScoreboard() {
        scoreboard = FadeScoreboard.getPlugin().getServer().getScoreboardManager()
                .getNewScoreboard();
        scoreboard.registerNewObjective("Arcade", "Arcade");
    }

    public boolean hasLine(int lineID) {
        return storedLines.get(lineID) != null;
    }

    public void setSlot(DisplaySlot slot) {
        scoreboard.getObjective("Arcade").setDisplaySlot(slot);
    }

    public void setName(String name) {
        scoreboard.getObjective("Arcade").setDisplayName(
                MessageUtils.translateToColorCode(name));
    }

    public String getName() {
        return scoreboard.getObjective("Arcade").getDisplayName();
    }

    public void addLine(int id, String name, int scoreValue) {
        storedLines.put(id, name);
        scoreboard.getObjective("Arcade")
                .getScore(MessageUtils.translateToColorCode(name))
                .setScore(scoreValue);
    }

    public void removeLine(int id) {
        scoreboard.resetScores(storedLines.get(id));
        storedLines.remove(id);
    }

    public void updateLine(int id, String newName, int newScoreValue) {
        scoreboard.resetScores(storedLines.get(id));
        addLine(id, newName, newScoreValue);
    }

    public void setForPlayer(Player player) {
        player.setScoreboard(scoreboard);
    }
}
