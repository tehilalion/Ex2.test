import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Intro2CS, 2026A, this is a very
 */
class MapTest {
    /**
     *
     */
    private int[][] _map_3_3 = {{0, 1, 0}, {1, 0, 1}, {0, 1, 0}};
    private Map2D _m0, _m1, _m3_3;

    @BeforeEach
    public void setup() {

        _m3_3 = new Map(_map_3_3);
        _m0 = new Map(_map_3_3);
        _m1 = new Map(20,25,16);
    }

    /**
     * Tests the init method with a large array.
     * Verifies that width, height are updated correctly
     * and that fill works after initialization.
     */
    @Test
    @Timeout(value = 1, unit = SECONDS)
    void init() {
        int[][] bigarr = new int[500][500];
        _m1.init(bigarr);
        assertEquals(bigarr.length, _m1.getHeight());
        assertEquals(bigarr[0].length, _m1.getWidth());
        Pixel2D p1 = new Index2D(3, 2);
        _m1.fill(p1, 1, true);
    }

    /**
     * Tests that initializing two maps with the same array
     * results in equal maps.
     */
    @Test
    void testInit() {
        _m0.init(_map_3_3);
        _m1.init(_map_3_3);
        assertEquals(_m0, _m1);
    }

    /**
     *  Tests the isInside method with points that are:
     *   inside the map, on the edge, and outside the map.
     */
     @Test
    void testIsInside() {
        // Assuming a 10x10 map
        Index2D inside = new Index2D(5, 5);
        Index2D edge = new Index2D(0, 0);
        Index2D outsideX = new Index2D(11, 5);
        Index2D outsideY = new Index2D(5, -1);
    }

    /**
     *  Tests that getMap returns a deep copy of the map
     *   and not a reference to the internal array.
     */
    @Test
    void getMap() {
        Map m = new Map(2, 2, 5);
        int[][] copy = m.getMap();
        assertEquals(5, copy[0][0]);
        assertEquals(2, copy.length);
        assertEquals(2, copy[0].length);
        copy[0][0] = 9;
        assertEquals(5, m.getPixel(0, 0));
    }

    /**
     * Tests that getWidth returns the correct width of the map.
     */
    @Test
    void getWidth() {
        Map m = new Map(4, 7, 0);
        assertEquals(4, m.getWidth());
    }

    /**
     * Tests that getHeight returns the correct Height of the map
     */
    @Test
    void getHeight() {
        Map m = new Map(4, 7, 0);
        assertEquals(7, m.getHeight());
    }

    /**
     *Tests getting a pixel value after setting it  using coordinates
     */
    @Test
    void getPixel() {
        Map m = new Map(3, 3, 0);
        m.setPixel(1, 1, 9);
        assertEquals(9, m.getPixel(1, 1));
    }

    /**
     *Tests setting a pixel value using x,y coordinates.
     */
    @Test
    void setPixel() {
        Map m = new Map(3, 3, 0);
        m.setPixel(1, 2, 7);
        assertEquals(7, m.getPixel(1, 2));
    }

    /**
     *Tests setting a pixel value using a Pixel2D object.
     */
    @Test
    void testSetPixel() {
        Map m = new Map(3, 3, 0);
        Pixel2D p = new Index2D(0, 1);
        m.setPixel(p, 4);
        assertEquals(4, m.getPixel(p));
    }

    /**
     *Tests the sameDimensions method.
     * Verifies true for maps with identical dimensions and false otherwise.
     */
    @Test
    void sameDimensions() {
        Map a = new Map(3, 3, 1);
        Map b = new Map(3, 3, 2);
        Map c = new Map(2, 3, 0);
        assertTrue(a.sameDimensions(b));
        assertFalse(a.sameDimensions(c));
    }

    /**
     *Tests addMap2D by adding another map's values
     *  to the current map.
     */
    @Test
    void addMap2D() {
        Map a = new Map(2, 2, 1);
        Map b = new Map(2, 2, 3);
        a.addMap2D(b);
        assertEquals(4, a.getPixel(0, 0));
        assertEquals(4, a.getPixel(1, 1));
    }

    /**
     *  Tests the mul method by multiplying all pixel values by scalar
     */
    @Test
    void mul() {
        Map m = new Map(2, 2, 4);
        m.mul(0.5);
        assertEquals(2, m.getPixel(0, 0));

    }

    /**
     *Tests the rescale method.
     * Verifies that the map size changes correctly
     * and pixel positions are scaled properly.
     */
    @Test
    void rescale() {
        Map m = new Map(2, 2, 1);
        m.setPixel(1, 1, 9);
        m.rescale(2, 2);
        assertEquals(4, m.getWidth());
        assertEquals(4, m.getHeight());
        assertEquals(9, m.getPixel(2, 2));
    }

    /**
     *Tests drawing a circle on the map.
     * Verifies that pixels inside the circle are colored and pixels outside remain unchanged.
     */
    @Test
    void drawCircle() {
        Map m = new Map(7, 7, 0);
        Pixel2D c = new Index2D(3, 3);
        m.drawCircle(c, 2.1, 5);
        assertEquals(5, m.getPixel(3, 3));
        assertEquals(5, m.getPixel(4, 3));
        assertEquals(0, m.getPixel(0, 0));
    }

    /**
     *Tests drawing a diagonal line from (0,0) to (4,4).
     * Verifies that all diagonal pixels are colored.
     */
    @Test
    void drawLine() {
        Map m = new Map(5, 5, 0);
        m.drawLine(new Index2D(0, 0), new Index2D(4, 4), 1);
        for (int i = 0; i < 5; i++) {
            assertEquals(1, m.getPixel(i, i));
        }
    }

    /**
     *Tests drawing a rectangle between two points.
     * Verifies that pixels inside the rectangle are filled.
     */
    @Test
    void drawRect() {
        Map m = new Map(5, 5, 0);
        m.drawRect(new Index2D(1, 1), new Index2D(3, 3), 8);
        assertEquals(8, m.getPixel(2, 2));
        assertEquals(8, m.getPixel(1, 1));
        assertEquals(8, m.getPixel(3, 3));
    }

    /**
     *Tests the fill (flood fill) method.
     * Verifies that connected pixels are filled correctly
     * without overriding blocked pixels.
     */
    @Test
    void fill() {
        Map m = new Map(4, 4, 1);
        m.setPixel(2, 2, 9);
        int filled = m.fill(new Index2D(0, 0), 3, false);
        assertEquals(15, filled);
        assertEquals(9, m.getPixel(2, 2));
    }

    /**
     *Tests the shortestPath method.
     *Verifies that a valid path is returned with correct start and end points.
     */
    @Test
    void shortestPath() {
        Map m = new Map(3, 3, 0);
        Pixel2D[] path = m.shortestPath(
                new Index2D(0, 0),
                new Index2D(2, 2),
                9,
                false
        );
        assertNotNull(path);
        assertEquals(5, path.length);
        assertEquals(new Index2D(0, 0), path[0]);
        assertEquals(new Index2D(2, 2), path[4]);
    }

    /**
     *Tests the allDistance method.
     *  Verifies that the distance map contains correct shortest distances from the source pixel.
     */
    @Test
    void allDistance() {
        Map m = new Map(3, 3, 0);
        Map2D dist = m.allDistance(new Index2D(0, 0), 9, false);
        assertEquals(0, dist.getPixel(0, 0));
        assertEquals(2, dist.getPixel(1, 1));
    }

}