package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationTests {

    @Test
    void hashCode_sameCoordinates_returnsSameHashCode() {
        Location first = new Location(1, 2);
        Location second = new Location(1, 2);

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void locationConstructor_x0y0_returnsValidLocationObj() {
        Location loc = new Location(0, 0);

        assertEquals(0, loc.getX());
        assertEquals(0, loc.getY());
    }

    @Test
    void locationConstructor_x7y7_returnsValidLocationObj() {
        Location loc = new Location(7, 7);

        assertEquals(7, loc.getX());
        assertEquals(7, loc.getY());
    }


}
