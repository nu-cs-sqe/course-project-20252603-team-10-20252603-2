package ui;

import javax.swing.*;
import java.awt.*;

public class GameStatsView extends JPanel {

    private JLabel currentPlayerLabel;

    public GameStatsView(String player1Name, String player2Name) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(true);
        setBackground(new Color(104, 76, 150));

        JLabel playerInfoLabel = new JLabel("Player Information");
        playerInfoLabel.setFont(new Font("Arial", Font.BOLD, 30));
        playerInfoLabel.setForeground(new Color(255, 255, 255));

        JLabel player1Label = new JLabel("\t\t\tPlayer 1: " + player1Name +
                " (Team: " + "White" + ")");
        player1Label.setFont(new Font("Arial", Font.BOLD, 20));
        player1Label.setForeground(new Color(255, 255, 255));

        JLabel player2Label = new JLabel("\t\t\tPlayer 2: " + player2Name +
                " (Team: " + "Black" + ")");
        player2Label.setFont(new Font("Arial", Font.BOLD, 20));
        player2Label.setForeground(new Color(255, 255, 255));

        //FIXME: hardcoded for now, but should be updated dynamically
        currentPlayerLabel = new JLabel("Current Player: " + player1Name);
        currentPlayerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        currentPlayerLabel.setForeground(new Color(255, 255, 255));

        add(playerInfoLabel);
        add(player1Label);
        add(player2Label);
        add(currentPlayerLabel);
    }
}
