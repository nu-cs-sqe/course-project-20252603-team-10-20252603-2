package domain;

import org.junit.jupiter.api.Test;
import domain.piece.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PieceTests {

    @Test
    public void isSameColor_bothPiecesAreBlack_returnTrue(){
        Piece piece1 = new Pawn(PieceColor.BLACK);
        Piece piece2 = new Pawn(PieceColor.BLACK);
        boolean sameColor = piece1.isSameColor(piece2);
        assertTrue(sameColor);
    }

    @Test
    public void isSameColor_bothPiecesAreWhite_returnTrue(){
        Piece piece1 = new Pawn(PieceColor.WHITE);
        Piece piece2 = new Pawn(PieceColor.WHITE);
        boolean sameColor = piece1.isSameColor(piece2);
        assertTrue(sameColor);
    }
}
