package domain;

import domain.piece.Rook;
import domain.piece.Piece;
import domain.piece.PieceColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class RookTests {

    @Test
    public void isValidMove_Rook_sameSquare_returnFalse() {
        Piece rook = new Rook(PieceColor.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(0, 0);

        Board board = new Board(false);

        boolean result = rook.isValidMove(start, chosen, board);

        assertFalse(result);
    }
}
