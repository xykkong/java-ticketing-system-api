package upwork.booking.utils;

import io.quarkus.test.junit.QuarkusTest;
import org.graalvm.collections.Pair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class ClosestNeighborStrategyTest {
    /**
     * ----------
     * --X-------
     * ----------
     * ----------
     * ----------
     * ----------
     * ----------
     * ----------
     * ----------
     * ----------
     */
    @Test
    public void testListNeighbor1() {
        // Mock the expected list of neighbors
        var expectedNeighbors = new ArrayList<Pair<Integer, Integer>>();
        expectedNeighbors.add(Pair.create(4, 3));

        var actualNeighbors = new ClosestNeighborStrategy().listNeighbor(3, 2);

        // Assertions
        assertEquals(expectedNeighbors.size(), actualNeighbors.size());
        assertTrue(expectedNeighbors.containsAll(actualNeighbors));
    }

    /**
     * ----------
     * ----X-----
     * ----------
     * ----------
     * ----------
     * ----------
     * ----------
     * ----------
     * ----------
     * ----------
     */
    @Test
    public void testListNeighbor2() {
        // Mock the expected list of neighbors
        var expectedNeighbors = new ArrayList<Pair<Integer, Integer>>();
        expectedNeighbors.add(Pair.create(5, 3));

        var actualNeighbors = new ClosestNeighborStrategy().listNeighbor(5, 2);

        // Assertions
        assertEquals(expectedNeighbors.size(), actualNeighbors.size());
        assertTrue(expectedNeighbors.containsAll(actualNeighbors));
    }

    /**
     * ----------
     * -------X--
     * ----------
     * ----------
     * ----------
     * ----------
     * ----------
     * ----------
     * ----------
     * ----------
     */
    @Test
    public void testListNeighbor3() {
        // Mock the expected list of neighbors
        var expectedNeighbors = new ArrayList<Pair<Integer, Integer>>();
        expectedNeighbors.add(Pair.create(7, 3));

        var actualNeighbors = new ClosestNeighborStrategy().listNeighbor(8, 2);

        // Assertions
        assertEquals(expectedNeighbors.size(), actualNeighbors.size());
        assertTrue(expectedNeighbors.containsAll(actualNeighbors));
    }

    /**
     * ----------
     * ----------
     * ----------
     * ----------
     * -------X--
     * ----------
     * ----------
     * ----------
     * ----------
     * ----------
     */
    @Test
    public void testListNeighbor4() {
        // Mock the expected list of neighbors
        var expectedNeighbors = new ArrayList<Pair<Integer, Integer>>();
        expectedNeighbors.add(Pair.create(7, 5));

        var actualNeighbors = new ClosestNeighborStrategy().listNeighbor(8, 5);

        // Assertions
        assertEquals(expectedNeighbors.size(), actualNeighbors.size());
        assertTrue(expectedNeighbors.containsAll(actualNeighbors));
    }

    /**
     * ----------
     * ----------
     * ----------
     * ----------
     * ----------
     * ----------
     * ----------
     * -------X--
     * ----------
     * ----------
     */
    @Test
    public void testListNeighbor5() {
        // Mock the expected list of neighbors
        var expectedNeighbors = new ArrayList<Pair<Integer, Integer>>();
        expectedNeighbors.add(Pair.create(8, 7));

        var actualNeighbors = new ClosestNeighborStrategy().listNeighbor(9, 8);

        // Assertions
        assertEquals(expectedNeighbors.size(), actualNeighbors.size());
        assertTrue(expectedNeighbors.containsAll(actualNeighbors));
    }

    /**
     * ----------
     * ----------
     * ----------
     * ----------
     * ----------
     * ----------
     * ----------
     * ----------
     * ----X-----
     * ----------
     */
    @Test
    public void testListNeighbor6() {
        // Mock the expected list of neighbors
        var expectedNeighbors = new ArrayList<Pair<Integer, Integer>>();
        expectedNeighbors.add(Pair.create(5, 8));

        var actualNeighbors = new ClosestNeighborStrategy().listNeighbor(5, 9);

        // Assertions
        assertEquals(expectedNeighbors.size(), actualNeighbors.size());
        assertTrue(expectedNeighbors.containsAll(actualNeighbors));
    }

    /**
     * ----------
     * ----------
     * ----------
     * ----------
     * ----------
     * ----------
     * ----------
     * X---------
     * ----------
     * ----------
     */
    @Test
    public void testListNeighbor7() {
        // Mock the expected list of neighbors
        var expectedNeighbors = new ArrayList<Pair<Integer, Integer>>();
        expectedNeighbors.add(Pair.create(2, 7));

        var actualNeighbors = new ClosestNeighborStrategy().listNeighbor(1, 8);

        // Assertions
        assertEquals(expectedNeighbors.size(), actualNeighbors.size());
        assertTrue(expectedNeighbors.containsAll(actualNeighbors));
    }

    /**
     * ----------
     * ----------
     * ----------
     * ----------
     * -X--------
     * ----------
     * ----------
     * ----------
     * ----------
     * ----------
     */
    @Test
    public void testListNeighbor8() {
        // Mock the expected list of neighbors
        var expectedNeighbors = new ArrayList<Pair<Integer, Integer>>();
        expectedNeighbors.add(Pair.create(3, 5));

        var actualNeighbors = new ClosestNeighborStrategy().listNeighbor(2, 5);

        // Assertions
        assertEquals(expectedNeighbors.size(), actualNeighbors.size());
        assertTrue(expectedNeighbors.containsAll(actualNeighbors));
    }

    /**
     * ----------
     * ----------
     * ----------
     * ----------
     * ----X-----
     * ----------
     * ----------
     * ----------
     * ----------
     * ----------
     */
    @Test
    public void testListNeighbor9() {
        var actualNeighbors = new ClosestNeighborStrategy().listNeighbor(5, 5);

        // Assertions
        assertEquals(0, actualNeighbors.size());
    }
}

