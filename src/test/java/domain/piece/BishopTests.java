package domain;

import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Rook;
import domain.piece.Pawn;
import domain.piece.Piece;
import constants.Color;
import domain.piece.PieceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    public void isValidMove_Bishop_EmptyDestination_returnTrue_RestoresBoardAfterSimulation() {
        Bishop bishop = new Bishop(Color.WHITE);
        King king = new King(Color.WHITE);

        Location start = new Location(2, 0);
        Location end = new Location(3, 1);
        Location kingPos = new Location(0, 0);

        Board board = new Board(false);
        board.setPiece(start, bishop);
        board.setPiece(kingPos, king);

        boolean result = bishop.isValidMove(start, end, board);

        assertTrue(result);
        assertSame(bishop, board.getPiece(start));
        assertFalse(board.isPieceHere(end));
    }

    @Test
    public void isValidMove_Bishop_CaptureDestination_returnsTrue_RestoresOriginalTargetAfterSimulation() {
        Bishop bishop = new Bishop(Color.WHITE);
        King king = new King(Color.WHITE);
        Pawn enemyPawn = new Pawn(Color.BLACK);

        Location start = new Location(0, 0);
        Location end = new Location(2, 2);
        Location kingPos = new Location(7, 7);

        Board board = new Board(false);
        board.setPiece(start, bishop);
        board.setPiece(end, enemyPawn);
        board.setPiece(kingPos, king);

        boolean result = bishop.isValidMove(start, end, board);

        assertTrue(result);
        assertSame(bishop, board.getPiece(start));
        assertSame(enemyPawn, board.getPiece(end));
    }

    @Test
    public void isValidMove_Bishop_BlocksCheck_ReturnsTrue() {
        Bishop bishop = new Bishop(Color.WHITE);
        King king = new King(Color.WHITE);
        Bishop enemyBishop = new Bishop(Color.BLACK);

        Location kingPos = new Location(0, 0);
        Location bishopStart = new Location(2, 0);
        Location bishopEnd = new Location(1, 1);
        Location enemyBishopPos = new Location(3, 3);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(bishopStart, bishop);
        board.setPiece(enemyBishopPos, enemyBishop);

        boolean result = bishop.isValidMove(bishopStart, bishopEnd, board);

        assertTrue(result);
    }

    @Test
    public void isValidMove_Bishop_SkipsNonAlliedKingPiecesAndFindsAlliedKing_ReturnsTrue() {
        Bishop bishop = new Bishop(Color.WHITE);
        Pawn enemyPawn = new Pawn(Color.BLACK);
        Pawn sameColorPawn = new Pawn(Color.WHITE);
        King king = new King(Color.WHITE);

        Location enemyPawnPos = new Location(0, 0);
        Location sameColorPawnPos = new Location(0, 1);
        Location kingPos = new Location(0, 2);

        Location bishopStart = new Location(2, 0);
        Location bishopEnd = new Location(3, 1);

        Board board = new Board(false);
        board.setPiece(enemyPawnPos, enemyPawn);
        board.setPiece(sameColorPawnPos, sameColorPawn);
        board.setPiece(kingPos, king);
        board.setPiece(bishopStart, bishop);

        boolean result = bishop.isValidMove(bishopStart, bishopEnd, board);

        assertTrue(result);
    }

    @Test
    public void makeCopy_Bishop_black_returnsNewBishopWithSameColorAndType() {
        Bishop original = new Bishop(Color.BLACK);

        Piece copy = original.makeCopy();

        assertNotNull(copy);
        assertNotSame(original, copy);
        assertInstanceOf(Bishop.class, copy);
        assertEquals(PieceType.BISHOP, copy.getType());
        assertEquals(Color.BLACK, copy.getColor());
    }

    @Test
    public void makeCopy_Bishop_white_returnsNewBishopWithSameColorAndType() {
        Bishop original = new Bishop(Color.WHITE);

        Piece copy = original.makeCopy();

        assertNotNull(copy);
        assertNotSame(original, copy);
        assertInstanceOf(Bishop.class, copy);
        assertEquals(PieceType.BISHOP, copy.getType());
        assertEquals(Color.WHITE, copy.getColor());
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
        King king = new King(Color.WHITE);
        Piece rook = new Rook(Color.BLACK);

        Location bishopPos = new Location(bishopRow, bishopCol);
        Location kingPos = new Location(kingRow, kingCol);
        Location rookPos = new Location(rookRow, rookCol);

        Board board = new Board(false);
        board.setPiece(bishopPos, bishop);
        board.setPiece(kingPos, king);
        board.setPiece(rookPos, rook);

        assertFalse(bishop.hasValidMoves(bishopPos, board));
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

    @Test
    public void hasValidMoves_Bishop_OnlyUpLeftOpen_ReturnsTrue() {
        Bishop bishop = new Bishop(Color.WHITE);
        Location bishopPos = new Location(4, 4);

        Board board = new Board(false);
        board.setPiece(bishopPos, bishop);

        board.setPiece(new Location(3, 5), new Pawn(Color.WHITE));
        board.setPiece(new Location(5, 3), new Pawn(Color.WHITE));
        board.setPiece(new Location(5, 5), new Pawn(Color.WHITE));

        assertTrue(bishop.hasValidMoves(bishopPos, board));
    }

    @Test
    public void hasValidMoves_Bishop_OnlyUpRightOpen_ReturnsTrue() {
        Bishop bishop = new Bishop(Color.WHITE);
        Location bishopPos = new Location(4, 4);

        Board board = new Board(false);
        board.setPiece(bishopPos, bishop);

        board.setPiece(new Location(3, 3), new Pawn(Color.WHITE));
        board.setPiece(new Location(5, 3), new Pawn(Color.WHITE));
        board.setPiece(new Location(5, 5), new Pawn(Color.WHITE));

        assertTrue(bishop.hasValidMoves(bishopPos, board));
    }

    @Test
    public void hasValidMoves_Bishop_OnlyDownLeftOpen_ReturnsTrue() {
        Bishop bishop = new Bishop(Color.WHITE);
        Location bishopPos = new Location(4, 4);

        Board board = new Board(false);
        board.setPiece(bishopPos, bishop);

        board.setPiece(new Location(3, 3), new Pawn(Color.WHITE));
        board.setPiece(new Location(3, 5), new Pawn(Color.WHITE));
        board.setPiece(new Location(5, 5), new Pawn(Color.WHITE));

        assertTrue(bishop.hasValidMoves(bishopPos, board));
    }

    @Test
    public void hasValidMoves_Bishop_OnlyDownRightOpen_ReturnsTrue() {
        Bishop bishop = new Bishop(Color.WHITE);
        Location bishopPos = new Location(4, 4);

        Board board = new Board(false);
        board.setPiece(bishopPos, bishop);

        board.setPiece(new Location(3, 3), new Pawn(Color.WHITE));
        board.setPiece(new Location(3, 5), new Pawn(Color.WHITE));
        board.setPiece(new Location(5, 3), new Pawn(Color.WHITE));

        assertTrue(bishop.hasValidMoves(bishopPos, board));
    }

    @Test
    public void hasValidMoves_BishopFarUpLeftEnemy_ReturnsTrue() {
        Bishop bishop = new Bishop(Color.WHITE);
        Pawn enemyPawn = new Pawn(Color.BLACK);

        Location bishopPos = new Location(4, 4);

        Board board = new Board(false);
        board.setPiece(bishopPos, bishop);
        board.setPiece(new Location(2, 2), enemyPawn);

        board.setPiece(new Location(3, 5), new Pawn(Color.WHITE));
        board.setPiece(new Location(5, 3), new Pawn(Color.WHITE));
        board.setPiece(new Location(5, 5), new Pawn(Color.WHITE));

        assertTrue(bishop.hasValidMoves(bishopPos, board));
    }

    @Test
    public void hasValidMoves_BishopOnlyEdgeDestinationAvailable_ReturnsTrue() {
        Bishop bishop = new Bishop(Color.WHITE);
        Location bishopPos = new Location(1, 1);

        Board board = new Board(false);
        board.setPiece(bishopPos, bishop);

        board.setPiece(new Location(2, 0), new Pawn(Color.WHITE));
        board.setPiece(new Location(2, 2), new Pawn(Color.WHITE));
        board.setPiece(new Location(0, 2), new Pawn(Color.WHITE));

        assertTrue(bishop.hasValidMoves(bishopPos, board));
    }

}
