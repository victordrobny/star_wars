package net;

import javafx.util.Pair;

import java.net.InetAddress;
import java.util.LinkedList;
import java.util.List;

public class Game {
    //EL: private fields
    private List<Pair<InetAddress, Integer>> players = new LinkedList<>();

    private Boolean isStarted = false;
    public Integer getNumberOfPlayers() {
        return players.size();
    }

    public List<Pair<InetAddress, Integer>> getPlayers() {
        return players;
    }

    public void setPlayers(List<Pair<InetAddress, Integer>> players) {
        this.players = players;
    }

    public Boolean getIsStarted() {
        return isStarted;
    }

    public void setIsStarted(Boolean isStarted) {
        this.isStarted = isStarted;
    }

    public void addPlayer(Pair<InetAddress, Integer> player) {
        players.add(player);
    }

    public boolean contains(Pair<InetAddress, Integer> player) {
        for (Pair<InetAddress, Integer> p : players ) {
            if (p.equals(player))
                return true;
        }
        return false;
    }
}
