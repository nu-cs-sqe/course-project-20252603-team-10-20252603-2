package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void locationConstructor_x0y7_returnsValidLocationObj() {
        Location loc = new Location(0, 7);

        assertEquals(0, loc.getX());
        assertEquals(7, loc.getY());
    }

    @Test
    void locationConstructor_x7y0_returnsValidLocationObj() {
        Location loc = new Location(7, 0);

        assertEquals(7, loc.getX());
        assertEquals(0, loc.getY());
    }

    @Test
    void locationConstructor_xneg1y0_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Location(-1, 0));

    }

    @Test
    void locationConstructor_x8y0_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Location(8, 0));

    }

    @Test
    void locationConstructor_x0yneg1_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Location(0, -1));

    }

    @Test
    void locationConstructor_x0y8_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Location(0, 8));

    }

    @Test
    void equals_sameLoc_returnTrue() {
        Location first = new Location(1, 1);

        assertTrue(first.equals(first));
    }

    @Test
    void equals_diffLocSameCoord_returnTrue() {
        Location first = new Location(3, 4);
        Location second = new Location(3, 4);

        assertTrue(first.equals(second));
    }

    @Test
    void equals_diffLocDiffX_returnTrue() {
        Location first = new Location(3, 4);
        Location second = new Location(4, 4);

        assertFalse(first.equals(second));
    }


}
