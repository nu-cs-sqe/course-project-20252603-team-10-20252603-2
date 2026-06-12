package ui;

import domain.GameManager;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import javax.swing.*;
import java.awt.*;

public final class MainView extends JFrame {
    private transient BoardView boardView;
    private transient BoardController boardController;
    private transient final GameManager gameManager;

    private transient GameStatsView gameStatsView;
    private String player1Name;
    private String player2Name;

    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP2",
            justification = "MainView intentionally stores the shared GameManager used to coordinate board and game state."
    )
    public MainView(GameManager gameManager, String player1Name, String player2Name) {
        this.gameManager = gameManager;
        this.player1Name = player1Name;
        this.player2Name = player2Name;

        configureMainView();
        addGameStatsView();
        addBoardView();

        setVisible(true);
        validate();
    }

    private void configureMainView() {
        setTitle(gameManager.getMessage("main.title"));
        setTitle("Team 10 Chess Game GUI Example :)!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 1200);
        setLocationRelativeTo(null); // Center the window on the screen
    }

    private void addGameStatsView() {
        gameStatsView = new GameStatsView(gameManager, player1Name, player2Name);
        add(gameStatsView, BorderLayout.PAGE_START);
    }
    private void addBoardView() {
        boardController = new BoardController(gameManager);
        boardController.setGameStatsView(gameStatsView);

        boardView = new BoardView(boardController);
        add(boardView, BorderLayout.CENTER);
    }
}
