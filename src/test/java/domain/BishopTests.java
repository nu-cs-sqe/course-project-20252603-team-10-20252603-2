package domain;

import domain.piece.Bishop;
import domain.piece.Piece;
import domain.piece.PieceColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BishopTests {

    @Test
    public void isValidMove_Bishop_sameSquare_returnFalse() {
        Piece bishop = new Bishop(PieceColor.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(0, 0);

        Board board = new Board(false);

        boolean result = bishop.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Bishop_VerticalMove_returnFalse() {
        Piece bishop = new Bishop(PieceColor.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(3, 0);

        Board board = new Board(false);

        boolean result = bishop.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Bishop_OneDiagonalUpAndRight_returnTrue() {
        Piece bishop = new Bishop(PieceColor.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(1, 1);

        Board board = new Board(false);

        boolean result = bishop.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Bishop_OneDiagonalUpAndLeft_returnTrue() {
        Piece bishop = new Bishop(PieceColor.WHITE);

        Location start = new Location(0, 7);
        Location chosen = new Location(1, 6);

        Board board = new Board(false);

        boolean result = bishop.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Bishop_OneDiagonalDownAndRight_returnTrue() {
        Piece bishop = new Bishop(PieceColor.WHITE);

        Location start = new Location(7, 0);
        Location chosen = new Location(6, 1);

        Board board = new Board(false);

        boolean result = bishop.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Bishop_OneDiagonalDownAndLeft_returnTrue() {
        Piece bishop = new Bishop(PieceColor.WHITE);

        Location start = new Location(7, 7);
        Location chosen = new Location(6, 6);

        Board board = new Board(false);

        boolean result = bishop.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Bishop_SevenDiagonalUpAndRight_returnTrue() {
        Piece bishop = new Bishop(PieceColor.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(7, 7);

        Board board = new Board(false);

        boolean result = bishop.isValidMove(start, chosen, board);

        assertTrue(result);
    }
}