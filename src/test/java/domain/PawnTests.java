package domain;

import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.PieceColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class PawnTests {

    @Test
    public void isValidMove_Pawn_sameSquare_returnFalse() {
        Piece pawn = new Pawn(PieceColor.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(0, 0);

        Board board = new Board();

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }


}


