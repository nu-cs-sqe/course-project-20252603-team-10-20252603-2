package ui;

import javax.swing.*;
import java.awt.*;

public class WelcomeView extends JFrame {

    private JTextField player1NameField;
    private JTextField player2NameField;

    public WelcomeView() {
        setTitle("Welcome Screen!");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createWelcomeScreenUI();
    }

    private void validatePlayerNames(JTextField player1NameField, JTextField player2NameField) {

    }

    private void createWelcomeScreenUI() {
        JPanel welcomePanel = new JPanel(new GridBagLayout());
        welcomePanel.setBackground(new Color(104, 76, 150)); // NU Purple 80

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Welcome message
        JLabel welcomeLabel = new JLabel("Welcome to our Chess Game!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28));
        welcomeLabel.setForeground(new Color(255, 255, 255));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        welcomePanel.add(welcomeLabel, gbc);

        // Player 1 Name Input
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel player1NameLabel = new JLabel("Player 1 Name:");
        player1NameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        player1NameLabel.setForeground(new Color(255, 255, 255));
        welcomePanel.add(player1NameLabel, gbc);
        player1NameField = new JTextField(20);
        player1NameField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        welcomePanel.add(player1NameField, gbc);

        // Player 2 Name Input
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel player2NameLabel = new JLabel("Player 2 Name:");
        player2NameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        player2NameLabel.setForeground(new Color(255, 255, 255));
        welcomePanel.add(player2NameLabel, gbc);
        player2NameField = new JTextField(20);
        player2NameField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.gridy = 2;
        welcomePanel.add(player2NameField, gbc);

        // Start Game Button
        JButton startGameButton = new JButton("Start Game");
        startGameButton.setFont(new Font("Arial", Font.BOLD, 20));
        startGameButton.setOpaque(true);
        startGameButton.setBackground(Color.WHITE);
        startGameButton.setForeground(Color.BLACK);

        startGameButton.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255),
                2, true));
        startGameButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Hand cursor on hover
        startGameButton.addActionListener(e -> {
            // Get player names
            String player1Name = player1NameField.getText().trim();
            String player2Name = player2NameField.getText().trim();

            // FIXME: perform input validation on player names

            // Hide the welcome screen
            setVisible(false);
            dispose(); // Dispose of this frame to free up resources

            // Create and show the main game screen
            MainView mainScreen = new MainView(player1Name, player2Name);
            mainScreen.setVisible(true);

            System.out.println(e);

        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Span two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        welcomePanel.add(startGameButton, gbc);

        add(welcomePanel);
    }
}
