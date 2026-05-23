package domain;

import constants.Color;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private List<Player> players = new ArrayList<>();
    private boolean isGameRunning = false;
    private Player whitePlayer;
    private Player blackPlayer;
    private Player currentPlayer;
    private boolean isGameADraw;
    private int consecutiveDrawMoves = 0;

    public void incrementDrawCounter() {
        this.consecutiveDrawMoves++;
    }

    public boolean isGameADraw() {
        System.out.println("MOVES: " +consecutiveDrawMoves);
        return consecutiveDrawMoves >= 50;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void start() throws IllegalStateException {
        if (players.size() < 2) {
            throw new IllegalStateException("Not enough players in the game.");
        } else if (players.size() > 2) {
            throw new IllegalStateException("Maximum number of players allowed is 2.");
        }

        if (players.get(0).getPlayerColor() == players.get(1).getPlayerColor()) {
            throw new IllegalStateException("Players cannot have the same color.");
        }
        assignPlayers(players.get(0), players.get(1));
        this.isGameRunning = true;
    }

    public void assignPlayers(Player player1, Player player2) {
        if (player1.getPlayerColor() == Color.WHITE) {
            whitePlayer = player1;
            blackPlayer = player2;
        } else {
            blackPlayer = player1;
            whitePlayer = player2;
        }
        currentPlayer = whitePlayer;
    }

    public void changeTurns() throws IllegalStateException {
        if (!isGameADraw()) {
            currentPlayer = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;
        } else {
            System.out.println("HERE");
            throw new IllegalStateException("Game is a draw.");
        }
    }

    public boolean isGameRunning() {
        return isGameRunning;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
