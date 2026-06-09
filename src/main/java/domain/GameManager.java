package domain;

import constants.Color;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.Bishop;
import domain.piece.Knight;
import domain.piece.King;
import domain.piece.Piece;
import domain.piece.PieceType;

import java.util.MissingResourceException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


public class GameManager {
    private ResourceBundle messages;

    private List<Player> players = new ArrayList<>();
    private boolean isGameRunning = false;
    private Player whitePlayer;
    private Player blackPlayer;
    private Player currentPlayer;
    private int consecutiveDrawMoves = 0;
    private Board board;

    private static final int WHITE_PROMOTION_ROW = 0;
    private static final int BLACK_PROMOTION_ROW = 7;

    public GameManager() {
        this.board = null;
        this.whitePlayer = null;
        this.blackPlayer = null;
        this.currentPlayer = null;
    }

    public void incrementDrawCounter() {
        this.consecutiveDrawMoves++;
    }

    public boolean isGameADraw() {
        return consecutiveDrawMoves >= 50;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void start() throws IllegalStateException {
        if (players.size() < 2) {
            throw new IllegalStateException("Not enough players in the game.");
        } else if (players.size() > 2) {
            throw new IllegalStateException("Maximum number of players allowed is 2.");
        }

        if (players.get(0).getPlayerColor() == players.get(1).getPlayerColor()) {
            throw new IllegalStateException("Players cannot have the same color.");
        }
        assignPlayers(players.get(0), players.get(1));
        board = new Board(true);
        this.isGameRunning = true;
    }

    public void assignPlayers(Player player1, Player player2) {
        if (player1.getPlayerColor() == Color.WHITE) {
            whitePlayer = player1;
            blackPlayer = player2;
        } else {
            blackPlayer = player1;
            whitePlayer = player2;
        }
        currentPlayer = whitePlayer;
    }

    public void changeTurns() throws IllegalStateException {
        if (!isGameADraw()) {
            currentPlayer = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;
        } else {
            throw new IllegalStateException("Game is a draw.");
        }
    }

    public boolean isGameRunning() {
        return isGameRunning;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getWhitePlayer() { return whitePlayer; }

    public Player getBlackPlayer() { return blackPlayer; }

    public Board getBoard() {
        if (this.board == null) {
            return null;
        }
        return new Board(this.board);
    }

    public void setBoard(Board board) {
        if (board == null) {
            this.board = null;
        } else {
            this.board = new Board(board);
        }
    }

    public boolean isGameOver() {
        if (currentPlayer == null || board == null) {
            return false;
        }
        return isGameADraw() || isCheckmate() || isStalemate();
    }

    public boolean isCheckmate() {
        if (currentPlayer == null || board == null) {
            return false;
        };

        Location kingLocation = findKingLocation(currentPlayer.getPlayerColor());
        if (kingLocation == null) return false;

        King alliedKing = (King) board.getPiece(kingLocation);
        boolean hasValidMoves = !board.getValidPiecesByColor(currentPlayer.getPlayerColor()).isEmpty();

        return alliedKing.isInCheck(kingLocation, board) && !hasValidMoves;
    }

    public boolean isStalemate() {
        if (currentPlayer == null || board == null) return false;

        Location kingLocation = findKingLocation(currentPlayer.getPlayerColor());
        if (kingLocation == null) return false;

        King alliedKing = (King) board.getPiece(kingLocation);
        boolean hasValidMoves = !board.getValidPiecesByColor(currentPlayer.getPlayerColor()).isEmpty();

        return !alliedKing.isInCheck(kingLocation, board) && !hasValidMoves;
    }

    private Location findKingLocation(Color playerColor) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Location loc = new Location(i, j);
                if (board.isPieceHere(loc)) {
                    Piece p = board.getPiece(loc);
                    if (p.getColor() == playerColor && p.getType() == PieceType.KING) {
                        return loc;
                    }
                }
            }
        }
        return null;
    }

    public void setLocale(Locale locale) {
        messages = ResourceBundle.getBundle("messages", locale);
    }

    public String getMessage(String key) {
        if (messages == null) {
            setLocale(Locale.ENGLISH);
        }
        try {
            return messages.getString(key);
        } catch (MissingResourceException e) {
            return key;
        }
    }

    public enum MoveResult {
        INVALID_MOVE,
        NO_PIECE_SELECTED,
        WRONG_PLAYER_PIECE,
        SUCCESS,
        PROMOTION_REQUIRED,
    }

    private boolean isPromotionMove(Piece piece, Location location) {

        if (piece == null) {
            return false;
        }

        if (piece.getType() != PieceType.PAWN) {
            return false;
        }

        if (piece.getColor() == Color.BLACK) {
            return location.getX() == BLACK_PROMOTION_ROW;
        }

        return location.getX() == WHITE_PROMOTION_ROW;

    }

    public MoveResult movePiece(Location start, Location end) {
        if (!board.isPieceHere(start)) {
            return MoveResult.NO_PIECE_SELECTED;
        }

        Piece pieceToMove = this.board.getPiece(start);
        Player currentPlayer = this.currentPlayer;

        if (pieceToMove.getColor() != currentPlayer.getPlayerColor()) {
            return MoveResult.WRONG_PLAYER_PIECE;
        }

        if (pieceToMove.isValidMove(start, end, board)) {
            if (board.isPieceHere(end)) {
                Piece capturedPiece = board.getPiece(end);
                this.currentPlayer.incrementPoints(capturedPiece.getType());
            }

            board.setPiece(end, pieceToMove);
            board.removePiece(start);

            if (isPromotionMove(pieceToMove, end)) {
                return MoveResult.PROMOTION_REQUIRED;
            }

            this.changeTurns();
            return MoveResult.SUCCESS;

        }

        return MoveResult.INVALID_MOVE;
    }

    public void promotePawn(Location location, PieceType newType) {
        Piece piece = board.getPiece(location);

        if (!isPromotionMove(piece, location)) {
            throw new IllegalArgumentException("Piece is not eligible for promotion.");
        }

        Color color = piece.getColor();

        switch(newType) {
            case QUEEN:
                board.setPiece(location, new Queen(color));
                break;
            case ROOK:
                board.setPiece(location, new Rook(color));
                break;
            case BISHOP:
                board.setPiece(location, new Bishop(color));
                break;
            case KNIGHT:
                board.setPiece(location, new Knight(color));
                break;
            default:
                throw new IllegalArgumentException("Invalid promotion piece.");

        }

    }



}
