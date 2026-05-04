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

        Board board = new Board(false);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Pawn_sameSquare77edge_returnFalse() {
        Piece pawn = new Pawn(PieceColor.BLACK);

        Location start = new Location(7, 7);
        Location chosen = new Location(7, 7);

        Board board = new Board(false);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }

    @Test
    public void isValidMove_Pawn_tooFar_returnFalse() {
        Piece pawn = new Pawn(PieceColor.WHITE);

        Location start = new Location(0, 0);
        Location chosen = new Location(7, 7);

        Board board = new Board(false);

        boolean result = pawn.isValidMove(start, chosen, board);

        assertFalse(result);
    }


}


