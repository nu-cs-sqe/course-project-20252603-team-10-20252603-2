package integration;

import constants.Color;
import domain.Board;
import domain.GameManager;
import domain.Location;
import domain.Player;
import domain.piece.King;
import domain.piece.Queen;
import domain.piece.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
}