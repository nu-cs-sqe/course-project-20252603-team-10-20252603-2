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

    @Test
    public void hasValidMoves_Queen_ClearBoard_ReturnsTrue() {
        final int queenRow = 4;
        final int queenCol = 4;

        Queen queen = new Queen(Color.WHITE);
        Location queenPos = new Location(queenRow, queenCol);

        Board board = new Board(false);
        board.setPiece(queenPos, queen);

        assertTrue(queen.hasValidMoves(queenPos, board));
    }

    @Test
    public void hasValidMoves_Queen_ClearBoardMin_ReturnsTrue() {
        final int queenRow = 0;
        final int queenCol = 0;

        Queen queen = new Queen(Color.WHITE);
        Location queenPos = new Location(queenRow, queenCol);

        Board board = new Board(false);
        board.setPiece(queenPos, queen);

        assertTrue(queen.hasValidMoves(queenPos, board));
    }

    @Test
    public void hasValidMoves_Queen_ClearBoardMax_ReturnsTrue() {
        final int queenRow = 7;
        final int queenCol = 7;

        Queen queen = new Queen(Color.WHITE);
        Location queenPos = new Location(queenRow, queenCol);

        Board board = new Board(false);
        board.setPiece(queenPos, queen);

        assertTrue(queen.hasValidMoves(queenPos, board));
    }

    @Test
    public void hasValidMoves_Queen_AllFriendlySurrounding_ReturnsFalse() {
        final int queenRow = 4;
        final int queenCol = 4;
        final int aboveRow = queenRow - 1;
        final int belowRow = queenRow + 1;
        final int leftCol = queenCol - 1;
        final int rightCol = queenCol + 1;

        Queen queen = new Queen(Color.WHITE);
        Location queenPos = new Location(queenRow, queenCol);

        Board board = new Board(false);
        board.setPiece(queenPos, queen);
        board.setPiece(new Location(aboveRow, leftCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(aboveRow, queenCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(aboveRow, rightCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(queenRow, leftCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(queenRow, rightCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, leftCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, queenCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, rightCol), new Pawn(Color.WHITE));

        assertFalse(queen.hasValidMoves(queenPos, board));
    }

    @Test
    public void hasValidMoves_Queen_CornerAllFriendlySurrounding_ReturnsFalse() {
        final int queenRow = 0;
        final int queenCol = 0;
        final int belowRow = queenRow + 1;
        final int rightCol = queenCol + 1;

        Queen queen = new Queen(Color.WHITE);
        Location queenPos = new Location(queenRow, queenCol);

        Board board = new Board(false);
        board.setPiece(queenPos, queen);
        board.setPiece(new Location(queenRow, rightCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, queenCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, rightCol), new Pawn(Color.WHITE));

        assertFalse(queen.hasValidMoves(queenPos, board));
    }

    @Test
    public void hasValidMoves_Queen_MovesKingIntoCheck_ReturnsFalse() {
        final int queenRow = 4;
        final int queenCol = 4;
        final int kingRow = 4;
        final int kingCol = 3;
        final int rook1Row = 4;
        final int rook1Col = 5;
        final int rook2Row = 4;
        final int rook2Col = 0;
        final int aboveRow = queenRow - 1;
        final int belowRow = queenRow + 1;
        final int leftCol = queenCol - 1;
        final int rightCol = queenCol + 1;

        Queen queen = new Queen(Color.WHITE);
        King king = new King(Color.WHITE);
        Piece rook1 = new Rook(Color.BLACK);
        Piece rook2 = new Rook(Color.BLACK);

        Location queenPos = new Location(queenRow, queenCol);
        Location kingPos = new Location(kingRow, kingCol);
        Location rook1Pos = new Location(rook1Row, rook1Col);
        Location rook2Pos = new Location(rook2Row, rook2Col);

        Board board = new Board(false);
        board.setPiece(queenPos, queen);
        board.setPiece(kingPos, king);
        board.setPiece(rook1Pos, rook1);
        board.setPiece(rook2Pos, rook2);

        board.setPiece(new Location(aboveRow, leftCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(aboveRow, queenCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(aboveRow, rightCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, leftCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, queenCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, rightCol), new Pawn(Color.WHITE));

        assertFalse(queen.hasValidMoves(queenPos, board));
    }

    @Test
    public void hasValidMoves_Queen_OneDiagonalOpen_ReturnsTrue() {
        final int queenRow = 4;
        final int queenCol = 4;
        final int aboveRow = queenRow - 1;
        final int belowRow = queenRow + 1;
        final int leftCol = queenCol - 1;
        final int rightCol = queenCol + 1;

        Queen queen = new Queen(Color.WHITE);
        Location queenPos = new Location(queenRow, queenCol);

        Board board = new Board(false);
        board.setPiece(queenPos, queen);

        board.setPiece(new Location(aboveRow, queenCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(aboveRow, rightCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(queenRow, leftCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(queenRow, rightCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, leftCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, queenCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, rightCol), new Pawn(Color.WHITE));

        assertTrue(queen.hasValidMoves(queenPos, board));
    }

    @Test
    public void hasValidMoves_Queen_StraightPathOpen_ReturnsTrue() {
        final int queenRow = 4;
        final int queenCol = 4;
        final int aboveRow = queenRow - 1;
        final int belowRow = queenRow + 1;
        final int leftCol = queenCol - 1;
        final int rightCol = queenCol + 1;

        Queen queen = new Queen(Color.WHITE);
        Location queenPos = new Location(queenRow, queenCol);

        Board board = new Board(false);
        board.setPiece(queenPos, queen);

        board.setPiece(new Location(aboveRow, leftCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(aboveRow, rightCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(queenRow, leftCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(queenRow, rightCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, leftCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, queenCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, rightCol), new Pawn(Color.WHITE));

        assertTrue(queen.hasValidMoves(queenPos, board));
    }

    @Test
    public void hasValidMoves_Queen_UnprotectedEnemy_ReturnsTrue() {
        final int queenRow = 4;
        final int queenCol = 4;
        final int enemyRow = 3;
        final int enemyCol = 3;

        Queen queen = new Queen(Color.WHITE);
        Piece enemy = new Pawn(Color.BLACK);
        Location queenPos = new Location(queenRow, queenCol);
        Location enemyPos = new Location(enemyRow, enemyCol);

        Board board = new Board(false);
        board.setPiece(queenPos, queen);
        board.setPiece(enemyPos, enemy);

        assertTrue(queen.hasValidMoves(queenPos, board));
    }

    @Test
    public void hasValidMoves_BlackQueen_ClearBoard_ReturnsTrue() {
        final int queenRow = 7;
        final int queenCol = 7;

        Queen queen = new Queen(Color.BLACK);
        Location queenPos = new Location(queenRow, queenCol);

        Board board = new Board(false);
        board.setPiece(queenPos, queen);

        assertTrue(queen.hasValidMoves(queenPos, board));
    }

    @Test
    public void isValidMove_invalidQueenMovementPattern_returnsFalse() {
        Board board = new Board(false);
        Queen queen = new Queen(Color.WHITE);

        Location start = new Location(0, 0);
        Location end = new Location(2, 1);

        board.setPiece(start, queen);

        assertFalse(queen.isValidMove(start, end, board));
    }

    @Test
    public void isValidMove_preservesOriginalBoardStateAfterEvaluation() {
        Board testBoard = new Board(false);
        Queen whiteQueen = new Queen(Color.WHITE);
        Location start = new Location(3, 3);
        Location end = new Location(3, 5);

        testBoard.setPiece(start, whiteQueen);

        whiteQueen.isValidMove(start, end, testBoard);

        assertTrue(testBoard.isPieceHere(start));
        assertFalse(testBoard.isPieceHere(end));
    }

    @Test
    public void hasValidMoves_onlyNegativeYAxisPathOpen_killsDirectionMathMutant() {
        Board board = new Board(false);
        Queen queen = new Queen(Color.WHITE);
        Location queenLoc = new Location(4, 4);
        board.setPiece(queenLoc, queen);

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                if (i == -1 && j == 1) continue;

                board.setPiece(new Location(4 + i, 4 + j), new Pawn(Color.WHITE));
            }
        }

        assertTrue(queen.hasValidMoves(queenLoc, board));
    }
}
