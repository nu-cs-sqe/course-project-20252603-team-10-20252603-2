package domain;

import org.junit.jupiter.api.Test;
import domain.piece.*;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTests {
    @Test
    public void isSameColor_bothPiecesAreBlack_returnTrue() {
        Piece piece1 = new Pawn(PieceColor.BLACK);
        Piece piece2 = new Pawn(PieceColor.BLACK);
        boolean sameColor = piece1.isSameColor(piece2);
        assertTrue(sameColor);
    }

    @Test
    public void isSameColor_bothPiecesAreWhite_returnTrue() {
        Piece piece1 = new Pawn(PieceColor.WHITE);
        Piece piece2 = new Pawn(PieceColor.WHITE);
        boolean sameColor = piece1.isSameColor(piece2);
        assertTrue(sameColor);
    }

    @Test
    public void isSameColor_diffColorsCheckingWhite_returnFalse() {
        Piece piece1 = new Pawn(PieceColor.BLACK);
        Piece piece2 = new Pawn(PieceColor.WHITE);
        boolean sameColor = piece1.isSameColor(piece2);
        assertFalse(sameColor);
    }

    @Test
    public void isSameColor_diffColorsCheckingBlack_returnFalse() {
        Piece piece1 = new Pawn(PieceColor.WHITE);
        Piece piece2 = new Pawn(PieceColor.BLACK);
        boolean sameColor = piece1.isSameColor(piece2);
        assertFalse(sameColor);
    }

    @Test
    public void getType_blackPawn_returnFalse() {
        Piece piece = new Pawn(PieceColor.BLACK);
        assertEquals(PieceType.PAWN, piece.getType());
    }

    @Test
    public void toString_whitePawn_returnString() {
        Piece piece = new Pawn(PieceColor.WHITE);
        assertEquals("WHITE PAWN", piece.toString());
    }

}
