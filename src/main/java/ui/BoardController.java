package ui;

import domain.Board;
import domain.Location;
import domain.piece.Piece;

public class BoardController {
    private BoardView boardView;
    private Board board;
    private Location selectedLocation;

    public BoardController() {
        this.board = new Board(true);
    }

    void setBoardView(BoardView boardView) {
        this.boardView = boardView;
    }

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
        selectedLocation = location;
    }

    public void handleSecondClick(Location location) {
        selectedLocation = null;
    }

    public Piece[][] getBoardSnapshot() {
        return this.board.getSnapshot();
    }
}
