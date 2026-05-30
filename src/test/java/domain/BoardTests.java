package domain;

import domain.piece.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTests {

    private static final int BOARD_SIZE = 8;

    private void assertBoardIsEmpty(Board board) {
        for (int x = 0; x < BOARD_SIZE ; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                assertFalse(board.isPieceHere(new Location(x,y)));
            }
        }
    }

    private void assertMiddleEmpty(Board board) {
        for (int x = 2; x < 5 ; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                assertFalse(board.isPieceHere(new Location(x,y)));
            }
        }
    }

    private void assertPawnRow(Board board, int row, PieceColor color) {
        for (int y = 0; y < BOARD_SIZE; y++) {
            Location loc = new Location(row, y);

            assertTrue(board.isPieceHere(loc));
            Piece piece = board.getPiece(loc);
            assertInstanceOf(Pawn.class, piece);
            assertEquals(color, piece.getColor());
        }
    }

    private void assertMajorRow(Board board, int row, PieceColor color) {

        List<Class<? extends Piece>> expectedPieces = List.of(
                Rook.class,
                Knight.class,
                Bishop.class,
                Queen.class,
                King.class,
                Bishop.class,
                Knight.class,
                Rook.class
        );

        for (int y = 0; y < BOARD_SIZE; y++) {
            Location loc = new Location(row, y);

            assertTrue(board.isPieceHere(loc));
            Piece piece = board.getPiece(loc);
            assertInstanceOf(expectedPieces.get(y), piece);
            assertEquals(color, piece.getColor());
        }
    }

    private void assertSnapshotEmpty(Piece[][] snapshot) {
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < BOARD_SIZE; y++) {
                assertNull(snapshot[x][y]);
            }
        }
    }

    private void assertSnapshotCopiedPiece(Board board, Piece original, Location location) {
        board.setPiece(location, original);

        Piece[][] snapshot = board.getSnapshot();
        Piece copy = snapshot[location.getX()][location.getY()];

        assertTrue(board.isPieceHere(location));
        assertNotSame(original, copy);
        assertEquals(original.getType(), copy.getType());
        assertEquals(original.getColor(), copy.getColor());
    }


    @Test
    void initBoard_noInit_returnsEmptyBoard() {
        Board board = new Board(false);

        assertBoardIsEmpty(board);

    }

    @Test
    void initBoard_init_returnsInitBoardCheckPawns() {
        Board board = new Board(true);

        assertPawnRow(board, 1, PieceColor.BLACK);
        assertPawnRow(board, 6, PieceColor.WHITE);

    }

    @Test
    void initBoard_init_returnsInitBoardCheckMajorWhite() {
        Board board = new Board(true);

        assertMajorRow(board, 7, PieceColor.WHITE);

    }

    @Test
    void initBoard_init_returnsInitBoardCheckMajorBlack() {
        Board board = new Board(true);

        assertMajorRow(board, 0, PieceColor.BLACK);

    }

    @Test
    void initBoard_init_returnsInitBoardCheckEmptyMiddle() {
        Board board = new Board(true);

        assertMiddleEmpty(board);

    }

    @Test
    void getSnapshot_emptyBoard_returnsAllNull() {
        Board board = new Board(false);

        Piece[][] snapshot = board.getSnapshot();

        assertSnapshotEmpty(snapshot);

    }

    @Test
    void getSnapshot_onePiece_returnsSnapshotSamePiece() {
        Board board = new Board(false);
        Pawn piece = new Pawn(PieceColor.BLACK);
        Location location = new Location(0,0);
        board.setPiece(location, piece);

        assertSnapshotCopiedPiece(board, piece, location);

    }

    @Test
    void isPieceHere_emptyBoardx0y0_returnsFalse() {
        Board board = new Board(false);
        Location location = new Location(0,0);

        assertFalse(board.isPieceHere(location));
    }

    @Test
    void isPieceHere_onePiecex0y0_returnsTrue() {
        Board board = new Board(false);
        Location location = new Location(0,0);

        board.setPiece(location, new Pawn(PieceColor.WHITE));

        assertTrue(board.isPieceHere(location));
    }

    @Test
    void isPieceHere_onePiecex7y7_returnsTrue() {
        Board board = new Board(false);
        Location location = new Location(7,7);

        board.setPiece(location, new Pawn(PieceColor.BLACK));

        assertTrue(board.isPieceHere(location));
    }

    @Test
    void isPieceHere_initBoardBlackPawn_returnsTrue() {
        Board board = new Board(true);
        Location location = new Location(1,0);

        assertTrue(board.isPieceHere(location));
    }

    @Test
    void isPieceHere_initBoardEmptyMiddle_returnsFalse() {
        Board board = new Board(true);
        Location location = new Location(3,3);

        assertFalse(board.isPieceHere(location));
    }

    @Test
    void getPiece_emptyBoardx0y0_returnsNull() {
        Board board = new Board(false);
        Location location = new Location(0,0);

        assertNull(board.getPiece(location));
    }

    @Test
    void getPiece_whitePawnx0y0_returnsWhitePawn() {
        Board board = new Board(false);
        Location location = new Location(0,0);
        Pawn piece = new Pawn(PieceColor.WHITE);

        board.setPiece(location, piece);

        assertSame(piece, board.getPiece(location));
    }

    @Test
    void getPiece_blackQueenx7y7_returnsBlackQueen() {
        Board board = new Board(false);
        Location location = new Location(7,7);
        Queen piece = new Queen(PieceColor.BLACK);

        board.setPiece(location, piece);

        assertSame(piece, board.getPiece(location));
    }

    @Test
    void getPiece_initBoardx0y0_returnsBlackRook() {
        Board board = new Board(true);
        Location location = new Location(0,0);
        Rook piece = new Rook(PieceColor.BLACK);

        board.setPiece(location, piece);

        assertSame(piece, board.getPiece(location));
    }

    @Test
    void setPiece_emptyBoardx0y0_setsWhitePawn() {
        Board board = new Board(false);
        Location location = new Location(0,0);
        Pawn piece = new Pawn(PieceColor.WHITE);

        board.setPiece(location, piece);

        assertTrue(board.isPieceHere(location));
        assertSame(piece, board.getPiece(location));
    }

    @Test
    void setPiece_emptyBoardx7y7_setsBlackKnight() {
        Board board = new Board(false);
        Location location = new Location(7,7);
        Knight piece = new Knight(PieceColor.BLACK);

        board.setPiece(location, piece);

        assertTrue(board.isPieceHere(location));
        assertSame(piece, board.getPiece(location));
    }

    @Test
    void setPiece_occupiedSquarex0y0_setsBlackQueen() {
        Board board = new Board(false);
        Location location = new Location(0,0);
        Queen piece = new Queen(PieceColor.BLACK);
        Pawn occupier = new Pawn(PieceColor.WHITE);

        board.setPiece(location, occupier);

        assertTrue(board.isPieceHere(location));
        assertSame(occupier, board.getPiece(location));

        board.setPiece(location, piece);

        assertTrue(board.isPieceHere(location));
        assertSame(piece, board.getPiece(location));
    }

}

