package domain.piece;

import domain.Board;
import domain.Location;
import constants.Color;import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    public void isValidMove_Rook_oneUpFromCorner_returnTrue() {
        Piece rook = new Rook(Color.BLACK);

        Location start = new Location(7, 7);
        Location chosen = new Location(6, 7);

        Board board = new Board(false);

        boolean result = rook.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void makeCopy_Rook_black_returnsNewRookWithSameColorAndType() {
        Rook original = new Rook(Color.BLACK);

        Piece copy = original.makeCopy();

        assertNotNull(copy);
        assertNotSame(original, copy);
        assertInstanceOf(Rook.class, copy);
        assertEquals(PieceType.ROOK, copy.getType());
        assertEquals(Color.BLACK, copy.getColor());
    }
}
