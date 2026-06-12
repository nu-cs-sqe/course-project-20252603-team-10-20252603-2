package domain.piece;

import domain.Board;
import domain.Location;
import constants.Color;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class KnightTests {

    @Test
    public void isValidMove_Knight_Lmov1_returnTrue() {
        final int startRow = 3;
        final int startCol = 3;
        final int endRow = 5;
        final int endCol = 2;

        Piece knight = new Knight(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov2_returnTrue() {
        final int startRow = 3;
        final int startCol = 3;
        final int endRow = 5;
        final int endCol = 4;

        Piece knight = new Knight(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov3_returnTrue() {
        final int startRow = 3;
        final int startCol = 3;
        final int endRow = 4;
        final int endCol = 1;

        Piece knight = new Knight(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov4_returnTrue() {
        final int startRow = 3;
        final int startCol = 3;
        final int endRow = 4;
        final int endCol = 5;

        Piece knight = new Knight(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov5_returnTrue() {
        final int startRow = 3;
        final int startCol = 3;
        final int endRow = 1;
        final int endCol = 2;

        Piece knight = new Knight(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov6_returnTrue() {
        final int startRow = 3;
        final int startCol = 3;
        final int endRow = 1;
        final int endCol = 4;

        Piece knight = new Knight(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov7_returnTrue() {
        final int startRow = 3;
        final int startCol = 3;
        final int endRow = 2;
        final int endCol = 1;

        Piece knight = new Knight(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov8_returnTrue() {
        final int startRow = 3;
        final int startCol = 3;
        final int endRow = 2;
        final int endCol = 5;

        Piece knight = new Knight(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_starting00_returnTrue() {
        final int startRow = 0;
        final int startCol = 0;
        final int endRow = 2;
        final int endCol = 1;

        Piece knight = new Knight(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_starting77_returnTrue() {
        final int startRow = 7;
        final int startCol = 7;
        final int endRow = 6;
        final int endCol = 5;

        Piece knight = new Knight(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_ending00_returnTrue() {
        final int startRow = 2;
        final int startCol = 1;
        final int endRow = 0;
        final int endCol = 0;

        Piece knight = new Knight(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_ending77_returnTrue() {
        final int startRow = 6;
        final int startCol = 5;
        final int endRow = 7;
        final int endCol = 7;

        Piece knight = new Knight(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov1blockedPathVert1_returnTrue() {
        final int startRow = 3;
        final int startCol = 3;
        final int blockerRow = 4;
        final int blockerCol = 3;
        final int endRow = 5;
        final int endCol = 2;

        Piece knight = new Knight(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location blocker = new Location(blockerRow, blockerCol);
        Location chosen = new Location(endRow, endCol);

        Board board = new Board(false);

        Piece pawnBlocker = new Pawn(Color.WHITE);
        board.setPiece(blocker, pawnBlocker);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov1blockedEndEnemy_returnTrue() {
        final int startRow = 3;
        final int startCol = 3;
        final int endRow = 5;
        final int endCol = 2;

        Piece knight = new Knight(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);

        Board board = new Board(false);

        Piece pawnBlocker = new Pawn(Color.WHITE);
        board.setPiece(chosen, pawnBlocker);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov1blockedEndFriendly_returnFalse() {
        final int startRow = 3;
        final int startCol = 3;
        final int endRow = 5;
        final int endCol = 2;

        Piece knight = new Knight(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);

        Board board = new Board(false);

        Piece pawnBlocker = new Pawn(Color.BLACK);
        board.setPiece(chosen, pawnBlocker);

        boolean result = knight.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Knight_xMovement_returnFalse() {
        final int startRow = 3;
        final int startCol = 3;
        final int endRow = 5;
        final int endCol = 3;

        Piece knight = new Knight(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Knight_yMovement_returnFalse() {
        final int startRow = 3;
        final int startCol = 3;
        final int endRow = 3;
        final int endCol = 5;

        Piece knight = new Knight(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Knight_diagonal_returnFalse() {
        final int startRow = 3;
        final int startCol = 3;
        final int endRow = 5;
        final int endCol = 5;

        Piece knight = new Knight(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Knight_sameSquare_returnFalse() {
        final int startRow = 3;
        final int startCol = 3;

        Piece knight = new Knight(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Board board = new Board(false);

        assertFalse(knight.isValidMove(start, start, board));
    }

    @Test
    public void isValidMove_Knight_Lmov1blockedPathVert2_returnTrue() {
        final int startRow = 3;
        final int startCol = 3;
        final int blockerRow = 5;
        final int blockerCol = 3;
        final int endRow = 5;
        final int endCol = 2;

        Piece knight = new Knight(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location blocker = new Location(blockerRow, blockerCol);
        Location chosen = new Location(endRow, endCol);

        Board board = new Board(false);

        Piece pawnBlocker = new Pawn(Color.WHITE);
        board.setPiece(blocker, pawnBlocker);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov3blockedPathHoriz1_returnTrue() {
        final int startRow = 3;
        final int startCol = 3;
        final int endRow = 4;
        final int endCol = 1;
        final int blockerRow = 4;
        final int blockerCol = 3;

        Piece knight = new Knight(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Location blocker = new Location(blockerRow, blockerCol);

        Board board = new Board(false);

        Piece pawnBlocker = new Pawn(Color.BLACK);
        board.setPiece(blocker, pawnBlocker);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Knight_Lmov3blockedPathHoriz2_returnTrue() {
        final int startRow = 3;
        final int startCol = 3;
        final int endRow = 4;
        final int endCol = 1;
        final int blockerRow = 4;
        final int blockerCol = 2;

        Piece knight = new Knight(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Location blocker = new Location(blockerRow, blockerCol);

        Board board = new Board(false);

        Piece pawnBlocker = new Pawn(Color.WHITE);
        board.setPiece(blocker, pawnBlocker);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void makeCopy_Knight_black_returnsNewKnightWithSameColorAndType() {
        Knight original = new Knight(Color.BLACK);

        Piece copy = original.makeCopy();

        assertNotNull(copy);
        assertNotSame(original, copy);
        assertInstanceOf(Knight.class, copy);
        assertEquals(PieceType.KNIGHT, copy.getType());
        assertEquals(Color.BLACK, copy.getColor());
    }

    @Test
    public void makeCopy_Knight_white_returnsNewKnightWithSameColorAndType() {
        Knight original = new Knight(Color.WHITE);

        Piece copy = original.makeCopy();

        assertNotNull(copy);
        assertNotSame(original, copy);
        assertInstanceOf(Knight.class, copy);
        assertEquals(PieceType.KNIGHT, copy.getType());
        assertEquals(Color.WHITE, copy.getColor());
    }

    public void hasValidMoves_knight_notBlocked_returnsTrue() {
        final int knightRow = 6;
        final int knightCol = 0;

        Piece knight = new Knight(Color.WHITE);
        Location location = new Location(knightRow, knightCol);

        Board board = new Board(false);
        board.setPiece(location, knight);

        boolean result = knight.hasValidMoves(location, board);

        assertTrue(result);
    }

    @Test
    public void hasValidMoves_knight_blockedByFriendlyPieces_returnsFalse() {
        final int knightRow = 6;
        final int knightCol = 0;
        final int friendly1Row = 4;
        final int friendly1Col = 1;
        final int friendly2Row = 5;
        final int friendly2Col = 2;
        final int friendly3Row = 7;
        final int friendly3Col = 2;

        Piece knight = new Knight(Color.WHITE);
        Location location = new Location(knightRow, knightCol);
        Board board = new Board(false);
        board.setPiece(location, knight);
        board.setPiece(new Location(friendly1Row, friendly1Col), new Knight(Color.WHITE));
        board.setPiece(new Location(friendly2Row, friendly2Col), new Knight(Color.WHITE));
        board.setPiece(new Location(friendly3Row, friendly3Col), new Knight(Color.WHITE));

        assertFalse(knight.hasValidMoves(location, board));
    }

    @Test
    public void hasValidMoves_knight_blockedByOneFriendlyPiece_returnsTrue() {
        final int knightRow = 5;
        final int knightCol = 4;
        final int friendlyRow = 3;
        final int friendlyCol = 5;

        Piece knight = new Knight(Color.BLACK);
        Piece friendlyKnight1 = new Knight(Color.BLACK);

        Location location = new Location(knightRow, knightCol);
        Location friendlyKnightLocation1 = new Location(friendlyRow, friendlyCol);


        Board board = new Board(false);
        board.setPiece(location, knight);
        board.setPiece(friendlyKnightLocation1, friendlyKnight1);

        boolean result = knight.hasValidMoves(location, board);

        assertTrue(result);
    }

    @Test
    public void hasValidMoves_knight_atEdgeBlockedByTwoFriendlyPieces_returnsFalse() {
        final int knightRow = 7;
        final int knightCol = 0;
        final int friendly1Row = 5;
        final int friendly1Col = 1;
        final int friendly2Row = 6;
        final int friendly2Col = 2;

        Piece knight = new Knight(Color.BLACK);
        Location location = new Location(knightRow, knightCol);
        Board board = new Board(false);
        board.setPiece(location, knight);
        board.setPiece(new Location(friendly1Row, friendly1Col), new Knight(Color.BLACK));
        board.setPiece(new Location(friendly2Row, friendly2Col), new Knight(Color.BLACK));

        boolean result = knight.hasValidMoves(location, board);

        assertFalse(result);
    }

    @Test
    public void hasValidMoves_knight_byKingAndEnemyPiece_returnsFalse() {
        final int knightRow = 7;
        final int knightCol = 1;
        final int rookRow = 7;
        final int rookCol = 7;
        final int kingRow = 7;
        final int kingCol = 0;

        Piece knight = new Knight(Color.WHITE);
        Piece enemyRook = new Rook(Color.BLACK);

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

        Location location = new Location(knightRow, knightCol);
        Location enemyRookLocation = new Location(rookRow, rookCol);
        Location kingLocation = new Location(kingRow, kingCol);

        Board board = new Board(false);
        board.setPiece(location, knight);
        board.setPiece(enemyRookLocation, enemyRook);
        board.setPiece(kingLocation, mockKing);

        assertFalse(knight.hasValidMoves(location, board));

        EasyMock.verify(mockKing);
    }

    @Test
    public void hasValidMoves_knight_blockedByEnemyPiece_returnsTrue() {
        final int knightRow = 7;
        final int knightCol = 1;
        final int enemyRow = 5;
        final int enemyCol = 2;

        Piece knight = new Knight(Color.WHITE);
        Location location = new Location(knightRow, knightCol);
        Board board = new Board(false);
        board.setPiece(location, knight);
        board.setPiece(new Location(enemyRow, enemyCol), new Rook(Color.BLACK));

        assertTrue(knight.hasValidMoves(location, board));
    }

    @Test
    public void hasValidMoves_knight_onlyL1OpenReturnsTrue() {
        final int row = 4;
        final int col = 4;

        Knight knight = new Knight(Color.WHITE);
        Location pos = new Location(row, col);
        Board board = new Board(false);
        board.setPiece(pos, knight);

        board.setPiece(new Location(row + 2, col + 1), new Knight(Color.WHITE));
        board.setPiece(new Location(row + 1, col - 2), new Knight(Color.WHITE));
        board.setPiece(new Location(row + 1, col + 2), new Knight(Color.WHITE));
        board.setPiece(new Location(row - 2, col - 1), new Knight(Color.WHITE));
        board.setPiece(new Location(row - 2, col + 1), new Knight(Color.WHITE));
        board.setPiece(new Location(row - 1, col - 2), new Knight(Color.WHITE));
        board.setPiece(new Location(row - 1, col + 2), new Knight(Color.WHITE));

        assertTrue(knight.hasValidMoves(pos, board));
    }

    @Test
    public void hasValidMoves_knight_onlyL2OpenReturnsTrue() {
        final int row = 4;
        final int col = 4;

        Knight knight = new Knight(Color.WHITE);
        Location pos = new Location(row, col);
        Board board = new Board(false);
        board.setPiece(pos, knight);

        board.setPiece(new Location(row + 2, col - 1), new Knight(Color.WHITE));
        board.setPiece(new Location(row + 1, col - 2), new Knight(Color.WHITE));
        board.setPiece(new Location(row + 1, col + 2), new Knight(Color.WHITE));
        board.setPiece(new Location(row - 2, col - 1), new Knight(Color.WHITE));
        board.setPiece(new Location(row - 2, col + 1), new Knight(Color.WHITE));
        board.setPiece(new Location(row - 1, col - 2), new Knight(Color.WHITE));
        board.setPiece(new Location(row - 1, col + 2), new Knight(Color.WHITE));

        assertTrue(knight.hasValidMoves(pos, board));
    }

    @Test
    public void hasValidMoves_knight_onlyL3OpenReturnsTrue() {
        final int row = 4;
        final int col = 4;

        Knight knight = new Knight(Color.WHITE);
        Location pos = new Location(row, col);
        Board board = new Board(false);
        board.setPiece(pos, knight);

        board.setPiece(new Location(row + 2, col - 1), new Knight(Color.WHITE));
        board.setPiece(new Location(row + 2, col + 1), new Knight(Color.WHITE));
        board.setPiece(new Location(row + 1, col + 2), new Knight(Color.WHITE));
        board.setPiece(new Location(row - 2, col - 1), new Knight(Color.WHITE));
        board.setPiece(new Location(row - 2, col + 1), new Knight(Color.WHITE));
        board.setPiece(new Location(row - 1, col - 2), new Knight(Color.WHITE));
        board.setPiece(new Location(row - 1, col + 2), new Knight(Color.WHITE));

        assertTrue(knight.hasValidMoves(pos, board));
    }

    @Test
    public void hasValidMoves_knight_onlyL4OpenReturnsTrue() {
        final int row = 4;
        final int col = 4;

        Knight knight = new Knight(Color.WHITE);
        Location pos = new Location(row, col);
        Board board = new Board(false);
        board.setPiece(pos, knight);

        board.setPiece(new Location(row + 2, col - 1), new Knight(Color.WHITE));
        board.setPiece(new Location(row + 2, col + 1), new Knight(Color.WHITE));
        board.setPiece(new Location(row + 1, col - 2), new Knight(Color.WHITE));
        board.setPiece(new Location(row - 2, col - 1), new Knight(Color.WHITE));
        board.setPiece(new Location(row - 2, col + 1), new Knight(Color.WHITE));
        board.setPiece(new Location(row - 1, col - 2), new Knight(Color.WHITE));
        board.setPiece(new Location(row - 1, col + 2), new Knight(Color.WHITE));

        assertTrue(knight.hasValidMoves(pos, board));
    }

    @Test
    public void hasValidMoves_knight_onlyL5OpenReturnsTrue() {
        final int row = 4;
        final int col = 4;

        Knight knight = new Knight(Color.WHITE);
        Location pos = new Location(row, col);
        Board board = new Board(false);
        board.setPiece(pos, knight);

        board.setPiece(new Location(row + 2, col - 1), new Knight(Color.WHITE));
        board.setPiece(new Location(row + 2, col + 1), new Knight(Color.WHITE));
        board.setPiece(new Location(row + 1, col - 2), new Knight(Color.WHITE));
        board.setPiece(new Location(row + 1, col + 2), new Knight(Color.WHITE));
        board.setPiece(new Location(row - 2, col + 1), new Knight(Color.WHITE));
        board.setPiece(new Location(row - 1, col - 2), new Knight(Color.WHITE));
        board.setPiece(new Location(row - 1, col + 2), new Knight(Color.WHITE));

        assertTrue(knight.hasValidMoves(pos, board));
    }

    @Test
    public void hasValidMoves_knight_onlyL6OpenReturnsTrue() {
        final int row = 4;
        final int col = 4;

        Knight knight = new Knight(Color.WHITE);
        Location pos = new Location(row, col);
        Board board = new Board(false);
        board.setPiece(pos, knight);

        board.setPiece(new Location(row + 2, col - 1), new Knight(Color.WHITE));
        board.setPiece(new Location(row + 2, col + 1), new Knight(Color.WHITE));
        board.setPiece(new Location(row + 1, col - 2), new Knight(Color.WHITE));
        board.setPiece(new Location(row + 1, col + 2), new Knight(Color.WHITE));
        board.setPiece(new Location(row - 2, col - 1), new Knight(Color.WHITE));
        board.setPiece(new Location(row - 1, col - 2), new Knight(Color.WHITE));
        board.setPiece(new Location(row - 1, col + 2), new Knight(Color.WHITE));

        assertTrue(knight.hasValidMoves(pos, board));
    }

    @Test
    public void hasValidMoves_knight_onlyL7OpenReturnsTrue() {
        final int row = 4;
        final int col = 4;

        Knight knight = new Knight(Color.WHITE);
        Location pos = new Location(row, col);
        Board board = new Board(false);
        board.setPiece(pos, knight);

        board.setPiece(new Location(row + 2, col - 1), new Knight(Color.WHITE));
        board.setPiece(new Location(row + 2, col + 1), new Knight(Color.WHITE));
        board.setPiece(new Location(row + 1, col - 2), new Knight(Color.WHITE));
        board.setPiece(new Location(row + 1, col + 2), new Knight(Color.WHITE));
        board.setPiece(new Location(row - 2, col - 1), new Knight(Color.WHITE));
        board.setPiece(new Location(row - 2, col + 1), new Knight(Color.WHITE));
        board.setPiece(new Location(row - 1, col + 2), new Knight(Color.WHITE));

        assertTrue(knight.hasValidMoves(pos, board));
    }

    @Test
    public void hasValidMoves_knight_onlyL8OpenReturnsTrue() {
        final int row = 4;
        final int col = 4;

        Knight knight = new Knight(Color.WHITE);
        Location pos = new Location(row, col);
        Board board = new Board(false);
        board.setPiece(pos, knight);

        board.setPiece(new Location(row + 2, col - 1), new Knight(Color.WHITE));
        board.setPiece(new Location(row + 2, col + 1), new Knight(Color.WHITE));
        board.setPiece(new Location(row + 1, col - 2), new Knight(Color.WHITE));
        board.setPiece(new Location(row + 1, col + 2), new Knight(Color.WHITE));
        board.setPiece(new Location(row - 2, col - 1), new Knight(Color.WHITE));
        board.setPiece(new Location(row - 2, col + 1), new Knight(Color.WHITE));
        board.setPiece(new Location(row - 1, col - 2), new Knight(Color.WHITE));

        assertTrue(knight.hasValidMoves(pos, board));
    }

    @Test
    public void isValidMove_Knight_vacatingSquareExposesKing_returnsFalse() {
        final int knightRow = 3;
        final int knightCol = 0;
        final int endRow = 1;
        final int endCol = 1;
        final int kingRow = 0;
        final int kingCol = 0;
        final int rookRow = 7;
        final int rookCol = 0;

        Knight knight = new Knight(Color.WHITE);
        King king = new King(Color.WHITE);
        Piece rook = new Rook(Color.BLACK);

        Location knightPos = new Location(knightRow, knightCol);
        Location end = new Location(endRow, endCol);
        Location kingPos = new Location(kingRow, kingCol);
        Location rookPos = new Location(rookRow, rookCol);

        Board board = new Board(false);
        board.setPiece(knightPos, knight);
        board.setPiece(kingPos, king);
        board.setPiece(rookPos, rook);

        assertFalse(knight.isValidMove(knightPos, end, board));
    }

    @Test
    public void isValidMove_Knight_boardRestoredAfterCapture_returnsTrue() {
        final int knightRow = 4;
        final int knightCol = 4;
        final int endRow = 2;
        final int endCol = 3;

        Knight knight = new Knight(Color.WHITE);
        Piece enemy = new Pawn(Color.BLACK);
        Location start = new Location(knightRow, knightCol);
        Location end = new Location(endRow, endCol);
        Board board = new Board(false);
        board.setPiece(start, knight);
        board.setPiece(end, enemy);

        assertTrue(knight.isValidMove(start, end, board));
        assertSame(knight, board.getPiece(start));
        assertSame(enemy, board.getPiece(end));
    }

    @Test
    public void isValidMove_Knight_landingSquareBlocksCheck_returnsTrue() {
        final int knightRow = 1;
        final int knightCol = 2;
        final int endRow = 2;
        final int endCol = 0;
        final int kingRow = 7;
        final int kingCol = 0;
        final int rookRow = 0;
        final int rookCol = 0;

        Knight knight = new Knight(Color.WHITE);
        King king = new King(Color.WHITE);
        Piece rook = new Rook(Color.BLACK);

        Location knightPos = new Location(knightRow, knightCol);
        Location end = new Location(endRow, endCol);
        Location kingPos = new Location(kingRow, kingCol);
        Location rookPos = new Location(rookRow, rookCol);

        Board board = new Board(false);
        board.setPiece(knightPos, knight);
        board.setPiece(kingPos, king);
        board.setPiece(rookPos, rook);

        assertTrue(knight.isValidMove(knightPos, end, board));
    }

    @Test
    public void isValidMove_Knight_boardRestoredAfterMoveToEmptySquare_returnsTrue() {
        final int knightRow = 4;
        final int knightCol = 4;
        final int endRow = 2;
        final int endCol = 3;

        Knight knight = new Knight(Color.WHITE);
        Location start = new Location(knightRow, knightCol);
        Location end = new Location(endRow, endCol);
        Board board = new Board(false);
        board.setPiece(start, knight);

        assertTrue(knight.isValidMove(start, end, board));
        assertSame(knight, board.getPiece(start));
        assertFalse(board.isPieceHere(end));
    }

    @Test
    public void hasValidMoves_knight_atMaxCornerOnlyTwoCandidatesValid_returnsTrue() {
        final int row = 7;
        final int col = 7;

        Knight knight = new Knight(Color.WHITE);
        Location pos = new Location(row, col);
        Board board = new Board(false);
        board.setPiece(pos, knight);

        board.setPiece(new Location(5, 6), new Knight(Color.WHITE));

        assertTrue(knight.hasValidMoves(pos, board));
    }
}

