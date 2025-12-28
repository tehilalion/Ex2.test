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

    @Test
    void testInit() {
        _m0.init(_map_3_3);
        _m1.init(_map_3_3);
        assertEquals(_m0, _m1);
    }

    @Test
    void testIsInside() {
        // Assuming a 10x10 map
        Index2D inside = new Index2D(5, 5);
        Index2D edge = new Index2D(0, 0);
        Index2D outsideX = new Index2D(11, 5);
        Index2D outsideY = new Index2D(5, -1);
    }

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

    @Test
    void getWidth() {
        Map m = new Map(4, 7, 0);
        assertEquals(4, m.getWidth());
    }

    @Test
    void getHeight() {
        Map m = new Map(4, 7, 0);
        assertEquals(7, m.getHeight());
    }

    @Test
    void getPixel() {
        Map m = new Map(3, 3, 0);
        m.setPixel(1, 1, 9);
        assertEquals(9, m.getPixel(1, 1));
    }


    @Test
    void setPixel() {
        Map m = new Map(3, 3, 0);
        m.setPixel(1, 2, 7);
        assertEquals(7, m.getPixel(1, 2));
    }

    @Test
    void testSetPixel() {
        Map m = new Map(3, 3, 0);
        Pixel2D p = new Index2D(0, 1);
        m.setPixel(p, 4);
        assertEquals(4, m.getPixel(p));
    }


    @Test
    void sameDimensions() {
        Map a = new Map(3, 3, 1);
        Map b = new Map(3, 3, 2);
        Map c = new Map(2, 3, 0);
        assertTrue(a.sameDimensions(b));
        assertFalse(a.sameDimensions(c));
    }

    @Test
    void addMap2D() {
        Map a = new Map(2, 2, 1);
        Map b = new Map(2, 2, 3);
        a.addMap2D(b);
        assertEquals(4, a.getPixel(0, 0));
        assertEquals(4, a.getPixel(1, 1));
    }

    @Test
    void mul() {
        Map m = new Map(2, 2, 4);
        m.mul(0.5);
        assertEquals(2, m.getPixel(0, 0));

    }

    @Test
    void rescale() {
        Map m = new Map(2, 2, 1);
        m.setPixel(1, 1, 9);
        m.rescale(2, 2);
        assertEquals(4, m.getWidth());
        assertEquals(4, m.getHeight());
        assertEquals(9, m.getPixel(2, 2));
    }

    @Test
    void drawCircle() {
        Map m = new Map(7, 7, 0);
        Pixel2D c = new Index2D(3, 3);
        m.drawCircle(c, 2.1, 5);
        assertEquals(5, m.getPixel(3, 3));
        assertEquals(5, m.getPixel(4, 3));
        assertEquals(0, m.getPixel(0, 0));
    }

    @Test
    void drawLine() {
        Map m = new Map(5, 5, 0);
        m.drawLine(new Index2D(0, 0), new Index2D(4, 4), 1);
        for (int i = 0; i < 5; i++) {
            assertEquals(1, m.getPixel(i, i));
        }
    }

    @Test
    void drawRect() {
        Map m = new Map(5, 5, 0);
        m.drawRect(new Index2D(1, 1), new Index2D(3, 3), 8);
        assertEquals(8, m.getPixel(2, 2));
        assertEquals(8, m.getPixel(1, 1));
        assertEquals(8, m.getPixel(3, 3));
    }

    @Test
    void fill() {
        Map m = new Map(4, 4, 1);
        m.setPixel(2, 2, 9);
        int filled = m.fill(new Index2D(0, 0), 3, false);
        assertEquals(15, filled);
        assertEquals(9, m.getPixel(2, 2));
    }

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

    @Test
    void allDistance() {
        Map m = new Map(3, 3, 0);
        Map2D dist = m.allDistance(new Index2D(0, 0), 9, false);
        assertEquals(0, dist.getPixel(0, 0));
        assertEquals(2, dist.getPixel(1, 1));
    }

}