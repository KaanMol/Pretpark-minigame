package com.a2.essteling.ScoreBoard;

import com.a2.essteling.HomeListener;
import com.a2.essteling.Pass.Player;
import com.a2.essteling.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class PlayerList {
    public static LinkedList<Player> players = new LinkedList<>();

    public static ArrayList<HomeListener> listeners = new ArrayList<>();

    public static void addPlayer(Player player){
        players.add(player);
        listeners.forEach(listener->{
            listener.onPlayerAdded();
        });
    }

    public static void addListener(HomeListener listener){
        listeners.add(listener);
    }

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

    public static void resetColor(){
        players.forEach(player -> {
            player.setColor(R.color.Red);
        });
    }

    //create a test list for the scoreboard
    public static void testPlayers() {
        if(players.size() < 4) {
            addPlayer(new Player("Momin", testHistories()));
            addPlayer(new Player("Coen", testHistories()));
            addPlayer(new Player("Lucas", testHistories()));
            addPlayer(new Player("Kaan", testHistories()));
            addPlayer(new Player("Koen", testHistories()));
        }
    }

    public static LinkedList<History> testHistories() {
        int i = (int) (Math.random()*100);
        LinkedList<History> histories = new LinkedList<>();
        histories.add(new History("game " + i % 4, "here", "14:20", i % 7 + 1, "0000"));
        i++;
        histories.add(new History("game " + i % 4, "there", "14:20", i % 7 + 1, "0000"));
        i++;
        histories.add(new History("game " + i % 4, "somewhere", "14:20", i % 7 + 1, "0000"));
        i++;
        histories.add(new History("game " + i % 4, "otherwhere", "14:20", i % 7 + 1, "0000"));
        i++;
        histories.add(new History("game " + i % 4, "softwhere", "14:20", i % 7 + 1, "0000"));
        i++;
        histories.add(new History("game " + i % 4, "hardwhere", "14:20", i % 7 + 1, "0000"));
        i++;
        histories.add(new History("game " + i % 4, "entrance", "14:20", i % 7 + 1, "0000"));
        i++;
        histories.add(new History("game " + i % 4, "ip addres", "14:20", i % 7 + 1, "0000"));
        i++;
        histories.add(new History("game " + i % 4, "test", "14:20", i % 7 + 1, "0000"));
        i++;
        return histories;
    }
}
