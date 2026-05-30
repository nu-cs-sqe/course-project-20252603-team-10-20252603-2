package domain;

import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.PieceColor;
import org.junit.jupiter.api.Test;

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

    private void assertPawnRow(Board board, Integer row, PieceColor color) {
        for (int y = 0; y < BOARD_SIZE; y++) {
            Location loc = new Location(row, y);

            assertTrue(board.isPieceHere(loc));
            Piece piece = board.getPiece(loc);
            assertInstanceOf(Pawn.class, piece);
            assertEquals(color, piece.getColor());
        }
    }

    @Test
    void initBoard_noInit_returnsEmptyBoard() {
        Board board = new Board(false);

        assertBoardIsEmpty(board);

    }

    @Test
    void initBoard_init_returnsInitBoard_checkPawns() {
        Board board = new Board(true);

        assertPawnRow(board, 1, PieceColor.BLACK);
        assertPawnRow(board, 6, PieceColor.WHITE);

    }

}

