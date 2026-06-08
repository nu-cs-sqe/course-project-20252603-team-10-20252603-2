package domain;

import constants.Color;
import domain.piece.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class GameManagerTests {
    private GameManager game;
    private Board board;

    @BeforeEach
    public void setUp() {
        game = new GameManager();
        game.addPlayer(new Player(Color.WHITE));
        game.addPlayer(new Player(Color.BLACK));
        game.start();

        board = new Board(false);
    }

    @Test
    public void start_withZeroPlayers_throwsException() {
        GameManager newGame = new GameManager();

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            newGame.start();
        });

        assertTrue(exception.getMessage().contains("Not enough players in the game."));
        assertFalse(newGame.isGameRunning());
    }

    @Test
    public void start_withOnePlayer_throwsException() {
        GameManager newGame = new GameManager();
        newGame.addPlayer(new Player(Color.BLACK));

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            newGame.start();
        });

        assertTrue(exception.getMessage().contains("Not enough players in the game."));
        assertFalse(newGame.isGameRunning());
    }

    @Test
    public void start_withThreePlayers_throwsException() {
        GameManager newGame = new GameManager();
        newGame.addPlayer(new Player(Color.BLACK));
        newGame.addPlayer(new Player(Color.WHITE));
        newGame.addPlayer(new Player(Color.BLACK));
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            newGame.start();
        });

        assertTrue(exception.getMessage().contains("Maximum number of players allowed is 2."));
        assertFalse(newGame.isGameRunning());
    }

    @Test
    public void start_withPlayers_isSuccessful() {
        assertDoesNotThrow(() -> game.start());
        assertTrue(game.isGameRunning());
    }

    @Test
    public void start_withPlayersOfTheSameColor_throwsException() {
        GameManager newGame = new GameManager();
        newGame.addPlayer(new Player(Color.BLACK));
        newGame.addPlayer(new Player(Color.BLACK));

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            newGame.start();
        });

        assertTrue(exception.getMessage().contains("Players cannot have the same color."));
        assertFalse(newGame.isGameRunning());
    }

    @Test
    public void changeTurns_startsWithWhite_isTrue() {
        Color actual = game.getCurrentPlayer().getPlayerColor();
        Color expected = Color.WHITE;

        assertEquals(actual, expected);
    }

    @Test
    public void changeTurns_whiteToBlack_isSuccessful() {
        game.changeTurns();

        Color actual = game.getCurrentPlayer().getPlayerColor();
        Color expected = Color.BLACK;

        assertEquals(actual, expected);
    }

    @Test
    public void changeTurns_blackToWhite_isSuccessful() {
        game.changeTurns();
        game.changeTurns();

        Color actual = game.getCurrentPlayer().getPlayerColor();
        Color expected = Color.WHITE;

        assertEquals(actual, expected);
    }

    @Test
    public void changeTurns_draw_throwsException() {
        for (int i = 0; i < 50; i++) {
            game.incrementDrawCounter();
        }

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            game.changeTurns();
        });

        assertTrue(exception.getMessage().contains("Game is a draw."));
    }

    @Test
    public void isGameOver_checkmateCondition_returnsTrue() {
        board.setPiece(new Location(0, 0), new King(Color.WHITE));
        board.setPiece(new Location(0, 1), new Queen(Color.BLACK));
        board.setPiece(new Location(1, 0), new Rook(Color.BLACK));
        board.setPiece(new Location(1, 1), new Rook(Color.BLACK));
        game.setBoard(board);

        assertTrue(game.isGameOver());
    }

    @Test
    public void isGameOver_stalemateCondition_returnsTrue() {
        board.setPiece(new Location(0, 0), new King(Color.WHITE));
        board.setPiece(new Location(1, 2), new Queen(Color.BLACK));
        board.setPiece(new Location(2, 1), new King(Color.BLACK));
        game.setBoard(board);

        assertTrue(game.isGameOver());
    }

    @Test
    public void isGameOver_normalGamePlayContinue_returnsFalse() {
        board.setPiece(new Location(4, 4), new King(Color.WHITE));
        board.setPiece(new Location(1, 1), new Pawn(Color.WHITE));
        game.setBoard(board);

        assertFalse(game.isGameOver());
    }

    @Test
    public void isGameOver_drawConditionMet_returnsTrue() {
        for (int i = 0; i < 50; i++) {
            game.incrementDrawCounter();
        }
        assertTrue(game.isGameOver());
    }

    @Test
    public void isStalemate_notInCheckZeroMoves_returnsTrue() {
        board.setPiece(new Location(0, 0), new King(Color.WHITE));
        board.setPiece(new Location(1, 2), new Queen(Color.BLACK));
        board.setPiece(new Location(2, 1), new King(Color.BLACK));
        game.setBoard(board);

        assertTrue(game.isStalemate());
    }

    @Test
    public void isStalemate_notInCheckOneMove_returnsFalse() {
        board.setPiece(new Location(0, 0), new King(Color.WHITE));

        board.setPiece(new Location(1, 0), new Pawn(Color.WHITE));
        board.setPiece(new Location(1, 1), new Pawn(Color.WHITE));
        game.setBoard(board);

        assertFalse(game.isStalemate());
    }
    @Test
    public void isStalemate_inCheckZeroMoves_returnsFalse() {
        board.setPiece(new Location(0, 0), new King(Color.WHITE));
        board.setPiece(new Location(0, 1), new Queen(Color.BLACK));
        board.setPiece(new Location(1, 0), new Rook(Color.BLACK));
        game.setBoard(board);

        assertFalse(game.isStalemate());
    }
    @Test
    public void isStalemate_notInCheckMultipleMoves_returnsFalse() {
        Board standardBoard = new Board(true);

        standardBoard.removePiece(new Location(5, 4));
        standardBoard.removePiece(new Location(4, 4));

        game.setBoard(standardBoard);

        assertFalse(game.isStalemate());
    }

    @Test
    public void isCheckmate_inCheckZeroMoves_returnsTrue() {
        board.setPiece(new Location(0, 0), new King(Color.WHITE));
        board.setPiece(new Location(0, 1), new Queen(Color.BLACK));
        board.setPiece(new Location(1, 0), new Rook(Color.BLACK));
        board.setPiece(new Location(1, 1), new Rook(Color.BLACK));
        game.setBoard(board);

        assertTrue(game.isCheckmate());
    }

    @Test
    public void isCheckmate_inCheckOneMove_returnsFalse() {
        Board standardBoard = new Board(true);

        game.setBoard(standardBoard);

        assertFalse(game.isCheckmate());
    }

    @Test
    public void isCheckmate_notInCheckAtLeastOneMove_returnsFalse() {
        board.setPiece(new Location(0, 0), new King(Color.WHITE));
        board.setPiece(new Location(1, 1), new Pawn(Color.WHITE));
        game.setBoard(board);

        assertFalse(game.isCheckmate());
    }

    @Test
    public void getMessageDefaultEnglishIfLocaleNotSet() {
        GameManager game = new GameManager();

        assertEquals("Start Game", game.getMessage("start.game"));
    }

    @Test
    public void getMessageEnglishLocaleReturnsEnglish() {
        GameManager game = new GameManager();
        game.setLocale(Locale.ENGLISH);

        assertEquals("Start Game", game.getMessage("start.game"));
    }

    @Test
    public void getMessageSpanishLocaleReturnsSpanish() {
        GameManager game = new GameManager();
        game.setLocale(new Locale("es"));

        assertEquals("Iniciar Juego", game.getMessage("start.game"));
    }

    @Test
    public void getMessageCanSwitchLocaleEnglishToSpanish() {
        GameManager game = new GameManager();

        game.setLocale(Locale.ENGLISH);
        assertEquals("Start Game", game.getMessage("start.game"));

        game.setLocale(new Locale("es"));
        assertEquals("Iniciar Juego", game.getMessage("start.game"));
    }

    @Test
    public void getMessageMissingKeyReturnsFallbackMessage() {
        GameManager game = new GameManager();
        game.setLocale(Locale.ENGLISH);
        assertEquals("nonexistent.key", game.getMessage("nonexistent.key"));
    }

    @Test
    public void getMessageCheckmateEnglishReturnsCorrectMessage() {
        GameManager game = new GameManager();
        game.setLocale(Locale.ENGLISH);
        assertEquals("Checkmate!", game.getMessage("checkmate"));
    }

    @Test
    public void getMessageCheckmateSpanishReturnsCorrectMessage() {
        GameManager game = new GameManager();
        game.setLocale(new Locale("es"));
        assertEquals("¡mate!", game.getMessage("checkmate"));
    }

    @Test
    public void getMessageNewLocaleWorks() {
        GameManager game = new GameManager();
        game.setLocale(Locale.FRENCH);
        assertEquals("Lancer la partie", game.getMessage("start.game"));
    }
    // TODO: last two tests of isCheckmate is waiting on King's hasValidMoves() implementation
}

