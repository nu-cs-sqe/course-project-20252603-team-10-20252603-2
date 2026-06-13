package domain;

import constants.Color;
import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.PieceType;
import domain.piece.Queen;
import domain.piece.Rook;

import java.util.ArrayList;
import java.util.List;

import static constants.Color.BLACK;
import static constants.Color.WHITE;

public class Board {

    private static final int TOTAL_ROWS = 8;
    private static final int TOTAL_COLS = 8;
    private static final int LAST_ROW = 7;
    private static final int BACK_ROW_BLACK = 0;
    private static final int PAWN_ROW_BLACK = 1;
    private static final int PAWN_ROW_WHITE = 6;
    private static final int ROOK_COL_QUEENSIDE = 0;
    private static final int KNIGHT_COL_QUEENSIDE = 1;
    private static final int BISHOP_COL_QUEENSIDE = 2;
    private static final int QUEEN_COL = 3;
    private static final int KING_COL = 4;
    private static final int BISHOP_COL_KINGSIDE = 5;
    private static final int KNIGHT_COL_KINGSIDE = 6;
    private static final int ROOK_COL_KINGSIDE = 7;

    private final Piece[][] pieces;

    public Board(boolean init) {
        pieces = new Piece[TOTAL_ROWS][TOTAL_COLS];
        if (init) {
            initializeBoard();
        }
    }

    public Board(Board other) {
        this.pieces = new Piece[TOTAL_ROWS][TOTAL_COLS];
        if (other != null && other.pieces != null) {
            copyPiecesFrom(other);
        }
    }

    private void copyPiecesFrom(Board other) {
        for (int i = 0; i < TOTAL_ROWS; i++) {
            for (int j = 0; j < TOTAL_COLS; j++) {
                if (other.pieces[i][j] != null) {
                    this.pieces[i][j] = other.pieces[i][j].makeCopy();
                }
            }
        }
    }

    private void initializeBoard() {
        initializePawns();
        initializeBackRow(BACK_ROW_BLACK, BLACK);
        initializeBackRow(LAST_ROW, WHITE);
    }

    private void initializePawns() {
        for (int i = 0; i < TOTAL_COLS; i++) {
            pieces[PAWN_ROW_BLACK][i] = new Pawn(BLACK);
            pieces[PAWN_ROW_WHITE][i] = new Pawn(WHITE);
        }
    }

    private void initializeBackRow(int row, Color color) {
        pieces[row][ROOK_COL_QUEENSIDE] = new Rook(color);
        pieces[row][ROOK_COL_KINGSIDE] = new Rook(color);
        pieces[row][KNIGHT_COL_QUEENSIDE] = new Knight(color);
        pieces[row][KNIGHT_COL_KINGSIDE] = new Knight(color);
        pieces[row][BISHOP_COL_QUEENSIDE] = new Bishop(color);
        pieces[row][BISHOP_COL_KINGSIDE] = new Bishop(color);
        pieces[row][QUEEN_COL] = new Queen(color);
        pieces[row][KING_COL] = new King(color);
    }

    public Piece[][] getSnapshot() {
        Piece[][] snapshot = new Piece[TOTAL_ROWS][TOTAL_COLS];
        for (int i = 0; i < TOTAL_ROWS; i++) {
            for (int j = 0; j < TOTAL_COLS; j++) {
                if (pieces[i][j] != null) {
                    snapshot[i][j] = pieces[i][j].makeCopy();
                }
            }
        }
        return snapshot;
    }

    public boolean isPieceHere(Location location) {
        return pieces[location.getX()][location.getY()] != null;
    }

    public Piece getPiece(Location location) {
        return pieces[location.getX()][location.getY()];
    }

    public void setPiece(Location location, Piece piece) {
        if (piece == null) {
            throw new IllegalArgumentException("piece cannot be null");
        }
        pieces[location.getX()][location.getY()] = piece;
    }

    public void removePiece(Location location) {
        pieces[location.getX()][location.getY()] = null;
    }

    public List<Piece> getValidPiecesByColor(Color color) {
        List<Piece> validPiecesByColor = new ArrayList<>();
        for (int i = 0; i < TOTAL_ROWS; i++) {
            for (int j = 0; j < TOTAL_COLS; j++) {
                Piece piece = pieces[i][j];
                Location location = new Location(i, j);
                if (piece != null && piece.getColor() == color && piece.hasValidMoves(location, this)) {
                    validPiecesByColor.add(piece);
                }
            }
        }
        return validPiecesByColor;
    }

    public Location findKingLocation(Color color) {
        for (int x = 0; x < TOTAL_ROWS; x++) {
            for (int y = 0; y < TOTAL_COLS; y++) {
                Piece piece = pieces[x][y];
                if (piece != null && piece.getType() == PieceType.KING && piece.getColor() == color) {
                    return new Location(x, y);
                }
            }
        }
        return null;
    }
}