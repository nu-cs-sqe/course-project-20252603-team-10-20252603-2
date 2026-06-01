package domain.piece;

import constants.Color;
import domain.Board;
import domain.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KingTests {

    @Test
    public void isInCheck_NoAttackingPieces_ReturnsFalse() {
        King king = new King(Color.WHITE);

        Location kingPosition = new Location(4, 4);

        Board board = new Board(false);
        board.setPiece(kingPosition, king);

        assertFalse(king.isInCheck(kingPosition, board));
    }

    @Test
    public void isInCheck_EnemyRookSameRowClearPath_ReturnsTrue() {
        King king = new King(Color.WHITE);
        Piece rook = new Rook(Color.BLACK);

        Location kingPos = new Location(4, 4);
        Location rookPos = new Location(4, 7);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(rookPos, rook);

        assertTrue(king.isInCheck(kingPos, board));
    }

    @Test
    public void isInCheck_EnemyRookBlockedByFriendly_ReturnsFalse() {
        King king = new King(Color.WHITE);
        Piece rook = new Rook(Color.BLACK);
        Piece blocker = new Pawn(Color.WHITE);
        Location kingPos = new Location(4, 4);
        Location rookPos = new Location(4, 7);
        Location blockerPiece = new Location(4, 6);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(rookPos, rook);
        board.setPiece(blockerPiece, blocker);

        assertFalse(king.isInCheck(kingPos, board));
    }

    @Test
    public void isInCheck_EnemyRookSameColClear_ReturnsTrue() {
        King king = new King(Color.WHITE);
        Piece rook = new Rook(Color.BLACK);
        Location kingPos = new Location(4, 4);
        Location rookPos = new Location(0, 4);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(rookPos, rook);

        assertTrue(king.isInCheck(kingPos, board));
    }

    @Test
    public void isInCheck_FriendlyRookSameRow_ReturnsFalse() {
        King king = new King(Color.WHITE);
        Piece rook = new Rook(Color.WHITE);
        Location kingPos = new Location(4, 4);
        Location rookPos = new Location(4, 7);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(rookPos, rook);

        assertFalse(king.isInCheck(kingPos, board));
    }

    @Test
    public void isInCheck_EnemyBishopDiagonalClear_ReturnsTrue() {
        King king = new King(Color.WHITE);
        Piece bishop = new Bishop(Color.BLACK);
        Location kingPos = new Location(4, 4);
        Location bishopPos = new Location(2, 2);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(bishopPos, bishop);

        assertTrue(king.isInCheck(kingPos, board));
    }

    @Test
    public void isInCheck_EnemyBishopBlocked_ReturnsFalse() {
        King king = new King(Color.WHITE);
        Piece bishop = new Bishop(Color.BLACK);
        Piece blocker = new Pawn(Color.WHITE);
        Location kingPos = new Location(4, 4);
        Location bishopPos = new Location(2, 2);
        Location blockerPos = new Location(3, 3);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(bishopPos, bishop);
        board.setPiece(blockerPos, blocker);

        assertFalse(king.isInCheck(kingPos, board));
    }

    @Test
    public void isInCheck_EnemyKnightLShape_ReturnsTrue() {
        King king = new King(Color.WHITE);
        Piece knight = new Knight(Color.BLACK);
        Location kingPos = new Location(4, 4);
        Location knightPos = new Location(2, 3);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(knightPos, knight);

        assertTrue(king.isInCheck(kingPos, board));
    }

    @Test
    public void isInCheck_EnemyPawnCorrectDirection_ReturnsTrue() {
        King king = new King(Color.WHITE);
        Piece pawn = new Pawn(Color.BLACK);
        Location kingPos = new Location(4, 4);
        Location pawnPos = new Location(3, 3);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(pawnPos, pawn);

        assertTrue(king.isInCheck(kingPos, board));
    }
    @Test
    public void isInCheck_EnemyPawnWrongDirection_ReturnsFalse() {
        King king = new King(Color.WHITE);
        Piece pawn = new Pawn(Color.BLACK);
        Location kingPos = new Location(4, 4);
        Location pawnPos = new Location(5, 3);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(pawnPos, pawn);

        assertFalse(king.isInCheck(kingPos, board));
    }
    @Test
    public void isInCheck_EnemyQueen_ReturnsTrue() {
        King king = new King(Color.WHITE);
        Piece queen = new Queen(Color.BLACK);
        Location kingPos = new Location(4, 4);
        Location queenPos = new Location(4, 0);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(queenPos, queen);

        assertTrue(king.isInCheck(kingPos, board));
    }

    @Test
    public void isInCheck_EnemyQueen2_ReturnsTrue() {
        King king = new King(Color.WHITE);
        Piece queen = new Queen(Color.BLACK);
        Location kingPos = new Location(4, 4);
        Location queenPos = new Location(1, 7);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(queenPos, queen);

        assertTrue(king.isInCheck(kingPos, board));
    }
    @Test
    public void isInCheck_FriendlyPawnSameDiagonal_ReturnsFalse() {
        King king = new King(Color.WHITE);
        Piece pawn = new Pawn(Color.WHITE);
        Location kingPos = new Location(4, 4);
        Location pawnPos = new Location(3, 3);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(pawnPos, pawn);

        assertFalse(king.isInCheck(kingPos, board));
    }
    @Test
    public void isInCheck_AdjacentEnemyKing_ReturnsTrue() {
        King king = new King(Color.WHITE);
        King enemyKing = new King(Color.BLACK);
        Location kingPos = new Location(4, 4);
        Location enemyPos = new Location(3, 4);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(enemyPos, enemyKing);

        assertTrue(king.isInCheck(kingPos, board));
    }

    @Test
    public void isInCheck_EnemyKingTooFar_ReturnsFalse() {
        King king = new King(Color.WHITE);
        King enemyKing = new King(Color.BLACK);
        Location kingPos = new Location(4, 4);
        Location enemyPos = new Location(2, 4);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(enemyPos, enemyKing);

        assertFalse(king.isInCheck(kingPos, board));
    }
    @Test
    public void isInCheck_EnemyRookSameRow_ReturnsTrue() {
        King king = new King(Color.WHITE);
        Piece rook = new Rook(Color.BLACK);
        Location kingPos = new Location(7, 7);
        Location rookPos = new Location(7, 0);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(rookPos, rook);

        assertTrue(king.isInCheck(kingPos, board));
    }
    @Test
    public void isValidMove_King_SameSquare_ReturnsFalse() {
        King king = new King(Color.WHITE);
        Location start = new Location(0, 0);

        Board board = new Board(false);
        board.setPiece(start, king);

        assertFalse(king.isValidMove(start, start, board));
    }
    @Test
    public void isValidMove_King_TwoSquaresHorizontal_ReturnsFalse() {
        King king = new King(Color.WHITE);
        Location start = new Location(0, 0);
        Location end = new Location(0, 2);

        Board board = new Board(false);
        board.setPiece(start, king);

        assertFalse(king.isValidMove(start, end, board));
    }
    @Test
    public void isValidMove_King_TwoSquaresVertical_ReturnsFalse() {
        King king = new King(Color.WHITE);
        Location start = new Location(7, 7);
        Location end = new Location(5, 7);

        Board board = new Board(false);
        board.setPiece(start, king);

        assertFalse(king.isValidMove(start, end, board));
    }
    @Test
    public void isValidMove_King_OneIncreasingCol_ReturnsTrue() {
        King king = new King(Color.WHITE);
        Location start = new Location(7, 4);
        Location end = new Location(7, 5);

        Board board = new Board(false);
        board.setPiece(start, king);

        assertTrue(king.isValidMove(start, end, board));
    }
    @Test
    public void isValidMove_King_OneDecreasingCol_ReturnsTrue() {
        King king = new King(Color.WHITE);
        Location start = new Location(0, 4);
        Location end = new Location(0, 3);

        Board board = new Board(false);
        board.setPiece(start, king);

        assertTrue(king.isValidMove(start, end, board));
    }
    @Test
    public void isValidMove_King_OneDecreasingRow_ReturnsTrue() {
        King king = new King(Color.WHITE);
        Location start = new Location(4, 0);
        Location end = new Location(3, 0);

        Board board = new Board(false);
        board.setPiece(start, king);

        assertTrue(king.isValidMove(start, end, board));
    }
}
