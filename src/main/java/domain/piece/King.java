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
        if (!isValidMovementPattern(start, end)) {
            return false;
        }
        if (!isValidDestination(end, board)) {
            return false;
        }
        return !doesMoveKingIntoCheck(start, end, board);
    }

    private boolean isValidMovementPattern(Location start, Location end) {
        if (start.equals(end)) {
            return false;
        }

        int diffX = Math.abs(start.getX() - end.getX());
        int diffY = Math.abs(start.getY() - end.getY());

        return diffX <= 1 && diffY <= 1;
    }

    private boolean isValidDestination(Location end, Board board) {
        if (board.isPieceHere(end)) {
            Piece target = board.getPiece(end);
            return target.getColor() != this.getColor();
        }
        return true;
    }

    private boolean doesMoveKingIntoCheck(Location start, Location end, Board board) {
        Piece originalPiece = board.getPiece(end);

        board.setPiece(end, this);
        board.removePiece(start);

        boolean inCheck = this.isInCheck(end, board);

        board.setPiece(start, this);
        if (originalPiece != null) {
            board.setPiece(end, originalPiece);
        } else {
            board.removePiece(end);
        }

        return inCheck;
    }

    @Override
    public boolean hasValidMoves(Location location, Board board) {
        return true;
    }

}