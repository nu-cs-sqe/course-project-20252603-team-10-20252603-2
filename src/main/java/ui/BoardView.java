package ui;

import domain.Location;
import domain.piece.Piece;
import domain.piece.PieceColor;
import domain.piece.PieceType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class BoardView extends JPanel {
    private static final int BOARD_SIZE = 8;
    private static final int TILE_SIZE = 100; // size of each square in pixels
    private final Color LIGHT_SQUARE_COLOR = new Color(240, 217, 181);
    private final Color DARK_SQUARE_COLOR = new Color(181, 136, 99);

    private final Color SELECTED_SQUARE_COLOR = new Color(164, 149, 195); // NU Purple 40
    private int selectedRow = -1;
    private int selectedCol = -1;

    private Map<PieceType, Image> whitePieceImages;
    private Map<PieceType, Image> blackPieceImages;

    private BoardController boardController;

    public BoardView(BoardController boardController) {
        setPreferredSize(new Dimension(BOARD_SIZE * TILE_SIZE, BOARD_SIZE * TILE_SIZE));
        loadPieceImages();
        this.boardController = boardController;
        boardController.setBoardView(this);
        addMouseListener(new BoardMouseListener());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        drawSelectedSquare(g);
        drawPieces(g); // draw piece after draw selected square to ensure piece image is on top
    }

    private void drawBoard(Graphics g) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Color squareColor = (row + col) % 2 == 0 ? LIGHT_SQUARE_COLOR : DARK_SQUARE_COLOR;
                g.setColor(squareColor);
                g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    private void loadPieceImages() {

        whitePieceImages = new HashMap<>();
        blackPieceImages = new HashMap<>();

        loadOnePieceImage(PieceType.PAWN, PieceColor.WHITE, "images/white_pawn.png");
        loadOnePieceImage(PieceType.ROOK, PieceColor.WHITE, "images/white_rook.png");
        loadOnePieceImage(PieceType.KNIGHT, PieceColor.WHITE, "images/white_knight.png");
        loadOnePieceImage(PieceType.BISHOP, PieceColor.WHITE, "images/white_bishop.png");
        loadOnePieceImage(PieceType.QUEEN, PieceColor.WHITE, "images/white_queen.png");
        loadOnePieceImage(PieceType.KING, PieceColor.WHITE, "images/white_king.png");

        loadOnePieceImage(PieceType.PAWN, PieceColor.BLACK, "images/black_pawn.png");
        loadOnePieceImage(PieceType.ROOK, PieceColor.BLACK, "images/black_rook.png");
        loadOnePieceImage(PieceType.KNIGHT, PieceColor.BLACK, "images/black_knight.png");
        loadOnePieceImage(PieceType.BISHOP, PieceColor.BLACK, "images/black_bishop.png");
        loadOnePieceImage(PieceType.QUEEN, PieceColor.BLACK, "images/black_queen.png");
        loadOnePieceImage(PieceType.KING, PieceColor.BLACK, "images/black_king.png");
    }

    private void loadOnePieceImage(PieceType type, PieceColor color, String imagePath) {
        InputStream imageInputStream = getClass().getClassLoader().getResourceAsStream(imagePath);
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (color.equals(PieceColor.BLACK)) {
            blackPieceImages.put(type, image);
        }
        else {
            this.whitePieceImages.put(type, image);
        }
    }

    private void drawPieces(Graphics g) {

        Piece[][] boardSnapshot = boardController.getBoardSnapshot();

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Piece piece = boardSnapshot[row][col];
                if (piece != null) {
                    Image pieceImage = piece.getColor() == PieceColor.WHITE ?
                            whitePieceImages.get(piece.getType()) :
                            blackPieceImages.get(piece.getType());
                    g.drawImage(pieceImage, col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
                }
            }
        }
    }

    private void drawSelectedSquare(Graphics g) {
        g.setColor(SELECTED_SQUARE_COLOR);
        g.fillRect(selectedCol * TILE_SIZE, selectedRow * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }


    private class BoardMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {

            selectedCol = e.getX() / TILE_SIZE;
            selectedRow = e.getY() / TILE_SIZE;

            // FIXME: perform input validation

            repaint();

            boardController.handleSquareClick(new Location(selectedCol, selectedRow));
        }
    }
}
