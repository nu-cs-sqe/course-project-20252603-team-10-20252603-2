package domain.piece;

import domain.Board;
import domain.Location;
import constants.Color;import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.easymock.EasyMock;

public class RookTests {

    @Test
    public void isValidMove_Rook_sameSquare_returnFalse() {
        final int startRow = 0;
        final int startCol = 0;

        Piece rook = new Rook(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Board board = new Board(false);

        assertFalse(rook.isValidMove(start, start, board));
    }

    @Test
    public void isValidMove_Rook_oneRight_returnTrue() {
        final int startRow = 0;
        final int startCol = 0;
        final int endRow = 0;
        final int endCol = 1;

        Piece rook = new Rook(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);

        assertTrue(rook.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Rook_sevenRight_returnTrue() {
        final int startRow = 0;
        final int startCol = 0;
        final int endRow = 0;
        final int endCol = 7;

        Piece rook = new Rook(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);

        assertTrue(rook.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Rook_oneLeft_returnTrue() {
        final int startRow = 0;
        final int startCol = 7;
        final int endRow = 0;
        final int endCol = 6;

        Piece rook = new Rook(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);

        assertTrue(rook.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Rook_sevenLeft_returnTrue() {
        final int startRow = 0;
        final int startCol = 7;
        final int endRow = 0;
        final int endCol = 0;

        Piece rook = new Rook(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);

        assertTrue(rook.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Rook_sevenDown_returnTrue() {
        final int startRow = 0;
        final int startCol = 0;
        final int endRow = 7;
        final int endCol = 0;

        Piece rook = new Rook(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);

        assertTrue(rook.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Rook_diagonal_returnFalse() {
        final int startRow = 0;
        final int startCol = 0;
        final int endRow = 3;
        final int endCol = 3;

        Piece rook = new Rook(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);

        assertFalse(rook.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Rook_blockedHorizontal_returnFalse() {
        final int startRow = 0;
        final int startCol = 0;
        final int endRow = 0;
        final int endCol = 3;
        final int blockerRow = 0;
        final int blockerCol = 1;

        Piece rook = new Rook(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);
        board.setPiece(new Location(blockerRow, blockerCol), new Rook(Color.BLACK));

        assertFalse(rook.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Rook_blockedVertical_returnFalse() {
        final int startRow = 0;
        final int startCol = 0;
        final int endRow = 3;
        final int endCol = 0;
        final int blockerRow = 1;
        final int blockerCol = 0;

        Piece rook = new Rook(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);
        board.setPiece(new Location(blockerRow, blockerCol), new Rook(Color.BLACK));

        assertFalse(rook.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Rook_horizontalCapture_returnTrue() {
        final int startRow = 0;
        final int startCol = 0;
        final int endRow = 0;
        final int endCol = 3;

        Piece rook = new Rook(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);
        board.setPiece(chosen, new Rook(Color.BLACK));

        assertTrue(rook.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Rook_verticalCapture_returnTrue() {
        final int startRow = 0;
        final int startCol = 0;
        final int endRow = 3;
        final int endCol = 0;

        Piece rook = new Rook(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);
        board.setPiece(chosen, new Rook(Color.BLACK));

        assertTrue(rook.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Rook_oneUp_returnTrue() {
        final int startRow = 7;
        final int startCol = 0;
        final int endRow = 6;
        final int endCol = 0;

        Piece rook = new Rook(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);

        assertTrue(rook.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Rook_sevenUp_returnTrue() {
        final int startRow = 7;
        final int startCol = 0;
        final int endRow = 0;
        final int endCol = 0;

        Piece rook = new Rook(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);

        assertTrue(rook.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_blackRook_horizontalCapture_returnTrue() {
        final int startRow = 0;
        final int startCol = 0;
        final int endRow = 0;
        final int endCol = 3;

        Piece rook = new Rook(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);
        board.setPiece(chosen, new Rook(Color.WHITE));

        assertTrue(rook.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_blackRook_horizontalFriendlyDestination_returnFalse() {
        final int startRow = 0;
        final int startCol = 0;
        final int endRow = 0;
        final int endCol = 1;

        Piece rook = new Rook(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);
        board.setPiece(chosen, new Rook(Color.BLACK));

        assertFalse(rook.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Rook_horizontalFriendlyDestination_returnFalse() {
        final int startRow = 0;
        final int startCol = 0;
        final int endRow = 0;
        final int endCol = 3;

        Piece rook = new Rook(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);
        board.setPiece(chosen, new Rook(Color.WHITE));

        assertFalse(rook.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Rook_verticalFriendlyDestination_returnFalse() {
        final int startRow = 0;
        final int startCol = 0;
        final int endRow = 3;
        final int endCol = 0;

        Piece rook = new Rook(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);
        board.setPiece(chosen, new Rook(Color.WHITE));

        assertFalse(rook.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_blackRook_verticalCapture_returnTrue() {
        final int startRow = 0;
        final int startCol = 0;
        final int endRow = 7;
        final int endCol = 0;

        Piece rook = new Rook(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);
        board.setPiece(chosen, new Rook(Color.WHITE));

        assertTrue(rook.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_blackRook_oneLeftFromCorner_returnTrue() {
        final int startRow = 7;
        final int startCol = 7;
        final int endRow = 7;
        final int endCol = 6;

        Piece rook = new Rook(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);

        assertTrue(rook.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_rook_oneUpFromCorner_returnTrue() {
        final int startRow = 7;
        final int startCol = 7;
        final int endRow = 6;
        final int endCol = 7;

        Piece rook = new Rook(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);

        assertTrue(rook.isValidMove(start, chosen, board));
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

    @Test
    public void makeCopy_Rook_white_returnsNewRookWithSameColorAndType() {
        Rook original = new Rook(Color.WHITE);

        Piece copy = original.makeCopy();

        assertNotNull(copy);
        assertNotSame(original, copy);
        assertInstanceOf(Rook.class, copy);
        assertEquals(PieceType.ROOK, copy.getType());
        assertEquals(Color.WHITE, copy.getColor());
    }

    @Test
    public void isValidMove_rookExposesKingToAbsolutePin_returnsFalse() {
        final int kingRow = 7;
        final int kingCol = 0;
        final int rookRow = 6;
        final int rookCol = 0;
        final int enemyRow = 0;
        final int enemyCol = 0;
        final int illegalMoveRow = 6;
        final int illegalMoveCol = 1;

        Piece rook = new Rook(Color.WHITE);
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

        Location kingLocation = new Location(kingRow, kingCol);
        Location rookLocation = new Location(rookRow, rookCol);
        Location enemyLocation = new Location(enemyRow, enemyCol);
        Location illegalMove = new Location(illegalMoveRow, illegalMoveCol);

        Board board = new Board(false);
        board.setPiece(kingLocation, mockKing);
        board.setPiece(rookLocation, rook);
        board.setPiece(enemyLocation, enemyRook);

        assertFalse(rook.isValidMove(rookLocation, illegalMove, board));

        EasyMock.verify(mockKing);
    }

    @Test
    public void hasValidMoves_rook_notBlocked_returnsTrue() {
        final int rookRow = 6;
        final int rookCol = 0;

        Piece rook = new Rook(Color.WHITE);
        Location location = new Location(rookRow, rookCol);
        Board board = new Board(false);
        board.setPiece(location, rook);

        assertTrue(rook.hasValidMoves(location, board));
    }

    @Test
    public void hasValidMoves_rook_blockedByFriendlyPieces_returnsFalse() {
        final int rookRow = 6;
        final int rookCol = 0;
        final int friendly1Row = 5;
        final int friendly1Col = 0;
        final int friendly2Row = 7;
        final int friendly2Col = 0;
        final int friendly3Row = 6;
        final int friendly3Col = 1;

        Piece rook = new Rook(Color.WHITE);
        Location location = new Location(rookRow, rookCol);
        Board board = new Board(false);
        board.setPiece(location, rook);
        board.setPiece(new Location(friendly1Row, friendly1Col), new Rook(Color.WHITE));
        board.setPiece(new Location(friendly2Row, friendly2Col), new Pawn(Color.WHITE));
        board.setPiece(new Location(friendly3Row, friendly3Col), new Pawn(Color.WHITE));

        assertFalse(rook.hasValidMoves(location, board));
    }

    @Test
    public void hasValidMoves_rook_blockedByOneFriendlyPieces_returnsTrue() {
        final int rookRow = 5;
        final int rookCol = 4;
        final int friendlyRow = 4;
        final int friendlyCol = 4;

        Piece rook = new Rook(Color.BLACK);
        Location location = new Location(rookRow, rookCol);
        Board board = new Board(false);
        board.setPiece(location, rook);
        board.setPiece(new Location(friendlyRow, friendlyCol), new Rook(Color.BLACK));

        assertTrue(rook.hasValidMoves(location, board));
    }

    @Test
    public void hasValidMoves_rook_atCornerBlockedByFriendlyPieces_returnsFalse() {
        final int rookRow = 7;
        final int rookCol = 7;
        final int friendly1Row = 6;
        final int friendly1Col = 7;
        final int friendly2Row = 7;
        final int friendly2Col = 6;

        Piece rook = new Rook(Color.BLACK);
        Location location = new Location(rookRow, rookCol);
        Board board = new Board(false);
        board.setPiece(location, rook);
        board.setPiece(new Location(friendly1Row, friendly1Col), new Rook(Color.BLACK));
        board.setPiece(new Location(friendly2Row, friendly2Col), new Pawn(Color.BLACK));

        assertFalse(rook.hasValidMoves(location, board));
    }

    @Test
    public void hasValidMoves_rook_atCornerBlockedByEnemyPieces_returnsTrue() {
        final int rookRow = 7;
        final int rookCol = 7;
        final int enemy1Row = 6;
        final int enemy1Col = 7;
        final int enemy2Row = 7;
        final int enemy2Col = 6;

        Piece rook = new Rook(Color.BLACK);
        Location location = new Location(rookRow, rookCol);
        Board board = new Board(false);
        board.setPiece(location, rook);
        board.setPiece(new Location(enemy1Row, enemy1Col), new Rook(Color.WHITE));
        board.setPiece(new Location(enemy2Row, enemy2Col), new Rook(Color.WHITE));

        assertTrue(rook.hasValidMoves(location, board));
    }
}
