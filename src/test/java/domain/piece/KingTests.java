package domain.piece;

import domain.Board;
import domain.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KingTests {

    @Test
    public void isInCheck_NoAttackingPieces_ReturnsFalse() {
        King king = new King(PieceColor.WHITE);

        Location kingPosition = new Location(4, 4);

        Board board = new Board(false);
        board.setPiece(kingPosition, king);

        assertFalse(king.isInCheck(kingPosition, board));
    }

    @Test
    public void isInCheck_EnemyRookSameRowClearPath_ReturnsTrue() {
        King king = new King(PieceColor.WHITE);
        Piece rook = new Rook(PieceColor.BLACK);

        Location kingPos = new Location(4, 4);
        Location rookPos = new Location(4, 7);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(rookPos, rook);

        assertTrue(king.isInCheck(kingPos, board));
    }

    @Test
    public void isInCheck_EnemyRookBlockedByFriendly_ReturnsFalse() {
        King king = new King(PieceColor.WHITE);
        Piece rook = new Rook(PieceColor.BLACK);
        Piece blocker = new Pawn(PieceColor.WHITE);
        Location kingPos = new Location(4, 4);
        Location rookPos = new Location(4, 7);
        Location blockerPiece = new Location(4, 6);

        Board board = new Board(false);
        board.setPiece(kingPos, king);
        board.setPiece(rookPos, rook);
        board.setPiece(blockerPiece, blocker);

        assertFalse(king.isInCheck(kingPos, board));
    }
}
