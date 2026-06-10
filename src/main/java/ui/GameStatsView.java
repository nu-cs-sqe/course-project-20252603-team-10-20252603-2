package ui;

import domain.GameManager;
import domain.Player;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import javax.swing.*;
import java.awt.*;
import java.text.MessageFormat;

public class GameStatsView extends JPanel {

    private transient final GameManager gameManager;

    private JLabel player1Label;
    private JLabel player2Label;
    private JLabel currentPlayerLabel;

    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP2",
            justification = "GameStatsView intentionally stores the shared GameManager used to display current game state."
    )
    public GameStatsView(GameManager gameManager, String player1Name, String player2Name) {
        this.gameManager = gameManager;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(true);
        setBackground(new Color(104, 76, 150));

        JLabel playerInfoLabel = new JLabel(
                gameManager.getMessage("main.playerInformation")
        );
        playerInfoLabel.setFont(new Font("Arial", Font.BOLD, 30));
        playerInfoLabel.setForeground(new Color(255, 255, 255));

        String playerPattern = gameManager.getMessage("main.playerStatus");
        player1Label = new JLabel(
                MessageFormat.format(
                        playerPattern,
                        1,
                        player1Name,
                        gameManager.getMessage("team.white"),
                        0
                )
        );
        player1Label.setFont(new Font("Arial", Font.BOLD, 20));
        player1Label.setForeground(new Color(255, 255, 255));

        player2Label = new JLabel(
                MessageFormat.format(
                        playerPattern,
                        2,
                        player2Name,
                        gameManager.getMessage("team.black"),
                        0
                )
        );
        player2Label.setFont(new Font("Arial", Font.BOLD, 20));
        player2Label.setForeground(new Color(255, 255, 255));

        String currentPlayerPattern = gameManager.getMessage("main.currentPlayer");
        currentPlayerLabel = new JLabel(
                MessageFormat.format(
                        currentPlayerPattern,
                        player1Name
                )
        );
        currentPlayerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        currentPlayerLabel.setForeground(new Color(255, 255, 255));

        add(playerInfoLabel);
        add(player1Label);
        add(player2Label);
        add(currentPlayerLabel);
    }

    public void updateCurrentPlayer(String name) {
        String currentPlayerPattern = gameManager.getMessage("main.currentPlayer");
        currentPlayerLabel.setText(
                MessageFormat.format(
                        currentPlayerPattern,
                        name
                )
        );
    }

    public void updatePoints(Player player1, Player player2) {
        String pattern = gameManager.getMessage("main.playerStatus");
        player1Label.setText(
                MessageFormat.format(
                        pattern,
                        1,
                        player1.getPlayerName(),
                        gameManager.getMessage("team.white"),
                        player1.getPoints())
        );
        player2Label.setText(
                MessageFormat.format(
                        pattern,
                        2,
                        player2.getPlayerName(),
                        gameManager.getMessage("team.black"),
                        player2.getPoints())
        );

    }
}
