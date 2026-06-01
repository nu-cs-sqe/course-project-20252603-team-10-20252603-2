package domain.piece;

import domain.Board;
import domain.Location;
import constants.Color;

public class King extends Piece{
    public King(Color color) {
        super(PieceType.KING, color);
    }

    public boolean isInCheck(Location kingLocation, Board board) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Location location = new Location(i, j);
                if (!board.isPieceHere(location)) continue;
                Piece piece = board.getPiece(location);
                if (piece.isSameColor(this)) continue;
                if (piece.getType() == PieceType.KING) {
                    if (canKingAttack(location, kingLocation)) return true;
                } else {
                    if (piece.isValidMove(location, kingLocation, board)) return true;
                }
            }
        }
        return false;
    }

    private boolean canKingAttack(Location from, Location kingPos) {
        int rowDistance = Math.abs(kingPos.getX() - from.getX());
        int colDistance = Math.abs(kingPos.getY() - from.getY());
        return rowDistance <= 1 && colDistance <= 1 && !(rowDistance == 0 && colDistance == 0);
    }

    @Override
    public Piece makeCopy() {
        return new King(this.getColor());
    }

    @Override
    public boolean isValidMove(Location start, Location end, Board board) {
        if (start.equals(end)) {
            return false;
        }

        int diffX = Math.abs(start.getX() - end.getX());
        int diffY = Math.abs(start.getY() - end.getY());

        if (diffX > 1 || diffY > 1) {
            return false;
        }
        return true;
    }

    @Override
    public boolean hasValidMoves() {
        //  TODO: complete method
        return false;
    }

}