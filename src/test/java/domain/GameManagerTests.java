package domain;

import constants.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameManagerTests {

    @Test
    public void start_WithOnePlayer_ThrowsException() {
        GameManager game = new GameManager();
        game.addPlayer(new Player(Color.BLACK));

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            game.start();
        });

        assertTrue(exception.getMessage().contains("Not enough players in the game."));
        assertFalse(game.isGameRunning());
    }
}
