package com.a2.essteling.ScoreBoard;

import com.a2.essteling.Pass.Player;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class PlayerList {
    public static LinkedList<Player> players = new LinkedList<>();

    public static int totalPoints() {
        if(players.isEmpty()){
            return 0;
        }

        AtomicInteger totalPoints = new AtomicInteger();


        players.forEach(player -> {
            player.getGameHistorie().forEach(historie -> {
                totalPoints.addAndGet(historie.getPoints());
            });
        });
        return totalPoints.get();

    }
}
