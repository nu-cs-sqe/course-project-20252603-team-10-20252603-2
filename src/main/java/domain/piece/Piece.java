package domain.piece;

import domain.Board;
import domain.Location;

public abstract class Piece {
    private final PieceType type;
    private final PieceColor color;

    public Piece(PieceType type, PieceColor color) {
        this.type = type;
        this.color = color;
    }

    public PieceType getType() {
        return type;
    }

    public PieceColor getColor() {
        return color;
    }

    public boolean isSameColor(Piece otherPiece) {
        return this.color == otherPiece.getColor();
    }

    public abstract Piece makeCopy();

    public abstract boolean isValidMove(Location start, Location end, Board board);

    @Override
    public String toString() {
        return String.format("%s %s", color.name(), type.name());
    }
}