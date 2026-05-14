package domain;

import domain.piece.Knight;
import domain.piece.Piece;
import domain.piece.PieceColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KnightTests {

    @Test
    public void isValidMove_Knight_Lmov1_returnTrue() {
        Piece knight = new Knight(PieceColor.WHITE);

        Location start = new Location(3, 3);
        Location chosen = new Location(5, 2);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov2_returnTrue() {
        Piece knight = new Knight(PieceColor.BLACK);

        Location start = new Location(3, 3);
        Location chosen = new Location(5, 4);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov3_returnTrue() {
        Piece knight = new Knight(PieceColor.WHITE);

        Location start = new Location(3, 3);
        Location chosen = new Location(4, 1);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov4_returnTrue() {
        Piece knight = new Knight(PieceColor.WHITE);

        Location start = new Location(3, 3);
        Location chosen = new Location(4, 5);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov5_returnTrue() {
        Piece knight = new Knight(PieceColor.WHITE);

        Location start = new Location(3, 3);
        Location chosen = new Location(1, 2);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

}
