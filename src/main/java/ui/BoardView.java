package ui;

import domain.Location;
import domain.piece.Piece;
import constants.Color;
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

public final class BoardView extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int BOARD_SIZE = 8;
    private static final int TILE_SIZE = 100; // size of each square in pixels
    private final java.awt.Color lightSquareColor = new java.awt.Color(240, 217, 181);
    private final java.awt.Color darkSquareColor = new java.awt.Color(181, 136, 99);

    private final java.awt.Color selectedSquareColor = new java.awt.Color(164, 149, 195); // NU Purple 40
    private int selectedRow = -1;
    private int selectedCol = -1;

    private transient Map<PieceType, Image> whitePieceImages;
    private transient Map<PieceType, Image> blackPieceImages;
    private transient BoardController boardController;

    public BoardView(BoardController boardController) {
        if (boardController == null) {
            throw new IllegalArgumentException("BoardController cannot be null.");
        }
        setPreferredSize(new Dimension(BOARD_SIZE * TILE_SIZE, BOARD_SIZE * TILE_SIZE));
        assignController(boardController);
        boardController.setBoardView(this);
        addMouseListener(new BoardMouseListener());
    }

    private void assignController(BoardController controller) {
        this.boardController = controller;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (whitePieceImages == null || blackPieceImages == null) {
            loadPieceImages();
        }
        drawBoard(g);
        drawSelectedSquare(g);
        drawPieces(g); // draw piece after draw selected square to ensure piece image is on top
    }

    private void drawBoard(Graphics g) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                java.awt.Color squareColor = (row + col) % 2 == 0 ? lightSquareColor : darkSquareColor;
                g.setColor(squareColor);
                g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    private void loadPieceImages() {

        whitePieceImages = new HashMap<>();
        blackPieceImages = new HashMap<>();

        loadOnePieceImage(PieceType.PAWN, Color.WHITE, "images/white_pawn.png");
        loadOnePieceImage(PieceType.ROOK, Color.WHITE, "images/white_rook.png");
        loadOnePieceImage(PieceType.KNIGHT, Color.WHITE, "images/white_knight.png");
        loadOnePieceImage(PieceType.BISHOP, Color.WHITE, "images/white_bishop.png");
        loadOnePieceImage(PieceType.QUEEN, Color.WHITE, "images/white_queen.png");
        loadOnePieceImage(PieceType.KING, Color.WHITE, "images/white_king.png");

        loadOnePieceImage(PieceType.PAWN, Color.BLACK, "images/black_pawn.png");
        loadOnePieceImage(PieceType.ROOK, Color.BLACK, "images/black_rook.png");
        loadOnePieceImage(PieceType.KNIGHT, Color.BLACK, "images/black_knight.png");
        loadOnePieceImage(PieceType.BISHOP, Color.BLACK, "images/black_bishop.png");
        loadOnePieceImage(PieceType.QUEEN, Color.BLACK, "images/black_queen.png");
        loadOnePieceImage(PieceType.KING, Color.BLACK, "images/black_king.png");
    }

    private void loadOnePieceImage(PieceType type, Color color, String imagePath) {
        InputStream imageInputStream = getClass().getClassLoader().getResourceAsStream(imagePath);

        if (imageInputStream == null) {
            System.err.println(imagePath);
            return;
        }

        try {
            BufferedImage image = ImageIO.read(imageInputStream);
            if (color.equals(Color.BLACK)) {
                blackPieceImages.put(type, image);
            } else {
                this.whitePieceImages.put(type, image);
            }
        } catch (IOException e) {
            System.err.println(imagePath);
        }
    }

    private void drawPieces(Graphics g) {

        Piece[][] boardSnapshot = boardController.getBoardSnapshot();

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Piece piece = boardSnapshot[row][col];
                if (piece != null) {
                    Image pieceImage = piece.getColor() == Color.WHITE ?
                            whitePieceImages.get(piece.getType()) :
                            blackPieceImages.get(piece.getType());
                    g.drawImage(pieceImage, col * TILE_SIZE,
                            row * TILE_SIZE, TILE_SIZE, TILE_SIZE, this);
                }
            }
        }
    }

    private void drawSelectedSquare(Graphics g) {
        g.setColor(selectedSquareColor);
        g.fillRect(selectedCol * TILE_SIZE, selectedRow * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }


    private class BoardMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {

            selectedCol = e.getX() / TILE_SIZE;
            selectedRow = e.getY() / TILE_SIZE;

            // FIXME: perform input validation

            boolean selected = boardController.handleSquareClick(new Location(selectedRow, selectedCol));

            if (!selected) {
                selectedCol = -1;
                selectedRow = -1;
            }

            repaint();
        }
    }
}
