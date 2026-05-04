package domain;

import constants.Color;
import org.junit.jupiter.api.Test;

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
}
