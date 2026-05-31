package domain;

import java.util.Objects;

public final class Location {

    private static final int MIN_COORD = 0;

    private static final int MAX_COORD = 7;

    private final int x;

    private final int y;

    public Location(int x, int y) {
        boolean xOutOfBounds = (x < MIN_COORD) || (x > MAX_COORD);
        boolean yOutOfBounds = (y < MIN_COORD) || (y > MAX_COORD);

        if (xOutOfBounds || yOutOfBounds) {
            throw new IllegalArgumentException("Coordinates must be between 0 and 7");
        }

        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Location)) {
            return false;
        }

        Location compareObj = (Location) obj;
        return (this.x == compareObj.x) && (this.y == compareObj.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
