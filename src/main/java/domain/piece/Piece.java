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

    public abstract boolean hasValidMoves(Location location, Board board);

    @Override
    public String toString() {
        return String.format("%s %s", color.name(), type.name());
    }

    protected boolean wouldExposeKing(Location start, Location end, Board board) {
        Piece originalTarget = board.getPiece(end);

        board.setPiece(end, this);
        board.removePiece(start);

        Location kingLocation = findAlliedKingLocation(board);
        boolean exposesKing = false;
        if (kingLocation != null) {
            King alliedKing = (King) board.getPiece(kingLocation);
            exposesKing = alliedKing.isInCheck(kingLocation, board);
        }

        board.setPiece(start, this);
        if (originalTarget != null) {
            board.setPiece(end, originalTarget);
        } else {
            board.removePiece(end);
        }

        return exposesKing;
    }

    private Location findAlliedKingLocation(Board board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Location loc = new Location(i, j);
                if (board.isPieceHere(loc)) {
                    Piece p = board.getPiece(loc);
                    if (p.getColor() == this.getColor() && p.getType() == PieceType.KING) {
                        return loc;
                    }
                }
            }
        }
        return null;
    }
}
