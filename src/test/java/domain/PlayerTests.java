package domain.piece;

import constants.Color;
import domain.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTests {
    @Test
    public void getColor_PlayerWhite_ReturnsWhite() {
        Player player = new Player("Player1", Color.WHITE);
        Color actual = player.getPlayerColor();
        assertEquals(Color.WHITE, actual);
    }

    @Test
    public void getColor_playerBlack_returnsBlack() {
        Player player = new Player("Player1", Color.BLACK);
        Color actual = player.getPlayerColor();
        assertEquals(Color.BLACK, actual);
    }

    @Test
    public void getPoints_zeroPoints_returnsZero() {
        Player player = new Player("Player1", Color.WHITE);
        Number actual = player.getPoints();
        Number expected = 0;

        assertEquals(expected, actual);
    }

    @Test
    public void getPoints_CapturedPawn_ReturnsOne() {
        Player player = new Player("Player1", Color.WHITE);
        player.incrementPoints(PieceType.PAWN);

        int actual = player.getPoints();
        assertEquals(1, actual);
    }

    @Test
    public void getPoints_CapturedQueen_ReturnsNine() {
        Player player = new Player("Player1", Color.WHITE);
        player.incrementPoints(PieceType.QUEEN);

        int actual = player.getPoints();
        assertEquals(9, actual);
    }

    @Test
    public void getPoints_AllPiecesCaptured_Returns39() {
        Player player = new Player("Player1", Color.WHITE);

        List<PieceType> pieces = new ArrayList<>(Arrays.asList(PieceType.PAWN, PieceType.PAWN, PieceType.PAWN, PieceType.PAWN, PieceType.PAWN, PieceType.PAWN, PieceType.PAWN, PieceType.PAWN, PieceType.BISHOP,
                PieceType.BISHOP, PieceType.KNIGHT, PieceType.KNIGHT, PieceType.QUEEN, PieceType.ROOK, PieceType.ROOK));

        for (int i = 0; i < pieces.size(); i++) {
            player.incrementPoints(pieces.get(i));
        }

        int actual = player.getPoints();
        assertEquals(39, actual);
    }

    @Test
    public void getPoints_AllPiecesCapturedWithPawnPromotedToQueen_Returns47() {
        Player player = new Player("Player1", Color.WHITE);

        List<PieceType> pieces = new ArrayList<>(Arrays.asList(PieceType.PAWN, PieceType.PAWN, PieceType.PAWN, PieceType.PAWN, PieceType.PAWN, PieceType.PAWN, PieceType.PAWN, PieceType.QUEEN,
                PieceType.BISHOP, PieceType.BISHOP, PieceType.KNIGHT, PieceType.KNIGHT, PieceType.QUEEN, PieceType.ROOK, PieceType.ROOK));

        for (int i = 0; i < pieces.size(); i++) {
            player.incrementPoints(pieces.get(i));
        }

        int actual = player.getPoints();
        assertEquals(47, actual);
    }

    @Test
    public void isInCheck_ReturnsFalse() {
        Player player = new Player("Player1", Color.WHITE);
        boolean isInCheck = player.isInCheck();

        assertFalse(isInCheck);
    }
}
