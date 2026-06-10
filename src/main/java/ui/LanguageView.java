package ui;

import domain.GameManager;
import domain.LanguageOption;

import javax.swing.*;
import java.awt.*;

public class LanguageView extends JFrame {

    private final GameManager gameManager;

    public LanguageView(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void initialize() {
        setTitle("Select Language");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(104, 76, 150));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel label = new JLabel("Select a Language", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        JComboBox<LanguageOption> languageBox =
                new JComboBox<>(gameManager.getSupportedLanguages().toArray(new LanguageOption[0]));

        JButton continueButton = new JButton("Continue");

        continueButton.addActionListener(e -> {
            LanguageOption selected =
                    (LanguageOption) languageBox.getSelectedItem();

            if (selected != null) {
                gameManager.setLocale(selected.getLocale());
            }

            dispose();

            WelcomeView welcomeView = new WelcomeView(gameManager);
            welcomeView.initialize();
            welcomeView.setVisible(true);
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label, gbc);

        gbc.gridy = 1;
        panel.add(languageBox, gbc);

        gbc.gridy = 2;
        panel.add(continueButton, gbc);

        add(panel);
    }
}