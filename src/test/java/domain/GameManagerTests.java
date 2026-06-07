package domain;

import constants.Color;
import domain.piece.Pawn;
import domain.piece.Piece;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameManagerTests {

    @Test
    public void start_WithZeroPlayers_ThrowsException() {
        GameManager game = new GameManager();

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            game.start();
        });

        assertTrue(exception.getMessage().contains("Not enough players in the game."));
        assertFalse(game.isGameRunning());
    }

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

    @Test
    public void changeTurns_Draw_ThrowsException() {
        GameManager game = new GameManager();
        game.addPlayer(new Player(Color.BLACK));
        game.addPlayer(new Player(Color.WHITE));
        game.start();

        for (int i = 0; i < 50; i++) {
            game.incrementDrawCounter();
        }

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            game.changeTurns();
        });

        assertTrue(exception.getMessage().contains("Game is a draw."));
    }

    @Test
    public void isGameOver_KingNotInCheck_ReturnsFalse() {
        GameManager game = new GameManager();

        Player blackPlayer = new Player(Color.BLACK);
        Player whitePlayer = new Player(Color.WHITE);
        game.addPlayer(blackPlayer);
        game.addPlayer(whitePlayer);
        game.start();

        Board mockedBoard = EasyMock.createMock(Board.class);

        List<Piece> dummyValidPieces = List.of(new Pawn(Color.WHITE));
        EasyMock.expect(mockedBoard.getValidPiecesByColor(Color.WHITE))
                .andReturn(dummyValidPieces).anyTimes();

        EasyMock.replay(mockedBoard);

        game.setBoard(mockedBoard);

        boolean gameOver = game.isGameOver();

        assertFalse(gameOver);
        EasyMock.verify(mockedBoard);
    }

    @Test
    public void isGameOver_DrawConditionMet_ReturnsTrue() {
        GameManager game = new GameManager();
        game.addPlayer(new Player(Color.BLACK));
        game.addPlayer(new Player(Color.WHITE));
        game.start();

        for (int i = 0; i < 50; i++) {
            game.incrementDrawCounter();
        }

        boolean gameOver = game.isGameOver();
        assertTrue(gameOver);
    }

    @Test
    public void isGameOver_Checkmate_ReturnsTrue() {
        GameManager game = new GameManager();

        Player mockedPlayer = EasyMock.createMock(Player.class);
        Board mockedBoard = EasyMock.createMock(Board.class);

        EasyMock.expect(mockedPlayer.getPlayerColor()).andReturn(Color.WHITE).anyTimes();
        EasyMock.expect(mockedPlayer.isInCheck()).andReturn(true).anyTimes();

        EasyMock.expect(mockedBoard.getValidPiecesByColor(Color.WHITE))
                .andReturn(new ArrayList<>()).anyTimes();

        EasyMock.replay(mockedPlayer, mockedBoard);

        game.addPlayer(mockedPlayer);
        game.addPlayer(new Player(Color.BLACK));
        game.assignPlayers(mockedPlayer, new Player(Color.BLACK));
        game.setBoard(mockedBoard);

        assertTrue(game.isGameOver());
        EasyMock.verify(mockedPlayer, mockedBoard);
    }

    @Test
    public void isGameOver_PlayerHasMoves_ReturnsFalse() {
        GameManager game = new GameManager();
        Player mockedPlayer = EasyMock.createMock(Player.class);
        Board mockedBoard = EasyMock.createMock(Board.class);

        EasyMock.expect(mockedPlayer.getPlayerColor()).andReturn(Color.WHITE).anyTimes();
        EasyMock.expect(mockedPlayer.isInCheck()).andReturn(false).anyTimes();

        List<Piece> validPieces = List.of(new Pawn(Color.WHITE));
        EasyMock.expect(mockedBoard.getValidPiecesByColor(Color.WHITE))
                .andReturn(validPieces).anyTimes();

        EasyMock.replay(mockedPlayer, mockedBoard);

        game.addPlayer(mockedPlayer);
        game.addPlayer(new Player(Color.BLACK));
        game.assignPlayers(mockedPlayer, new Player(Color.BLACK));
        game.setBoard(mockedBoard);

        assertFalse(game.isGameOver());
        EasyMock.verify(mockedPlayer, mockedBoard);
    }

    @Test
    public void getTextDefaultEnglishIfLocaleNotSet() {
        GameManager game = new GameManager();
        assertEquals("Start Game", game.getText("start.game"));
    }
}
