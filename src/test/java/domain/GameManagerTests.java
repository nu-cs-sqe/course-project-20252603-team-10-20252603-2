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

    @Test
    public void start_WithThreePlayers_ThrowsException() {
        GameManager game = new GameManager();
        game.addPlayer(new Player(Color.BLACK));
        game.addPlayer(new Player(Color.WHITE));
        game.addPlayer(new Player(Color.BLACK));
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            game.start();
        });

        assertTrue(exception.getMessage().contains("Maximum number of players allowed is 2."));
        assertFalse(game.isGameRunning());
    }

    @Test
    public void start_WithTwoPlayers_IsSuccessful() {
        GameManager game = new GameManager();
        game.addPlayer(new Player(Color.BLACK));
        game.addPlayer(new Player(Color.WHITE));

        assertDoesNotThrow(() -> game.start());
        assertTrue(game.isGameRunning());
    }

    @Test
    public void start_WithTwoPlayersOfTheSameColor_ThrowsException() {
        GameManager game = new GameManager();
        game.addPlayer(new Player(Color.BLACK));
        game.addPlayer(new Player(Color.BLACK));

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            game.start();
        });

        assertTrue(exception.getMessage().contains("Players cannot have the same color."));
        assertFalse(game.isGameRunning());
    }

    @Test
    public void changeTurns_StartsWithWhite_IsTrue() {
        GameManager game = new GameManager();
        game.addPlayer(new Player(Color.BLACK));
        game.addPlayer(new Player(Color.WHITE));

        game.start();

        Color actual = game.getCurrentPlayer().getPlayerColor();
        Color expected = Color.WHITE;

        assertEquals(actual, expected);
    }

    @Test
    public void changeTurns_WhiteToBlack_IsSuccessful() {
        GameManager game = new GameManager();
        game.addPlayer(new Player(Color.BLACK));
        game.addPlayer(new Player(Color.WHITE));

        game.start();
        game.changeTurns();

        Color actual = game.getCurrentPlayer().getPlayerColor();
        Color expected = Color.BLACK;

        assertEquals(actual, expected);
    }

    @Test
    public void changeTurns_BlackToWhite_IsSuccessful() {
        GameManager game = new GameManager();
        game.addPlayer(new Player(Color.BLACK));
        game.addPlayer(new Player(Color.WHITE));

        game.start();
        game.changeTurns();
        game.changeTurns();

        Color actual = game.getCurrentPlayer().getPlayerColor();
        Color expected = Color.WHITE;

        assertEquals(actual, expected);
    }
}
