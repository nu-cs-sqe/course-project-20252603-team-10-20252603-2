package domain;

import domain.piece.Rook;
import domain.piece.Piece;
import domain.piece.PieceColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RookTests {

    @Test
    public void isValidMove_Rook_sameSquare_returnFalse() {
        Piece rook = new Rook(PieceColor.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(0, 0);

        Board board = new Board(false);

        boolean result = rook.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Rook_oneRight_returnTrue() {
        Piece rook = new Rook(PieceColor.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(0, 1);

        Board board = new Board(false);

        boolean result = rook.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Rook_sevenRight_returnTrue() {
        Piece rook = new Rook(PieceColor.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(0, 7);

        Board board = new Board(false);

        boolean result = rook.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Rook_oneLeft_returnTrue() {
        Piece rook = new Rook(PieceColor.WHITE);

        Location start = new Location(0, 7);
        Location chosen = new Location(0, 6);

        Board board = new Board(false);

        boolean result = rook.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Rook_sevenLeft_returnTrue() {
        Piece rook = new Rook(PieceColor.WHITE);

        Location start = new Location(0, 7);
        Location chosen = new Location(0, 0);

        Board board = new Board(false);

        boolean result = rook.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Rook_sevenDown_returnTrue() {
        Piece rook = new Rook(PieceColor.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(7, 0);

        Board board = new Board(false);

        boolean result = rook.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Rook_diagonal_returnFalse() {
        Piece rook = new Rook(PieceColor.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(3, 3);

        Board board = new Board(false);

        boolean result = rook.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Rook_blockedHorizontal_returnFalse() {
        Piece rook = new Rook(PieceColor.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(0, 3);

        Board board = new Board(false);

        Piece blockingPiece = new Rook(PieceColor.BLACK);
        board.setPiece(new Location(0, 1), blockingPiece);

        boolean result = rook.isValidMove(start, chosen, board);

        assertFalse(result);
    }
    @Test
    public void isValidMove_Rook_blockedVertical_returnFalse() {
        Piece rook = new Rook(PieceColor.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(3, 0);

        Board board = new Board(false);

        Piece blockingPiece = new Rook(PieceColor.BLACK);
        board.setPiece(new Location(1, 0), blockingPiece);

        boolean result = rook.isValidMove(start, chosen, board);

        assertFalse(result);
    }
}
