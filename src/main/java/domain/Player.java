package domain;

import constants.Color;

public class Player {

    private final Color playerColor;

    public Player(Color playerColor) {
        this.playerColor = playerColor;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

}
