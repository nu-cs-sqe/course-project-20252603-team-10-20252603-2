package ui;

import domain.GameManager;
import domain.Player;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.text.MessageFormat;

public class GameStatsView extends JPanel {

    private static final Color BACKGROUND_COLOR = new Color(104, 76, 150);
    private static final Color TEXT_COLOR = new Color(255, 255, 255);
    private static final int TITLE_FONT_SIZE = 30;
    private static final int LABEL_FONT_SIZE = 20;
    private static final int STARTING_POINTS = 0;
    private static final int PLAYER_ONE = 1;
    private static final int PLAYER_TWO = 2;

    private transient final GameManager gameManager;

    private final JLabel player1Label;
    private final JLabel player2Label;
    private final JLabel currentPlayerLabel;

    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP2",
            justification = "GameStatsView intentionally stores the shared GameManager used to display current game state."
    )
    public GameStatsView(GameManager gameManager, String player1Name, String player2Name) {
        this.gameManager = gameManager;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(true);
        setBackground(BACKGROUND_COLOR);

        JLabel playerInfoLabel = createTitleLabel(gameManager.getMessage("main.playerInformation"));
        player1Label = createPlayerStatusLabel(PLAYER_ONE, player1Name, "team.white", STARTING_POINTS);
        player2Label = createPlayerStatusLabel(PLAYER_TWO, player2Name, "team.black", STARTING_POINTS);
        currentPlayerLabel = createTitleLabel(currentPlayerText(player1Name));

        add(playerInfoLabel);
        add(player1Label);
        add(player2Label);
        add(currentPlayerLabel);
    }

    private JLabel createTitleLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, TITLE_FONT_SIZE));
        label.setForeground(TEXT_COLOR);
        return label;
    }

    private JLabel createPlayerStatusLabel(int playerNumber, String playerName, String teamKey, int points) {
        JLabel label = new JLabel(playerStatusText(playerNumber, playerName, teamKey, points));
        label.setFont(new Font("Arial", Font.BOLD, LABEL_FONT_SIZE));
        label.setForeground(TEXT_COLOR);
        return label;
    }

    private String playerStatusText(int playerNumber, String playerName, String teamKey, int points) {
        String pattern = gameManager.getMessage("main.playerStatus");
        return MessageFormat.format(pattern, playerNumber, playerName, gameManager.getMessage(teamKey), points);
    }

    private String currentPlayerText(String name) {
        String pattern = gameManager.getMessage("main.currentPlayer");
        return MessageFormat.format(pattern, name);
    }

    public void updateCurrentPlayer(String name) {
        currentPlayerLabel.setText(currentPlayerText(name));
    }

    public void updatePoints(Player player1, Player player2) {
        player1Label.setText(playerStatusText(PLAYER_ONE, player1.getPlayerName(), "team.white", player1.getPoints()));
        player2Label.setText(playerStatusText(PLAYER_TWO, player2.getPlayerName(), "team.black", player2.getPoints()));
    }
}
