package domain;

import domain.piece.Knight;
import domain.piece.Piece;
import domain.piece.PieceColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KnightTests {

    @Test
    public void isValidMove_Knight_Lmov1_returnTrue() {
        Piece knight = new Knight(PieceColor.WHITE);

        Location start = new Location(3, 3);
        Location chosen = new Location(5, 2);

        Board board = new Board(false);

        boolean result = knight.isValidMove(start, chosen, board);

        assertTrue(result);
    }

}
