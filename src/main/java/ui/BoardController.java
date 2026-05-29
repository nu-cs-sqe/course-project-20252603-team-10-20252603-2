package ui;

import domain.Board;
import domain.Location;
import domain.piece.Piece;

public class BoardController {
    private BoardView boardView;
    private Board board;

    public BoardController() {
        this.board = new Board(true);
    }

    void setBoardView(BoardView boardView) {
        this.boardView = boardView;
    }

    public void handleSquareClick(Location location) {
        // TODO
        System.out.println("TEST: Square clicked at " + location.getX() + ", " + location.getY());
    }

    public Piece[][] getBoardSnapshot() {
        return this.board.getSnapshot();
    }
}
