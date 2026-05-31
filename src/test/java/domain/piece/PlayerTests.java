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
        Player player = new Player(Color.WHITE);
        Color actual = player.getPlayerColor();
        assertEquals(actual, Color.WHITE);
    }

    @Test
    public void getPoints_ZeroPoints_ReturnsZero() {
        Player player = new Player(Color.WHITE);
        Number actual = player.getPoints();
        Number expected = 0;

        assertEquals(actual, expected);
    }

    @Test
    public void getPoints_CapturedPawn_ReturnsOne() {
        Player player = new Player(Color.WHITE);
        player.incrementPoints("pawn");

        int actual = player.getPoints();
        assertEquals(1, actual);
    }

    @Test
    public void getPoints_CapturedQueen_ReturnsNine() {
        Player player = new Player(Color.WHITE);
        player.incrementPoints("queen");

        int actual = player.getPoints();
        assertEquals(9, actual);
    }

    @Test
    public void getPoints_AllPiecesCaptured_Returns39() {
        Player player = new Player(Color.WHITE);

        List<String> pieces = new ArrayList<>(Arrays.asList("pawn", "pawn", "pawn", "pawn", "pawn", "pawn", "pawn", "pawn", "bishop",
                "bishop", "knight", "knight", "queen", "rook", "rook"));

        for (int i = 0; i < pieces.size(); i++) {
            player.incrementPoints(pieces.get(i));
        }

        int actual = player.getPoints();
        assertEquals(39, actual);
    }

    @Test
    public void getPoints_AllPiecesCapturedWithPawnPromotedToQueen_Returns47() {
        Player player = new Player(Color.WHITE);

        List<String> pieces = new ArrayList<>(Arrays.asList("pawn", "pawn", "pawn", "pawn", "pawn", "pawn", "pawn", "queen",
                "bishop", "bishop", "knight", "knight", "queen", "rook", "rook"));

        for (int i = 0; i < pieces.size(); i++) {
            player.incrementPoints(pieces.get(i));
        }

        int actual = player.getPoints();
        assertEquals(47, actual);
    }

    @Test
    public void isCurrentTurn_WhenWhitePlayer_ReturnsTrue() {
        Player player = new Player(Color.WHITE);
        boolean actual = player.isCurrentTurn();

        assertTrue(actual);
    }

    @Test
    public void isCurrentTurn_WhenBlackPlayer_ReturnsFalse() {
        Player player = new Player(Color.BLACK);
        boolean actual = player.isCurrentTurn();

        assertFalse(actual);
    }

    @Test
    public void isInCheck_ReturnsFalse() {
        Player player = new Player(Color.WHITE);
        boolean isInCheck = player.isInCheck();

        assertFalse(isInCheck);
    }
}
