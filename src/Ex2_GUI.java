import java.io.*;
import java.util.Scanner;


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
     * StdDraw centers squares, so we add 0.5 to x and y to fill the grid cell (0,0) to (1,1)
     *
     * @param map
     */


    public static void drawMap(Map2D map) {
        if (map == null) return;

        int w = map.getWidth();
        int h = map.getHeight();

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                if (map.getPixel(x, y) == 0) {
                    StdDraw.setPenColor(StdDraw.WHITE);
                }
                if (map.getPixel(x, y) == -2) {
                    StdDraw.setPenColor(StdDraw.PINK);
                }
                if (map.getPixel(x, y) == 1) {
                    StdDraw.setPenColor(StdDraw.GREEN);
                }
                if (map.getPixel(x, y) == 2) {
                    StdDraw.setPenColor(StdDraw.RED);
                }
                if (map.getPixel(x, y) == 3) {
                    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                }
                if (map.getPixel(x, y) == 4) {
                    StdDraw.setPenColor(StdDraw.MAGENTA);
                }
                StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5);
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.square(x + 0.5, y + 0.5, 0.5);
            }
        }
        StdDraw.show();
    }

    /**
     * this saves a map to test file so we can load it back again
     *
     * @param mapFileName
     * @return
     */
    public static Map2D loadMap(String mapFileName) {
        Map2D ans = null;
        try {
            FileReader fr = new FileReader(mapFileName);
            Scanner sc = new Scanner(fr);
            sc.useDelimiter(",|\\s+");

            int w = sc.nextInt();
            int h = sc.nextInt();

            ans = new Map(w, h, 0);

            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    if (sc.hasNextInt()) {
                        ans.setPixel(x, y, sc.nextInt());
                    }
                }
            }
            sc.close();

        } catch (IOException e) {
            System.err.println("Error loading map: " + e.getMessage());
        }
        return ans;
    }


    /**
     * this saves OUR map to test file.
     *
     * @param map
     * @param mapFileName
     */
    public static void saveMap(Map2D map, String mapFileName) {
        try {
            FileWriter map2d = new FileWriter(mapFileName);
            map2d.write(map.getWidth() + "," + map.getHeight() + "\n");

            for (int i = 0; i < map.getHeight(); i++) {
                for (int j = 0; j < map.getWidth(); j++) {
                    map2d.write(map.getPixel(j, i) + " ");
                }

                map2d.write("\n");
            }

            map2d.close();
        } catch (IOException e) {
            System.err.println("Error saving map: " + e.getMessage());
        }
    }

    /**
     * this main implements all algorithms in order to see the visual map
     * using StdDraw class
     * @param args
     */

    public static void main(String[] args) {
        Map2D map = new Map(20, 20, 0);
        StdDraw.setCanvasSize(450, 450);
        StdDraw.setXscale(0, map.getWidth());
        StdDraw.setYscale(0, map.getHeight());
        StdDraw.enableDoubleBuffering();

        char w = 'p';
        Pixel2D p1 = null;
        Pixel2D s = null;
        Pixel2D e = null;
        boolean mouseWasDown = false;
        while (true) {
            StdDraw.clear();
            drawMap(map);
            if (StdDraw.hasNextKeyTyped()) {
                char n = StdDraw.nextKeyTyped();
                if (n == 'p'|| n == 'f' || n == 's' || n == 'e' || n == 'c' || n == 'r' || n == 'l') {
                    w=n;
                    p1=null;
                }
                if (n == '0' && s!=null && e != null) {
                    Pixel2D[] shortestPath = map.shortestPath(s, e, 3, false);
                    if (shortestPath != null) {
                        for (int i = 0; i < shortestPath.length; i++) {
                            map.setPixel(shortestPath[i], 4);
                        }
                    }
                }
                if (n == ' ') {
                    map.init(20, 20, 0);
                    p1 = null;
                    s = null;
                    e = null;
                }
                if (n == 'a') {
                    saveMap(map, "map.txt");
                }
                if (n == 'd') {
                    Map2D newmap = loadMap("map.txt");
                    if (newmap != null) {
                        map = newmap;
                    }
                }

            }
            boolean mouseIsDown = StdDraw.isMousePressed();
            if (mouseIsDown && !mouseWasDown) {
                Pixel2D p0 = new Index2D((int) StdDraw.mouseX(), (int) StdDraw.mouseY());
                if (map.isInside(p0)) {
                    if (w == 'p') {
                        map.setPixel(p0, -2);
                    }
                    if (w == 'f') {
                        map.fill(p0, -2, false);
                    }
                    if (w == 's') {
                        s = p0;
                        map.setPixel(s, 1);
                    }
                    if (w == 'e') {
                        e = p0;
                        map.setPixel(e, 2);
                    }
                    if (w == 'l') {
                        if (p1 == null) p1 = p0;
                        else {
                            map.drawLine(p1, p0, 3);
                            p1 = null;
                        }
                    }
                    if (w == 'c') {
                        if (p1 == null) p1 = p0;
                        else {
                            map.drawCircle(p1, p1.distance2D(p0), 3);
                            p1 = null;
                        }
                    }
                    if (w == 'r') {
                        if (p1 == null) p1 = p0;
                        else {
                            map.drawRect(p1, p0, 3);
                            p1 = null;
                        }
                    }
                }
            }
            mouseWasDown = mouseIsDown;
            StdDraw.show();

            StdDraw.pause(10);
        }
    }
    }
