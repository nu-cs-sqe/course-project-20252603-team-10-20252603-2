package domain.piece;

import domain.Board;
import domain.Location;
import constants.Color;import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RookTests {

    @Test
    public void isValidMove_Rook_sameSquare_returnFalse() {
        Piece rook = new Rook(Color.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(0, 0);

        Board board = new Board(false);

        boolean result = rook.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Rook_oneRight_returnTrue() {
        Piece rook = new Rook(Color.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(0, 1);

        Board board = new Board(false);

        boolean result = rook.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Rook_sevenRight_returnTrue() {
        Piece rook = new Rook(Color.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(0, 7);

        Board board = new Board(false);

        boolean result = rook.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Rook_oneLeft_returnTrue() {
        Piece rook = new Rook(Color.WHITE);

        Location start = new Location(0, 7);
        Location chosen = new Location(0, 6);

        Board board = new Board(false);

        boolean result = rook.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Rook_sevenLeft_returnTrue() {
        Piece rook = new Rook(Color.WHITE);

        Location start = new Location(0, 7);
        Location chosen = new Location(0, 0);

        Board board = new Board(false);

        boolean result = rook.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Rook_sevenDown_returnTrue() {
        Piece rook = new Rook(Color.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(7, 0);

        Board board = new Board(false);

        boolean result = rook.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Rook_diagonal_returnFalse() {
        Piece rook = new Rook(Color.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(3, 3);

        Board board = new Board(false);

        boolean result = rook.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Rook_blockedHorizontal_returnFalse() {
        Piece rook = new Rook(Color.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(0, 3);

        Board board = new Board(false);

        Piece blockingPiece = new Rook(Color.BLACK);
        board.setPiece(new Location(0, 1), blockingPiece);

        boolean result = rook.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Rook_blockedVertical_returnFalse() {
        Piece rook = new Rook(Color.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(3, 0);

        Board board = new Board(false);

        Piece blockingPiece = new Rook(Color.BLACK);
        board.setPiece(new Location(1, 0), blockingPiece);

        boolean result = rook.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Rook_horizontalCapture_returnTrue() {
        Piece rook = new Rook(Color.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(0, 3);

        Board board = new Board(false);

        Piece capturePiece = new Rook(Color.BLACK);
        board.setPiece(new Location(0, 3), capturePiece);

        boolean result = rook.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Rook_verticalCapture_returnTrue() {
        Piece rook = new Rook(Color.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(3, 0);

        Board board = new Board(false);

        Piece capturePiece = new Rook(Color.BLACK);
        board.setPiece(new Location(3, 0), capturePiece);

        boolean result = rook.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Rook_oneUp_returnTrue() {
        Piece rook = new Rook(Color.WHITE);

        Location start = new Location(7, 0);
        Location chosen = new Location(6, 0);

        Board board = new Board(false);

        boolean result = rook.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Rook_sevenUp_returnTrue() {
        Piece rook = new Rook(Color.WHITE);

        Location start = new Location(7, 0);
        Location chosen = new Location(0, 0);

        Board board = new Board(false);

        boolean result = rook.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_blackRook_horizontalCapture_returnTrue() {
        Piece rook = new Rook(Color.BLACK);

        Location start = new Location(0, 0);
        Location chosen = new Location(0, 3);

        Board board = new Board(false);

        Piece capturePiece = new Rook(Color.WHITE);
        board.setPiece(new Location(0, 3), capturePiece);

        boolean result = rook.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_blackRook_horizontalFriendlyDestination_returnFalse() {
        Piece rook = new Rook(Color.BLACK);

        Location start = new Location(0, 0);
        Location chosen = new Location(0, 1);

        Board board = new Board(false);

        Piece friendlyPiece = new Rook(Color.BLACK);
        board.setPiece(new Location(0, 1), friendlyPiece);

        boolean result = rook.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Rook_horizontalFriendlyDestination_returnFalse() {
        Piece rook = new Rook(Color.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(0, 3);

        Board board = new Board(false);

        Piece friendlyPiece = new Rook(Color.WHITE);
        board.setPiece(new Location(0, 3), friendlyPiece);

        boolean result = rook.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Rook_verticalFriendlyDestination_returnFalse() {
        Piece rook = new Rook(Color.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(3, 0);

        Board board = new Board(false);

        Piece friendlyPiece = new Rook(Color.WHITE);
        board.setPiece(new Location(3, 0), friendlyPiece);

        boolean result = rook.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_blackRook_verticalCapture_returnTrue() {
        Piece rook = new Rook(Color.BLACK);

        Location start = new Location(0, 0);
        Location chosen = new Location(7, 0);

        Board board = new Board(false);

        Piece capturePiece = new Rook(Color.WHITE);
        board.setPiece(new Location(7, 0), capturePiece);

        boolean result = rook.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_blackRook_oneLeftFromCorner_returnTrue() {
        Piece rook = new Rook(Color.BLACK);

        Location start = new Location(7, 7);
        Location chosen = new Location(7, 6);

        Board board = new Board(false);

        boolean result = rook.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_rook_oneUpFromCorner_returnTrue() {
        Piece rook = new Rook(Color.BLACK);

        Location start = new Location(7, 7);
        Location chosen = new Location(6, 7);

        Board board = new Board(false);

        boolean result = rook.isValidMove(start, chosen, board);

        assertTrue(result);
    }


    @Test
    public void isValidMove_rookExposesKingToAbsolutePin_returnsFalse() {
        Board board = new Board(false);

        Piece rook = new Rook(Color.WHITE);
        Piece friendlyKing = new King(Color.WHITE);
        Piece enemyRook = new Rook(Color.BLACK);

        Location kingLocation = new Location(7, 0);
        Location rookLocation = new Location(6, 0);
        Location enemyLocation = new Location(0, 0);

        board.setPiece(kingLocation, friendlyKing);
        board.setPiece(rookLocation, rook);
        board.setPiece(enemyLocation, enemyRook);

        Location illegalSidewaysMove = new Location(6, 1);
        boolean result = rook.isValidMove(rookLocation, illegalSidewaysMove, board);


        assertFalse(result);
    }

    @Test
    public void hasValidMoves_rook_notBlocked_returnsTrue() {
        Piece rook = new Rook(Color.WHITE);

        Location location = new Location(6, 0);

        Board board = new Board(false);
        board.setPiece(location, rook);

        boolean result = rook.hasValidMoves(location, board);

        assertTrue(result);
    }

    @Test
    public void hasValidMoves_rook_blockedByFriendlyPieces_returnsFalse() {
        Piece rook = new Rook(Color.WHITE);
        Piece friendlyRook1 = new Rook(Color.WHITE);
        Piece friendlyPiece2 = new Pawn(Color.WHITE);
        Piece friendlyPiece3 = new Pawn(Color.WHITE);

        Location location = new Location(6, 0);
        Location location1 = new Location(5, 0);
        Location location2 = new Location(7, 0);
        Location location3 = new Location(6, 1);

        Board board = new Board(false);
        board.setPiece(location, rook);
        board.setPiece(location1, friendlyRook1);
        board.setPiece(location2, friendlyPiece2);
        board.setPiece(location3, friendlyPiece3);

        boolean result = rook.hasValidMoves(location, board);

        assertFalse(result);
    }

    @Test
    public void hasValidMoves_rook_blockedByOneFriendlyPieces_returnsTrue() {
        Piece rook = new Rook(Color.BLACK);
        Piece friendlyRook1 = new Rook(Color.BLACK);

        Location location = new Location(5, 4);
        Location location1 = new Location(4, 4);

        Board board = new Board(false);
        board.setPiece(location, rook);
        board.setPiece(location1, friendlyRook1);

        boolean result = rook.hasValidMoves(location, board);

        assertTrue(result);
    }

    @Test
    public void hasValidMoves_rook_atCornerBlockedByFriendlyPieces_returnsFalse() {
        Piece rook = new Rook(Color.BLACK);
        Piece friendlyRook1 = new Rook(Color.BLACK);
        Piece friendlyPiece = new Pawn(Color.BLACK);

        Location location = new Location(7, 7);
        Location location1 = new Location(6, 7);
        Location location2 = new Location(7, 6);

        Board board = new Board(false);
        board.setPiece(location, rook);
        board.setPiece(location1, friendlyRook1);
        board.setPiece(location2, friendlyPiece);

        boolean result = rook.hasValidMoves(location, board);

        assertFalse(result);
    }

    @Test
    public void hasValidMoves_rook_atCornerBlockedByEnemyPieces_returnsTrue() {
        Piece rook = new Rook(Color.BLACK);
        Piece enemyRook1 = new Rook(Color.WHITE);
        Piece enemyRook2 = new Rook(Color.WHITE);

        Location location = new Location(7, 7);
        Location enemyRookLocation1 = new Location(6, 7);
        Location enemyRookLocation2 = new Location(7, 6);

        Board board = new Board(false);
        board.setPiece(location, rook);
        board.setPiece(enemyRookLocation1, enemyRook1);
        board.setPiece(enemyRookLocation2, enemyRook2);

        boolean result = rook.hasValidMoves(location, board);

        assertTrue(result);
    }
}
