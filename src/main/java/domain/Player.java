package domain;

import constants.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Player {

    private final Color playerColor;

    private int points = 0;

    private static final Map<String, Integer> PIECE_VALUES = Map.of(
            "pawn", 1,
            "knight", 3,
            "bishop", 3,
            "rook", 5,
            "queen", 9
    );

    public Player(Color playerColor) {
        this.playerColor = playerColor;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public int getPoints() {
        return points;
    }

    public int getPieceValue(String piece) {
        return PIECE_VALUES.get(piece);
    }

    // using String as input instead of Piece for now
    public void incrementPoints(String piece) {
        points += getPieceValue(piece);
    }

    // placeholder return value because Board + King piece has not been implemented yet
    public boolean isInCheck() {
        return false;
    }
}
