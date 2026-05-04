package domain;

import domain.piece.*;

import static domain.piece.PieceColor.*;

public class Board {

    private final int TOTAL_ROWS = 8;
    private final int TOTAL_COLS = 8;
    private Piece[][] pieces;


    public Board(boolean init){
        pieces = new Piece[TOTAL_ROWS][TOTAL_COLS];
        if (init) {
            initializeBoard();
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
        for(int i = 0; i < TOTAL_ROWS; i++) {
            for (int j = 0; j < TOTAL_COLS; j++) {
                if (pieces[i][j] != null) {
                    snapshot[i][j] = pieces[i][j].makeCopy();
                }
            }
        }
        return snapshot;
    }

}