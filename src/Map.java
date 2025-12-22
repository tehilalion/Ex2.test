import java.io.Serializable;
/**
 * This class represents a 2D map (int[w][h]) as a "screen" or a raster matrix or maze over integers.
 * This is the main class needed to be implemented.
 *
 * @author boaz.benmoshe
 *
 */
public class Map implements Map2D, Serializable{
    private int[][] map;
    private int w;  // width
    private int h;  // height

    // edit this class below
	/**
	 * Constructs a w*h 2D raster map with an init value v.
	 * @param w
	 * @param h
	 * @param v
	 */
	public Map(int w, int h, int v) {init(w, h, v);}

    /**
	 * Constructs a square map (size*size).
	 * @param size
     */

	public Map(int size) {this(size,size, 0);}
	
	/**
	 * Constructs a map from a given 2D array.
	 * @param data
	 */

	public Map(int[][] data) {
		init(data);
	}
	@Override
	public void init(int w, int h, int v) {
        if (w<=0  || h<=0)
            throw new RuntimeException("Invalid width or height");
        this.w=w
        this.h=h
        map= new int [h][w];
        for (int i=0; i<h; i=i+1) {
            for (int j=0; j<w; j=j+1) {
                map[i][j]=v;
            }
        }
	}
	@Override
	public void init(int[][] arr) {
        if   (arr==null || arr.length==0)
            throw new RuntimeException("Invalid input array");
        int c= arr[0].length;
        if (c==0) {
            throw new RuntimeException("Invalid Row Input");
        }
        for (int i=1; i<arr.length; i=i+1) {
            if arr[i].length != c {
                throw new RuntimeException("Ragged array");
            }
        }
	}
	@Override
	public int[][] getMap() {
        if (map==null) return null;
        int h= map.length;
        int w= map[0].length;
        int [][]copy= new int [h][w];
        for (int i=0; i<h; i=i+1) {
            for (int j=0; j<w; j=j+1) {
                copy [i][j]= map [i][j];
            }
        }
		return copy;
	}

	@Override
	public int getWidth() {
       return this.w;
    }
	@Override
	public int getHeight() {
        return this.h;
    }
	@Override
	public int getPixel(int x, int y) {
        int ans = -1;

        return ans;
    }
	@Override
	public int getPixel(Pixel2D p) {
        int ans = -1;

        return ans;
	}
	@Override
	public void setPixel(int x, int y, int v) {

    }
	@Override
	public void setPixel(Pixel2D p, int v) {

	}

    @Override
    public boolean isInside(Pixel2D p) {
        boolean ans = true;

        return ans;
    }

    @Override
    public boolean sameDimensions(Map2D p) {
        boolean ans = false;

        return ans;
    }

    @Override
    public void addMap2D(Map2D p) {

    }

    @Override
    public void mul(double scalar) {

    }

    @Override
    public void rescale(double sx, double sy) {

    }

    @Override
    public void drawCircle(Pixel2D center, double rad, int color) {

    }

    @Override
    public void drawLine(Pixel2D p1, Pixel2D p2, int color) {

    }

    @Override
    public void drawRect(Pixel2D p1, Pixel2D p2, int color) {

    }

    @Override
    public boolean equals(Object ob) {
        boolean ans = false;

        return ans;
    }
	@Override
	/** 
	 * Fills this map with the new color (new_v) starting from p.
	 * https://en.wikipedia.org/wiki/Flood_fill
	 */
	public int fill(Pixel2D xy, int new_v,  boolean cyclic) {
		int ans = -1;

		return ans;
	}

	@Override
	/**
	 * BFS like shortest the computation based on iterative raster implementation of BFS, see:
	 * https://en.wikipedia.org/wiki/Breadth-first_search
	 */
	public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor, boolean cyclic) {
		Pixel2D[] ans = null;  // the result.

		return ans;
	}
    @Override
    public Map2D allDistance(Pixel2D start, int obsColor, boolean cyclic) {
        Map2D ans = null;  // the result.

        return ans;
    }
	////////////////////// Private Methods ///////////////////////

}
