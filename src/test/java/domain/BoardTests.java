package domain;

import domain.piece.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTests {

    private static final int BOARD_SIZE = 8;

    private void assertBoardIsEmpty(Board board) {
        for (int x = 0; x < BOARD_SIZE ; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                assertFalse(board.isPieceHere(new Location(x,y)));
            }
        }
    }

    private void assertMiddleEmpty(Board board) {
        for (int x = 2; x < 5 ; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                assertFalse(board.isPieceHere(new Location(x,y)));
            }
        }
    }

    private void assertPawnRow(Board board, Integer row, PieceColor color) {
        for (int y = 0; y < BOARD_SIZE; y++) {
            Location loc = new Location(row, y);

            assertTrue(board.isPieceHere(loc));
            Piece piece = board.getPiece(loc);
            assertInstanceOf(Pawn.class, piece);
            assertEquals(color, piece.getColor());
        }
    }

    private void assertMajorRow(Board board, Integer row, PieceColor color) {

        List<Class<? extends Piece>> expectedPieces = List.of(
                Rook.class,
                Knight.class,
                Bishop.class,
                Queen.class,
                King.class,
                Bishop.class,
                Knight.class,
                Rook.class
        );

        for (int y = 0; y < BOARD_SIZE; y++) {
            Location loc = new Location(row, y);

            assertTrue(board.isPieceHere(loc));
            Piece piece = board.getPiece(loc);
            assertInstanceOf(expectedPieces.get(y), piece);
            assertEquals(color, piece.getColor());
        }
    }

    @Test
    void initBoard_noInit_returnsEmptyBoard() {
        Board board = new Board(false);

        assertBoardIsEmpty(board);

    }

    @Test
    void initBoard_init_returnsInitBoardCheckPawns() {
        Board board = new Board(true);

        assertPawnRow(board, 1, PieceColor.BLACK);
        assertPawnRow(board, 6, PieceColor.WHITE);

    }

    @Test
    void initBoard_init_returnsInitBoardCheckMajorWhite() {
        Board board = new Board(true);

        assertMajorRow(board, 7, PieceColor.WHITE);

    }

    @Test
    void initBoard_init_returnsInitBoardCheckMajorBlack() {
        Board board = new Board(true);

        assertMajorRow(board, 0, PieceColor.BLACK);

    }

    @Test
    void initBoard_init_returnsInitBoardCheckEmptyMiddle() {
        Board board = new Board(true);

        assertMiddleEmpty(board);

    }

}

