package domain;

import constants.Color;
import domain.piece.*;
import org.junit.jupiter.api.BeforeEach;
import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class GameManagerTests {
    private GameManager game;
    private Board board;

    @BeforeEach
    public void setUp() {
        game = new GameManager();
        game.addPlayer(new Player("Player1", Color.WHITE));
        game.addPlayer(new Player("Player2", Color.BLACK));
        game.start();

        board = new Board(false);
    }

    private List<String> getConfiguredLocaleCodes() {
        ResourceBundle config = ResourceBundle.getBundle("languages");

        return Arrays.stream(config.getString("supported").split(","))
                .map(String::trim)
                .collect(Collectors.toList());
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
        newGame.addPlayer(new Player("Player1", Color.BLACK));

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            newGame.start();
        });

        assertTrue(exception.getMessage().contains("Not enough players in the game."));
        assertFalse(newGame.isGameRunning());
    }

    @Test
    public void start_withThreePlayers_throwsException() {
        GameManager newGame = new GameManager();
        newGame.addPlayer(new Player("Player1", Color.BLACK));
        newGame.addPlayer(new Player("Player2", Color.WHITE));
        newGame.addPlayer(new Player("Player3", Color.BLACK));
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            newGame.start();
        });

        assertTrue(exception.getMessage().contains("Maximum number of players allowed is 2."));
        assertFalse(newGame.isGameRunning());
    }

    @Test
    public void start_withTwoPlayers_isSuccessful() {
        assertDoesNotThrow(() -> game.start());
        assertTrue(game.isGameRunning());
    }

    @Test
    public void start_withPlayersOfTheSameColor_throwsException() {
        GameManager newGame = new GameManager();
        newGame.addPlayer(new Player("Player1", Color.BLACK));
        newGame.addPlayer(new Player("Player2", Color.BLACK));

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
        King mockKing = EasyMock.createMock(King.class);
        EasyMock.expect(mockKing.getColor()).andReturn(Color.WHITE).anyTimes();
        EasyMock.expect(mockKing.getType()).andReturn(PieceType.KING).anyTimes();
        EasyMock.expect(mockKing.makeCopy()).andReturn(mockKing).anyTimes();
        EasyMock.expect(mockKing.isInCheck(EasyMock.anyObject(Location.class), EasyMock.anyObject(Board.class))).andReturn(true).anyTimes();

        EasyMock.expect(mockKing.hasValidMoves(EasyMock.anyObject(Location.class), EasyMock.anyObject(Board.class))).andReturn(false).anyTimes();
        EasyMock.replay(mockKing);

        board.setPiece(new Location(0, 0), mockKing);
        game.setBoard(board);

        assertTrue(game.isGameOver());
        EasyMock.verify(mockKing);
    }

    @Test
    public void isGameOver_stalemateCondition_returnsTrue() {
        King mockKing = EasyMock.createMock(King.class);
        EasyMock.expect(mockKing.getColor()).andReturn(Color.WHITE).anyTimes();
        EasyMock.expect(mockKing.getType()).andReturn(PieceType.KING).anyTimes();
        EasyMock.expect(mockKing.makeCopy()).andReturn(mockKing).anyTimes();
        EasyMock.expect(mockKing.isInCheck(EasyMock.anyObject(Location.class), EasyMock.anyObject(Board.class))).andReturn(false).anyTimes();

        EasyMock.expect(mockKing.hasValidMoves(EasyMock.anyObject(Location.class), EasyMock.anyObject(Board.class))).andReturn(false).anyTimes();
        EasyMock.replay(mockKing);

        board.setPiece(new Location(0, 0), mockKing);
        game.setBoard(board);

        assertTrue(game.isGameOver());
        EasyMock.verify(mockKing);
    }

    @Test
    public void isGameOver_normalGamePlayContinue_returnsFalse() {
        King mockKing = EasyMock.createMock(King.class);
        Pawn mockPawn = EasyMock.createMock(Pawn.class);

        EasyMock.expect(mockKing.getColor()).andReturn(Color.WHITE).anyTimes();
        EasyMock.expect(mockKing.getType()).andReturn(PieceType.KING).anyTimes();
        EasyMock.expect(mockKing.makeCopy()).andReturn(mockKing).anyTimes();
        EasyMock.expect(mockKing.isInCheck(EasyMock.anyObject(Location.class), EasyMock.anyObject(Board.class))).andReturn(false).anyTimes();
        EasyMock.expect(mockKing.hasValidMoves(EasyMock.anyObject(Location.class), EasyMock.anyObject(Board.class))).andReturn(true).anyTimes();

        EasyMock.expect(mockPawn.getColor()).andReturn(Color.WHITE).anyTimes();
        EasyMock.expect(mockPawn.getType()).andReturn(PieceType.PAWN).anyTimes();
        EasyMock.expect(mockPawn.makeCopy()).andReturn(mockPawn).anyTimes();
        EasyMock.expect(mockPawn.hasValidMoves(EasyMock.anyObject(Location.class), EasyMock.anyObject(Board.class))).andReturn(true).anyTimes();

        EasyMock.replay(mockKing, mockPawn);

        board.setPiece(new Location(4, 4), mockKing);
        board.setPiece(new Location(1, 1), mockPawn);
        game.setBoard(board);

        assertFalse(game.isGameOver());
        EasyMock.verify(mockKing, mockPawn);
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
        King mockKing = EasyMock.createMock(King.class);
        EasyMock.expect(mockKing.getColor()).andReturn(Color.WHITE).anyTimes();
        EasyMock.expect(mockKing.getType()).andReturn(PieceType.KING).anyTimes();
        EasyMock.expect(mockKing.makeCopy()).andReturn(mockKing).anyTimes();
        EasyMock.expect(mockKing.isInCheck(EasyMock.anyObject(Location.class), EasyMock.anyObject(Board.class))).andReturn(false);

        EasyMock.expect(mockKing.hasValidMoves(EasyMock.anyObject(Location.class), EasyMock.anyObject(Board.class))).andReturn(false).anyTimes();

        EasyMock.replay(mockKing);

        board.setPiece(new Location(0, 0), mockKing);
        game.setBoard(board);

        assertTrue(game.isStalemate());
        EasyMock.verify(mockKing);
    }

    @Test
    public void isStalemate_notInCheckOneMove_returnsFalse() {
        King mockKing = EasyMock.createMock(King.class);
        Pawn mockPawn = EasyMock.createMock(Pawn.class);

        EasyMock.expect(mockKing.getColor()).andReturn(Color.WHITE).anyTimes();
        EasyMock.expect(mockKing.getType()).andReturn(PieceType.KING).anyTimes();
        EasyMock.expect(mockKing.makeCopy()).andReturn(mockKing).anyTimes();
        EasyMock.expect(mockKing.isInCheck(EasyMock.anyObject(Location.class), EasyMock.anyObject(Board.class))).andReturn(false).anyTimes();
        EasyMock.expect(mockKing.hasValidMoves(EasyMock.anyObject(Location.class), EasyMock.anyObject(Board.class))).andReturn(false).anyTimes();

        EasyMock.expect(mockPawn.getColor()).andReturn(Color.WHITE).anyTimes();
        EasyMock.expect(mockPawn.getType()).andReturn(PieceType.PAWN).anyTimes();
        EasyMock.expect(mockPawn.makeCopy()).andReturn(mockPawn).anyTimes();
        EasyMock.expect(mockPawn.hasValidMoves(EasyMock.anyObject(Location.class), EasyMock.anyObject(Board.class))).andReturn(true).anyTimes();

        EasyMock.replay(mockKing, mockPawn);

        board.setPiece(new Location(0, 0), mockKing);
        board.setPiece(new Location(1, 0), mockPawn);
        game.setBoard(board);

        assertFalse(game.isStalemate());
        EasyMock.verify(mockKing, mockPawn);
    }

    @Test
    public void isStalemate_inCheckZeroMoves_returnsFalse() {
        King mockKing = EasyMock.createMock(King.class);
        EasyMock.expect(mockKing.getColor()).andReturn(Color.WHITE).anyTimes();
        EasyMock.expect(mockKing.getType()).andReturn(PieceType.KING).anyTimes();
        EasyMock.expect(mockKing.makeCopy()).andReturn(mockKing).anyTimes();
        EasyMock.expect(mockKing.isInCheck(EasyMock.anyObject(Location.class), EasyMock.anyObject(Board.class))).andReturn(true);

        EasyMock.expect(mockKing.hasValidMoves(EasyMock.anyObject(Location.class), EasyMock.anyObject(Board.class))).andReturn(false).anyTimes();
        EasyMock.replay(mockKing);

        board.setPiece(new Location(0, 0), mockKing);
        game.setBoard(board);

        assertFalse(game.isStalemate());
        EasyMock.verify(mockKing);
    }

    @Test
    public void isStalemate_notInCheckMultipleMoves_returnsFalse() {
        King mockKing = EasyMock.createMock(King.class);
        EasyMock.expect(mockKing.getColor()).andReturn(Color.WHITE).anyTimes();
        EasyMock.expect(mockKing.getType()).andReturn(PieceType.KING).anyTimes();
        EasyMock.expect(mockKing.makeCopy()).andReturn(mockKing).anyTimes();
        EasyMock.expect(mockKing.isInCheck(EasyMock.anyObject(Location.class), EasyMock.anyObject(Board.class))).andReturn(false).anyTimes();
        EasyMock.expect(mockKing.hasValidMoves(EasyMock.anyObject(Location.class), EasyMock.anyObject(Board.class))).andReturn(true).anyTimes();

        EasyMock.replay(mockKing);

        board.setPiece(new Location(0, 0), mockKing);
        game.setBoard(board);

        assertFalse(game.isStalemate());
        EasyMock.verify(mockKing);
    }

    @Test
    public void isStalemate_gameNotStarted_returnsFalse() {
        GameManager newGame = new GameManager();

        assertFalse(newGame.isStalemate());
    }

    @Test
    public void isStalemate_noAlliedKingOnBoard_returnsFalse() {
        Board emptyBoard = new Board(false);
        game.setBoard(emptyBoard);

        assertFalse(game.isStalemate());
    }

    @Test
    public void isCheckmate_inCheckZeroMoves_returnsTrue() {
        King mockKing = EasyMock.createMock(King.class);
        EasyMock.expect(mockKing.getColor()).andReturn(Color.WHITE).anyTimes();
        EasyMock.expect(mockKing.getType()).andReturn(PieceType.KING).anyTimes();
        EasyMock.expect(mockKing.makeCopy()).andReturn(mockKing).anyTimes();
        EasyMock.expect(mockKing.isInCheck(EasyMock.anyObject(Location.class), EasyMock.anyObject(Board.class))).andReturn(true);

        EasyMock.expect(mockKing.hasValidMoves(EasyMock.anyObject(Location.class), EasyMock.anyObject(Board.class))).andReturn(false).anyTimes();

        EasyMock.replay(mockKing);

        board.setPiece(new Location(0, 0), mockKing);
        game.setBoard(board);

        assertTrue(game.isCheckmate());
        EasyMock.verify(mockKing);
    }

    @Test
    public void isCheckmate_inCheckOneMove_returnsFalse() {
        King mockKing = EasyMock.createMock(King.class);
        EasyMock.expect(mockKing.getColor()).andReturn(Color.WHITE).anyTimes();
        EasyMock.expect(mockKing.getType()).andReturn(PieceType.KING).anyTimes();
        EasyMock.expect(mockKing.makeCopy()).andReturn(mockKing).anyTimes();
        EasyMock.expect(mockKing.isInCheck(EasyMock.anyObject(Location.class), EasyMock.anyObject(Board.class))).andReturn(true).anyTimes();
        EasyMock.expect(mockKing.hasValidMoves(EasyMock.anyObject(Location.class), EasyMock.anyObject(Board.class))).andReturn(true).anyTimes();

        EasyMock.replay(mockKing);

        board.setPiece(new Location(0, 0), mockKing);
        game.setBoard(board);

        assertFalse(game.isCheckmate());
        EasyMock.verify(mockKing);
    }

    @Test
    public void isCheckmate_notInCheckAtLeastOneMove_returnsFalse() {
        King mockKing = EasyMock.createMock(King.class);
        EasyMock.expect(mockKing.getColor()).andReturn(Color.WHITE).anyTimes();
        EasyMock.expect(mockKing.getType()).andReturn(PieceType.KING).anyTimes();
        EasyMock.expect(mockKing.makeCopy()).andReturn(mockKing).anyTimes();
        EasyMock.expect(mockKing.isInCheck(EasyMock.anyObject(Location.class), EasyMock.anyObject(Board.class))).andReturn(false);

        EasyMock.expect(mockKing.hasValidMoves(EasyMock.anyObject(Location.class), EasyMock.anyObject(Board.class))).andReturn(false).anyTimes();

        EasyMock.replay(mockKing);

        board.setPiece(new Location(0, 0), mockKing);
        game.setBoard(board);

        assertFalse(game.isCheckmate());
        EasyMock.verify(mockKing);
    }

    @Test
    public void isCheckmate_gameNotStarted_returnsFalse() {
        GameManager newGame = new GameManager();

        assertFalse(newGame.isCheckmate());
    }

    @Test
    public void isCheckmate_noAlliedKingOnBoard_returnsFalse() {
        Board emptyBoard = new Board(false);
        game.setBoard(emptyBoard);

        assertFalse(game.isCheckmate());
    }

    @Test
    public void getMessageDefaultEnglishIfLocaleNotSet() {
        GameManager game = new GameManager();

        assertEquals("Start Game", game.getMessage("welcome.startGame"));
    }

    @Test
    public void getMessageEnglishLocaleReturnsEnglish() {
        GameManager game = new GameManager();
        game.setLocale(Locale.ENGLISH);

        assertEquals("Start Game", game.getMessage("welcome.startGame"));
    }

    @Test
    public void getMessageSpanishLocaleReturnsSpanish() {
        GameManager game = new GameManager();
        game.setLocale(new Locale("es"));

        assertEquals("Iniciar Juego", game.getMessage("welcome.startGame"));
    }

    @Test
    public void getMessageCanSwitchLocaleEnglishToSpanish() {
        GameManager game = new GameManager();

        game.setLocale(Locale.ENGLISH);
        assertEquals("Start Game", game.getMessage("welcome.startGame"));

        game.setLocale(new Locale("es"));
        assertEquals("Iniciar Juego", game.getMessage("welcome.startGame"));
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
        assertEquals("Lancer la partie", game.getMessage("welcome.startGame"));
    }

    @Test
    public void movePiece_nullLoc1_returnNO_PIECE_SELECTED() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();
        Player currentPlayer = game.getCurrentPlayer();

        game.setBoard(board);

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

        Knight mockKnight = EasyMock.createMock(Knight.class);
        EasyMock.expect(mockKnight.getColor()).andReturn(Color.BLACK).anyTimes();
        EasyMock.expect(mockKnight.getType()).andReturn(PieceType.KNIGHT).anyTimes();
        EasyMock.expect(mockKnight.makeCopy()).andReturn(mockKnight).anyTimes();
        EasyMock.expect(mockKnight.isValidMove(
                EasyMock.eq(new Location(0, 1)),
                EasyMock.eq(new Location(2, 0)),
                EasyMock.anyObject(Board.class)
        )).andReturn(true);
        EasyMock.replay(mockKnight);

        board.setPiece(new Location(0, 1), mockKnight);
        game.setBoard(board);

        GameManager.MoveResult movePiece = game.movePiece(new Location(0,1), new Location(2,0));

        assertEquals(GameManager.MoveResult.SUCCESS, movePiece);
        assertNull(game.getBoard().getPiece(new Location(0,1)));

        Piece moved = game.getBoard().getPiece(new Location(2,0));
        assertEquals(PieceType.KNIGHT, moved.getType());
        assertEquals(Color.BLACK, moved.getColor());
        assertEquals(1, game.getConsecutiveDrawMoves());

        assertNotEquals(currentPlayer, game.getCurrentPlayer());

        EasyMock.verify(mockKnight);
    }

    @Test
    public void movePiece_moveWhiteKnightx7y6tox5y5_returnSUCCESS() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();
        Player currentPlayer = game.getCurrentPlayer();

        Knight mockKnight = EasyMock.createMock(Knight.class);
        EasyMock.expect(mockKnight.getColor()).andReturn(Color.WHITE).anyTimes();
        EasyMock.expect(mockKnight.getType()).andReturn(PieceType.KNIGHT).anyTimes();
        EasyMock.expect(mockKnight.makeCopy()).andReturn(mockKnight).anyTimes();
        EasyMock.expect(mockKnight.isValidMove(
                EasyMock.eq(new Location(7, 6)),
                EasyMock.eq(new Location(5, 5)),
                EasyMock.anyObject(Board.class)
        )).andReturn(true);
        EasyMock.replay(mockKnight);

        board.setPiece(new Location(7, 6), mockKnight);
        game.setBoard(board);

        GameManager.MoveResult movePiece = game.movePiece(new Location(7,6), new Location(5,5));

        assertEquals(GameManager.MoveResult.SUCCESS, movePiece);
        assertNull(game.getBoard().getPiece(new Location(7,6)));

        Piece moved = game.getBoard().getPiece(new Location(5,5));
        assertEquals(PieceType.KNIGHT, moved.getType());
        assertEquals(Color.WHITE, moved.getColor());
        assertEquals(1, game.getConsecutiveDrawMoves());

        assertNotEquals(currentPlayer, game.getCurrentPlayer());

        EasyMock.verify(mockKnight);
    }

    @Test
    public void movePiece_moveBlackRookx0y0tox1y0_returnINVALID_MOVE() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();
        game.changeTurns();
        Player currentPlayer = game.getCurrentPlayer();

        Rook mockRook = EasyMock.createMock(Rook.class);
        EasyMock.expect(mockRook.getColor()).andReturn(Color.BLACK).anyTimes();
        EasyMock.expect(mockRook.getType()).andReturn(PieceType.ROOK).anyTimes();
        EasyMock.expect(mockRook.makeCopy()).andReturn(mockRook).anyTimes();
        EasyMock.expect(mockRook.isValidMove(
                EasyMock.eq(new Location(0, 0)),
                EasyMock.eq(new Location(1, 0)),
                EasyMock.anyObject(Board.class)
        )).andReturn(false);
        Pawn mockPawn = EasyMock.createMock(Pawn.class);
        EasyMock.expect(mockPawn.getColor()).andReturn(Color.BLACK).anyTimes();
        EasyMock.expect(mockPawn.getType()).andReturn(PieceType.PAWN).anyTimes();
        EasyMock.expect(mockPawn.makeCopy()).andReturn(mockPawn).anyTimes();

        EasyMock.replay(mockRook, mockPawn);

        board.setPiece(new Location(0, 0), mockRook);
        board.setPiece(new Location(1, 0), mockPawn);
        game.setBoard(board);

        GameManager.MoveResult movePiece = game.movePiece(new Location(0,0), new Location(1,0));

        assertEquals(GameManager.MoveResult.INVALID_MOVE, movePiece);

        Piece notMovedRook = game.getBoard().getPiece(new Location(0,0));
        assertEquals(PieceType.ROOK, notMovedRook.getType());
        assertEquals(Color.BLACK, notMovedRook.getColor());

        Piece notMovedPawn = game.getBoard().getPiece(new Location(1,0));
        assertEquals(PieceType.PAWN, notMovedPawn.getType());
        assertEquals(Color.BLACK, notMovedPawn.getColor());

        assertEquals(currentPlayer, game.getCurrentPlayer());
        EasyMock.verify(mockRook, mockPawn);
    }

    @Test
    public void movePiece_moveBlackRookx2y0tox6y0_returnSUCCESS() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();
        game.changeTurns();
        Player currentPlayer = game.getCurrentPlayer();

        Rook mockRook = EasyMock.createMock(Rook.class);
        EasyMock.expect(mockRook.getColor()).andReturn(Color.BLACK).anyTimes();
        EasyMock.expect(mockRook.getType()).andReturn(PieceType.ROOK).anyTimes();
        EasyMock.expect(mockRook.makeCopy()).andReturn(mockRook).anyTimes();
        EasyMock.expect(mockRook.isValidMove(
                EasyMock.eq(new Location(2, 0)),
                EasyMock.eq(new Location(6, 0)),
                EasyMock.anyObject(Board.class)
        )).andReturn(true);
        Pawn mockCapturedPawn = EasyMock.createMock(Pawn.class);
        EasyMock.expect(mockCapturedPawn.getType()).andReturn(PieceType.PAWN).anyTimes();
        EasyMock.expect(mockCapturedPawn.makeCopy()).andReturn(mockCapturedPawn).anyTimes();
        EasyMock.replay(mockRook, mockCapturedPawn);

        board.setPiece(new Location(2, 0), mockRook);
        board.setPiece(new Location(6, 0), mockCapturedPawn);
        game.setBoard(board);

        GameManager.MoveResult movePiece = game.movePiece(new Location(2,0), new Location(6,0));

        assertEquals(GameManager.MoveResult.SUCCESS, movePiece);
        assertNull(game.getBoard().getPiece(new Location(2,0)));

        Piece moved = game.getBoard().getPiece(new Location(6,0));
        assertEquals(PieceType.ROOK, moved.getType());
        assertEquals(Color.BLACK, moved.getColor());
        assertEquals(0, game.getConsecutiveDrawMoves());

        assertNotEquals(currentPlayer, game.getCurrentPlayer());
        assertEquals(1, currentPlayer.getPoints());
        EasyMock.verify(mockRook, mockCapturedPawn);
    }

    @Test
    public void movePiece_moveWhiteQueenx5y7tox1y1_returnINVALID_MOVE() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();
        Player currentPlayer = game.getCurrentPlayer();

        Queen mockQueen = EasyMock.createMock(Queen.class);
        EasyMock.expect(mockQueen.getColor()).andReturn(Color.WHITE).anyTimes();
        EasyMock.expect(mockQueen.getType()).andReturn(PieceType.QUEEN).anyTimes();
        EasyMock.expect(mockQueen.makeCopy()).andReturn(mockQueen).anyTimes();
        EasyMock.expect(mockQueen.isValidMove(
                EasyMock.eq(new Location(5, 7)),
                EasyMock.eq(new Location(1, 1)),
                EasyMock.anyObject(Board.class)
        )).andReturn(false);
        Pawn mockPawn = EasyMock.createMock(Pawn.class);
        EasyMock.expect(mockPawn.getColor()).andReturn(Color.BLACK).anyTimes();
        EasyMock.expect(mockPawn.getType()).andReturn(PieceType.PAWN).anyTimes();
        EasyMock.expect(mockPawn.makeCopy()).andReturn(mockPawn).anyTimes();

        EasyMock.replay(mockQueen, mockPawn);

        board.setPiece(new Location(5, 7), mockQueen);
        board.setPiece(new Location(1, 1), mockPawn);
        game.setBoard(board);

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
        EasyMock.verify(mockQueen, mockPawn);
    }

    @Test
    public void movePiece_moveWhiteKnightx7y6tox5y5_returnWRONG_PLAYER_PIECE() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();
        game.changeTurns();
        Player currentPlayer = game.getCurrentPlayer();

        Knight mockKnight = EasyMock.createMock(Knight.class);
        EasyMock.expect(mockKnight.getColor()).andReturn(Color.WHITE).anyTimes();
        EasyMock.expect(mockKnight.getType()).andReturn(PieceType.KNIGHT).anyTimes();
        EasyMock.expect(mockKnight.makeCopy()).andReturn(mockKnight).anyTimes();

        EasyMock.replay(mockKnight);

        board.setPiece(new Location(7, 6), mockKnight);
        game.setBoard(board);

        GameManager.MoveResult movePiece = game.movePiece(new Location(7,6), new Location(5,5));

        assertEquals(GameManager.MoveResult.WRONG_PLAYER_PIECE, movePiece);
        assertNull(game.getBoard().getPiece(new Location(5,5)));

        Piece notMovedKnight = game.getBoard().getPiece(new Location(7,6));
        assertEquals(PieceType.KNIGHT, notMovedKnight.getType());
        assertEquals(Color.WHITE, notMovedKnight.getColor());

        assertEquals(currentPlayer, game.getCurrentPlayer());
        EasyMock.verify(mockKnight);
    }

    @Test
    public void movePiece_moveBlackPawnx6y0tox7y0_returnPROMOTION_REQUIRED() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();
        game.changeTurns();
        Player currentPlayer = game.getCurrentPlayer();

        Pawn mockPawn = EasyMock.createMock(Pawn.class);
        EasyMock.expect(mockPawn.getColor()).andReturn(Color.BLACK).anyTimes();
        EasyMock.expect(mockPawn.getType()).andReturn(PieceType.PAWN).anyTimes();
        EasyMock.expect(mockPawn.makeCopy()).andReturn(mockPawn).anyTimes();
        EasyMock.expect(mockPawn.isValidMove(
                EasyMock.eq(new Location(6, 0)),
                EasyMock.eq(new Location(7, 0)),
                EasyMock.anyObject(Board.class)
        )).andReturn(true);

        EasyMock.replay(mockPawn);

        board.setPiece(new Location(6, 0), mockPawn);
        game.setBoard(board);

        GameManager.MoveResult movePiece = game.movePiece(new Location(6,0), new Location(7,0));

        assertEquals(GameManager.MoveResult.PROMOTION_REQUIRED, movePiece);
        assertNull(game.getBoard().getPiece(new Location(6,0)));

        Piece moved = game.getBoard().getPiece(new Location(7,0));
        assertEquals(PieceType.PAWN, moved.getType());
        assertEquals(Color.BLACK, moved.getColor());

        assertEquals(currentPlayer, game.getCurrentPlayer());
        assertEquals(0, currentPlayer.getPoints());
        assertEquals(0, game.getConsecutiveDrawMoves());

    }

    @Test
    public void promotePawn_whitePawnToQueen_pawnPromoted() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();

        Pawn mockPawn = EasyMock.createMock(Pawn.class);
        EasyMock.expect(mockPawn.getType()).andReturn(PieceType.PAWN).anyTimes();
        EasyMock.expect(mockPawn.getColor()).andReturn(Color.WHITE).anyTimes();
        EasyMock.expect(mockPawn.makeCopy()).andReturn(mockPawn).anyTimes();

        EasyMock.replay(mockPawn);

        board.setPiece(new Location(0, 0), mockPawn);
        game.setBoard(board);

        game.promotePawn(new Location(0,0), PieceType.QUEEN);

        Piece promoted = game.getBoard().getPiece(new Location(0,0));
        assertEquals(PieceType.QUEEN, promoted.getType());
        assertEquals(Color.WHITE, promoted.getColor());
        EasyMock.verify(mockPawn);
    }

    @Test
    public void promotePawn_blackPawnToQueen_pawnPromoted() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();

        Pawn mockPawn = EasyMock.createMock(Pawn.class);
        EasyMock.expect(mockPawn.getType()).andReturn(PieceType.PAWN).anyTimes();
        EasyMock.expect(mockPawn.getColor()).andReturn(Color.BLACK).anyTimes();
        EasyMock.expect(mockPawn.makeCopy()).andReturn(mockPawn).anyTimes();

        EasyMock.replay(mockPawn);

        board.setPiece(new Location(7, 7), mockPawn);
        game.setBoard(board);

        game.promotePawn(new Location(7,7), PieceType.QUEEN);

        Piece promoted = game.getBoard().getPiece(new Location(7,7));
        assertEquals(PieceType.QUEEN, promoted.getType());
        assertEquals(Color.BLACK, promoted.getColor());
        EasyMock.verify(mockPawn);
    }

    @Test
    public void promotePawn_whitePawnToKnight_pawnPromoted() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();

        Pawn mockPawn = EasyMock.createMock(Pawn.class);
        EasyMock.expect(mockPawn.getType()).andReturn(PieceType.PAWN).anyTimes();
        EasyMock.expect(mockPawn.getColor()).andReturn(Color.WHITE).anyTimes();
        EasyMock.expect(mockPawn.makeCopy()).andReturn(mockPawn).anyTimes();

        EasyMock.replay(mockPawn);

        board.setPiece(new Location(0, 0), mockPawn);
        game.setBoard(board);

        game.promotePawn(new Location(0,0), PieceType.KNIGHT);

        Piece promoted = game.getBoard().getPiece(new Location(0,0));
        assertEquals(PieceType.KNIGHT, promoted.getType());
        assertEquals(Color.WHITE, promoted.getColor());
        EasyMock.verify(mockPawn);
    }

    @Test
    public void promotePawn_whitePawnToBishop_pawnPromoted() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();

        Pawn mockPawn = EasyMock.createMock(Pawn.class);
        EasyMock.expect(mockPawn.getType()).andReturn(PieceType.PAWN).anyTimes();
        EasyMock.expect(mockPawn.getColor()).andReturn(Color.WHITE).anyTimes();
        EasyMock.expect(mockPawn.makeCopy()).andReturn(mockPawn).anyTimes();

        EasyMock.replay(mockPawn);

        board.setPiece(new Location(0, 0), mockPawn);
        game.setBoard(board);

        game.promotePawn(new Location(0,0), PieceType.BISHOP);

        Piece promoted = game.getBoard().getPiece(new Location(0,0));
        assertEquals(PieceType.BISHOP, promoted.getType());
        assertEquals(Color.WHITE, promoted.getColor());
        EasyMock.verify(mockPawn);
    }

    @Test
    public void promotePawn_whitePawnToRook_pawnPromoted() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();

        Pawn mockPawn = EasyMock.createMock(Pawn.class);
        EasyMock.expect(mockPawn.getType()).andReturn(PieceType.PAWN).anyTimes();
        EasyMock.expect(mockPawn.getColor()).andReturn(Color.WHITE).anyTimes();
        EasyMock.expect(mockPawn.makeCopy()).andReturn(mockPawn).anyTimes();

        EasyMock.replay(mockPawn);

        board.setPiece(new Location(0, 0), mockPawn);
        game.setBoard(board);

        game.promotePawn(new Location(0,0), PieceType.ROOK);

        Piece promoted = game.getBoard().getPiece(new Location(0,0));
        assertEquals(PieceType.ROOK, promoted.getType());
        assertEquals(Color.WHITE, promoted.getColor());
        EasyMock.verify(mockPawn);
    }

    @Test
    public void promotePawn_nullToRook_throwIllegalArgException() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();

        game.setBoard(board);

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

        Knight mockKnight = EasyMock.createMock(Knight.class);
        EasyMock.expect(mockKnight.getType()).andReturn(PieceType.KNIGHT).anyTimes();
        EasyMock.expect(mockKnight.makeCopy()).andReturn(mockKnight).anyTimes();

        EasyMock.replay(mockKnight);

        board.setPiece(new Location(7, 7), mockKnight);
        game.setBoard(board);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            game.promotePawn(new Location(7,7), PieceType.QUEEN);
        });

        assertTrue(exception.getMessage().contains("Piece is not eligible for promotion."));
        EasyMock.verify(mockKnight);
    }

    @Test
    public void promotePawn_whitePawnToKing_throwIllegalArgException() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();

        Pawn mockPawn = EasyMock.createMock(Pawn.class);
        EasyMock.expect(mockPawn.getType()).andReturn(PieceType.PAWN).anyTimes();
        EasyMock.expect(mockPawn.getColor()).andReturn(Color.WHITE).anyTimes();
        EasyMock.expect(mockPawn.makeCopy()).andReturn(mockPawn).anyTimes();

        EasyMock.replay(mockPawn);

        board.setPiece(new Location(0, 0), mockPawn);
        game.setBoard(board);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            game.promotePawn(new Location(0,0), PieceType.KING);
        });

        assertTrue(exception.getMessage().contains("Invalid promotion piece."));
        EasyMock.verify(mockPawn);
    }

    @Test
    public void loadSupportedLanguages_validFixedResources_gameManagerInitializes() {
        assertDoesNotThrow(() -> {
            GameManager game = new GameManager();

            assertNotNull(game.getSupportedLanguages());
            assertFalse(game.getSupportedLanguages().isEmpty());
        });
    }

    @Test
    public void loadSupportedLanguages_supportedValueWithMultipleLocaleCodes_returnsMatchingNumberOfLanguageOptions() {
        GameManager game = new GameManager();

        List<String> configuredCodes = getConfiguredLocaleCodes();
        List<LanguageOption> languages = game.getSupportedLanguages();

        assertEquals(configuredCodes.size(), languages.size());
    }

    @Test
    public void loadSupportedLanguages_supportedValueWithAtLeastTwoLocaleCodes_returnsAtLeastTwoLanguageOptions() {
        GameManager game = new GameManager();

        List<LanguageOption> languages = game.getSupportedLanguages();

        assertTrue(languages.size() >= 2);
    }

    @Test
    public void loadSupportedLanguages_configuredLocalesWithMatchingMessageBundles_completesWithoutMissingBundleException() {
        List<String> configuredCodes = getConfiguredLocaleCodes();

        for (String code : configuredCodes) {
            Locale locale = Locale.forLanguageTag(code);

            assertDoesNotThrow(() -> {
                ResourceBundle.getBundle("messages", locale);
            });
        }
    }

    @Test
    public void loadSupportedLanguages_configuredBundlesWithLanguageName_returnsNonEmptyDisplayNames() {
        List<String> configuredCodes = getConfiguredLocaleCodes();

        for (String code : configuredCodes) {
            Locale locale = Locale.forLanguageTag(code);
            ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

            String languageName = bundle.getString("language.name");

            assertNotNull(languageName);
            assertFalse(languageName.trim().isEmpty());
        }
    }

    @Test
    public void loadSupportedLanguages_supportedValueListsEnglish_returnsEnglishLanguageOption() {
        GameManager game = new GameManager();

        boolean containsEnglish = game.getSupportedLanguages().stream()
                .anyMatch(language -> language.getLocale().equals(Locale.ENGLISH));

        assertTrue(containsEnglish);
    }

    @Test
    public void loadSupportedLanguages_supportedValueListsSpanish_returnsSpanishLanguageOption() {
        GameManager game = new GameManager();

        boolean containsSpanish = game.getSupportedLanguages().stream()
                .anyMatch(language -> language.getLocale().equals(Locale.forLanguageTag("es")));

        assertTrue(containsSpanish);
    }

    @Test
    public void loadSupportedLanguages_supportedValueListsFrench_returnsFrenchLanguageOption() {
        GameManager game = new GameManager();

        boolean containsFrench = game.getSupportedLanguages().stream()
                .anyMatch(language -> language.getLocale().equals(Locale.FRENCH));

        assertTrue(containsFrench);
    }

    @Test
    public void loadSupportedLanguages_supportedValueWithOrderedLocaleCodes_preservesConfiguredOrder() {
        GameManager game = new GameManager();

        List<String> configuredCodes = getConfiguredLocaleCodes();
        List<LanguageOption> languages = game.getSupportedLanguages();

        for (int i = 0; i < configuredCodes.size(); i++) {
            Locale expectedLocale = Locale.forLanguageTag(configuredCodes.get(i));

            assertEquals(expectedLocale, languages.get(i).getLocale());
        }
    }

    @Test
    public void loadSupportedLanguages_supportedValueWithTrimmedLanguageTags_returnsMatchingLocales() {
        GameManager game = new GameManager();

        List<String> configuredCodes = getConfiguredLocaleCodes();
        List<LanguageOption> languages = game.getSupportedLanguages();

        for (int i = 0; i < configuredCodes.size(); i++) {
            Locale expectedLocale = Locale.forLanguageTag(configuredCodes.get(i));
            Locale actualLocale = languages.get(i).getLocale();

            assertEquals(expectedLocale, actualLocale);
        }
    }

    @Test
    public void getSupportedLanguages_validFixedResources_returnsNonEmptyList() {
        GameManager game = new GameManager();

        List<LanguageOption> languages = game.getSupportedLanguages();

        assertNotNull(languages);
        assertFalse(languages.isEmpty());
    }

    @Test
    public void getSupportedLanguages_validFixedResources_returnsMoreThanOneLanguageOption() {
        GameManager game = new GameManager();

        List<LanguageOption> languages = game.getSupportedLanguages();

        assertTrue(languages.size() > 1);
    }

    @Test
    public void getSupportedLanguages_validFixedResources_returnsAtLeastTwoLanguageOptions() {
        GameManager game = new GameManager();

        List<LanguageOption> languages = game.getSupportedLanguages();

        assertTrue(languages.size() >= 2);
    }

    @Test
    public void getSupportedLanguages_configIncludesEnglish_returnsEnglishLanguageOption() {
        GameManager game = new GameManager();

        boolean containsEnglish = game.getSupportedLanguages().stream()
                .anyMatch(language -> language.getLocale().equals(Locale.ENGLISH));

        assertTrue(containsEnglish);
    }

    @Test
    public void isGameOver_NotStarted_ReturnsFalse() {
        GameManager game = new GameManager();

        assertFalse(game.isGameOver());
    }

    @Test
    public void getBoard_BoardIsNull_ReturnsNull() {
        GameManager game = new GameManager();

        assertNull(game.getBoard());
    }

    @Test
    public void getBoard_BoardIsSet_ReturnsCopyOfBoard() {
        GameManager game = new GameManager();

        Board board = new Board(false);
        Location location = new Location(0, 0);
        Pawn pawn = new Pawn(Color.WHITE);
        board.setPiece(location, pawn);

        game.setBoard(board);

        Board copiedBoard = game.getBoard();

        assertNotNull(copiedBoard);
        assertNotSame(board, copiedBoard);
        assertTrue(copiedBoard.isPieceHere(location));
    }

    @Test
    public void setBoard_nullBoard_setsBoardToNull() {
        GameManager game = new GameManager();

        game.setBoard(null);

        assertNull(game.getBoard());
    }

    @Test
    public void setBoard_validBoard_storesCopyOfBoard() {
        GameManager game = new GameManager();

        Board original = new Board(false);
        original.setPiece(new Location(0, 0), new Pawn(Color.WHITE));

        game.setBoard(original);

        Board storedBoard = game.getBoard();

        assertNotNull(storedBoard);
        assertNotSame(original, storedBoard);

        Piece originalPiece = original.getPiece(new Location(0, 0));
        Piece storedPiece = storedBoard.getPiece(new Location(0, 0));

        assertNotNull(storedPiece);
        assertNotSame(originalPiece, storedPiece);
        assertEquals(originalPiece.getType(), storedPiece.getType());
        assertEquals(originalPiece.getColor(), storedPiece.getColor());
    }
  
    public void getSupportedLanguages_configIncludesSpanish_returnsSpanishLanguageOption() {
        GameManager game = new GameManager();

        boolean containsSpanish = game.getSupportedLanguages().stream()
                .anyMatch(language -> language.getLocale().equals(Locale.forLanguageTag("es")));

        assertTrue(containsSpanish);
    }

    @Test
    public void getSupportedLanguages_configIncludesFrench_returnsFrenchLanguageOption() {
        GameManager game = new GameManager();

        boolean containsFrench = game.getSupportedLanguages().stream()
                .anyMatch(language -> language.getLocale().equals(Locale.FRENCH));

        assertTrue(containsFrench);
    }

    @Test
    public void getSupportedLanguages_configWithOrderedLocaleCodes_preservesConfiguredOrder() {
        GameManager game = new GameManager();

        List<String> configuredCodes = getConfiguredLocaleCodes();
        List<LanguageOption> languages = game.getSupportedLanguages();

        for (int i = 0; i < configuredCodes.size(); i++) {
            Locale expectedLocale = Locale.forLanguageTag(configuredCodes.get(i));

            assertEquals(expectedLocale, languages.get(i).getLocale());
        }
    }

    @Test
    public void getSupportedLanguages_configuredLocaleCodes_returnsMatchingListSize() {
        GameManager game = new GameManager();

        List<String> configuredCodes = getConfiguredLocaleCodes();
        List<LanguageOption> languages = game.getSupportedLanguages();

        assertEquals(configuredCodes.size(), languages.size());
    }

    @Test
    public void getSupportedLanguages_calledTwice_returnsDifferentListObjectsWithEquivalentContents() {
        GameManager game = new GameManager();

        List<LanguageOption> firstCall = game.getSupportedLanguages();
        List<LanguageOption> secondCall = game.getSupportedLanguages();

        assertNotSame(firstCall, secondCall);
        assertEquals(firstCall.size(), secondCall.size());

        for (int i = 0; i < firstCall.size(); i++) {
            assertEquals(firstCall.get(i).getLocale(), secondCall.get(i).getLocale());
            assertEquals(firstCall.get(i).toString(), secondCall.get(i).toString());
        }
    }

    @Test
    public void getSupportedLanguages_returnedListCleared_preservesInternalList() {
        GameManager game = new GameManager();

        List<LanguageOption> languages = game.getSupportedLanguages();
        int originalSize = languages.size();

        languages.clear();

        List<LanguageOption> languagesAgain = game.getSupportedLanguages();

        assertEquals(originalSize, languagesAgain.size());
    }

    @Test
    public void getSupportedLanguages_fakeLanguageAddedToReturnedList_preservesInternalList() {
        GameManager game = new GameManager();

        List<LanguageOption> languages = game.getSupportedLanguages();
        int originalSize = languages.size();

        languages.add(new LanguageOption("Fake Language", Locale.forLanguageTag("xx")));

        List<LanguageOption> languagesAgain = game.getSupportedLanguages();

        assertEquals(originalSize, languagesAgain.size());

        boolean containsFakeLanguage = languagesAgain.stream()
                .anyMatch(language -> language.getLocale().equals(Locale.forLanguageTag("xx")));

        assertFalse(containsFakeLanguage);
    }

    @Test
    public void getWhitePlayer_afterStart_returnsWhitePlayer() {
        assertNotNull(game.getWhitePlayer());
        assertEquals(Color.WHITE, game.getWhitePlayer().getPlayerColor());
    }

    @Test
    public void getBlackPlayer_afterStart_returnsBlackPlayer() {
        assertNotNull(game.getBlackPlayer());
        assertEquals(Color.BLACK, game.getBlackPlayer().getPlayerColor());
    }

    @Test
    public void getWinner_newGame_returnsNull() {
        assertNull(game.getWinner());
    }

    @Test
    public void getWinner_afterWhiteCheckmated_returnsBlackPlayer() {
        King mockKing = EasyMock.createMock(King.class);
        EasyMock.expect(mockKing.getColor()).andReturn(Color.WHITE).anyTimes();
        EasyMock.expect(mockKing.getType()).andReturn(PieceType.KING).anyTimes();
        EasyMock.expect(mockKing.makeCopy()).andReturn(mockKing).anyTimes();
        EasyMock.expect(mockKing.isInCheck(
                EasyMock.anyObject(Location.class),
                EasyMock.anyObject(Board.class)
        )).andReturn(true).anyTimes();
        EasyMock.expect(mockKing.hasValidMoves(
                EasyMock.anyObject(Location.class),
                EasyMock.anyObject(Board.class)
        )).andReturn(false).anyTimes();

        EasyMock.replay(mockKing);

        board.setPiece(new Location(0, 0), mockKing);
        game.setBoard(board);

        assertTrue(game.isGameOver());

        assertNotNull(game.getWinner());
        assertEquals(Color.BLACK, game.getWinner().getPlayerColor());

        EasyMock.verify(mockKing);
    }

}
