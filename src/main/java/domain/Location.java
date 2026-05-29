package domain;

import java.util.Objects;

public class Location {
    private final int x;

    private final int y;

    public Location(int x, int y) {
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
