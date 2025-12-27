import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import static org.junit.jupiter.api.Assertions.*;

public class Index2DTest {

    /**
     * this test checks the getx function by giving it a point and seeing if it gives back the x.
     */
    @Test
    void getX() {
        Index2D p=new Index2D(3,7);
        assertEquals(3,p.getX());
    }

    /**
     * this test checks the getY function by giving it a point and seeing if it gives back the y.
     */
    @Test
    void getY() {
        Index2D p=new Index2D(3,7);
        assertEquals(7,p.getY());
    }

    /**
     * this tests checks the distance function by taking 2 points and checking the distance between them.
     * */
    @Test
    void distance2D() {
        Index2D p1 = new Index2D(0, 0);
        Index2D p2 = new Index2D(3, 4);
        double distance = p1.distance2D(p2);
        assertEquals(distance, 5, 0.00001);
    }

    /**
     * this test checks the toString function by giving it a point and seeing if it writes it in string form
     */
    @Test
    void testToString() {
Index2D p=new Index2D(3,7);
assertEquals("3,7" ,p.toString());
    }

    /**
     * tests if the functions give back equal
     */
    @Test
    void testEquals() {
        Index2D p = new Index2D(3, 4);
        assertTrue(p.equals(p));
    }

    /**
     * tests if the functions give back null
     */
    @Test
    void testEqualsnull() {
    Index2D p = new Index2D(3, 7);
    assertFalse(p.equals(null));

    }


    }

