package integration;

import constants.Color;
import domain.Board;
import domain.GameManager;
import domain.Location;
import domain.Player;
import domain.piece.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameEndIntegrationTest {

    private GameManager game;

    @BeforeEach
    public void setUp() {
        game = new GameManager();
        game.addPlayer(new Player("White", Color.WHITE));
        game.addPlayer(new Player("Black", Color.BLACK));
        game.start();
    }

    @Test
    public void gameEnd_blackKingInCheck_whiteWinsAndGameIsOver() {
        final int blackKingRow = 0;
        final int blackKingCol = 0;

        final int rook1Row = 1;
        final int rook1Col = 7;

        final int rook2Row = 0;
        final int rook2Col = 7;

        final int whiteKingRow = 2;
        final int whiteKingCol = 7;

        Board board = new Board(false);

        board.setPiece(new Location(blackKingRow, blackKingCol), new King(Color.BLACK));
        board.setPiece(new Location(rook1Row, rook1Col), new Rook(Color.WHITE));
        board.setPiece(new Location(rook2Row, rook2Col), new Rook(Color.WHITE));
        board.setPiece(new Location(whiteKingRow, whiteKingCol), new King(Color.WHITE));

        game.setBoard(board);
        game.changeTurns();

        assertTrue(game.getBoard().getValidPiecesByColor(Color.BLACK).isEmpty());
        assertTrue(game.isGameOver());
        assertTrue(game.isCheckmate());
        assertEquals(Color.BLACK, game.getCurrentPlayer().getPlayerColor());
    }

    @Test
    public void gameEnd_blackKingNotInCheckWithNoValidMoves_gameIsADraw() {
        final int blackKingRow = 0;
        final int blackKingCol = 0;

        final int whiteQueenRow = 2;
        final int whiteQueenCol = 1;

        final int whiteKingRow = 2;
        final int whiteKingCol = 2;

        Board board = new Board(false);

        board.setPiece(new Location(blackKingRow, blackKingCol), new King(Color.BLACK));
        board.setPiece(new Location(whiteQueenRow, whiteQueenCol), new Queen(Color.WHITE));
        board.setPiece(new Location(whiteKingRow, whiteKingCol), new King(Color.WHITE));

        game.setBoard(board);
        game.changeTurns();

        assertTrue(board.getValidPiecesByColor(Color.BLACK).isEmpty());
        assertTrue(game.isGameOver());
        assertTrue(game.isStalemate());
    }

    @Test
    public void gameEnd_movesAt49_whenCounterIncrements_gameIsOver() {
        final int moveCount = 49;

        for (int i = 0; i < moveCount; i++) {
            game.incrementDrawCounter();
        }

        assertFalse(game.isGameOver());
        assertFalse(game.isGameADraw());

        game.incrementDrawCounter();

        assertTrue(game.isGameADraw());
        assertTrue(game.isGameOver());
    }

    public static class MovePieceTest {

        private GameManager gameManager;

        private Board board;

        private Player playerOne;

        private Player playerTwo;

        @BeforeEach
        public void setUp() {
            gameManager = new GameManager();

            playerOne = new Player("Alice", Color.WHITE);
            playerTwo = new Player("Bob", Color.BLACK);
            gameManager.addPlayer(playerOne);
            gameManager.addPlayer(playerTwo);
            gameManager.start();
            board = gameManager.getBoard();
        }

        @Test
        public void testValidPawnMove_UpdatesBoardAndSwitchesTurn() {
            Location start = new Location(6, 0);
            Location end = new Location(4, 0);

            assertEquals(Color.WHITE, gameManager.getCurrentPlayer().getPlayerColor());
            assertTrue(board.isPieceHere(start));
            assertEquals(PieceType.PAWN, board.getPiece(start).getType());
            assertEquals(Color.WHITE, board.getPiece(start).getColor());

            GameManager.MoveResult moveSuccessful = gameManager.movePiece(start, end);

            board = gameManager.getBoard();

            assertEquals(GameManager.MoveResult.SUCCESS, moveSuccessful);
            assertFalse(board.isPieceHere(start));
            assertTrue(board.isPieceHere(end));

            Piece movedPiece = board.getPiece(end);
            assertEquals(PieceType.PAWN, movedPiece.getType());
            assertEquals(Color.WHITE, movedPiece.getColor());

            assertEquals(Color.BLACK, gameManager.getCurrentPlayer().getPlayerColor());
        }

        @Test
        public void testValidKnightMove_UpdatesBoardAndSwitchesTurn() {
            Location start = new Location(7, 1);
            Location end = new Location(5, 2);

            assertEquals(Color.WHITE, gameManager.getCurrentPlayer().getPlayerColor());
            assertTrue(board.isPieceHere(start));
            assertEquals(PieceType.KNIGHT, board.getPiece(start).getType());

            GameManager.MoveResult moveSuccessful = gameManager.movePiece(start, end);
            board = gameManager.getBoard();

            assertEquals(moveSuccessful, GameManager.MoveResult.SUCCESS);
            assertFalse(board.isPieceHere(start));
            assertTrue(board.isPieceHere(end));

            Piece movedPiece = board.getPiece(end);
            assertEquals(PieceType.KNIGHT, movedPiece.getType());
            assertEquals(Color.WHITE, movedPiece.getColor());

            assertEquals(Color.BLACK, gameManager.getCurrentPlayer().getPlayerColor());
        }

        @Test
        public void testInvalidMove_WrongTurn_ReturnsWrongPlayerPieceAndMaintainsState() {
            final int MIN_COORD = 0;
            final int BOARD_SIZE = 8;

            Location blackPawnStart = new Location(1, 0);
            Location illegalDestination = new Location(3, 0);

            assertEquals(Color.WHITE, gameManager.getCurrentPlayer().getPlayerColor());
            Piece[][] boardStateBefore = gameManager.getBoard().getSnapshot();

            GameManager.MoveResult result = gameManager.movePiece(blackPawnStart,
                    illegalDestination);

            assertEquals(GameManager.MoveResult.WRONG_PLAYER_PIECE, result);

            assertEquals(Color.WHITE, gameManager.getCurrentPlayer().getPlayerColor());

            Piece[][] boardStateAfter = gameManager.getBoard().getSnapshot();
            for (int i = MIN_COORD; i < BOARD_SIZE; i++) {
                for (int j = MIN_COORD; j < BOARD_SIZE; j++) {
                    Piece before = boardStateBefore[i][j];
                    Piece after = boardStateAfter[i][j];
                    if (before == null) {
                        assertNull(after);
                    } else {
                        assertNotNull(after);
                        assertEquals(before.getType(), after.getType());
                        assertEquals(before.getColor(), after.getColor());
                    }
                }
            }
        }
    }
}
