package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class BoardTests {

    private static final int BOARD_SIZE = 8;

    private void assertBoardIsEmpty(Board board) {
        for (int x = 0; x < BOARD_SIZE ; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                assertFalse(board.isPieceHere(new Location(x,y)));
            }
        }
    }

    @Test
    void initBoard_noInit_returnsEmptyBoard() {
        Board board = new Board(false);

        assertBoardIsEmpty(board);

    }

}

