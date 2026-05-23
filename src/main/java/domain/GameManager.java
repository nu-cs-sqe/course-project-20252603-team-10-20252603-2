package domain;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private List<Player> players = new ArrayList<>();
    private boolean isGameRunning = false;

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void start() throws IllegalStateException {
        if (players.size() < 2) {
            throw new IllegalStateException("Not enough players in the game.");
        }
    }

    public boolean isGameRunning() {
        return isGameRunning;
    }
}
