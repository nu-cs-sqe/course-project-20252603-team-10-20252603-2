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

    public BoardController() {
        this.gameManager = new GameManager();
        gameManager.addPlayer(new Player(Color.WHITE));
        gameManager.addPlayer(new Player(Color.BLACK));
        gameManager.start();
    }

    void setBoardView(BoardView boardView) {
        this.boardView = boardView;
    }

    void setGameStatsView(GameStatsView gameStatsView) { this.gameStatsView = gameStatsView; }

    public void handleSquareClick(Location location) {
        // TODO
        System.out.println("TEST: Square clicked at " + location.getX() + ", " + location.getY());
        if (boardView == null) {
            return;
        }
        if (selectedLocation == null) {
            handleFirstClick(location);
        } else {
            handleSecondClick(location);
        }

    }

    public void handleFirstClick(Location location) {
        System.out.println("TEST: Square first click at " + location.getX() + ", " + location.getY());
        Board board = gameManager.getBoard();
        if (!board.isPieceHere(location)) {
            return;
        }

        Piece selectedPiece = board.getPiece(location);
        Player currentPlayer = gameManager.getCurrentPlayer();

        if (selectedPiece.getColor() != currentPlayer.getPlayerColor()) {
            return;
        }

        selectedLocation = location;

    }

    public void handleSecondClick(Location endLocation) {
        System.out.println("TEST: Square second click at " + endLocation.getX() + ", " + endLocation.getY());
        Location startLocation = selectedLocation;
        selectedLocation = null;

        boolean movePiece = gameManager.movePiece(startLocation, endLocation);

        if (movePiece) {
            gameStatsView.updateCurrentPlayer(gameManager.getCurrentPlayer().getPlayerColor().toString());
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
