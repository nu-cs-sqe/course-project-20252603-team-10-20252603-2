package domain;

import constants.Color;
import domain.piece.*;

import java.util.ArrayList;
import java.util.List;

import static constants.Color.BLACK;
import static constants.Color.WHITE;

public class Board {

    private static final int TOTAL_ROWS = 8;
    private static final int TOTAL_COLS = 8;
    private Piece[][] pieces;


    public Board(boolean init){
        pieces = new Piece[TOTAL_ROWS][TOTAL_COLS];
        if (init) {
            initializeBoard();
        }

    }

    public Board(Board other) {
        this.pieces = new Piece[TOTAL_ROWS][TOTAL_COLS];
        if (other != null && other.pieces != null) {
            for (int i = 0; i < TOTAL_ROWS; i++) {
                for (int j = 0; j < TOTAL_COLS; j++) {
                    if (other.pieces[i][j] != null) {
                        this.pieces[i][j] = other.pieces[i][j].makeCopy();
                    }
                }
            }
        }
    }

    private void initializeBoard(){

        // Initialize pawns
        for (int i = 0; i < TOTAL_COLS; i++) {
            pieces[1][i] = new Pawn(BLACK);
            pieces[6][i] = new Pawn(WHITE);
        }


        // Initialize rooks
        pieces[0][0] = new Rook(BLACK);
        pieces[0][7] = new Rook(BLACK);
        pieces[7][0] = new Rook(WHITE);
        pieces[7][7] = new Rook(WHITE);

        // Initialize knights
        pieces[0][1] = new Knight(BLACK);
        pieces[0][6] = new Knight(BLACK);
        pieces[7][1] = new Knight(WHITE);
        pieces[7][6] = new Knight(WHITE);

        // Initialize bishops
        pieces[0][2] = new Bishop(BLACK);
        pieces[0][5] = new Bishop(BLACK);
        pieces[7][2] = new Bishop(WHITE);
        pieces[7][5] = new Bishop(WHITE);

        // Initialize queens and kings
        pieces[0][3] = new Queen(BLACK);
        pieces[0][4] = new King(BLACK);
        pieces[7][3] = new Queen(WHITE);
        pieces[7][4] = new King(WHITE);
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
    
    // TODO: requires BVA and testing (basic functionality written for testing purposes)
    public List<Piece> getValidPiecesByColor(Color color) {
        List<Piece> validPiecesByColor = new ArrayList<>();
        for (int i = 0; i < TOTAL_ROWS; i++) {
            for (int j = 0; j < TOTAL_COLS; j++) {
                Piece piece = pieces[i][j];
                if (piece != null && piece.getColor() == color && piece.hasValidMoves()) {
                    validPiecesByColor.add(piece);
                }
            }
        }
        return validPiecesByColor;
    }
}
