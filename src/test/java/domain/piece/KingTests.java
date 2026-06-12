package domain.piece;

import constants.Color;
import domain.Board;
import domain.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class KingTests {

    @Test
    public void isInCheck_NoAttackingPieces_ReturnsFalse() {
        final int kingRow = 4;
        final int kingCol = 4;

        King king = new King(Color.WHITE);

        Location kingPosition = new Location(kingRow, kingCol);

        Board board = new Board(false);
        board.setPiece(kingPosition, king);

        assertFalse(king.isInCheck(kingPosition, board));
    }

    @Test
    public void isInCheck_EnemyRookSameRowClearPath_ReturnsTrue() {
        final int kingRow = 4;
        final int kingCol = 4;
        final int rookRow = 4;
        final int rookCol = 7;

        King king = new King(Color.WHITE);
        Piece rook = new Rook(Color.BLACK);

        Location kingPos = new Location(kingRow, kingCol);
        Location rookPos = new Location(rookRow, rookCol);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(rookPos, rook);

        assertTrue(king.isInCheck(kingPos, board));
    }

    @Test
    public void isInCheck_EnemyRookBlockedByFriendly_ReturnsFalse() {
        final int kingRow = 4;
        final int kingCol = 4;
        final int rookRow = 4;
        final int rookCol = 7;
        final int blockerRow = 4;
        final int blockerCol = 6;

        King king = new King(Color.WHITE);
        Piece rook = new Rook(Color.BLACK);
        Piece blocker = new Pawn(Color.WHITE);
        Location kingPos = new Location(kingRow, kingCol);
        Location rookPos = new Location(rookRow, rookCol);
        Location blockerPiece = new Location(blockerRow, blockerCol);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(rookPos, rook);
        board.setPiece(blockerPiece, blocker);

        assertFalse(king.isInCheck(kingPos, board));
    }

    @Test
    public void isInCheck_EnemyRookSameColClear_ReturnsTrue() {
        final int kingRow = 4;
        final int kingCol = 4;
        final int rookRow = 0;
        final int rookCol = 4;

        King king = new King(Color.WHITE);
        Piece rook = new Rook(Color.BLACK);
        Location kingPos = new Location(kingRow, kingCol);
        Location rookPos = new Location(rookRow, rookCol);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(rookPos, rook);

        assertTrue(king.isInCheck(kingPos, board));
    }

    @Test
    public void isInCheck_FriendlyRookSameRow_ReturnsFalse() {
        final int kingRow = 4;
        final int kingCol = 4;
        final int rookRow = 4;
        final int rookCol = 7;

        King king = new King(Color.WHITE);
        Piece rook = new Rook(Color.WHITE);
        Location kingPos = new Location(kingRow, kingCol);
        Location rookPos = new Location(rookRow, rookCol);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(rookPos, rook);

        assertFalse(king.isInCheck(kingPos, board));
    }

    @Test
    public void isInCheck_EnemyBishopDiagonalClear_ReturnsTrue() {
        final int kingRow = 4;
        final int kingCol = 4;
        final int row = 2;
        final int col = 2;

        King king = new King(Color.WHITE);
        Piece bishop = new Bishop(Color.BLACK);
        Location kingPos = new Location(kingRow, kingCol);
        Location bishopPos = new Location(row, col);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(bishopPos, bishop);

        assertTrue(king.isInCheck(kingPos, board));
    }

    @Test
    public void isInCheck_EnemyBishopBlocked_ReturnsFalse() {
        final int kingRow = 4;
        final int kingCol = 4;
        final int row = 2;
        final int col = 2;
        final int blockerRow = 3;
        final int blockerCol = 3;

        King king = new King(Color.WHITE);
        Piece bishop = new Bishop(Color.BLACK);
        Piece blocker = new Pawn(Color.WHITE);
        Location kingPos = new Location(kingRow, kingCol);
        Location bishopPos = new Location(row, col);
        Location blockerPos = new Location(blockerRow, blockerCol);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(bishopPos, bishop);
        board.setPiece(blockerPos, blocker);

        assertFalse(king.isInCheck(kingPos, board));
    }

    @Test
    public void isInCheck_EnemyKnightLShape_ReturnsTrue() {
        final int kingRow = 4;
        final int kingCol = 4;
        final int row = 2;
        final int col = 3;

        King king = new King(Color.WHITE);
        Piece knight = new Knight(Color.BLACK);
        Location kingPos = new Location(kingRow, kingCol);
        Location knightPos = new Location(row, col);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(knightPos, knight);

        assertTrue(king.isInCheck(kingPos, board));
    }

    @Test
    public void isInCheck_EnemyPawnCorrectDirection_ReturnsTrue() {
        final int kingRow = 4;
        final int kingCol = 4;
        final int row = 3;
        final int col = 3;

        King king = new King(Color.WHITE);
        Piece pawn = new Pawn(Color.BLACK);
        Location kingPos = new Location(kingRow, kingCol);
        Location pawnPos = new Location(row, col);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(pawnPos, pawn);

        assertTrue(king.isInCheck(kingPos, board));
    }

    @Test
    public void isInCheck_EnemyPawnWrongDirection_ReturnsFalse() {
        final int kingRow = 4;
        final int kingCol = 4;
        final int row = 5;
        final int col = 3;

        King king = new King(Color.WHITE);
        Piece pawn = new Pawn(Color.BLACK);
        Location kingPos = new Location(kingRow, kingCol);
        Location pawnPos = new Location(row, col);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(pawnPos, pawn);

        assertFalse(king.isInCheck(kingPos, board));
    }

    @Test
    public void isInCheck_EnemyQueen_ReturnsTrue() {
        final int kingRow = 4;
        final int kingCol = 4;
        final int row = 4;
        final int col = 0;

        King king = new King(Color.WHITE);
        Piece queen = new Queen(Color.BLACK);
        Location kingPos = new Location(kingRow, kingCol);
        Location queenPos = new Location(row, col);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(queenPos, queen);

        assertTrue(king.isInCheck(kingPos, board));
    }

    @Test
    public void isInCheck_EnemyQueen2_ReturnsTrue() {
        final int kingRow = 4;
        final int kingCol = 4;
        final int row = 1;
        final int col = 7;

        King king = new King(Color.WHITE);
        Piece queen = new Queen(Color.BLACK);
        Location kingPos = new Location(kingRow, kingCol);
        Location queenPos = new Location(row, col);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(queenPos, queen);

        assertTrue(king.isInCheck(kingPos, board));
    }

    @Test
    public void isInCheck_FriendlyPawnSameDiagonal_ReturnsFalse() {
        final int kingRow = 4;
        final int kingCol = 4;
        final int row = 2;
        final int col = 3;

        King king = new King(Color.WHITE);
        Piece pawn = new Pawn(Color.WHITE);
        Location kingPos = new Location(kingRow, kingCol);
        Location pawnPos = new Location(row, col);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(pawnPos, pawn);

        assertFalse(king.isInCheck(kingPos, board));
    }

    @Test
    public void isInCheck_AdjacentEnemyKing_ReturnsTrue() {
        final int kingRow = 4;
        final int kingCol = 4;
        final int row = 3;
        final int col = 4;

        King king = new King(Color.WHITE);
        King enemyKing = new King(Color.BLACK);
        Location kingPos = new Location(kingRow, kingCol);
        Location enemyPos = new Location(row, col);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(enemyPos, enemyKing);

        assertTrue(king.isInCheck(kingPos, board));
    }

    @Test
    public void isInCheck_EnemyKingTooFar_ReturnsFalse() {
        final int kingRow = 4;
        final int kingCol = 4;
        final int row = 2;
        final int col = 3;

        King king = new King(Color.WHITE);
        King enemyKing = new King(Color.BLACK);
        Location kingPos = new Location(kingRow, kingCol);
        Location enemyPos = new Location(row, col);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(enemyPos, enemyKing);

        assertFalse(king.isInCheck(kingPos, board));
    }

    @Test
    public void isInCheck_EnemyRookSameRow_ReturnsTrue() {
        final int kingRow = 7;
        final int kingCol = 7;
        final int row = 7;
        final int col = 0;

        King king = new King(Color.WHITE);
        Piece rook = new Rook(Color.BLACK);
        Location kingPos = new Location(kingRow, kingCol);
        Location rookPos = new Location(row, col);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(rookPos, rook);

        assertTrue(king.isInCheck(kingPos, board));
    }

    @Test
    public void isInCheckEnemyKingDiagonalAdjacentReturnsTrue() {
        final int kingRow = 4;
        final int kingCol = 4;
        final int enemyRow = 3;
        final int enemyCol = 3;

        King king = new King(Color.WHITE);
        King enemyKing = new King(Color.BLACK);

        Location kingPos = new Location(kingRow, kingCol);
        Location enemyPos = new Location(enemyRow, enemyCol);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(enemyPos, enemyKing);

        assertTrue(king.isInCheck(kingPos, board));
    }

    @Test
    public void isInCheckEnemyKingTooFarByColumnReturnsFalse() {
        final int kingRow = 4;
        final int kingCol = 4;
        final int enemyRow = 3;
        final int enemyCol = 2;

        King king = new King(Color.WHITE);
        King enemyKing = new King(Color.BLACK);

        Location kingPos = new Location(kingRow, kingCol);
        Location enemyPos = new Location(enemyRow, enemyCol);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(enemyPos, enemyKing);

        assertFalse(king.isInCheck(kingPos, board));
    }

    @Test
    public void isInCheckEnemyKingSameSquareReturnsFalse() {
        final int kingRow = 4;
        final int kingCol = 4;

        King king = new King(Color.WHITE);
        King enemyKing = new King(Color.BLACK);

        Location kingPos = new Location(kingRow, kingCol);

        Board board = new Board(false);
        board.setPiece(kingPos, enemyKing);

        assertFalse(king.isInCheck(kingPos, board));
    }

    @Test
    public void isInCheckEnemyKingSameRowAdjacentReturnsTrue() {
        final int kingRow = 4;
        final int kingCol = 4;
        final int enemyRow = 4;
        final int enemyCol = 3;

        King king = new King(Color.WHITE);
        King enemyKing = new King(Color.BLACK);

        Location kingPos = new Location(kingRow, kingCol);
        Location enemyPos = new Location(enemyRow, enemyCol);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(enemyPos, enemyKing);

        assertTrue(king.isInCheck(kingPos, board));
    }

    @Test
    public void isValidMoveKingSameSquareReturnsFalse() {
        final int kingRow = 0;
        final int kingCol = 0;

        King king = new King(Color.WHITE);
        Location start = new Location(kingRow, kingCol);

        Board board = new Board(false);
        board.setPiece(start, king);

        assertFalse(king.isValidMove(start, start, board));
    }

    @Test
    public void isValidMove_King_TwoSquaresHorizontal_ReturnsFalse() {
        final int kingRow = 0;
        final int kingCol = 0;
        final int row = 0;
        final int col = 2;

        King king = new King(Color.WHITE);
        Location start = new Location(kingRow, kingCol);
        Location end = new Location(row, col);

        Board board = new Board(false);
        board.setPiece(start, king);

        assertFalse(king.isValidMove(start, end, board));
    }

    @Test
    public void isValidMove_King_TwoSquaresVertical_ReturnsFalse() {
        final int kingRow = 7;
        final int kingCol = 7;
        final int row = 5;
        final int col = 7;

        King king = new King(Color.WHITE);
        Location start = new Location(kingRow, kingCol);
        Location end = new Location(row, col);

        Board board = new Board(false);
        board.setPiece(start, king);

        assertFalse(king.isValidMove(start, end, board));
    }

    @Test
    public void isValidMove_King_OneIncreasingCol_ReturnsTrue() {
        final int kingRow = 7;
        final int kingCol = 4;
        final int row = 7;
        final int col = 5;

        King king = new King(Color.WHITE);
        Location start = new Location(kingRow, kingCol);
        Location end = new Location(row, col);

        Board board = new Board(false);
        board.setPiece(start, king);

        assertTrue(king.isValidMove(start, end, board));
    }

    @Test
    public void isValidMove_King_OneDecreasingCol_ReturnsTrue() {
        final int kingRow = 0;
        final int kingCol = 4;
        final int row = 0;
        final int col = 3;

        King king = new King(Color.WHITE);
        Location start = new Location(kingRow, kingCol);
        Location end = new Location(row, col);

        Board board = new Board(false);
        board.setPiece(start, king);

        assertTrue(king.isValidMove(start, end, board));
    }

    @Test
    public void isValidMove_King_OneDecreasingRow_ReturnsTrue() {
        final int kingRow = 4;
        final int kingCol = 0;
        final int row = 3;
        final int col = 0;

        King king = new King(Color.WHITE);
        Location start = new Location(kingRow, kingCol);
        Location end = new Location(row, col);

        Board board = new Board(false);
        board.setPiece(start, king);

        assertTrue(king.isValidMove(start, end, board));
    }

    @Test
    public void isValidMove_King_OneIncreasingRow_ReturnsTrue() {
        final int kingRow = 4;
        final int kingCol = 7;
        final int row = 5;
        final int col = 7;

        King king = new King(Color.WHITE);
        Location start = new Location(kingRow, kingCol);
        Location end = new Location(row, col);

        Board board = new Board(false);
        board.setPiece(start, king);

        assertTrue(king.isValidMove(start, end, board));
    }

    @Test
    public void isValidMove_King_Diagonal1_ReturnsTrue() {
        final int kingRow = 0;
        final int kingCol = 0;
        final int row = 1;
        final int col = 1;

        King king = new King(Color.WHITE);
        Location start = new Location(kingRow, kingCol);
        Location end = new Location(row, col);

        Board board = new Board(false);
        board.setPiece(start, king);

        assertTrue(king.isValidMove(start, end, board));
    }

    @Test
    public void isValidMove_King_Diagonal2_ReturnsTrue() {
        final int kingRow = 0;
        final int kingCol = 7;
        final int row = 1;
        final int col = 6;

        King king = new King(Color.WHITE);
        Location start = new Location(kingRow, kingCol);
        Location end = new Location(row, col);

        Board board = new Board(false);
        board.setPiece(start, king);

        assertTrue(king.isValidMove(start, end, board));
    }

    @Test
    public void isValidMove_King_Diagonal3_ReturnsTrue() {
        final int kingRow = 7;
        final int kingCol = 0;
        final int endRow = 6;
        final int endCol = 1;

        King king = new King(Color.WHITE);
        Location start = new Location(kingRow, kingCol);
        Location end = new Location(endRow, endCol);

        Board board = new Board(false);
        board.setPiece(start, king);

        assertTrue(king.isValidMove(start, end, board));
    }

    @Test
    public void isValidMove_King_Diagonal4_ReturnsTrue() {
        final int kingRow = 7;
        final int kingCol = 7;
        final int endRow = 6;
        final int endCol = 6;

        King king = new King(Color.WHITE);
        Location start = new Location(kingRow, kingCol);
        Location end = new Location(endRow, endCol);

        Board board = new Board(false);
        board.setPiece(start, king);

        assertTrue(king.isValidMove(start, end, board));
    }

    @Test
    public void isValidMove_King_FriendlyDestination_ReturnsFalse() {
        final int kingRow = 7;
        final int kingCol = 7;
        final int endRow = 6;
        final int endCol = 7;

        King king = new King(Color.WHITE);
        Piece friendly = new Pawn(Color.WHITE);
        Location start = new Location(kingRow, kingCol);
        Location end = new Location(endRow, endCol);

        Board board = new Board(false);
        board.setPiece(start, king);
        board.setPiece(end, friendly);

        assertFalse(king.isValidMove(start, end, board));
    }

    @Test
    public void isValidMove_King_CaptureEnemy_ReturnsTrue() {
        final int kingRow = 0;
        final int kingCol = 0;
        final int endRow = 1;
        final int endCol = 1;

        King king = new King(Color.WHITE);
        Piece enemy = new Pawn(Color.BLACK);
        Location start = new Location(kingRow, kingCol);
        Location end = new Location(endRow, endCol);

        Board board = new Board(false);
        board.setPiece(start, king);
        board.setPiece(end, enemy);

        assertTrue(king.isValidMove(start, end, board));
    }

    @Test
    public void isValidMove_BlackKing_ValidMove_ReturnsTrue() {
        final int kingRow = 0;
        final int kingCol = 0;
        final int endRow = 0;
        final int endCol = 1;

        King king = new King(Color.BLACK);
        Location start = new Location(kingRow, kingCol);
        Location end = new Location(endRow, endCol);

        Board board = new Board(false);
        board.setPiece(start, king);

        assertTrue(king.isValidMove(start, end, board));
    }

    @Test
    public void isValidMove_King_MovesIntoCheck_ReturnsFalse() {
        final int kingRow = 4;
        final int kingCol = 4;
        final int endRow = 3;
        final int endCol = 4;
        final int rookRow = 3;
        final int rookCol = 0;

        King king = new King(Color.WHITE);
        Piece rook = new Rook(Color.BLACK);
        Location start = new Location(kingRow, kingCol);
        Location end = new Location(endRow, endCol);
        Location rookPos = new Location(rookRow, rookCol);

        Board board = new Board(false);
        board.setPiece(start, king);
        board.setPiece(rookPos, rook);

        assertFalse(king.isValidMove(start, end, board));
    }

    @Test
    public void isValidMove_King_CaptureMovesIntoCheck_ReturnsFalse() {
        final int kingRow = 4;
        final int kingCol = 4;
        final int endRow = 3;
        final int endCol = 4;
        final int rookRow = 0;
        final int rookCol = 4;

        King king = new King(Color.WHITE);
        Piece enemyPawn = new Pawn(Color.BLACK);
        Piece enemyRook = new Rook(Color.BLACK);
        Location start = new Location(kingRow, kingCol);
        Location end = new Location(endRow, endCol);
        Location rookPos = new Location(rookRow, rookCol);

        Board board = new Board(false);
        board.setPiece(start, king);
        board.setPiece(end, enemyPawn);
        board.setPiece(rookPos, enemyRook);

        assertFalse(king.isValidMove(start, end, board));
    }

    @Test
    public void isValidMove_King_EmptyDestination_ReturnsTrue_RestoresBoardState() {
        King king = new King(Color.WHITE);

        Location start = new Location(4, 4);
        Location end = new Location(4, 5);

        Board board = new Board(false);
        board.setPiece(start, king);

        assertTrue(king.isValidMove(start, end, board));

        assertSame(king, board.getPiece(start));
        assertFalse(board.isPieceHere(end));
    }

    @Test
    public void isValidMove_King_EnemyDestination_ReturnsTrue_RestoresBoardState() {
        King king = new King(Color.WHITE);
        Piece enemyPawn = new Pawn(Color.BLACK);

        Location start = new Location(4, 4);
        Location end = new Location(5, 5);

        Board board = new Board(false);
        board.setPiece(start, king);
        board.setPiece(end, enemyPawn);

        assertTrue(king.isValidMove(start, end, board));

        assertSame(king, board.getPiece(start));
        assertSame(enemyPawn, board.getPiece(end));
    }

    @Test
    public void isValidMove_KingOldSquareDoesNotBlockCheck_ReturnsFalse() {
        final int kingRow = 4;
        final int kingCol = 4;
        final int endRow = 4;
        final int endCol = 5;
        final int rookRow = 4;
        final int rookCol = 0;

        King king = new King(Color.WHITE);
        Rook rook = new Rook(Color.BLACK);

        Location start = new Location(kingRow, kingCol);
        Location end = new Location(endRow, endCol);
        Location rookPos = new Location(rookRow, rookCol);

        Board board = new Board(false);
        board.setPiece(start, king);
        board.setPiece(rookPos, rook);

        assertFalse(king.isValidMove(start, end, board));
        assertSame(king, board.getPiece(start));
        assertFalse(board.isPieceHere(end));
    }

    @Test
    public void makeCopy_King_black_returnsNewKingWithSameColorAndType() {
        King original = new King(Color.BLACK);

        Piece copy = original.makeCopy();

        assertNotNull(copy);
        assertNotSame(original, copy);
        assertInstanceOf(King.class, copy);
        assertEquals(PieceType.KING, copy.getType());
        assertEquals(Color.BLACK, copy.getColor());
    }

    @Test
    public void makeCopy_King_white_returnsNewKingWithSameColorAndType() {
        King original = new King(Color.WHITE);

        Piece copy = original.makeCopy();

        assertNotNull(copy);
        assertNotSame(original, copy);
        assertInstanceOf(King.class, copy);
        assertEquals(PieceType.KING, copy.getType());
        assertEquals(Color.WHITE, copy.getColor());
    }

    @Test
    public void hasValidMovesKingClearBoardReturnsTrue() {
        final int kingRow = 4;
        final int kingCol = 4;

        King king = new King(Color.WHITE);
        Location kingPos = new Location(kingRow, kingCol);

        Board board = new Board(false);
        board.setPiece(kingPos, king);

        assertTrue(king.hasValidMoves(kingPos, board));
    }

    @Test
    public void hasValidMoves_King_ClearBoardCorner_ReturnsTrue() {
        final int kingRow = 0;
        final int kingCol = 0;

        King king = new King(Color.WHITE);
        Location kingPos = new Location(kingRow, kingCol);

        Board board = new Board(false);
        board.setPiece(kingPos, king);

        assertTrue(king.hasValidMoves(kingPos, board));
    }

    @Test
    public void hasValidMoves_King_AllAdjacentFriendly_ReturnsFalse() {
        final int kingRow = 4;
        final int kingCol = 4;
        final int aboveRow = 3;
        final int sameRow = 4;
        final int belowRow = 5;
        final int leftCol = 3;
        final int sameCol = 4;
        final int rightCol = 5;

        King king = new King(Color.WHITE);
        Location kingPos = new Location(kingRow, kingCol);

        Board board = new Board(false);
        board.setPiece(kingPos, king);

        board.setPiece(new Location(aboveRow, leftCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(aboveRow, sameCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(aboveRow, rightCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(sameRow, leftCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(sameRow, rightCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, leftCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, sameCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, rightCol), new Pawn(Color.WHITE));

        assertFalse(king.hasValidMoves(kingPos, board));
    }

    @Test
    public void hasValidMoves_King_CornerAdjacentFriendly_ReturnsFalse() {
        final int kingRow = 0;
        final int kingCol = 0;

        King king = new King(Color.WHITE);
        Location kingPos = new Location(kingRow, kingCol);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(new Location(0, 1), new Pawn(Color.WHITE));
        board.setPiece(new Location(1, 0), new Pawn(Color.WHITE));
        board.setPiece(new Location(1, 1), new Pawn(Color.WHITE));

        assertFalse(king.hasValidMoves(kingPos, board));
    }

    @Test
    public void hasValidMoves_King_AllAdjacentAttacked_ReturnsFalse() {
        final int kingRow = 4;
        final int kingCol = 4;
        final int rook1Row = 3;
        final int rook1Col = 0;
        final int rook2Row = 5;
        final int rook2Col = 0;
        final int rook3Row = 0;
        final int rook3Col = 3;
        final int rook4Row = 0;
        final int rook4Col = 5;

        King king = new King(Color.WHITE);
        Location kingPos = new Location(kingRow, kingCol);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(new Location(rook1Row, rook1Col), new Rook(Color.BLACK));
        board.setPiece(new Location(rook2Row, rook2Col), new Rook(Color.BLACK));
        board.setPiece(new Location(rook3Row, rook3Col), new Rook(Color.BLACK));
        board.setPiece(new Location(rook4Row, rook4Col), new Rook(Color.BLACK));

        assertFalse(king.hasValidMoves(kingPos, board));
    }

    @Test
    public void hasValidMoves_King_OneSafeSquare_ReturnsTrue() {
        final int kingRow = 4;
        final int kingCol = 4;
        final int aboveRow = kingRow - 1;
        final int belowRow = kingRow + 1;
        final int leftCol = kingCol - 1;
        final int rightCol = kingCol + 1;

        King king = new King(Color.WHITE);
        Location kingPos = new Location(kingRow, kingCol);

        Board board = new Board(false);
        board.setPiece(kingPos, king);

        board.setPiece(new Location(aboveRow, leftCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(aboveRow, rightCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(kingRow, leftCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(kingRow, rightCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, leftCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, kingCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, rightCol), new Pawn(Color.WHITE));

        assertTrue(king.hasValidMoves(kingPos, board));
    }

    @Test
    public void hasValidMoves_King_UnprotectedEnemy_ReturnsTrue() {
        final int kingRow = 4;
        final int kingCol = 4;
        final int enemyRow = 3;
        final int enemyCol = 4;

        King king = new King(Color.WHITE);
        Piece enemy = new Pawn(Color.BLACK);
        Location kingPos = new Location(kingRow, kingCol);
        Location enemyPos = new Location(enemyRow, enemyCol);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(enemyPos, enemy);

        assertTrue(king.hasValidMoves(kingPos, board));
    }

    @Test
    public void hasValidMoves_BlackKing_Clear_ReturnsTrue() {
        final int kingRow = 0;
        final int kingCol = 0;

        King king = new King(Color.BLACK);
        Location kingPos = new Location(kingRow, kingCol);

        Board board = new Board(false);
        board.setPiece(kingPos, king);

        assertTrue(king.hasValidMoves(kingPos, board));
    }

    @Test
    public void hasValidMoves_King_ClearBoardMaxValue_ReturnsTrue() {
        final int kingRow = 7;
        final int kingCol = 7;

        King king = new King(Color.WHITE);
        Location kingPos = new Location(kingRow, kingCol);

        Board board = new Board(false);
        board.setPiece(kingPos, king);

        assertTrue(king.hasValidMoves(kingPos, board));
    }

    @Test
    public void hasValidMovesKingOnlyDownRightSafeReturnsTrue() {
        final int kingRow = 4;
        final int kingCol = 4;
        final int aboveRow = kingRow - 1;
        final int belowRow = kingRow + 1;
        final int leftCol = kingCol - 1;
        final int rightCol = kingCol + 1;

        King king = new King(Color.WHITE);
        Location kingPos = new Location(kingRow, kingCol);

        Board board = new Board(false);
        board.setPiece(kingPos, king);

        board.setPiece(new Location(aboveRow, leftCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(aboveRow, kingCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(aboveRow, rightCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(kingRow, leftCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(kingRow, rightCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, leftCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(belowRow, kingCol), new Pawn(Color.WHITE));

        assertTrue(king.hasValidMoves(kingPos, board));
    }

    @Test
    public void hasValidMovesKingOnlyUpperLeftSafeReturnsTrue() {
        final int kingRow = 1;
        final int kingCol = 1;
        final int upperRow = kingRow - 1;
        final int lowerRow = kingRow + 1;
        final int leftCol = kingCol - 1;
        final int rightCol = kingCol + 1;

        King king = new King(Color.WHITE);
        Location kingPos = new Location(kingRow, kingCol);

        Board board = new Board(false);
        board.setPiece(kingPos, king);

        board.setPiece(new Location(upperRow, kingCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(upperRow, rightCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(kingRow, leftCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(kingRow, rightCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(lowerRow, leftCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(lowerRow, kingCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(lowerRow, rightCol), new Pawn(Color.WHITE));

        assertTrue(king.hasValidMoves(kingPos, board));
    }

    @Test
    public void hasValidMovesKingMaxCornerAdjacentFriendlyReturnsFalse() {
        final int kingRow = 7;
        final int kingCol = 7;
        final int adjacentRow = kingRow - 1;
        final int adjacentCol = kingCol - 1;

        King king = new King(Color.WHITE);
        Location kingPos = new Location(kingRow, kingCol);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(new Location(adjacentRow, adjacentCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(adjacentRow, kingCol), new Pawn(Color.WHITE));
        board.setPiece(new Location(kingRow, adjacentCol), new Pawn(Color.WHITE));

        assertFalse(king.hasValidMoves(kingPos, board));
    }

}


