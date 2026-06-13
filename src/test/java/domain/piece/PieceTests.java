package domain.piece;

import org.junit.jupiter.api.Test;
import constants.Color;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTests {
    @Test
    public void isSameColor_bothPiecesAreBlack_returnTrue() {
        Piece piece1 = new Pawn(Color.BLACK);
        Piece piece2 = new Pawn(Color.BLACK);
        boolean sameColor = piece1.isSameColor(piece2);
        assertTrue(sameColor);
    }

    @Test
    public void isSameColor_bothPiecesAreWhite_returnTrue() {
        Piece piece1 = new Pawn(Color.WHITE);
        Piece piece2 = new Pawn(Color.WHITE);
        boolean sameColor = piece1.isSameColor(piece2);
        assertTrue(sameColor);
    }

    @Test
    public void isSameColor_diffColorsCheckingWhite_returnFalse() {
        Piece piece1 = new Pawn(Color.BLACK);
        Piece piece2 = new Pawn(Color.WHITE);
        boolean sameColor = piece1.isSameColor(piece2);
        assertFalse(sameColor);
    }

    @Test
    public void isSameColor_diffColorsCheckingBlack_returnFalse() {
        Piece piece1 = new Pawn(Color.WHITE);
        Piece piece2 = new Pawn(Color.BLACK);
        boolean sameColor = piece1.isSameColor(piece2);
        assertFalse(sameColor);
    }

    @Test
    public void getType_blackPawn_returnFalse() {
        Piece piece = new Pawn(Color.BLACK);
        assertEquals(PieceType.PAWN, piece.getType());
    }

    @Test
    public void toString_whitePawn_returnString() {
        Piece piece = new Pawn(Color.WHITE);
        assertEquals("WHITE PAWN", piece.toString());
    }

}
