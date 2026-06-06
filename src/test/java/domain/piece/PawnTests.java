package domain;

import domain.piece.*;
import constants.Color;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PawnTests {

    @Test
    public void isValidMove_Pawn_sameSquare_returnFalse() {
        Piece pawn = new Pawn(Color.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(0, 0);

        Board board = new Board(false);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Pawn_sameSquare77edge_returnFalse() {
        Piece pawn = new Pawn(Color.BLACK);

        Location start = new Location(7, 7);
        Location chosen = new Location(7, 7);

        Board board = new Board(false);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Pawn_tooFar_returnFalse() {
        Piece pawn = new Pawn(Color.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(7, 7);

        Board board = new Board(false);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Pawn_oneForward_returnTrue() {
        Piece pawn = new Pawn(Color.WHITE);

        Location start = new Location(7, 7);
        Location chosen = new Location(6, 7);

        Board board = new Board(false);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Pawn_twoForward_returnTrue() {
        Piece pawn = new Pawn(Color.BLACK);

        Location start = new Location(1, 0);
        Location chosen = new Location(3, 0);

        Board board = new Board(false);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Pawn_twoForwardBlockedEnd_returnFalse() {
        Piece pawn = new Pawn(Color.BLACK);

        Location start = new Location(1, 0);
        Location chosen = new Location(3, 0);

        Board board = new Board(false);

        Piece pawnBlocker = new Pawn(Color.WHITE);
        board.setPiece(chosen, pawnBlocker);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Pawn_oneDiagonalRight_returnTrue() {
        Piece pawn = new Pawn(Color.WHITE);

        Location start = new Location(7, 6);
        Location chosen = new Location(6, 7);

        Board board = new Board(false);

        Piece pawnBlocker = new Pawn(Color.BLACK);
        board.setPiece(chosen, pawnBlocker);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Pawn_oneDiagonalLeft_returnTrue() {
        Piece pawn = new Pawn(Color.WHITE);

        Location start = new Location(7, 6);
        Location chosen = new Location(6, 5);

        Board board = new Board(false);

        Piece pawnBlocker = new Pawn(Color.BLACK);
        board.setPiece(chosen, pawnBlocker);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Pawn_oneDiagonalLeftSquareEmpty_returnFalse() {
        Piece pawn = new Pawn(Color.WHITE);

        Location start = new Location(7, 6);
        Location chosen = new Location(6, 5);

        Board board = new Board(false);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Pawn_oneDiagonalLeftSquareFriendly_returnFalse() {
        Piece pawn = new Pawn(Color.WHITE);

        Location start = new Location(7, 6);
        Location chosen = new Location(6, 5);

        Board board = new Board(false);

        Piece pawnBlocker = new Pawn(Color.WHITE);
        board.setPiece(chosen, pawnBlocker);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Pawn_backwards_returnFalse() {
        Piece pawn = new Pawn(Color.WHITE);

        Location start = new Location(6, 6);
        Location chosen = new Location(7, 6);

        Board board = new Board(false);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Pawn_sideways_returnFalse() {
        Piece pawn = new Pawn(Color.WHITE);

        Location start = new Location(6, 6);
        Location chosen = new Location(6, 5);

        Board board = new Board(false);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Pawn_twoForwardBlockedPath_returnFalse() {
        Piece pawn = new Pawn(Color.BLACK);

        Location start = new Location(0, 0);
        Location mid = new Location(1, 0);
        Location chosen = new Location(2, 0);

        Board board = new Board(false);

        Piece pawnBlocker = new Pawn(Color.WHITE);
        board.setPiece(mid, pawnBlocker);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Pawn_blackTwoForwardNotStartRow_returnFalse() {
        Piece pawn = new Pawn(Color.BLACK);

        Location start = new Location(2, 0);
        Location chosen = new Location(4, 0);

        Board board = new Board(false);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void hasValidMoves_Pawn_NotBlocked_returnsTrue() {
        Piece pawn = new Pawn(Color.WHITE);

        Location location = new Location(6, 0);

        Board board = new Board(false);
        board.setPiece(location, pawn);

        boolean result = pawn.hasValidMoves(location, board);

        assertTrue(result);
    }

    @Test
    public void hasValidMoves_PawnBlockedVertically_returnsFalse() {
        Piece pawn = new Pawn(Color.WHITE);
        Piece enemyPawn = new Pawn(Color.BLACK);
        Piece friendlyPawn = new Pawn(Color.WHITE);

        Location location = new Location(6, 0);
        Location enemyLocation = new Location(4, 0);
        Location friendlyLocation = new Location(5,0);

        Board board = new Board(false);
        board.setPiece(location, pawn);
        board.setPiece(enemyLocation, enemyPawn);
        board.setPiece(friendlyLocation, friendlyPawn);

        boolean result = pawn.hasValidMoves(location, board);

        assertFalse(result);
    }


    @Test
    public void hasValidMoves_PawnBlockedDiagonally_returnsTrue() {
        Piece pawn = new Pawn(Color.BLACK);
        Piece enemyPawn = new Pawn(Color.WHITE);
        Piece friendlyPawn = new Pawn(Color.BLACK);

        Location location = new Location(5, 4);
        Location enemyLocation = new Location(4, 5);
        Location friendlyLocation = new Location(4,4);

        Board board = new Board(false);
        board.setPiece(location, pawn);
        board.setPiece(enemyLocation, enemyPawn);
        board.setPiece(friendlyLocation, friendlyPawn);

        boolean result = pawn.hasValidMoves(location, board);

        assertTrue(result);
    }

    @Test
    public void hasValidMoves_PawnAtEdge_returnsFalse() {
        Piece pawn = new Pawn(Color.BLACK);

        Location location = new Location(7, 3);
        System.out.println("X: " + location.getX());

        Board board = new Board(false);
        board.setPiece(location, pawn);

        boolean result = pawn.hasValidMoves(location, board);

        assertFalse(result);
    }

    @Test
    public void hasValidMoves_pawnTrapped_returnsFalse() {
        Board board = new Board(false);

        Piece pawn = new Pawn(Color.WHITE);
        King realKing = new King(Color.WHITE);
        Piece rook = new Rook(Color.BLACK);

        Location pawnLocation = new Location(7, 1);
        Location kingLocation = new Location(7, 0);
        Location rookLocation = new Location(7, 7);

        board.setPiece(pawnLocation, pawn);
        board.setPiece(kingLocation, realKing);
        board.setPiece(rookLocation, rook);

        boolean result = pawn.hasValidMoves(pawnLocation, board);

        assertFalse(result);
    }
}


