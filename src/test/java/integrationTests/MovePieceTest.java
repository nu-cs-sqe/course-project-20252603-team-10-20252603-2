package domain;

import constants.Color;
import domain.piece.Piece;
import domain.piece.PieceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MovePieceTest {

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
}