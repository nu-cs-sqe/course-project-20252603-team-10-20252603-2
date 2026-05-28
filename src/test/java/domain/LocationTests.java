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
}
