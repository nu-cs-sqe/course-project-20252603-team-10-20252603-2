package ui;

import constants.Color;
import domain.Board;
import domain.GameManager;
import domain.Location;
import domain.Player;
import domain.piece.Piece;

public class BoardController {
    private BoardView boardView;
    private GameStatsView gameStatsView;
    private GameManager gameManager;
    private Location selectedLocation;

    public BoardController(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    void setBoardView(BoardView boardView) {
        this.boardView = boardView;
    }

    void setGameStatsView(GameStatsView gameStatsView) { this.gameStatsView = gameStatsView; }

    public boolean handleSquareClick(Location location) {
        // TODO
        System.out.println("TEST: Square clicked at " + location.getX() + ", " + location.getY());
        if (selectedLocation == null) {
            return handleFirstClick(location);
        }
        handleSecondClick(location);
        return false;

    }

    public boolean handleFirstClick(Location location) {
        System.out.println("TEST: Square first click at " + location.getX() + ", " + location.getY());
        Board board = gameManager.getBoard();
        if (!board.isPieceHere(location)) {
            return false;
        }

        Piece selectedPiece = board.getPiece(location);
        Player currentPlayer = gameManager.getCurrentPlayer();

        if (selectedPiece.getColor() != currentPlayer.getPlayerColor()) {
            return false;
        }

        selectedLocation = location;
        return true;

    }

    public void handleSecondClick(Location endLocation) {
        System.out.println("TEST: Square second click at " + endLocation.getX() + ", " + endLocation.getY());
        Location startLocation = selectedLocation;
        selectedLocation = null;

        boolean movePiece = gameManager.movePiece(startLocation, endLocation);

        if (movePiece) {
            gameStatsView.updateCurrentPlayer(gameManager.getCurrentPlayer().getPlayerName());
            gameStatsView.updatePoints(gameManager.getWhitePlayer(), gameManager.getBlackPlayer());
        }

        if (boardView != null) {
            boardView.repaint();
        }

    }

    public Piece[][] getBoardSnapshot() {
        Board board = gameManager.getBoard();
        return board.getSnapshot();
    }
}
