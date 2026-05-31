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
}
