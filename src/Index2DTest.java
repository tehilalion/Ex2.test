import org.junit.jupiter.api.Test;

public class Index2DTest {
    import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

    class Index2DTest {

        @Test
        void testConstructorAndGetters() {
            Index2D p = new Index2D(5, 10);
            assertEquals(5, p.getX(), "X coordinate should be 5");
            assertEquals(10, p.getY(), "Y coordinate should be 10");
        }

        @Test
        void testCopyConstructor() {
            Index2D original = new Index2D(3, 4);
            Index2D copy = new Index2D(original);

            assertEquals(original.getX(), copy.getX());
            assertEquals(original.getY(), copy.getY());
            assertNotSame(original, copy, "Copy should be a different object instance");

            assertThrows(IllegalArgumentException.class, () -> new Index2D(null),
                    "Should throw exception if null is passed to copy constructor");
        }

        @Test
        void testDistance2D() {
            Index2D p1 = new Index2D(0, 0);
            Index2D p2 = new Index2D(3, 4);

            // Distance should be sqrt(3^2 + 4^2) = 5
            double distance = p1.distance2D(p2);
            assertEquals(5.0, distance, 0.0001);

            // Test symmetry (p1 to p2 should be same as p2 to p1)
            assertEquals(p1.distance2D(p2), p2.distance2D(p1));
        }

        @Test
        void testDistanceToNullThrowsException() {
            Index2D p1 = new Index2D(1, 1);
            // Note: Your current code checks p2 == null AFTER accessing p2.getX().
            // This test will confirm if a RuntimeException is thrown.
            assertThrows(RuntimeException.class, () -> p1.distance2D(null));
        }

        @Test
        void testToString() {
            Index2D p = new Index2D(12, 7);
            assertEquals("12,7", p.toString());
        }

        @Test
        void testEquals() {
            Index2D p1 = new Index2D(10, 20);
            Index2D p2 = new Index2D(10, 20);
            Index2D p3 = new Index2D(5, 5);

            assertEquals(p1, p2, "Points with same coordinates should be equal");
            assertNotEquals(p1, p3, "Points with different coordinates should not be equal");
            assertNotEquals(p1, null, "Should not be equal to null");
            assertNotEquals(p1, "a string", "Should not be equal to different types");
        }
    }
}
