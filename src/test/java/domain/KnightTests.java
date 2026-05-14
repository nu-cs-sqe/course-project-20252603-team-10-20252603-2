package domain;

import domain.piece.Knight;
import domain.piece.Pawn;
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

    @Test
    public void isValidMove_Knight_Lmov6_returnTrue() {
        Piece knight = new Knight(PieceColor.WHITE);

        Location start = new Location(3, 3);
        Location chosen = new Location(1, 4);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov7_returnTrue() {
        Piece knight = new Knight(PieceColor.WHITE);

        Location start = new Location(3, 3);
        Location chosen = new Location(2, 1);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov8_returnTrue() {
        Piece knight = new Knight(PieceColor.WHITE);

        Location start = new Location(3, 3);
        Location chosen = new Location(2, 5);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_starting00_returnTrue() {
        Piece knight = new Knight(PieceColor.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(2, 1);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_starting77_returnTrue() {
        Piece knight = new Knight(PieceColor.WHITE);

        Location start = new Location(7, 7);
        Location chosen = new Location(6, 5);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_ending00_returnTrue() {
        Piece knight = new Knight(PieceColor.WHITE);

        Location start = new Location(2, 1);
        Location chosen = new Location(0, 0);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_ending77_returnTrue() {
        Piece knight = new Knight(PieceColor.WHITE);

        Location start = new Location(6, 5);
        Location chosen = new Location(7, 7);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov1blockedPathVert1_returnTrue() {
        Piece knight = new Knight(PieceColor.BLACK);

        Location start = new Location(3, 3);
        Location blocker = new Location(4, 3);
        Location chosen = new Location(5, 2);

        Board board = new Board(false);

        Piece pawnBlocker = new Pawn(PieceColor.WHITE);
        board.setPiece(blocker, pawnBlocker);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov1blockedEndEnemy_returnTrue() {
        Piece knight = new Knight(PieceColor.BLACK);

        Location start = new Location(3, 3);
        Location chosen = new Location(5, 2);

        Board board = new Board(false);

        Piece pawnBlocker = new Pawn(PieceColor.WHITE);
        board.setPiece(chosen, pawnBlocker);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov1blockedEndFriendly_returnFalse() {
        Piece knight = new Knight(PieceColor.BLACK);

        Location start = new Location(3, 3);
        Location chosen = new Location(5, 2);

        Board board = new Board(false);

        Piece pawnBlocker = new Pawn(PieceColor.BLACK);
        board.setPiece(chosen, pawnBlocker);

        boolean result = knight.isValidMove(start, chosen, board);

        assertFalse(result);
    }

}
