package domain.piece;

import domain.Board;
import domain.Location;
import constants.Color;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertSame;

public class PawnTests {

    @Test
    public void isValidMove_Pawn_sameSquare_returnFalse() {
        final int startRow = 0;
        final int startCol = 0;

        Piece pawn = new Pawn(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Board board = new Board(false);

        assertFalse(pawn.isValidMove(start, start, board));
    }

    @Test
    public void isValidMove_Pawn_sameSquare77edge_returnFalse() {
        final int startRow = 7;
        final int startCol = 7;

        Piece pawn = new Pawn(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Board board = new Board(false);

        assertFalse(pawn.isValidMove(start, start, board));
    }

    @Test
    public void isValidMove_Pawn_tooFar_returnFalse() {
        final int startRow = 0;
        final int startCol = 0;
        final int endRow = 7;
        final int endCol = 7;

        Piece pawn = new Pawn(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);

        assertFalse(pawn.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Pawn_oneForward_returnTrue() {
        final int startRow = 7;
        final int startCol = 7;
        final int endRow = 6;
        final int endCol = 7;

        Piece pawn = new Pawn(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);

        assertTrue(pawn.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Pawn_twoForward_returnTrue() {
        final int startRow = 1;
        final int startCol = 0;
        final int endRow = 3;
        final int endCol = 0;

        Piece pawn = new Pawn(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);

        assertTrue(pawn.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Pawn_twoForwardBlockedEnd_returnFalse() {
        final int startRow = 1;
        final int startCol = 0;
        final int endRow = 3;
        final int endCol = 0;

        Piece pawn = new Pawn(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);
        board.setPiece(chosen, new Pawn(Color.WHITE));

        assertFalse(pawn.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Pawn_oneDiagonalRight_returnTrue() {
        final int startRow = 7;
        final int startCol = 6;
        final int endRow = 6;
        final int endCol = 7;

        Piece pawn = new Pawn(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);
        board.setPiece(chosen, new Pawn(Color.BLACK));

        assertTrue(pawn.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Pawn_oneDiagonalLeft_returnTrue() {
        final int startRow = 7;
        final int startCol = 6;
        final int endRow = 6;
        final int endCol = 5;

        Piece pawn = new Pawn(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);
        board.setPiece(chosen, new Pawn(Color.BLACK));

        assertTrue(pawn.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Pawn_oneDiagonalLeftSquareEmpty_returnFalse() {
        final int startRow = 7;
        final int startCol = 6;
        final int endRow = 6;
        final int endCol = 5;

        Piece pawn = new Pawn(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);

        assertFalse(pawn.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Pawn_oneDiagonalLeftSquareFriendly_returnFalse() {
        final int startRow = 7;
        final int startCol = 6;
        final int endRow = 6;
        final int endCol = 5;

        Piece pawn = new Pawn(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);
        board.setPiece(chosen, new Pawn(Color.WHITE));

        assertFalse(pawn.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Pawn_backwards_returnFalse() {
        final int startRow = 6;
        final int startCol = 6;
        final int endRow = 7;
        final int endCol = 6;

        Piece pawn = new Pawn(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);

        assertFalse(pawn.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Pawn_sideways_returnFalse() {
        final int startRow = 6;
        final int startCol = 6;
        final int endRow = 6;
        final int endCol = 5;

        Piece pawn = new Pawn(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);

        assertFalse(pawn.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Pawn_twoForwardBlockedPath_returnFalse() {
        final int startRow = 0;
        final int startCol = 0;
        final int midRow = 1;
        final int midCol = 0;
        final int endRow = 2;
        final int endCol = 0;

        Piece pawn = new Pawn(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location mid = new Location(midRow, midCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);
        board.setPiece(mid, new Pawn(Color.WHITE));

        assertFalse(pawn.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Pawn_blackTwoForwardNotStartRow_returnFalse() {
        final int startRow = 2;
        final int startCol = 0;
        final int endRow = 4;
        final int endCol = 0;

        Piece pawn = new Pawn(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location chosen = new Location(endRow, endCol);
        Board board = new Board(false);

        assertFalse(pawn.isValidMove(start, chosen, board));
    }

    @Test
    public void isValidMove_Pawn_whiteTwoForwardNotStartRow_returnFalse() {
        Piece pawn = new Pawn(Color.WHITE);

        Location start = new Location(5, 0);
        Location chosen = new Location(3, 0);

        Board board = new Board(false);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Pawn_whiteOneForwardBlocked_returnFalse() {
        Piece pawn = new Pawn(Color.WHITE);
        Piece blocker = new Pawn(Color.BLACK);

        Location start = new Location(6, 0);
        Location chosen = new Location(5, 0);

        Board board = new Board(false);
        board.setPiece(chosen, blocker);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Pawn_movingExposesKingToCheck_returnsFalse() {
        final int pawnRow = 6;
        final int pawnCol = 4;
        final int endRow = 5;
        final int endCol = 5;
        final int kingRow = 7;
        final int kingCol = 4;
        final int rookRow = 0;
        final int rookCol = 4;

        Piece pawn = new Pawn(Color.WHITE);
        King king = new King(Color.WHITE);
        Piece rook = new Rook(Color.BLACK);
        Piece enemyToCapture = new Pawn(Color.BLACK);

        Location pawnStart = new Location(pawnRow, pawnCol);
        Location pawnEnd = new Location(endRow, endCol);
        Location kingPos = new Location(kingRow, kingCol);
        Location rookPos = new Location(rookRow, rookCol);

        Board board = new Board(false);
        board.setPiece(pawnStart, pawn);
        board.setPiece(pawnEnd, enemyToCapture);
        board.setPiece(kingPos, king);
        board.setPiece(rookPos, rook);

        assertFalse(pawn.isValidMove(pawnStart, pawnEnd, board));
    }

    @Test
    public void isValidMove_Pawn_boardRestoredAfterOneForward_returnsTrue() {
        final int startRow = 6;
        final int startCol = 0;
        final int endRow = 5;
        final int endCol = 0;

        Piece pawn = new Pawn(Color.WHITE);
        Location start = new Location(startRow, startCol);
        Location end = new Location(endRow, endCol);
        Board board = new Board(false);
        board.setPiece(start, pawn);

        assertTrue(pawn.isValidMove(start, end, board));
        assertSame(pawn, board.getPiece(start));
        assertFalse(board.isPieceHere(end));
    }

    @Test
    public void isValidMove_Pawn_boardRestoredAfterDiagonalCapture_returnsTrue() {
        final int startRow = 6;
        final int startCol = 4;
        final int endRow = 5;
        final int endCol = 5;

        Piece pawn = new Pawn(Color.WHITE);
        Piece enemy = new Pawn(Color.BLACK);
        Location start = new Location(startRow, startCol);
        Location end = new Location(endRow, endCol);
        Board board = new Board(false);
        board.setPiece(start, pawn);
        board.setPiece(end, enemy);

        assertTrue(pawn.isValidMove(start, end, board));
        assertSame(pawn, board.getPiece(start));
        assertSame(enemy, board.getPiece(end));
    }

    @Test
    public void hasValidMoves_Pawn_onlyDiagonalRightCaptureOpen_returnsTrue() {
        final int pawnRow = 6;
        final int pawnCol = 4;
        final int forwardRow = 5;
        final int rightCol = 5;

        Piece pawn = new Pawn(Color.WHITE);
        Location pawnPos = new Location(pawnRow, pawnCol);
        Board board = new Board(false);
        board.setPiece(pawnPos, pawn);
        board.setPiece(new Location(forwardRow, pawnCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(forwardRow, rightCol), new Pawn(Color.BLACK));

        assertTrue(pawn.hasValidMoves(pawnPos, board));
    }

    @Test
    public void hasValidMoves_Pawn_NotBlocked_returnsTrue() {
        final int pawnRow = 6;
        final int pawnCol = 0;

        Piece pawn = new Pawn(Color.WHITE);
        Location location = new Location(pawnRow, pawnCol);
        Board board = new Board(false);
        board.setPiece(location, pawn);

        assertTrue(pawn.hasValidMoves(location, board));
    }

    @Test
    public void hasValidMoves_PawnBlockedVertically_returnsFalse() {
        final int pawnRow = 6;
        final int pawnCol = 0;
        final int enemyRow = 4;
        final int enemyCol = 0;
        final int friendlyRow = 5;
        final int friendlyCol = 0;

        Piece pawn = new Pawn(Color.WHITE);
        Location location = new Location(pawnRow, pawnCol);
        Location enemyLocation = new Location(enemyRow, enemyCol);
        Location friendlyLocation = new Location(friendlyRow, friendlyCol);
        Board board = new Board(false);
        board.setPiece(location, pawn);
        board.setPiece(enemyLocation, new Pawn(Color.BLACK));
        board.setPiece(friendlyLocation, new Pawn(Color.WHITE));

        assertFalse(pawn.hasValidMoves(location, board));
    }

    @Test
    public void isValidMove_Pawn_whiteTwoForwardMidBlocked_returnFalse() {
        Piece pawn = new Pawn(Color.WHITE);
        Piece blocker = new Pawn(Color.BLACK);

        Location start = new Location(6, 0);
        Location chosen = new Location(4, 0);
        Location blockLocation = new Location(5, 0);

        Board board = new Board(false);
        board.setPiece(blockLocation, blocker);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Pawn_whiteTwoForward_returnTrue() {
        Piece pawn = new Pawn(Color.WHITE);

        Location start = new Location(6, 0);
        Location chosen = new Location(4, 0);

        Board board = new Board(false);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertTrue(result);
    }

    @Test
    public void hasValidMoves_PawnBlockedDiagonally_returnsTrue() {
        final int pawnRow = 5;
        final int pawnCol = 4;
        final int enemyRow = 4;
        final int enemyCol = 5;
        final int friendlyRow = 4;
        final int friendlyCol = 4;

        Piece pawn = new Pawn(Color.BLACK);
        Location location = new Location(pawnRow, pawnCol);
        Location enemyLocation = new Location(enemyRow, enemyCol);
        Location friendlyLocation = new Location(friendlyRow, friendlyCol);
        Board board = new Board(false);
        board.setPiece(location, pawn);
        board.setPiece(enemyLocation, new Pawn(Color.WHITE));
        board.setPiece(friendlyLocation, new Pawn(Color.BLACK));

        assertTrue(pawn.hasValidMoves(location, board));
    }

    @Test
    public void hasValidMoves_PawnAtEdge_returnsFalse() {
        final int pawnRow = 7;
        final int pawnCol = 3;

        Piece pawn = new Pawn(Color.BLACK);
        Location location = new Location(pawnRow, pawnCol);
        Board board = new Board(false);
        board.setPiece(location, pawn);

        assertFalse(pawn.hasValidMoves(location, board));
    }

    @Test
    public void hasValidMoves_pawnTrapped_returnsFalse() {
        final int pawnRow = 7;
        final int pawnCol = 1;
        final int kingRow = 7;
        final int kingCol = 0;
        final int rookRow = 7;
        final int rookCol = 7;

        Piece pawn = new Pawn(Color.WHITE);
        Piece rook = new Rook(Color.BLACK);

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

        Location pawnLocation = new Location(pawnRow, pawnCol);
        Location kingLocation = new Location(kingRow, kingCol);
        Location rookLocation = new Location(rookRow, rookCol);

        Board board = new Board(false);
        board.setPiece(pawnLocation, pawn);
        board.setPiece(kingLocation, mockKing);
        board.setPiece(rookLocation, rook);

        assertFalse(pawn.hasValidMoves(pawnLocation, board));

        EasyMock.verify(mockKing);
    }
}
