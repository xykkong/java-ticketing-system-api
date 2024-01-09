package upwork.booking.utils;

import io.quarkus.test.junit.QuarkusTest;
import org.graalvm.collections.Pair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class AllNeighborStrategyTest {

    @Test
    public void testListNeighbor() {
        // Mock the expected list of neighbors
        var expectedNeighbors = new ArrayList<Pair<Integer, Integer>>();
        expectedNeighbors.add(Pair.create(3, 3));
        expectedNeighbors.add(Pair.create(3, 4));
        expectedNeighbors.add(Pair.create(3, 5));
        expectedNeighbors.add(Pair.create(4, 3));
        expectedNeighbors.add(Pair.create(4, 5));
        expectedNeighbors.add(Pair.create(5, 3));
        expectedNeighbors.add(Pair.create(5, 4));
        expectedNeighbors.add(Pair.create(5, 5));

        var actualNeighbors = new AllNeighborStrategy().listNeighbor(4, 4);

        // Assertions
        assertEquals(expectedNeighbors.size(), actualNeighbors.size());
        assertTrue(expectedNeighbors.containsAll(actualNeighbors));
    }
}

