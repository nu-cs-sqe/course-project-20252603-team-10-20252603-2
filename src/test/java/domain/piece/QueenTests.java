package domain.piece;

import domain.Board;
import domain.Location;
import org.junit.jupiter.api.Test;
import constants.Color;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.easymock.EasyMock;

public class QueenTests {

    @Test
    public void isValidMove_Queen_xMovPos_returnTrue() {
        final int startRow = 0;
        final int startCol = 0;
        final int endRow = 7;
        final int endCol = 0;

        Piece queen = new Queen(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);

        assertTrue(queen.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_QueenBlack_xMovPos_returnTrue() {
        final int startRow = 0;
        final int startCol = 0;
        final int endRow = 7;
        final int endCol = 0;

        Piece queen = new Queen(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);

        assertTrue(queen.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Queen_xMovNeg_returnTrue() {
        final int startRow = 7;
        final int startCol = 7;
        final int endRow = 0;
        final int endCol = 7;

        Piece queen = new Queen(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);

        assertTrue(queen.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Queen_yMovNeg_returnTrue() {
        final int startRow = 7;
        final int startCol = 7;
        final int endRow = 7;
        final int endCol = 0;

        Piece queen = new Queen(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);

        assertTrue(queen.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Queen_yMovPos_returnTrue() {
        final int startRow = 0;
        final int startCol = 0;
        final int endRow = 0;
        final int endCol = 7;

        Piece queen = new Queen(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);

        assertTrue(queen.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Queen_diagonalPos_returnTrue() {
        final int startRow = 0;
        final int startCol = 0;
        final int endRow = 7;
        final int endCol = 7;

        Piece queen = new Queen(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);

        assertTrue(queen.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Queen_diagonalNeg_returnTrue() {
        final int startRow = 7;
        final int startCol = 7;
        final int endRow = 0;
        final int endCol = 0;

        Piece queen = new Queen(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);

        assertTrue(queen.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Queen_xMovPosFriendlyBlock_returnFalse() {
        final int startRow = 0;
        final int startCol = 0;
        final int endRow = 7;
        final int endCol = 0;
        final int blockerRow = 3;
        final int blockerCol = 0;

        Piece queen = new Queen(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Location blockerLocation = new Location(blockerRow, blockerCol);
        Board board = new Board(false);
        board.setPiece(blockerLocation, new Pawn(Color.BLACK));

        assertFalse(queen.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Queen_xMovPosEnemyBlock_returnFalse() {
        final int startRow = 0;
        final int startCol = 0;
        final int endRow = 7;
        final int endCol = 0;
        final int blockerRow = 3;
        final int blockerCol = 0;

        Piece queen = new Queen(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Location blockerLocation = new Location(blockerRow, blockerCol);
        Board board = new Board(false);
        board.setPiece(blockerLocation, new Pawn(Color.WHITE));

        assertFalse(queen.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Queen_yMovPosEnemyBlock_returnFalse() {
        final int startRow = 0;
        final int startCol = 0;
        final int endRow = 0;
        final int endCol = 7;
        final int blockerRow = 0;
        final int blockerCol = 3;

        Piece queen = new Queen(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Location blockerLocation = new Location(blockerRow, blockerCol);
        Board board = new Board(false);
        board.setPiece(blockerLocation, new Pawn(Color.WHITE));

        assertFalse(queen.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Queen_diagonalPosEnemyBlock_returnFalse() {
        final int startRow = 0;
        final int startCol = 0;
        final int endRow = 7;
        final int endCol = 7;
        final int blockerRow = 3;
        final int blockerCol = 3;

        Piece queen = new Queen(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Location blockerLocation = new Location(blockerRow, blockerCol);
        Board board = new Board(false);
        board.setPiece(blockerLocation, new Pawn(Color.BLACK));

        assertFalse(queen.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Queen_xMovNegEnemyEnd_returnTrue() {
        final int startRow = 7;
        final int startCol = 7;
        final int endRow = 0;
        final int endCol = 7;

        Piece queen = new Queen(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);
        board.setPiece(chosen, new Pawn(Color.BLACK));

        assertTrue(queen.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Queen_xMovNegFriendlyEnd_returnFalse() {
        final int startRow = 7;
        final int startCol = 7;
        final int endRow = 0;
        final int endCol = 7;

        Piece queen = new Queen(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);
        board.setPiece(chosen, new Pawn(Color.WHITE));

        assertFalse(queen.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Queen_sameSquare_returnFalse() {
        final int startRow = 5;
        final int startCol = 7;

        Piece queen = new Queen(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Board board = new Board(false);

        assertFalse(queen.isValidMove(start, start, board));
    }

    @Test
    public void isValidMove_Queen_LMov1_returnFalse() {
        final int startRow = 3;
        final int startCol = 3;
        final int endRow = 5;
        final int endCol = 2;

        Piece queen = new Queen(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);

        assertFalse(queen.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Queen_LMov3_returnFalse() {
        final int startRow = 3;
        final int startCol = 3;
        final int endRow = 4;
        final int endCol = 1;

        Piece queen = new Queen(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);

        assertFalse(queen.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Queen_diagonalPosMidEndBlocker_returnFalse() {
        final int startRow = 0;
        final int startCol = 0;
        final int endRow = 7;
        final int endCol = 7;
        final int blockerRow = 6;
        final int blockerCol = 6;

        Piece queen = new Queen(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Location blockingLoc = new Location(blockerRow, blockerCol);
        Board board = new Board(false);
        board.setPiece(blockingLoc, new Pawn(Color.BLACK));

        assertFalse(queen.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Queen_diagonal70to07_returnTrue() {
        final int startRow = 7;
        final int startCol = 0;
        final int endRow = 0;
        final int endCol = 7;

        Piece queen = new Queen(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);

        assertTrue(queen.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Queen_mutantTestDiagonalPosEnemy_returnTrue() {
        final int startRow = 0;
        final int startCol = 0;
        final int endRow = 7;
        final int endCol = 7;

        Piece queen = new Queen(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);
        board.setPiece(chosen, new Queen(Color.BLACK));

        assertTrue(queen.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Queen_mutantTestYDirEnemyBlocker_returnTrue() {
        final int startRow = 0;
        final int startCol = 0;
        final int endRow = 0;
        final int endCol = 7;

        Piece queen = new Queen(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);
        board.setPiece(chosen, new Queen(Color.WHITE));

        assertTrue(queen.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Queen_mutantTestXDirEnemyBlocker_returnTrue() {
        final int startRow = 0;
        final int startCol = 0;
        final int endRow = 7;
        final int endCol = 0;

        Piece queen = new Queen(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);
        board.setPiece(chosen, new Queen(Color.WHITE));

        assertTrue(queen.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Queen_mutantTestYNegEnemyPath_returnFalse() {
        final int startRow = 0;
        final int startCol = 7;
        final int endRow = 0;
        final int endCol = 0;
        final int blockerRow = 0;
        final int blockerCol = 1;

        Piece queen = new Queen(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Location blockingAt = new Location(blockerRow, blockerCol);
        Board board = new Board(false);
        board.setPiece(blockingAt, new Queen(Color.WHITE));

        assertFalse(queen.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Queen_mutantTestXNegEnemyPath_returnFalse() {
        final int startRow = 7;
        final int startCol = 0;
        final int endRow = 0;
        final int endCol = 0;
        final int blockerRow = 1;
        final int blockerCol = 0;

        Piece queen = new Queen(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Location blockingAt = new Location(blockerRow, blockerCol);
        Board board = new Board(false);
        board.setPiece(blockingAt, new Queen(Color.WHITE));

        assertFalse(queen.isValidMove(start, chosen, board));
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
        Piece rook1 = new Rook(Color.BLACK);
        Piece rook2 = new Rook(Color.BLACK);

        King mockKing = EasyMock.createMock(King.class);
        EasyMock.expect(mockKing.getType()).andReturn(PieceType.KING).anyTimes();
        EasyMock.expect(mockKing.getColor()).andReturn(Color.WHITE).anyTimes();
        EasyMock.expect(mockKing.isSameColor(EasyMock.anyObject())).andReturn(true).anyTimes();
        EasyMock.expect(mockKing.makeCopy()).andReturn(mockKing).anyTimes();
        EasyMock.expect(mockKing.isInCheck(
                        EasyMock.anyObject(Location.class),
                        EasyMock.isA(Board.class)))
                .andReturn(true).anyTimes();
        EasyMock.replay(mockKing);

        Location queenPos = new Location(queenRow, queenCol);
        Location kingPos = new Location(kingRow, kingCol);
        Location rook1Pos = new Location(rook1Row, rook1Col);
        Location rook2Pos = new Location(rook2Row, rook2Col);

        Board board = new Board(false);
        board.setPiece(queenPos, queen);
        board.setPiece(kingPos, mockKing);
        board.setPiece(rook1Pos, rook1);
        board.setPiece(rook2Pos, rook2);
        board.setPiece(new Location(aboveRow, leftCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(aboveRow, queenCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(aboveRow, rightCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, leftCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, queenCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, rightCol), new Pawn(Color.WHITE));

        assertFalse(queen.hasValidMoves(queenPos, board));

        EasyMock.verify(mockKing);
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
        Location queenPos = new Location(queenRow, queenCol);
        Location enemyPos = new Location(enemyRow, enemyCol);
        Board board = new Board(false);
        board.setPiece(queenPos, queen);
        board.setPiece(enemyPos, new Pawn(Color.BLACK));

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
}
