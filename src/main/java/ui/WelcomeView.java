package ui;

import domain.GameManager;
import domain.Player;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class WelcomeView extends JFrame {

    private static final Color BACKGROUND_COLOR = new Color(104, 76, 150);
    private static final Color TEXT_COLOR = new Color(255, 255, 255);
    private static final int WINDOW_SIZE = 600;
    private static final int INSET_SIZE = 10;
    private static final int TITLE_FONT_SIZE = 28;
    private static final int LABEL_FONT_SIZE = 16;
    private static final int BUTTON_FONT_SIZE = 20;
    private static final int BUTTON_BORDER_THICKNESS = 2;
    private static final int FIELD_COLUMNS = 20;
    private static final int TITLE_GRID_WIDTH = 2;

    private JTextField player1NameField;
    private JTextField player2NameField;
    private final transient GameManager gameManager;

    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP2",
            justification = "This UI class intentionally stores the shared GameManager used to coordinate game state."
    )
    public WelcomeView(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void initialize() {
        setTitle(gameManager.getMessage("welcome.title"));
        setSize(WINDOW_SIZE, WINDOW_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createWelcomeScreenUI();
    }

    private void createWelcomeScreenUI() {
        JPanel welcomePanel = new JPanel(new GridBagLayout());
        welcomePanel.setBackground(BACKGROUND_COLOR);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSET_SIZE, INSET_SIZE, INSET_SIZE, INSET_SIZE);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addWelcomeLabel(welcomePanel, gbc);
        addPlayerNameInputs(welcomePanel, gbc);
        addStartGameButton(welcomePanel, gbc);

        add(welcomePanel);
    }

    private void addWelcomeLabel(JPanel panel, GridBagConstraints gbc) {
        JLabel welcomeLabel = new JLabel(gameManager.getMessage("welcome.message"), SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, TITLE_FONT_SIZE));
        welcomeLabel.setForeground(TEXT_COLOR);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = TITLE_GRID_WIDTH;
        panel.add(welcomeLabel, gbc);
        gbc.gridwidth = 1;
    }

    private void addPlayerNameInputs(JPanel panel, GridBagConstraints gbc) {
        player1NameField = addNameRow(panel, gbc, 1, "welcome.player1Name");
        player2NameField = addNameRow(panel, gbc, 2, "welcome.player2Name");
    }

    private JTextField addNameRow(JPanel panel, GridBagConstraints gbc, int row, String labelKey) {
        JLabel nameLabel = new JLabel(gameManager.getMessage(labelKey));
        nameLabel.setFont(new Font("Arial", Font.PLAIN, LABEL_FONT_SIZE));
        nameLabel.setForeground(TEXT_COLOR);
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(nameLabel, gbc);

        JTextField nameField = new JTextField(FIELD_COLUMNS);
        nameField.setFont(new Font("Arial", Font.PLAIN, LABEL_FONT_SIZE));
        gbc.gridx = 1;
        gbc.gridy = row;
        panel.add(nameField, gbc);

        return nameField;
    }

    private void addStartGameButton(JPanel panel, GridBagConstraints gbc) {
        JButton startGameButton = new JButton(gameManager.getMessage("welcome.startGame"));
        startGameButton.setFont(new Font("Arial", Font.BOLD, BUTTON_FONT_SIZE));
        startGameButton.setOpaque(true);
        startGameButton.setBackground(Color.WHITE);
        startGameButton.setForeground(Color.BLACK);
        startGameButton.setBorder(BorderFactory.createLineBorder(TEXT_COLOR, BUTTON_BORDER_THICKNESS, true));
        startGameButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        startGameButton.addActionListener(this::onStartGame);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = TITLE_GRID_WIDTH;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(startGameButton, gbc);
        gbc.gridwidth = 1;
    }

    private void onStartGame(java.awt.event.ActionEvent event) {
        String player1Name = player1NameField.getText().trim();
        String player2Name = player2NameField.getText().trim();

        // FIXME: perform input validation on player names

        gameManager.addPlayer(new Player(player1Name, constants.Color.WHITE));
        gameManager.addPlayer(new Player(player2Name, constants.Color.BLACK));
        gameManager.start();

        setVisible(false);
        dispose();

        MainView mainScreen = new MainView(gameManager, player1Name, player2Name);
        mainScreen.setVisible(true);
    }
}