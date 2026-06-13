package ui;

import domain.GameManager;
import domain.LanguageOption;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import javax.swing.*;
import java.awt.*;

public class LanguageView extends JFrame {

    private final transient GameManager gameManager;

    @SuppressFBWarnings(
            value = "EI_EXPOSE_REP2",
            justification = "LanguageView intentionally stores the " +
                    "shared GameManager so the selected locale can be applied."
    )
    public LanguageView(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void initialize() {
        final int width = 400;
        final int height = 250;
        setTitle("Select Language");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        final int r = 104;
        final int g = 76;
        final int b = 150;
        panel.setBackground(new Color(r, g, b));

        GridBagConstraints gbc = new GridBagConstraints();
        final int insets = 10;
        gbc.insets = new Insets(insets, insets, insets, insets);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel label = new JLabel("Select a Language", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        final int size = 24;
        label.setFont(new Font("Arial", Font.BOLD, size));

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
