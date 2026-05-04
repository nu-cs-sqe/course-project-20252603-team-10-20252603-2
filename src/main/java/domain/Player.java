package domain;

import constants.Color;

public class Player {

    private final Color playerColor;
    private Number points = 0;

    public Player(Color playerColor) {
        this.playerColor = playerColor;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public Number getPoints() {
        return points;
    }

}
