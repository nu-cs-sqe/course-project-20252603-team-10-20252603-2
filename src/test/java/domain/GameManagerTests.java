package domain;

import constants.Color;
import domain.piece.*;
import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
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
    public void start_WithZeroPlayers_ThrowsException() {
        GameManager newGame = new GameManager();

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            newGame.start();
        });

        assertTrue(exception.getMessage().contains("Not enough players in the game."));
        assertFalse(newGame.isGameRunning());
    }

    @Test
    public void start_WithOnePlayer_ThrowsException() {
        GameManager newGame = new GameManager();
        newGame.addPlayer(new Player(Color.BLACK));

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            newGame.start();
        });

        assertTrue(exception.getMessage().contains("Not enough players in the game."));
        assertFalse(newGame.isGameRunning());
    }

    @Test
    public void start_WithThreePlayers_ThrowsException() {
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
    public void start_WithTwoPlayers_IsSuccessful() {
        assertDoesNotThrow(() -> game.start());
        assertTrue(game.isGameRunning());
    }

    @Test
    public void start_WithTwoPlayersOfTheSameColor_ThrowsException() {
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
    public void changeTurns_StartsWithWhite_IsTrue() {
        Color actual = game.getCurrentPlayer().getPlayerColor();
        Color expected = Color.WHITE;

        assertEquals(actual, expected);
    }

    @Test
    public void changeTurns_WhiteToBlack_IsSuccessful() {
        game.changeTurns();

        Color actual = game.getCurrentPlayer().getPlayerColor();
        Color expected = Color.BLACK;

        assertEquals(actual, expected);
    }

    @Test
    public void changeTurns_BlackToWhite_IsSuccessful() {
        game.changeTurns();
        game.changeTurns();

        Color actual = game.getCurrentPlayer().getPlayerColor();
        Color expected = Color.WHITE;

        assertEquals(actual, expected);
    }

    @Test
    public void changeTurns_Draw_ThrowsException() {
        for (int i = 0; i < 50; i++) {
            game.incrementDrawCounter();
        }

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            game.changeTurns();
        });

        assertTrue(exception.getMessage().contains("Game is a draw."));
    }
    @Test
    public void isGameOver_CheckmateCondition_ReturnsTrue() {
        board.setPiece(new Location(0, 0), new King(Color.WHITE));
        board.setPiece(new Location(0, 1), new Queen(Color.BLACK));
        board.setPiece(new Location(1, 0), new Rook(Color.BLACK));
        game.setBoard(board);

        assertTrue(game.isGameOver());
    }

    @Test
    public void isGameOver_StalemateCondition_ReturnsTrue() {
        board.setPiece(new Location(0, 0), new King(Color.WHITE));
        board.setPiece(new Location(1, 2), new Queen(Color.BLACK));
        board.setPiece(new Location(2, 1), new King(Color.BLACK));
        game.setBoard(board);

        assertTrue(game.isGameOver());
    }

    @Test
    public void isGameOver_NormalGamePlayContinue_ReturnsFalse() {
        board.setPiece(new Location(4, 4), new King(Color.WHITE));
        board.setPiece(new Location(1, 1), new Pawn(Color.WHITE));
        game.setBoard(board);

        assertFalse(game.isGameOver());
    }

    @Test
    public void isGameOver_DrawConditionMet_ReturnsTrue() {
        for (int i = 0; i < 50; i++) {
            game.incrementDrawCounter();
        }
        assertTrue(game.isGameOver());
    }

    @Test
    public void isStalemate_NotInCheckZeroMoves_ReturnsTrue() {
        board.setPiece(new Location(0, 0), new King(Color.WHITE));
        board.setPiece(new Location(1, 2), new Queen(Color.BLACK));
        board.setPiece(new Location(2, 1), new King(Color.BLACK));
        game.setBoard(board);

        assertTrue(game.isStalemate());
    }

    @Test
    public void isStalemate_NotInCheckOneMove_ReturnsFalse() {
        board.setPiece(new Location(0, 0), new King(Color.WHITE));

        board.setPiece(new Location(1, 0), new Pawn(Color.WHITE));
        board.setPiece(new Location(1, 1), new Pawn(Color.WHITE));
        game.setBoard(board);

        assertFalse(game.isStalemate());
    }
    @Test
    public void isStalemate_InCheckZeroMoves_ReturnsFalse() {
        board.setPiece(new Location(0, 0), new King(Color.WHITE));
        board.setPiece(new Location(0, 1), new Queen(Color.BLACK));
        board.setPiece(new Location(1, 0), new Rook(Color.BLACK));
        game.setBoard(board);

        assertFalse(game.isStalemate());
    }
    @Test
    public void isStalemate_NotInCheckMultipleMoves_ReturnsFalse() {
        Board standardBoard = new Board(true);

        standardBoard.removePiece(new Location(5, 4));
        standardBoard.removePiece(new Location(4, 4));

        game.setBoard(standardBoard);

        assertFalse(game.isStalemate());
    }

    @Test
    public void isCheckmate_InCheckZeroMoves_ReturnsTrue() {
        board.setPiece(new Location(0, 0), new King(Color.WHITE));
        board.setPiece(new Location(0, 1), new Queen(Color.BLACK));
        board.setPiece(new Location(1, 0), new Rook(Color.BLACK));
        game.setBoard(board);

        assertTrue(game.isCheckmate());
    }

    @Test
    public void isCheckmate_InCheckOneMove_ReturnsFalse() {
        Board standardBoard = new Board(true);

        game.setBoard(standardBoard);

        assertFalse(game.isCheckmate());
    }

    @Test
    public void isCheckmate_NotInCheckAtLeastOneMove_ReturnsFalse() {
        board.setPiece(new Location(0, 0), new King(Color.WHITE));
        board.setPiece(new Location(1, 1), new Pawn(Color.WHITE));
        game.setBoard(board);

        assertFalse(game.isCheckmate());
    }
}