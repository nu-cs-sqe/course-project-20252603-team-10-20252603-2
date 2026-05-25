package domain;

import org.junit.jupiter.api.Test;
import domain.piece.*;
import constants.Color;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PieceTests {
    @Test
    public void isSameColor_bothPiecesAreBlack_returnTrue(){
        Piece piece1 = new Pawn(Color.BLACK);
        Piece piece2 = new Pawn(Color.BLACK);
        boolean sameColor = piece1.isSameColor(piece2);
        assertTrue(sameColor);
    }

    @Test
    public void isSameColor_bothPiecesAreWhite_returnTrue(){
        Piece piece1 = new Pawn(Color.WHITE);
        Piece piece2 = new Pawn(Color.WHITE);
        boolean sameColor = piece1.isSameColor(piece2);
        assertTrue(sameColor);
    }

    @Test
    public void isSameColor_diffColorsCheckingWhite_returnFalse(){
        Piece piece1 = new Pawn(Color.BLACK);
        Piece piece2 = new Pawn(Color.WHITE);
        boolean sameColor = piece1.isSameColor(piece2);
        assertFalse(sameColor);
    }

    @Test
    public void isSameColor_diffColorsCheckingBlack_returnFalse(){
        Piece piece1 = new Pawn(Color.WHITE);
        Piece piece2 = new Pawn(Color.BLACK);
        boolean sameColor = piece1.isSameColor(piece2);
        assertFalse(sameColor);
    }

}
