package ui;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import domain.Board;
import domain.GameManager;
import domain.Location;
import domain.Player;
import domain.piece.Piece;
import domain.piece.PieceType;

import javax.swing.*;

public class BoardController {
    private BoardView boardView;
    private GameStatsView gameStatsView;
    private final GameManager gameManager;
    private Location selectedLocation;

    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP2",
            justification = "BoardController intentionally stores the shared GameManager used to coordinate game state."
    )
    public BoardController(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    void setBoardView(BoardView boardView) {
        this.boardView = boardView;
    }

    void setGameStatsView(GameStatsView gameStatsView) { this.gameStatsView = gameStatsView; }

    public boolean handleSquareClick(Location location) {
        if (selectedLocation == null) {
            return handleFirstClick(location);
        }
        handleSecondClick(location);
        return false;

    }

    public boolean handleFirstClick(Location location) {
        Board board = gameManager.getBoard();
        if (!board.isPieceHere(location)) {
            return false;
        }

        Piece selectedPiece = board.getPiece(location);
        Player currentPlayer = gameManager.getCurrentPlayer();

        if (selectedPiece.getColor() != currentPlayer.getPlayerColor()) {
            return false;
        }

        selectedLocation = location;
        return true;

    }

    public void handleSecondClick(Location endLocation) {
        Location startLocation = selectedLocation;
        selectedLocation = null;

        GameManager.MoveResult result = gameManager.movePiece(startLocation, endLocation);

        switch (result) {
            case SUCCESS:
                updateGameStatsView();
                break;
            case PROMOTION_REQUIRED:
                PieceType[] options = {
                        PieceType.QUEEN,
                        PieceType.KNIGHT,
                        PieceType.ROOK,
                        PieceType.BISHOP,
                };

                PieceType choice = (PieceType) JOptionPane.showInputDialog(
                        boardView,
                        gameManager.getMessage("board.pawnPromotionMessage"),
                        gameManager.getMessage("board.pawnPromotionTitle"),
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        PieceType.QUEEN
                );

                if (choice != null) {
                    gameManager.promotePawn(endLocation, choice);
                } else {
                    gameManager.promotePawn(endLocation, PieceType.QUEEN);
                }

                gameManager.changeTurns();
                updateGameStatsView();

                break;
            case INVALID_MOVE:
                JOptionPane.showMessageDialog(
                        boardView,
                        gameManager.getMessage("error.invalidMoveMessage"),
                        gameManager.getMessage("error.invalidMoveTitle"),
                        JOptionPane.WARNING_MESSAGE
                );
                break;
            case WRONG_PLAYER_PIECE:
                JOptionPane.showMessageDialog(
                        boardView,
                        gameManager.getMessage("error.wrongTeamMessage"),
                        gameManager.getMessage("error.wrongTeamTitle"),
                        JOptionPane.WARNING_MESSAGE
                );
                break;
            case NO_PIECE_SELECTED:
                JOptionPane.showMessageDialog(
                        boardView,
                        gameManager.getMessage("error.noSelectionMessage"),
                        gameManager.getMessage("error.noSelectionTitle"),
                        JOptionPane.WARNING_MESSAGE
                );
                break;

        }

        if (boardView != null) {
            boardView.repaint();
        }

    }

    public Piece[][] getBoardSnapshot() {
        Board board = gameManager.getBoard();
        return board.getSnapshot();
    }

    private void updateGameStatsView() {
        if (gameStatsView != null) {
            gameStatsView.updateCurrentPlayer(gameManager.getCurrentPlayer().getPlayerName());
            gameStatsView.updatePoints(gameManager.getWhitePlayer(), gameManager.getBlackPlayer());
        }
    }
}
