package domain;

import constants.Color;
import domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private List<Player> players = new ArrayList<>();
    private boolean isGameRunning = false;
    private Player whitePlayer;
    private Player blackPlayer;
    private Player currentPlayer;
    private int consecutiveDrawMoves = 0;
    private Board board;

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

        if (isGameADraw()) {
            return true;
        }

        Color playerColor = currentPlayer.getPlayerColor();
        boolean hasValidMoves = !board.getValidPiecesByColor(playerColor).isEmpty();

        if (currentPlayer.isInCheck() && !hasValidMoves) {
            return true;
        }

        return false;
    }

    public boolean movePiece(Location start, Location end) {
        if (!board.isPieceHere(start)) {
            return false;
        }

        Piece pieceToMove = this.board.getPiece(start);
        Player currentPlayer = this.currentPlayer;

        if (pieceToMove.getColor() != currentPlayer.getPlayerColor()) {
            return false;
        }

        if (pieceToMove.isValidMove(start, end, board)) {
            if (board.isPieceHere(end)) {
                Piece capturedPiece = board.getPiece(end);
                this.currentPlayer.incrementPoints(capturedPiece.getType());
            }
            board.setPiece(end, pieceToMove);
            board.removePiece(start);
            this.changeTurns();
            return true;
        }

        return false;
    }
}
