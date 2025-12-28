import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
     * @param map
     */


     public static void drawMap(Map2D map) {
        if (map == null) return;

        int w = map.getWidth();
        int h = map.getHeight();

         StdDraw.setCanvasSize(30, 30);
         StdDraw.setXscale(0, w);
         StdDraw.setYscale(0, h);
         StdDraw.enableDoubleBuffering();

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
                StdDraw.filledSquare(x + 0.5, y + 0.5, 0.5);
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.square(x + 0.5, y + 0.5, 0.5);
            }
        }
        StdDraw.show();
    }

    /** this saves our map to test file
     * @param mapFileName
     * @return
     */
    public static Map2D loadMap(String mapFileName) {
            try {
                FileReader Map2D = new FileReader(mapFileName);
                Scanner sc = new Scanner(Map2D);
                sc.useDelimiter(",|\\s+");

                int w = sc.nextInt();
                int h = sc.nextInt();

                Map2D ans = new Map(w, h,0);

                for (int y = 0; y < h; y++) {
                    for (int x = 0; x < w; x++) {
                        if (sc.hasNextInt()) {
                            ans.setPixel(x, y, sc.nextInt());
                        }
                    }
                }
                sc.close();
                return ans;

            } catch (FileNotFoundException) {
                System.err.println("File not found: " + mapFileName);
                return null;
            }
        }


    /**
     *
     * @param map
     * @param mapFileName
     */
    public static void saveMap(Map2D map, String mapFileName) {

        if (map == null) {
            System.out.println("Error: map is null! Nothing to save.");
            return;
        }

        int w = map.getWidth();
        int h = map.getHeight();

        try (PrintWriter writer = new PrintWriter(mapFileName)) {
            // Save width and height first
            writer.println(w + " " + h);

            // Save each pixel
            for (int y = 0; y < h; y++) {          // row by row
                for (int x = 0; x < w; x++) {
                    Integer pixel = map.getPixel(x, y);
                    int v = (pixel != null) ? pixel : 0; // default black if null
                    writer.print(v);
                    if (x < w - 1) writer.print(" ");  // space between pixels
                }
                writer.println(); // new line for next row
            }

            System.out.println("Map saved to " + mapFileName);

        } catch (IOException e) {
            throw new IOException("Error saving map")
        }

    }



    public static void main(String[] a) {



        String mapFile = "map.txt";
        Map2D map = loadMap(mapFile);
        drawMap(map);
    }

}