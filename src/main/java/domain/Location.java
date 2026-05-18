package domain;

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

        Location compare_obj = (Location) obj;
        return (this.x == compare_obj.x) && (this.y == compare_obj.y);
    }

}