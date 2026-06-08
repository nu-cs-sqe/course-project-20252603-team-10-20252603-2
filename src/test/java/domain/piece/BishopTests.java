package domain;

import domain.piece.Bishop;
import domain.piece.Piece;
import constants.Color;
import domain.piece.PieceType;
import domain.piece.Rook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class BishopTests {

    @Test
    public void isValidMove_Bishop_sameSquare_returnFalse() {
        Piece bishop = new Bishop(Color.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(0, 0);

        Board board = new Board(false);

        boolean result = bishop.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Bishop_VerticalMove_returnFalse() {
        Piece bishop = new Bishop(Color.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(3, 0);

        Board board = new Board(false);

        boolean result = bishop.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Bishop_oneDiag1_returnTrue() {
        Piece bishop = new Bishop(Color.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(1, 1);

        Board board = new Board(false);

        boolean result = bishop.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Bishop_oneDiag2_returnTrue() {
        Piece bishop = new Bishop(Color.WHITE);

        Location start = new Location(0, 7);
        Location chosen = new Location(1, 6);

        Board board = new Board(false);

        boolean result = bishop.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Bishop_oneDiag3_returnTrue() {
        Piece bishop = new Bishop(Color.WHITE);

        Location start = new Location(7, 0);
        Location chosen = new Location(6, 1);

        Board board = new Board(false);

        boolean result = bishop.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Bishop_oneDiag4_returnTrue() {
        Piece bishop = new Bishop(Color.WHITE);

        Location start = new Location(7, 7);
        Location chosen = new Location(6, 6);

        Board board = new Board(false);

        boolean result = bishop.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Bishop_sevenDiag1_returnTrue() {
        Piece bishop = new Bishop(Color.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(7, 7);

        Board board = new Board(false);

        boolean result = bishop.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Bishop_sevenDiag2_returnTrue() {
        Piece bishop = new Bishop(Color.WHITE);

        Location start = new Location(0, 7);
        Location chosen = new Location(7, 0);

        Board board = new Board(false);

        boolean result = bishop.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Bishop_sevenDiag3_returnTrue() {
        Piece bishop = new Bishop(Color.WHITE);

        Location start = new Location(7, 0);
        Location chosen = new Location(0, 7);

        Board board = new Board(false);

        boolean result = bishop.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Bishop_sevenDiag4_returnTrue() {
        Piece bishop = new Bishop(Color.WHITE);

        Location start = new Location(7, 7);
        Location chosen = new Location(0, 0);

        Board board = new Board(false);

        boolean result = bishop.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Bishop_HorizontalMove_returnFalse() {
        Piece bishop = new Bishop(Color.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(0, 3);

        Board board = new Board(false);

        boolean result = bishop.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Bishop_DiagonalCapture_returnTrue() {
        Piece bishop = new Bishop(Color.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(7, 7);

        Board board = new Board(false);

        Piece capturePiece = new Bishop(Color.BLACK);
        board.setPiece(new Location(7, 7), capturePiece);

        boolean result = bishop.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Bishop_DiagonalFriendlyDestination_returnFalse() {
        Piece bishop = new Bishop(Color.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(3, 3);

        Board board = new Board(false);

        Piece friendlyPiece = new Bishop(Color.WHITE);
        board.setPiece(new Location(3, 3), friendlyPiece);

        boolean result = bishop.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_BlackBishop_DiagonalCapture_returnTrue() {
        Piece bishop = new Bishop(Color.BLACK);

        Location start = new Location(0, 0);
        Location chosen = new Location(7, 7);

        Board board = new Board(false);

        Piece capturePiece = new Bishop(Color.WHITE);
        board.setPiece(new Location(7, 7), capturePiece);

        boolean result = bishop.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Bishop_blockedDiagonal_returnFalse() {
        Piece bishop = new Bishop(Color.BLACK);


        Location start = new Location(0, 0);
        Location chosen = new Location(7, 7);


        Board board = new Board(false);


        Piece blockingPiece = new Bishop(Color.WHITE);
        board.setPiece(new Location(1, 1), blockingPiece);


        boolean result = bishop.isValidMove(start, chosen, board);


        assertFalse(result);
    }

    @Test
    public void isValidMove_BlackBishop_sevenDiag1_returnTrue() {
        Piece bishop = new Bishop(Color.BLACK);

        Location start = new Location(0, 0);
        Location chosen = new Location(7, 7);

        Board board = new Board(false);

        boolean result = bishop.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void makeCopy_Bishop_black_returnsNewBishopWithSameColorAndType() {
        Bishop original = new Bishop(Color.BLACK);

        Piece copy = original.makeCopy();

        assertNotNull(copy);
        assertNotSame(original, copy);
        assertInstanceOf(Bishop.class, copy);
        assertEquals(PieceType.BISHOP, copy.getType());
        assertEquals(Color.BLACK, copy.getColor());
    }

}