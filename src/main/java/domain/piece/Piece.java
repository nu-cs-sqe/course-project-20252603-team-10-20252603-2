package domain.piece;

import constants.Color;
import domain.Board;
import domain.Location;

public abstract class Piece {
    private final PieceType type;
    private final Color color;

    public Piece(PieceType type, Color color) {
        this.type = type;
        this.color = color;
    }

    public PieceType getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public boolean isSameColor(Piece otherPiece) {
        return this.color == otherPiece.getColor();
    }

    public abstract Piece makeCopy();

    public abstract boolean isValidMove(Location start, Location end, Board board);

    // TODO: implement for each piece whether they have any valid moves
    public abstract boolean hasValidMoves(Location location, Board board);

    @Override
    public String toString() {
        return String.format("%s %s", color.name(), type.name());
    }
}