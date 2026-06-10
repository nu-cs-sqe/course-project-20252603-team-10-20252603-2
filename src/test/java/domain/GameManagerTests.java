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
                .filter(code -> !code.isEmpty())
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
        board.setPiece(new Location(0, 0), new King(Color.WHITE));
        board.setPiece(new Location(0, 1), new Queen(Color.BLACK));
        board.setPiece(new Location(1, 0), new Rook(Color.BLACK));
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

    @Test
    public void promotePawn_whitePawnToKing_throwIllegalArgException() {
        GameManager game = new GameManager();
        game.addPlayer(new Player("Player1", Color.BLACK));
        game.addPlayer(new Player("Player2", Color.WHITE));
        game.start();

        Pawn piece = new Pawn(Color.WHITE);
        Board updatedBoard = game.getBoard();
        updatedBoard.setPiece(new Location(0,0), piece);
        game.setBoard(updatedBoard);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            game.promotePawn(new Location(0,0), PieceType.KING);
        });

        assertTrue(exception.getMessage().contains("Invalid promotion piece."));

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
    public void loadSupportedLanguages_validFixedResources_supportedValueMatchesConfiguredLocaleCount() {
        GameManager game = new GameManager();

        List<String> configuredCodes = getConfiguredLocaleCodes();
        List<LanguageOption> languages = game.getSupportedLanguages();

        assertEquals(configuredCodes.size(), languages.size());
    }

}
