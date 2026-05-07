package domain;

import constants.Color;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.expect;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTests {
    @Test
    public void getColor_PlayerWhite_ReturnsWhite() {
        Player player = new Player(Color.WHITE);
        Color actual = player.getPlayerColor();
        assertEquals(actual, Color.WHITE);
    }

    @Test
    public void getPoints_ZeroPoints_ReturnsZero(){
        Player player = new Player(Color.WHITE);
        Number actual = player.getPoints();
        Number expected = 0;

        assertEquals(actual, expected);
    }

    @Test
    public void getPoints_CapturedPawn_ReturnsOne() {
        Player player = new Player(Color.WHITE);
        player.addCapturedPieceAndIncrementPoints("pawn");

        int actual = player.getPoints();
        assertEquals(1, actual);
    }

    @Test
    public void getPoints_AllPiecesCaptured_Returns39() {
        Player player = new Player(Color.WHITE);
        player.addCapturedPieceAndIncrementPoints("pawn");
        player.addCapturedPieceAndIncrementPoints("pawn");
        player.addCapturedPieceAndIncrementPoints("pawn");
        player.addCapturedPieceAndIncrementPoints("pawn");
        player.addCapturedPieceAndIncrementPoints("pawn");
        player.addCapturedPieceAndIncrementPoints("pawn");
        player.addCapturedPieceAndIncrementPoints("pawn");
        player.addCapturedPieceAndIncrementPoints("pawn");
        player.addCapturedPieceAndIncrementPoints("knight");
        player.addCapturedPieceAndIncrementPoints("knight");
        player.addCapturedPieceAndIncrementPoints("rook");
        player.addCapturedPieceAndIncrementPoints("rook");
        player.addCapturedPieceAndIncrementPoints("bishop");
        player.addCapturedPieceAndIncrementPoints("bishop");
        player.addCapturedPieceAndIncrementPoints("queen");

        int actual = player.getPoints();
        assertEquals(39, actual);
    }

    @Test
    public void getPoints_AllPiecesCapturedWithPawnPromotedToQueen_Returns() {
        Player player = new Player(Color.WHITE);
        player.addCapturedPieceAndIncrementPoints("pawn");
        player.addCapturedPieceAndIncrementPoints("pawn");
        player.addCapturedPieceAndIncrementPoints("pawn");
        player.addCapturedPieceAndIncrementPoints("pawn");
        player.addCapturedPieceAndIncrementPoints("pawn");
        player.addCapturedPieceAndIncrementPoints("pawn");
        player.addCapturedPieceAndIncrementPoints("pawn");
        player.addCapturedPieceAndIncrementPoints("queen");
        player.addCapturedPieceAndIncrementPoints("knight");
        player.addCapturedPieceAndIncrementPoints("knight");
        player.addCapturedPieceAndIncrementPoints("rook");
        player.addCapturedPieceAndIncrementPoints("rook");
        player.addCapturedPieceAndIncrementPoints("bishop");
        player.addCapturedPieceAndIncrementPoints("bishop");
        player.addCapturedPieceAndIncrementPoints("queen");

        int actual = player.getPoints();
        assertEquals(47, actual);
    }
}
