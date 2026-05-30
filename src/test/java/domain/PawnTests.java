package domain;

import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.PieceColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PawnTests {

    @Test
    public void isValidMove_Pawn_sameSquare_returnFalse() {
        Piece pawn = new Pawn(PieceColor.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(0, 0);

        Board board = new Board(false);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Pawn_sameSquare77edge_returnFalse() {
        Piece pawn = new Pawn(PieceColor.BLACK);

        Location start = new Location(7, 7);
        Location chosen = new Location(7, 7);

        Board board = new Board(false);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Pawn_tooFar_returnFalse() {
        Piece pawn = new Pawn(PieceColor.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(7, 7);

        Board board = new Board(false);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Pawn_oneForward_returnTrue() {
        Piece pawn = new Pawn(PieceColor.WHITE);

        Location start = new Location(7, 7);
        Location chosen = new Location(6, 7);

        Board board = new Board(false);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Pawn_twoForward_returnTrue() {
        Piece pawn = new Pawn(PieceColor.BLACK);

        Location start = new Location(1, 0);
        Location chosen = new Location(3, 0);

        Board board = new Board(false);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Pawn_twoForwardBlockedEnd_returnFalse() {
        Piece pawn = new Pawn(PieceColor.BLACK);

        Location start = new Location(1, 0);
        Location chosen = new Location(3, 0);

        Board board = new Board(false);

        Piece pawnBlocker = new Pawn(PieceColor.WHITE);
        board.setPiece(chosen, pawnBlocker);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Pawn_oneDiagonalRight_returnTrue() {
        Piece pawn = new Pawn(PieceColor.WHITE);

        Location start = new Location(7, 6);
        Location chosen = new Location(6, 7);

        Board board = new Board(false);

        Piece pawnBlocker = new Pawn(PieceColor.BLACK);
        board.setPiece(chosen, pawnBlocker);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Pawn_oneDiagonalLeft_returnTrue() {
        Piece pawn = new Pawn(PieceColor.WHITE);

        Location start = new Location(7, 6);
        Location chosen = new Location(6, 5);

        Board board = new Board(false);

        Piece pawnBlocker = new Pawn(PieceColor.BLACK);
        board.setPiece(chosen, pawnBlocker);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Pawn_oneDiagonalLeftSquareEmpty_returnFalse() {
        Piece pawn = new Pawn(PieceColor.WHITE);

        Location start = new Location(7, 6);
        Location chosen = new Location(6, 5);

        Board board = new Board(false);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Pawn_oneDiagonalLeftSquareFriendly_returnFalse() {
        Piece pawn = new Pawn(PieceColor.WHITE);

        Location start = new Location(7, 6);
        Location chosen = new Location(6, 5);

        Board board = new Board(false);

        Piece pawnBlocker = new Pawn(PieceColor.WHITE);
        board.setPiece(chosen, pawnBlocker);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Pawn_backwards_returnFalse() {
        Piece pawn = new Pawn(PieceColor.WHITE);

        Location start = new Location(6, 6);
        Location chosen = new Location(7, 6);

        Board board = new Board(false);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Pawn_sideways_returnFalse() {
        Piece pawn = new Pawn(PieceColor.WHITE);

        Location start = new Location(6, 6);
        Location chosen = new Location(6, 5);

        Board board = new Board(false);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Pawn_twoForwardBlockedPath_returnFalse() {
        Piece pawn = new Pawn(PieceColor.BLACK);

        Location start = new Location(0, 0);
        Location mid = new Location(1, 0);
        Location chosen = new Location(2, 0);

        Board board = new Board(false);

        Piece pawnBlocker = new Pawn(PieceColor.WHITE);
        board.setPiece(mid, pawnBlocker);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Pawn_blackTwoForwardNotStartRow_returnFalse() {
        Piece pawn = new Pawn(PieceColor.BLACK);

        Location start = new Location(2, 0);
        Location chosen = new Location(4, 0);

        Board board = new Board(false);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Pawn_whiteTwoForwardNotStartRow_returnFalse() {
        Piece pawn = new Pawn(PieceColor.WHITE);

        Location start = new Location(5, 0);
        Location chosen = new Location(3, 0);

        Board board = new Board(false);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Pawn_whiteOneForwardBlocked_returnFalse() {
        Piece pawn = new Pawn(PieceColor.WHITE);
        Piece blocker = new Pawn(PieceColor.BLACK);

        Location start = new Location(6, 0);
        Location chosen = new Location(5, 0);

        Board board = new Board(false);
        board.setPiece(chosen, blocker);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Pawn_whiteTwoForwardMidBlocked_returnFalse() {
        Piece pawn = new Pawn(PieceColor.WHITE);
        Piece blocker = new Pawn(PieceColor.BLACK);

        Location start = new Location(6, 0);
        Location chosen = new Location(4, 0);
        Location blockLocation = new Location(5, 0);

        Board board = new Board(false);
        board.setPiece(blockLocation, blocker);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Pawn_whiteTwoForward_returnTrue() {
        Piece pawn = new Pawn(PieceColor.WHITE);

        Location start = new Location(6, 0);
        Location chosen = new Location(4, 0);

        Board board = new Board(false);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertTrue(result);
    }


}


