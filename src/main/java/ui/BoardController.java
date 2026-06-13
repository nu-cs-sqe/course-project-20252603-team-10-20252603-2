package ui;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import domain.Board;
import domain.GameManager;
import domain.Location;
import domain.Player;
import domain.piece.Piece;
import domain.piece.PieceType;

import javax.swing.*;
import java.awt.*;
import java.text.MessageFormat;

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
        repaintBoard();

        GameManager.MoveResult result = gameManager.movePiece(startLocation, endLocation);

        Object[] closeButton = {
                gameManager.getMessage("button.close")
        };

        switch (result) {
            case SUCCESS:
                updateGameStatsView();
                repaintBoard();
                checkGameOver();
                break;
            case PROMOTION_REQUIRED:
                PieceType[] options = {
                        PieceType.QUEEN,
                        PieceType.KNIGHT,
                        PieceType.ROOK,
                        PieceType.BISHOP,
                };

                JComboBox<PieceType> promotionBox = new JComboBox<>(options);
                promotionBox.setSelectedItem(PieceType.QUEEN);

                Object[] promotionButtons = {
                        gameManager.getMessage("button.select")
                };

                JOptionPane promotionPane = new JOptionPane(
                        new Object[] {
                                gameManager.getMessage("board.pawnPromotionMessage"),
                                promotionBox
                        },
                        JOptionPane.QUESTION_MESSAGE,
                        JOptionPane.DEFAULT_OPTION,
                        null,
                        promotionButtons,
                        promotionButtons[0]
                );

                JDialog promotionDialog = promotionPane.createDialog(
                        boardView,
                        gameManager.getMessage("board.pawnPromotionTitle")
                );

                promotionDialog.setVisible(true);

                Object selectedButton = promotionPane.getValue();

                PieceType choice;
                if (selectedButton == promotionButtons[0]) {
                    choice = (PieceType) promotionBox.getSelectedItem();
                } else {
                    choice = PieceType.QUEEN;
                }

                gameManager.promotePawn(endLocation, choice);

                gameManager.changeTurns();
                updateGameStatsView();
                repaintBoard();
                checkGameOver();
                break;
            case INVALID_MOVE:
                JOptionPane.showOptionDialog(
                        boardView,
                        gameManager.getMessage("error.invalidMoveMessage"),
                        gameManager.getMessage("error.invalidMoveTitle"),
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        closeButton,
                        closeButton[0]
                );

                break;
            case WRONG_PLAYER_PIECE:
                JOptionPane.showOptionDialog(
                        boardView,
                        gameManager.getMessage("error.wrongTeamMessage"),
                        gameManager.getMessage("error.wrongTeamTitle"),
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        closeButton,
                        closeButton[0]
                );

                break;
            case NO_PIECE_SELECTED:
                JOptionPane.showOptionDialog(
                        boardView,
                        gameManager.getMessage("error.noSelectionMessage"),
                        gameManager.getMessage("error.noSelectionTitle"),
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        closeButton,
                        closeButton[0]
                );

                break;
            default:
                repaintBoard();
                updateGameStatsView();
                break;

        }


    }

    private void repaintBoard() {
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

    private void checkGameOver() {
        if (!gameManager.isGameOver()) {
            return;
        }

        String message;

        if (gameManager.isGameADraw()) {
            message = gameManager.getMessage("gameOver.message.draw");
        } else if (gameManager.isCheckmate()) {
            message = MessageFormat.format(
                    gameManager.getMessage("gameOver.message.checkmate"),
                    gameManager.getWinner().getPlayerName());
        } else if (gameManager.isStalemate()) {
            message = gameManager.getMessage("gameOver.message.stalemate");
        } else {
            message = gameManager.getMessage("gameOver.message.generic");
        }

        Object[] options = {
                gameManager.getMessage("button.close")
        };

        JOptionPane.showOptionDialog(
                boardView,
                message,
                gameManager.getMessage("gameOver.title"),
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        Window window = SwingUtilities.getWindowAncestor(boardView);
        if (window != null) {
            window.dispose();
        }
    }
}
