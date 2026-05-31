package domain.piece;

import domain.Board;
import domain.Location;
import org.junit.jupiter.api.Test;
import constants.Color;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueenTests {

    @Test
    public void isValidMove_Queen_xMovPos_returnTrue() {
        Piece queen = new Queen(Color.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(7, 0);

        Board board = new Board(false);

        boolean result = queen.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_QueenBlack_xMovPos_returnTrue() {
        Piece queen = new Queen(Color.BLACK);

        Location start = new Location(0, 0);
        Location chosen = new Location(7, 0);

        Board board = new Board(false);

        boolean result = queen.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Queen_xMovNeg_returnTrue() {
        Piece queen = new Queen(Color.BLACK);

        Location start = new Location(7, 7);
        Location chosen = new Location(0, 7);

        Board board = new Board(false);

        boolean result = queen.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Queen_yMovNeg_returnTrue() {
        Piece queen = new Queen(Color.BLACK);

        Location start = new Location(7, 7);
        Location chosen = new Location(7, 0);

        Board board = new Board(false);

        boolean result = queen.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Queen_yMovPos_returnTrue() {
        Piece queen = new Queen(Color.BLACK);

        Location start = new Location(0, 0);
        Location chosen = new Location(0, 7);

        Board board = new Board(false);

        boolean result = queen.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Queen_diagonalPos_returnTrue() {
        Piece queen = new Queen(Color.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(7, 7);

        Board board = new Board(false);

        boolean result = queen.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Queen_diagonalNeg_returnTrue() {
        Piece queen = new Queen(Color.WHITE);

        Location start = new Location(7, 7);
        Location chosen = new Location(0, 0);

        Board board = new Board(false);

        boolean result = queen.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Queen_xMovPosFriendlyBlock_returnFalse() {
        Piece queen = new Queen(Color.BLACK);
        Piece blocker = new Pawn(Color.BLACK);

        Location start = new Location(0, 0);
        Location chosen = new Location(7, 0);
        Location blockerLocation = new Location(3, 0);

        Board board = new Board(false);
        board.setPiece(blockerLocation, blocker);

        boolean result = queen.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Queen_xMovPosEnemyBlock_returnFalse() {
        Piece queen = new Queen(Color.BLACK);
        Piece blocker = new Pawn(Color.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(7, 0);
        Location blockerLocation = new Location(3, 0);

        Board board = new Board(false);
        board.setPiece(blockerLocation, blocker);

        boolean result = queen.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Queen_yMovPosEnemyBlock_returnFalse() {
        Piece queen = new Queen(Color.BLACK);
        Piece blocker = new Pawn(Color.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(0, 7);
        Location blockerLocation = new Location(0, 3);

        Board board = new Board(false);
        board.setPiece(blockerLocation, blocker);

        boolean result = queen.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Queen_diagonalPosEnemyBlock_returnFalse() {
        Piece queen = new Queen(Color.WHITE);
        Piece blocker = new Pawn(Color.BLACK);

        Location start = new Location(0, 0);
        Location chosen = new Location(7, 7);
        Location blockerLocation = new Location(3, 3);

        Board board = new Board(false);
        board.setPiece(blockerLocation, blocker);

        boolean result = queen.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Queen_xMovNegEnemyEnd_returnTrue() {
        Piece queen = new Queen(Color.WHITE);
        Piece blocker = new Pawn(Color.BLACK);

        Location start = new Location(7, 7);
        Location chosen = new Location(0, 7);

        Board board = new Board(false);
        board.setPiece(chosen, blocker);

        boolean result = queen.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Queen_xMovNegFriendlyEnd_returnFalse() {
        Piece queen = new Queen(Color.WHITE);
        Piece blocker = new Pawn(Color.WHITE);

        Location start = new Location(7, 7);
        Location chosen = new Location(0, 7);

        Board board = new Board(false);
        board.setPiece(chosen, blocker);

        boolean result = queen.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Queen_sameSquare_returnFalse() {
        Piece queen = new Queen(Color.WHITE);

        Location start = new Location(5, 7);
        Location chosen = new Location(5, 7);

        Board board = new Board(false);

        boolean result = queen.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Queen_LMov1_returnFalse() {
        Piece queen = new Queen(Color.WHITE);

        Location start = new Location(3, 3);
        Location chosen = new Location(5, 2);

        Board board = new Board(false);

        boolean result = queen.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Queen_LMov3_returnFalse() {
        Piece queen = new Queen(Color.BLACK);

        Location start = new Location(3, 3);
        Location chosen = new Location(4, 1);

        Board board = new Board(false);

        boolean result = queen.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Queen_diagonalPosMidEndBlocker_returnFalse() {
        Piece queen = new Queen(Color.WHITE);
        Piece blocker = new Pawn(Color.BLACK);

        Location start = new Location(0, 0);
        Location chosen = new Location(7, 7);
        Location blocking_loc = new Location(6, 6);

        Board board = new Board(false);
        board.setPiece(blocking_loc, blocker);

        boolean result = queen.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Queen_diagonal70to07_returnTrue() {
        Piece queen = new Queen(Color.WHITE);

        Location start = new Location(7, 0);
        Location chosen = new Location(0, 7);

        Board board = new Board(false);

        boolean result = queen.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Queen_mutantTestDiagonalPosEnemy_returnTrue() {
        Piece queen = new Queen(Color.WHITE);
        Piece blocker = new Queen(Color.BLACK);

        Location start = new Location(0, 0);
        Location chosen = new Location(7, 7);

        Board board = new Board(false);
        board.setPiece(chosen, blocker);

        boolean result = queen.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Queen_mutantTestYDirEnemyBlocker_returnTrue() {
        Piece queen = new Queen(Color.BLACK);
        Piece blocker = new Queen(Color.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(0, 7);

        Board board = new Board(false);
        board.setPiece(chosen, blocker);

        boolean result = queen.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Queen_mutantTestXDirEnemyBlocker_returnTrue() {
        Piece queen = new Queen(Color.BLACK);
        Piece blocker = new Queen(Color.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(7, 0);

        Board board = new Board(false);
        board.setPiece(chosen, blocker);

        boolean result = queen.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Queen_mutantTestYNegEnemyPath_returnFalse() {
        Piece queen = new Queen(Color.BLACK);
        Piece blocker = new Queen(Color.WHITE);

        Location start = new Location(0, 7);
        Location chosen = new Location(0, 0);
        Location blocking_at = new Location(0, 1);

        Board board = new Board(false);
        board.setPiece(blocking_at, blocker);

        boolean result = queen.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Queen_mutantTestXNegEnemyPath_returnFalse() {
        Piece queen = new Queen(Color.BLACK);
        Piece blocker = new Queen(Color.WHITE);

        Location start = new Location(7, 0);
        Location chosen = new Location(0, 0);
        Location blocking_at = new Location(1, 0);

        Board board = new Board(false);
        board.setPiece(blocking_at, blocker);

        boolean result = queen.isValidMove(start, chosen, board);

        assertFalse(result);
    }



}
