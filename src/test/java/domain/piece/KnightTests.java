package domain.piece;

import domain.Board;
import domain.Location;
import constants.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KnightTests {

    @Test
    public void isValidMove_Knight_Lmov1_returnTrue() {
        Piece knight = new Knight(Color.WHITE);

        Location start = new Location(3, 3);
        Location chosen = new Location(5, 2);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov2_returnTrue() {
        Piece knight = new Knight(Color.BLACK);

        Location start = new Location(3, 3);
        Location chosen = new Location(5, 4);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov3_returnTrue() {
        Piece knight = new Knight(Color.WHITE);

        Location start = new Location(3, 3);
        Location chosen = new Location(4, 1);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov4_returnTrue() {
        Piece knight = new Knight(Color.WHITE);

        Location start = new Location(3, 3);
        Location chosen = new Location(4, 5);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov5_returnTrue() {
        Piece knight = new Knight(Color.WHITE);

        Location start = new Location(3, 3);
        Location chosen = new Location(1, 2);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov6_returnTrue() {
        Piece knight = new Knight(Color.WHITE);

        Location start = new Location(3, 3);
        Location chosen = new Location(1, 4);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov7_returnTrue() {
        Piece knight = new Knight(Color.WHITE);

        Location start = new Location(3, 3);
        Location chosen = new Location(2, 1);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov8_returnTrue() {
        Piece knight = new Knight(Color.WHITE);

        Location start = new Location(3, 3);
        Location chosen = new Location(2, 5);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_starting00_returnTrue() {
        Piece knight = new Knight(Color.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(2, 1);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_starting77_returnTrue() {
        Piece knight = new Knight(Color.WHITE);

        Location start = new Location(7, 7);
        Location chosen = new Location(6, 5);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_ending00_returnTrue() {
        Piece knight = new Knight(Color.WHITE);

        Location start = new Location(2, 1);
        Location chosen = new Location(0, 0);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_ending77_returnTrue() {
        Piece knight = new Knight(Color.WHITE);

        Location start = new Location(6, 5);
        Location chosen = new Location(7, 7);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov1blockedPathVert1_returnTrue() {
        Piece knight = new Knight(Color.BLACK);

        Location start = new Location(3, 3);
        Location blocker = new Location(4, 3);
        Location chosen = new Location(5, 2);

        Board board = new Board(false);

        Piece pawnBlocker = new Pawn(Color.WHITE);
        board.setPiece(blocker, pawnBlocker);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov1blockedEndEnemy_returnTrue() {
        Piece knight = new Knight(Color.BLACK);

        Location start = new Location(3, 3);
        Location chosen = new Location(5, 2);

        Board board = new Board(false);

        Piece pawnBlocker = new Pawn(Color.WHITE);
        board.setPiece(chosen, pawnBlocker);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov1blockedEndFriendly_returnFalse() {
        Piece knight = new Knight(Color.BLACK);

        Location start = new Location(3, 3);
        Location chosen = new Location(5, 2);

        Board board = new Board(false);

        Piece pawnBlocker = new Pawn(Color.BLACK);
        board.setPiece(chosen, pawnBlocker);

        boolean result = knight.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Knight_xMovement_returnFalse() {
        Piece knight = new Knight(Color.BLACK);

        Location start = new Location(3, 3);
        Location chosen = new Location(5, 3);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Knight_yMovement_returnFalse() {
        Piece knight = new Knight(Color.BLACK);

        Location start = new Location(3, 3);
        Location chosen = new Location(3, 5);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Knight_diagonal_returnFalse() {
        Piece knight = new Knight(Color.WHITE);

        Location start = new Location(3, 3);
        Location chosen = new Location(5, 5);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Knight_sameSquare_returnFalse() {
        Piece knight = new Knight(Color.WHITE);

        Location start = new Location(3, 3);
        Location chosen = new Location(3, 3);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Knight_Lmov1blockedPathVert2_returnTrue() {
        Piece knight = new Knight(Color.BLACK);

        Location start = new Location(3, 3);
        Location blocker = new Location(5, 3);
        Location chosen = new Location(5, 2);

        Board board = new Board(false);

        Piece pawnBlocker = new Pawn(Color.WHITE);
        board.setPiece(blocker, pawnBlocker);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov3blockedPathHoriz1_returnTrue() {
        Piece knight = new Knight(Color.WHITE);

        Location start = new Location(3, 3);
        Location chosen = new Location(4, 1);

        Location blocker = new Location(4, 3);

        Board board = new Board(false);

        Piece pawnBlocker = new Pawn(Color.BLACK);
        board.setPiece(blocker, pawnBlocker);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov3blockedPathHoriz2_returnTrue() {
        Piece knight = new Knight(Color.WHITE);

        Location start = new Location(3, 3);
        Location chosen = new Location(4, 1);

        Location blocker = new Location(4, 2);

        Board board = new Board(false);

        Piece pawnBlocker = new Pawn(Color.WHITE);
        board.setPiece(blocker, pawnBlocker);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void hasValidMoves_knight_notBlocked_returnsTrue() {
        Piece knight = new Knight(Color.WHITE);

        Location location = new Location(6, 0);

        Board board = new Board(false);
        board.setPiece(location, knight);

        boolean result = knight.hasValidMoves(location, board);

        assertTrue(result);
    }

    @Test
    public void hasValidMoves_knight_blockedByFriendlyPieces_returnsFalse() {
        Piece knight = new Knight(Color.WHITE);
        Piece friendlyKnight1 = new Knight(Color.WHITE);
        Piece friendlyKnight2 = new Knight(Color.WHITE);
        Piece friendlyKnight3 = new Knight(Color.WHITE);

        Location location = new Location(6, 0);
        Location friendlyKnightLocation1 = new Location(4, 1);
        Location friendlyKnightLocation2 = new Location(5, 2);
        Location friendlyKnightLocation3 = new Location(7, 2);


        Board board = new Board(false);
        board.setPiece(location, knight);
        board.setPiece(friendlyKnightLocation1, friendlyKnight1);
        board.setPiece(friendlyKnightLocation2, friendlyKnight2);
        board.setPiece(friendlyKnightLocation3, friendlyKnight3);

        boolean result = knight.hasValidMoves(location, board);

        assertFalse(result);
    }

    @Test
    public void hasValidMoves_knight_blockedByOneFriendlyPiece_returnsTrue() {
        Piece knight = new Knight(Color.BLACK);
        Piece friendlyKnight1 = new Knight(Color.BLACK);

        Location location = new Location(5, 4);
        Location friendlyKnightLocation1 = new Location(3, 5);


        Board board = new Board(false);
        board.setPiece(location, knight);
        board.setPiece(friendlyKnightLocation1, friendlyKnight1);

        boolean result = knight.hasValidMoves(location, board);

        assertTrue(result);
    }

    @Test
    public void hasValidMoves_knight_atEdgeBlockedByTwoFriendlyPieces_returnsFalse() {
        Piece knight = new Knight(Color.BLACK);
        Piece friendlyKnight1 = new Knight(Color.BLACK);
        Piece friendlyKnight2 = new Knight(Color.BLACK);

        Location location = new Location(7, 0);
        Location friendlyKnightLocation1 = new Location(5, 1);
        Location friendlyKnightLocation2 = new Location(6, 2);

        Board board = new Board(false);
        board.setPiece(location, knight);
        board.setPiece(friendlyKnightLocation1, friendlyKnight1);
        board.setPiece(friendlyKnightLocation2, friendlyKnight2);

        boolean result = knight.hasValidMoves(location, board);

        assertFalse(result);
    }

    @Test
    public void hasValidMoves_knight_byKingAndEnemyPiece_returnsFalse() {
        Piece knight = new Knight(Color.WHITE);
        Piece enemyRook = new Rook(Color.BLACK);
        Piece friendlyKing = new King(Color.WHITE);

        Location location = new Location(7, 1);
        Location enemyRookLocation = new Location(7, 7);
        Location friendlyKingLocation = new Location(7, 0);

        Board board = new Board(false);
        board.setPiece(location, knight);
        board.setPiece(enemyRookLocation, enemyRook);
        board.setPiece(friendlyKingLocation, friendlyKing);

        boolean result = knight.hasValidMoves(location, board);

        assertFalse(result);
    }

    @Test
    public void hasValidMoves_knight_blockedByEnemyPiece_returnsTrue() {
        Piece knight = new Knight(Color.WHITE);
        Piece enemyKnight = new Rook(Color.BLACK);

        Location location = new Location(7, 1);
        Location enemyKnightLocation = new Location(5, 2);

        Board board = new Board(false);
        board.setPiece(location, knight);
        board.setPiece(enemyKnightLocation, enemyKnight);

        boolean result = knight.hasValidMoves(location, board);

        assertTrue(result);
    }
}
