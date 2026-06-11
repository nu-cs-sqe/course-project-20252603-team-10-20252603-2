package domain.piece;

import constants.Color;
import domain.Board;
import domain.Location;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void hasValidMoves_Bishop_ClearBoardCenter_ReturnsTrue() {
        final int bishopRow = 4;
        final int bishopCol = 4;

        Bishop bishop = new Bishop(Color.WHITE);
        Location bishopPos = new Location(bishopRow, bishopCol);

        Board board = new Board(false);
        board.setPiece(bishopPos, bishop);

        assertTrue(bishop.hasValidMoves(bishopPos, board));
    }

    @Test
    public void hasValidMoves_Bishop_ClearBoardMinimum_ReturnsTrue() {
        final int bishopRow = 0;
        final int bishopCol = 0;

        Bishop bishop = new Bishop(Color.WHITE);
        Location bishopPos = new Location(bishopRow, bishopCol);

        Board board = new Board(false);
        board.setPiece(bishopPos, bishop);

        assertTrue(bishop.hasValidMoves(bishopPos, board));
    }

    @Test
    public void hasValidMoves_Bishop_AllFriendlySurrounding_ReturnsFalse() {
        final int bishopRow = 4;
        final int bishopCol = 4;
        final int aboveRow = bishopRow - 1;
        final int belowRow = bishopRow + 1;
        final int leftCol = bishopCol - 1;
        final int rightCol = bishopCol + 1;

        Bishop bishop = new Bishop(Color.WHITE);
        Location bishopPos = new Location(bishopRow, bishopCol);

        Board board = new Board(false);
        board.setPiece(bishopPos, bishop);
        board.setPiece(new Location(aboveRow, leftCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(aboveRow, rightCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, leftCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, rightCol), new Pawn(Color.WHITE));

        assertFalse(bishop.hasValidMoves(bishopPos, board));
    }

    @Test
    public void hasValidMoves_Bishop_CornerFriendlyDiagonal_ReturnsFalse() {
        final int bishopRow = 0;
        final int bishopCol = 0;
        final int diagonalRow = bishopRow + 1;
        final int diagonalCol = bishopCol + 1;

        Bishop bishop = new Bishop(Color.WHITE);
        Location bishopPos = new Location(bishopRow, bishopCol);

        Board board = new Board(false);
        board.setPiece(bishopPos, bishop);
        board.setPiece(new Location(diagonalRow, diagonalCol), new Pawn(Color.WHITE));

        assertFalse(bishop.hasValidMoves(bishopPos, board));
    }

    @Test
    public void hasValidMoves_Bishop_MovesKingIntoCheck_ReturnsFalse() {
        final int bishopRow = 4;
        final int bishopCol = 4;
        final int kingRow = 4;
        final int kingCol = 0;
        final int rookRow = 4;
        final int rookCol = 7;

        Bishop bishop = new Bishop(Color.WHITE);
        King mockKing = EasyMock.createMock(King.class);
        Piece rook = new Rook(Color.BLACK);

        Location bishopPos = new Location(bishopRow, bishopCol);
        Location kingPos = new Location(kingRow, kingCol);
        Location rookPos = new Location(rookRow, rookCol);

        EasyMock.expect(mockKing.getType()).andReturn(PieceType.KING).anyTimes();
        EasyMock.expect(mockKing.getColor()).andReturn(Color.WHITE).anyTimes();
        EasyMock.expect(mockKing.isSameColor(EasyMock.anyObject())).andReturn(true).anyTimes();
        EasyMock.expect(mockKing.makeCopy()).andReturn(mockKing).anyTimes();

        EasyMock.expect(mockKing.isInCheck(
                        EasyMock.anyObject(Location.class),
                        EasyMock.isA(Board.class)))
                .andReturn(true).anyTimes();

        EasyMock.replay(mockKing);

        Board board = new Board(false);
        board.setPiece(bishopPos, bishop);
        board.setPiece(kingPos, mockKing);
        board.setPiece(rookPos, rook);

        assertFalse(bishop.hasValidMoves(bishopPos, board));

        EasyMock.verify(mockKing);
    }

    @Test
    public void hasValidMoves_Bishop_OneDiagonalFree_ReturnsTrue() {
        final int bishopRow = 4;
        final int bishopCol = 4;
        final int aboveRow = bishopRow - 1;
        final int belowRow = bishopRow + 1;
        final int leftCol = bishopCol - 1;
        final int rightCol = bishopCol + 1;

        Bishop bishop = new Bishop(Color.WHITE);
        Location bishopPos = new Location(bishopRow, bishopCol);

        Board board = new Board(false);
        board.setPiece(bishopPos, bishop);

        board.setPiece(new Location(aboveRow, rightCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, leftCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, rightCol), new Pawn(Color.WHITE));

        assertTrue(bishop.hasValidMoves(bishopPos, board));
    }

    @Test
    public void hasValidMoves_Bishop_UnprotectedDiagonalEnemy_ReturnsTrue() {
        final int bishopRow = 4;
        final int bishopCol = 4;
        final int enemyRow = 3;
        final int enemyCol = 3;

        Bishop bishop = new Bishop(Color.WHITE);
        Piece enemy = new Pawn(Color.BLACK);
        Location bishopPos = new Location(bishopRow, bishopCol);
        Location enemyPos = new Location(enemyRow, enemyCol);

        Board board = new Board(false);
        board.setPiece(bishopPos, bishop);
        board.setPiece(enemyPos, enemy);

        assertTrue(bishop.hasValidMoves(bishopPos, board));
    }
    @Test
    public void hasValidMoves_BlackBishop_ClearBoardCorner_ReturnsTrue() {
        final int bishopRow = 7;
        final int bishopCol = 7;

        Bishop bishop = new Bishop(Color.BLACK);
        Location bishopPos = new Location(bishopRow, bishopCol);

        Board board = new Board(false);
        board.setPiece(bishopPos, bishop);

        assertTrue(bishop.hasValidMoves(bishopPos, board));
    }
}
