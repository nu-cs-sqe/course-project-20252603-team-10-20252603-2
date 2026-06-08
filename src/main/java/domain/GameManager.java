package domain;

import constants.Color;
import domain.piece.King;
import domain.piece.Piece;
import domain.piece.PieceType;

import java.util.MissingResourceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


public class GameManager {
    private ResourceBundle messages;

    private List<Player> players = new ArrayList<>();
    private boolean isGameRunning = false;
    private Player whitePlayer;
    private Player blackPlayer;
    private Player currentPlayer;
    private int consecutiveDrawMoves = 0;
    private Board board;

    public GameManager() {
        this.board = null;
        this.whitePlayer = null;
        this.blackPlayer = null;
        this.currentPlayer = null;
    }

    public void incrementDrawCounter() {
        this.consecutiveDrawMoves++;
    }

    public boolean isGameADraw() {
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
        board = new Board(true);
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
            throw new IllegalStateException("Game is a draw.");
        }
    }

    public boolean isGameRunning() {
        return isGameRunning;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Board getBoard() {
        if (this.board == null) {
            return null;
        }
        return new Board(this.board);
    }

    public void setBoard(Board board) {
        if (board == null) {
            this.board = null;
        } else {
            this.board = new Board(board);
        }
    }

    public boolean isGameOver() {
        if (currentPlayer == null || board == null) {
            return false;
        }
        return isGameADraw() || isCheckmate() || isStalemate();
    }

    public boolean isCheckmate() {
        if (currentPlayer == null || board == null) {
            return false;
        };

        Location kingLocation = findKingLocation(currentPlayer.getPlayerColor());
        if (kingLocation == null) return false;

        King alliedKing = (King) board.getPiece(kingLocation);
        boolean hasValidMoves = !board.getValidPiecesByColor(currentPlayer.getPlayerColor()).isEmpty();

        return alliedKing.isInCheck(kingLocation, board) && !hasValidMoves;
    }

    public boolean isStalemate() {
        if (currentPlayer == null || board == null) return false;

        Location kingLocation = findKingLocation(currentPlayer.getPlayerColor());
        if (kingLocation == null) return false;

        King alliedKing = (King) board.getPiece(kingLocation);
        boolean hasValidMoves = !board.getValidPiecesByColor(currentPlayer.getPlayerColor()).isEmpty();

        return !alliedKing.isInCheck(kingLocation, board) && !hasValidMoves;
    }

    private Location findKingLocation(Color playerColor) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Location loc = new Location(i, j);
                if (board.isPieceHere(loc)) {
                    Piece p = board.getPiece(loc);
                    if (p.getColor() == playerColor && p.getType() == PieceType.KING) {
                        return loc;
                    }
                }
            }
        }
        return null;
    }

    public void setLocale(Locale locale) {
        messages = ResourceBundle.getBundle("messages", locale);
    }

    public String getMessage(String key) {
        if (messages == null) {
            setLocale(Locale.ENGLISH);
        }
        try {
            return messages.getString(key);
        } catch (MissingResourceException e) {
            return key;
        }
    }
}
