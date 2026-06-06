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
    public void movePiece_nullLoc1_returnNO_PIECE_SELECTED() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();
        Player currentPlayer = game.getCurrentPlayer();

        GameManager.MoveResult movePiece = game.movePiece(new Location(4,0), new Location(5,0));

        assertEquals(GameManager.MoveResult.NO_PIECE_SELECTED, movePiece);
        assertNull(game.getBoard().getPiece(new Location(4,0)));
        assertNull(game.getBoard().getPiece(new Location(5,0)));
        assertEquals(currentPlayer, game.getCurrentPlayer());

    }

    @Test
    public void movePiece_moveBlackKnightx0y1tox2y0_returnSUCCESS() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();
        game.changeTurns();

        Player currentPlayer = game.getCurrentPlayer();
        Piece piece = game.getBoard().getPiece(new Location(0,1));

        GameManager.MoveResult movePiece = game.movePiece(new Location(0,1), new Location(2,0));

        assertEquals(GameManager.MoveResult.SUCCESS, movePiece);

        assertNull(game.getBoard().getPiece(new Location(0,1)));

        Piece moved = game.getBoard().getPiece(new Location(2,0));
        assertEquals(PieceType.KNIGHT, moved.getType());
        assertEquals(Color.BLACK, moved.getColor());

        assertNotEquals(currentPlayer, game.getCurrentPlayer());

    }

    @Test
    public void movePiece_moveWhiteKnightx7y6tox5y5_returnSUCCESS() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();
        Player currentPlayer = game.getCurrentPlayer();

        GameManager.MoveResult movePiece = game.movePiece(new Location(7,6), new Location(5,5));

        assertEquals(GameManager.MoveResult.SUCCESS, movePiece);

        assertNull(game.getBoard().getPiece(new Location(7,6)));

        Piece moved = game.getBoard().getPiece(new Location(5,5));
        assertEquals(PieceType.KNIGHT, moved.getType());
        assertEquals(Color.WHITE, moved.getColor());

        assertNotEquals(currentPlayer, game.getCurrentPlayer());

    }

    @Test
    public void movePiece_moveBlackRookx0y0tox1y0_returnINVALID_MOVE() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();
        game.changeTurns();
        Player currentPlayer = game.getCurrentPlayer();

        GameManager.MoveResult movePiece = game.movePiece(new Location(0,0), new Location(1,0));

        assertEquals(GameManager.MoveResult.INVALID_MOVE, movePiece);

        Piece notMovedRook = game.getBoard().getPiece(new Location(0,0));
        assertEquals(PieceType.ROOK, notMovedRook.getType());
        assertEquals(Color.BLACK, notMovedRook.getColor());

        Piece notMovedPawn = game.getBoard().getPiece(new Location(1,0));
        assertEquals(PieceType.PAWN, notMovedPawn.getType());
        assertEquals(Color.BLACK, notMovedPawn.getColor());

        assertEquals(currentPlayer, game.getCurrentPlayer());

    }

    @Test
    public void movePiece_moveBlackRookx2y0tox6y0_returnSUCCESS() {
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

        GameManager.MoveResult movePiece = game.movePiece(new Location(2,0), new Location(6,0));

        assertEquals(GameManager.MoveResult.SUCCESS, movePiece);

        assertNull(game.getBoard().getPiece(new Location(2,0)));

        Piece moved = game.getBoard().getPiece(new Location(6,0));
        assertEquals(PieceType.ROOK, moved.getType());
        assertEquals(Color.BLACK, moved.getColor());

        assertNotEquals(currentPlayer, game.getCurrentPlayer());
        assertEquals(1, currentPlayer.getPoints());

    }

    @Test
    public void movePiece_moveWhiteQueenx5y7tox1y1_returnINVALID_MOVE() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();
        Player currentPlayer = game.getCurrentPlayer();

        Queen piece = new Queen(Color.WHITE);
        Board updatedBoard = game.getBoard();
        updatedBoard.setPiece(new Location(5,7), piece);
        game.setBoard(updatedBoard);

        GameManager.MoveResult movePiece = game.movePiece(new Location(5,7), new Location(1,1));

        assertEquals(GameManager.MoveResult.INVALID_MOVE, movePiece);

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
    public void movePiece_moveWhiteKnightx7y6tox5y5_returnWRONG_PLAYER_PIECE() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();
        game.changeTurns();
        Player currentPlayer = game.getCurrentPlayer();

        GameManager.MoveResult movePiece = game.movePiece(new Location(7,6), new Location(5,5));

        assertEquals(GameManager.MoveResult.WRONG_PLAYER_PIECE, movePiece);

        assertNull(game.getBoard().getPiece(new Location(5,5)));

        Piece notMovedKnight = game.getBoard().getPiece(new Location(7,6));
        assertEquals(PieceType.KNIGHT, notMovedKnight.getType());
        assertEquals(Color.WHITE, notMovedKnight.getColor());

        assertEquals(currentPlayer, game.getCurrentPlayer());

    }

    @Test
    public void movePiece_moveBlackPawnx6y0tox7y0_returnPROMOTION_REQUIRED() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();
        game.changeTurns();
        Player currentPlayer = game.getCurrentPlayer();

        Pawn piece = new Pawn(Color.BLACK);
        Board updatedBoard = game.getBoard();
        updatedBoard.setPiece(new Location(6,0), piece);
        updatedBoard.removePiece(new Location(7,0));
        game.setBoard(updatedBoard);

        GameManager.MoveResult movePiece = game.movePiece(new Location(6,0), new Location(7,0));

        assertEquals(GameManager.MoveResult.PROMOTION_REQUIRED, movePiece);

        assertNull(game.getBoard().getPiece(new Location(6,0)));

        Piece moved = game.getBoard().getPiece(new Location(7,0));
        assertEquals(PieceType.PAWN, moved.getType());
        assertEquals(Color.BLACK, moved.getColor());

        assertEquals(currentPlayer, game.getCurrentPlayer());
        assertEquals(0, currentPlayer.getPoints());

    }

    @Test
    public void promotePawn_whitePawnToQueen_pawnPromoted() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();

        Pawn piece = new Pawn(Color.WHITE);
        Board updatedBoard = game.getBoard();
        updatedBoard.setPiece(new Location(0,0), piece);
        game.setBoard(updatedBoard);

        game.promotePawn(new Location(0,0), PieceType.QUEEN);

        Piece promoted = game.getBoard().getPiece(new Location(0,0));
        assertEquals(PieceType.QUEEN, promoted.getType());
        assertEquals(Color.WHITE, promoted.getColor());

    }

    @Test
    public void promotePawn_blackPawnToQueen_pawnPromoted() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();

        Pawn piece = new Pawn(Color.BLACK);
        Board updatedBoard = game.getBoard();
        updatedBoard.setPiece(new Location(7,7), piece);
        game.setBoard(updatedBoard);

        game.promotePawn(new Location(7,7), PieceType.QUEEN);

        Piece promoted = game.getBoard().getPiece(new Location(7,7));
        assertEquals(PieceType.QUEEN, promoted.getType());
        assertEquals(Color.BLACK, promoted.getColor());

    }

    @Test
    public void promotePawn_whitePawnToKnight_pawnPromoted() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();

        Pawn piece = new Pawn(Color.WHITE);
        Board updatedBoard = game.getBoard();
        updatedBoard.setPiece(new Location(0,0), piece);
        game.setBoard(updatedBoard);

        game.promotePawn(new Location(0,0), PieceType.KNIGHT);

        Piece promoted = game.getBoard().getPiece(new Location(0,0));
        assertEquals(PieceType.KNIGHT, promoted.getType());
        assertEquals(Color.WHITE, promoted.getColor());

    }

    @Test
    public void promotePawn_whitePawnToBishop_pawnPromoted() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();

        Pawn piece = new Pawn(Color.WHITE);
        Board updatedBoard = game.getBoard();
        updatedBoard.setPiece(new Location(0,0), piece);
        game.setBoard(updatedBoard);

        game.promotePawn(new Location(0,0), PieceType.BISHOP);

        Piece promoted = game.getBoard().getPiece(new Location(0,0));
        assertEquals(PieceType.BISHOP, promoted.getType());
        assertEquals(Color.WHITE, promoted.getColor());

    }

    @Test
    public void promotePawn_whitePawnToRook_pawnPromoted() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();

        Pawn piece = new Pawn(Color.WHITE);
        Board updatedBoard = game.getBoard();
        updatedBoard.setPiece(new Location(0,0), piece);
        game.setBoard(updatedBoard);

        game.promotePawn(new Location(0,0), PieceType.ROOK);

        Piece promoted = game.getBoard().getPiece(new Location(0,0));
        assertEquals(PieceType.ROOK, promoted.getType());
        assertEquals(Color.WHITE, promoted.getColor());

    }

    @Test
    public void promotePawn_nullToRook_throwIllegalArgException() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();

        Board updatedBoard = game.getBoard();
        updatedBoard.removePiece(new Location(0,0));
        game.setBoard(updatedBoard);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            game.promotePawn(new Location(0,0), PieceType.ROOK);
        });

        assertTrue(exception.getMessage().contains("Piece is not eligible for promotion."));

    }

    @Test
    public void promotePawn_blackKnightToQueen_throwIllegalArgException() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();

        Knight piece = new Knight(Color.BLACK);
        Board updatedBoard = game.getBoard();
        updatedBoard.setPiece(new Location(7,7), piece);
        game.setBoard(updatedBoard);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            game.promotePawn(new Location(7,7), PieceType.QUEEN);
        });

        assertTrue(exception.getMessage().contains("Piece is not eligible for promotion."));

    }

}
