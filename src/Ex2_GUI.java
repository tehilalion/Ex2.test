/**
 * Intro2CS_2026A
 * This class represents a Graphical User Interface (GUI) for Map2D.
 * The class has save and load functions, and a GUI draw function.
 * You should implement this class, it is recommender to use the StdDraw class, as in:
 * https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html
 *
 *
 */


public class Ex2_GUI {
    /**
     * this method translates the data into a picture.
     * we do this by first finding the width and height of the map
     * then we set up our picture and the coordinates
     * we use our get pixel function in order to fill our map with the pixel values.
     * we set our color
     * then we draw our pixels
     * @param map
     */
    public static void drawMap(Map2D map) {
        if (map == null) return;
        int w = map.getWidth();
        int h = map.getHeight();
        StdDraw.setCanvasSize(600, 600);
        StdDraw.setXscale(0, w);
        StdDraw.setYscale(0, h);
        StdDraw.enableDoubleBuffering();
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                int v= map.getPixel(x, y);
                StdDraw.setPenColor(new java.awt.Color(v));
                StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5);
            }
        }
        StdDraw.show();
    }

    /**
     * @param mapFileName
     * @return
     */
    public static Map2D loadMap(String mapFileName) {
        Map2D ans = null;

        return ans;
    }

    /**
     *
     * @param map
     * @param mapFileName
     */
    public static void saveMap(Map2D map, String mapFileName) {


    }
    public static void main(String[] a) {
        String mapFile = "map.txt";
        Map2D map = loadMap(mapFile);
        drawMap(map);
    }
    /// ///////////// Private functions ///////////////
}