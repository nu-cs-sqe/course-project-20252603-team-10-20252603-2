package domain;

import java.util.Objects;

public class Location {
    private final int x;

    private final int y;

    public Location(int x, int y) {
        boolean xOutOfBounds = (x < 0) || (x > 7);
        boolean yOutOfBounds = (y < 0) || (y > 7);

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

        Location compare_obj = (Location) obj;
        return (this.x == compare_obj.x) && (this.y == compare_obj.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
