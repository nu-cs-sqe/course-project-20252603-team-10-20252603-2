package ui;

import domain.GameManager;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameManager gameManager = new GameManager();

            LanguageView languageView = new LanguageView(gameManager);
            languageView.initialize();
            languageView.setVisible(true);

        });
    }
}
