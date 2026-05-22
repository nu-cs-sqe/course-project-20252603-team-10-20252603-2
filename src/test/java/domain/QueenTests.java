package domain;

import domain.piece.Pawn;
import domain.piece.Queen;
import domain.piece.Piece;
import domain.piece.PieceColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueenTests {

    @Test
    public void isValidMove_Queen_xMovPos_returnTrue() {
        Piece queen = new Queen(PieceColor.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(7, 0);

        Board board = new Board(false);

        boolean result = queen.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_QueenBlack_xMovPos_returnTrue() {
        Piece queen = new Queen(PieceColor.BLACK);

        Location start = new Location(0, 0);
        Location chosen = new Location(7, 0);

        Board board = new Board(false);

        boolean result = queen.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Queen_xMovNeg_returnTrue() {
        Piece queen = new Queen(PieceColor.BLACK);

        Location start = new Location(7, 7);
        Location chosen = new Location(0, 7);

        Board board = new Board(false);

        boolean result = queen.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Queen_yMovNeg_returnTrue() {
        Piece queen = new Queen(PieceColor.BLACK);

        Location start = new Location(7, 7);
        Location chosen = new Location(7, 0);

        Board board = new Board(false);

        boolean result = queen.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Queen_yMovPos_returnTrue() {
        Piece queen = new Queen(PieceColor.BLACK);

        Location start = new Location(0, 0);
        Location chosen = new Location(0, 7);

        Board board = new Board(false);

        boolean result = queen.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Queen_diagonalPos_returnTrue() {
        Piece queen = new Queen(PieceColor.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(7, 7);

        Board board = new Board(false);

        boolean result = queen.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Queen_diagonalNeg_returnTrue() {
        Piece queen = new Queen(PieceColor.WHITE);

        Location start = new Location(7, 7);
        Location chosen = new Location(0, 0);

        Board board = new Board(false);

        boolean result = queen.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Queen_xMovPosFriendlyBlock_returnFalse() {
        Piece queen = new Queen(PieceColor.BLACK);
        Piece blocker = new Pawn(PieceColor.BLACK);

        Location start = new Location(0, 0);
        Location chosen = new Location(7, 0);
        Location blockerLocation = new Location(3, 0);

        Board board = new Board(false);
        board.setPiece(blockerLocation, blocker);

        boolean result = queen.isValidMove(start, chosen, board);

        assertFalse(result);
    }


}
