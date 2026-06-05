package domain;

import constants.Color;
import domain.piece.PieceType;

import java.util.Map;

public class Player {

    private final Color playerColor;

    private int points = 0;

    private static final Map<PieceType, Integer> PIECE_VALUES = Map.of(
            PieceType.PAWN, 1,
            PieceType.KNIGHT, 3,
            PieceType.BISHOP, 3,
            PieceType.ROOK, 5,
            PieceType.QUEEN, 9
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

    public int getPieceValue(PieceType type) {
        return PIECE_VALUES.get(type);
    }

    public void incrementPoints(PieceType type) {
        points += getPieceValue(type);
    }

    // placeholder return value because Board + King piece has not been implemented yet
    public boolean isInCheck() {
        return false;
    }
}
