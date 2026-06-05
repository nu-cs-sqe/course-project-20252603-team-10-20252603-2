package domain;

import constants.Color;
import domain.piece.*;
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
        game.addPlayer(new Player("Player1", Color.BLACK));

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            game.start();
        });

        assertTrue(exception.getMessage().contains("Not enough players in the game."));
        assertFalse(game.isGameRunning());
    }

    @Test
    public void start_WithThreePlayers_ThrowsException() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.addPlayer(new Player("Player3", Color.BLACK));
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            game.start();
        });

        assertTrue(exception.getMessage().contains("Maximum number of players allowed is 2."));
        assertFalse(game.isGameRunning());
    }

    @Test
    public void start_WithTwoPlayers_IsSuccessful() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));

        assertDoesNotThrow(() -> game.start());
        assertTrue(game.isGameRunning());
    }

    @Test
    public void start_WithTwoPlayersOfTheSameColor_ThrowsException() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.BLACK));

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            game.start();
        });

        assertTrue(exception.getMessage().contains("Players cannot have the same color."));
        assertFalse(game.isGameRunning());
    }

    @Test
    public void changeTurns_StartsWithWhite_IsTrue() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));

        game.start();

        Color actual = game.getCurrentPlayer().getPlayerColor();
        Color expected = Color.WHITE;

        assertEquals(actual, expected);
    }

    @Test
    public void changeTurns_WhiteToBlack_IsSuccessful() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));

        game.start();
        game.changeTurns();

        Color actual = game.getCurrentPlayer().getPlayerColor();
        Color expected = Color.BLACK;

        assertEquals(actual, expected);
    }

    @Test
    public void changeTurns_BlackToWhite_IsSuccessful() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));

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
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
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

        Player blackPlayer = new Player("Player1", Color.BLACK);
        Player whitePlayer = new Player("Player2", Color.WHITE);
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
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
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
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.assignPlayers(mockedPlayer, new Player("Player1", Color.BLACK));
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
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.assignPlayers(mockedPlayer, new Player("Player1", Color.BLACK));
        game.setBoard(mockedBoard);

        assertFalse(game.isGameOver());
        EasyMock.verify(mockedPlayer, mockedBoard);
    }

    @Test
    public void movePiece_nullLoc1_returnFalse() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();
        Player currentPlayer = game.getCurrentPlayer();

        boolean movePiece = game.movePiece(new Location(4,0), new Location(5,0));

        assertFalse(movePiece);
        assertNull(game.getBoard().getPiece(new Location(4,0)));
        assertNull(game.getBoard().getPiece(new Location(5,0)));
        assertEquals(currentPlayer, game.getCurrentPlayer());

    }

    @Test
    public void movePiece_moveBlackKnightx0y1tox2y0_returnTrue() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();
        game.changeTurns();

        Player currentPlayer = game.getCurrentPlayer();
        Piece piece = game.getBoard().getPiece(new Location(0,1));

        boolean movePiece = game.movePiece(new Location(0,1), new Location(2,0));

        assertTrue(movePiece);

        assertNull(game.getBoard().getPiece(new Location(0,1)));

        Piece moved = game.getBoard().getPiece(new Location(2,0));
        assertEquals(PieceType.KNIGHT, moved.getType());
        assertEquals(Color.BLACK, moved.getColor());

        assertNotEquals(currentPlayer, game.getCurrentPlayer());

    }

    @Test
    public void movePiece_moveWhiteKnightx7y6tox5y5_returnTrue() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();
        Player currentPlayer = game.getCurrentPlayer();

        boolean movePiece = game.movePiece(new Location(7,6), new Location(5,5));

        assertTrue(movePiece);

        assertNull(game.getBoard().getPiece(new Location(7,6)));

        Piece moved = game.getBoard().getPiece(new Location(5,5));
        assertEquals(PieceType.KNIGHT, moved.getType());
        assertEquals(Color.WHITE, moved.getColor());

        assertNotEquals(currentPlayer, game.getCurrentPlayer());

    }

    @Test
    public void movePiece_moveBlackRookx0y0tox1y0_returnFalse() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();
        game.changeTurns();
        Player currentPlayer = game.getCurrentPlayer();

        boolean movePiece = game.movePiece(new Location(0,0), new Location(1,0));

        assertFalse(movePiece);

        Piece notMovedRook = game.getBoard().getPiece(new Location(0,0));
        assertEquals(PieceType.ROOK, notMovedRook.getType());
        assertEquals(Color.BLACK, notMovedRook.getColor());

        Piece notMovedPawn = game.getBoard().getPiece(new Location(1,0));
        assertEquals(PieceType.PAWN, notMovedPawn.getType());
        assertEquals(Color.BLACK, notMovedPawn.getColor());

        assertEquals(currentPlayer, game.getCurrentPlayer());

    }

    @Test
    public void movePiece_moveBlackRookx2y0tox6y0_returnTrue() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();
        game.changeTurns();
        Player currentPlayer = game.getCurrentPlayer();

        Rook piece = new Rook(Color.BLACK);
        Board updatedBoard = game.getBoard();
        updatedBoard.setPiece(new Location(2,0), piece);
        game.setBoard(updatedBoard);

        boolean movePiece = game.movePiece(new Location(2,0), new Location(6,0));

        assertTrue(movePiece);

        assertNull(game.getBoard().getPiece(new Location(2,0)));

        Piece moved = game.getBoard().getPiece(new Location(6,0));
        assertEquals(PieceType.ROOK, moved.getType());
        assertEquals(Color.BLACK, moved.getColor());

        assertNotEquals(currentPlayer, game.getCurrentPlayer());
        assertEquals(1, currentPlayer.getPoints());

    }

    @Test
    public void movePiece_moveWhiteQueenx5y7tox1y1_returnFalse() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();
        Player currentPlayer = game.getCurrentPlayer();

        Queen piece = new Queen(Color.WHITE);
        Board updatedBoard = game.getBoard();
        updatedBoard.setPiece(new Location(5,7), piece);
        game.setBoard(updatedBoard);

        boolean movePiece = game.movePiece(new Location(5,7), new Location(1,1));

        assertFalse(movePiece);

        Piece notMovedPawn = game.getBoard().getPiece(new Location(1,1));
        assertEquals(PieceType.PAWN, notMovedPawn.getType());
        assertEquals(Color.BLACK, notMovedPawn.getColor());

        Piece notMovedQueen = game.getBoard().getPiece(new Location(5,7));
        assertEquals(PieceType.QUEEN, notMovedQueen.getType());
        assertEquals(Color.WHITE, notMovedQueen.getColor());

        assertEquals(currentPlayer, game.getCurrentPlayer());
        assertEquals(0, currentPlayer.getPoints());

    }

    @Test
    public void movePiece_moveWhiteKnightx7y6tox5y5_returnFalse() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();
        game.changeTurns();
        Player currentPlayer = game.getCurrentPlayer();

        boolean movePiece = game.movePiece(new Location(7,6), new Location(5,5));

        assertFalse(movePiece);

        assertNull(game.getBoard().getPiece(new Location(5,5)));

        Piece notMovedKnight = game.getBoard().getPiece(new Location(7,6));
        assertEquals(PieceType.KNIGHT, notMovedKnight.getType());
        assertEquals(Color.WHITE, notMovedKnight.getColor());

        assertEquals(currentPlayer, game.getCurrentPlayer());

    }

}
