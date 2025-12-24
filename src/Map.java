import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents a 2D map (int[w][h]) as a "screen" or a raster matrix or maze over integers.
 * This is the main class needed to be implemented.
 *
 * @author boaz.benmoshe
 *
 */
public class Map implements Map2D, Serializable{
    private int[][] map;

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

       return  map[0].length;
    }
	@Override
	public int getHeight() {
        return map.length;
    }
	@Override
	public int getPixel(int x, int y) {
        int ans = map[y][x];

        return ans;
    }
	@Override
	public int getPixel(Pixel2D p) {
        int ans = map[p.getY()][p.getX()];

        return ans;
	}
	@Override // change the value of xy
	public void setPixel(int x, int y, int v) {
        map[y][x] = v;

    }
	@Override
	public void setPixel(Pixel2D p, int v) {
        map[p.getY()][p.getX()] = v;


	}

    @Override // is the point in the radius check by bigger than 0 and smaller than the length
    public boolean isInside(Pixel2D p) {
        boolean ans = false;
        if (p.getY()>=0 && p.getX()>=0
                && p.getY()<map.length && p.getX()<map[0].length ){
            ans = true;
        }

        return ans;
    }

    @Override
    public boolean sameDimensions(Map2D p) {
        boolean ans = true;
        if (this.getWidth()!=p.getWidth() && this.getHeight()!=p.getHeight()) {
            return false;
        }

        return ans;
    }

    @Override // check the sameDimesions
    public void addMap2D(Map2D p) {
        (if !sameDimensions()){
            throw new RuntimeException("Invalid dimensions");
        }
        for (int y=0; y<this.getHeight(); y=y+1) {
            for (int x=0; x<this.getWidth(); x=x+1) {
                map[y][x]=map[y][x]+p.getPixel(x, y);
            }
        }

    }

    @Override //
    public void mul(double scalar) {
        for (int i = 0; i<this.getHeight(); i=i+1) {
            for (int j = 0; j<this.getWidth(); j=j+1) {
                int v= getPixel(j,i);
                setPixel(j,i,(int)(v*scalar));
            }
        }
    }

    @Override//  find the pixel in the new picture find the matching pixel in the given, return the value of the given in the new
    public void rescale(double sx, double sy) {

    }

    @Override
    public void drawCircle(Pixel2D center, double rad, int color) {
        for( int x=0; x<this.getWidth(); x=x+1 ) {
            for( int y=0; y<this.getHeight(); y=y+1 ) {
               Pixel2D p1=new Index2D(x,y);
                if (center.distance2D(p1)<rad){
                    this.setPixel(x,y,color);
                }
            }
        }

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
        int old_v= getPixel(xy)
                if (old_v==new_v) return 0;
		ArrayList<Pixel2D> q= new ArrayList<Pixel2D>();
        q.add(xy);
        int count= 0;

        while (!q.isEmpty()){
           Pixel2D c=q.remove(0);
            if( getPixel(c)== old_v){
                setPixel(c,new_v);
                count++
                        int [][] dir= {{1,0},{-1,0},{0,1},{0,-1}};
                for (int []step:dir){
                    int nx = c.getX() + step[0]
                    int ny = c.getY() + step[1]
                            if (cyclic) {
                                nx =(nx + getWidth()) % getWidth();
                                ny =(ny + getHeight()) % getHeight();
                            }

                    if (nx >= 0 && nx < getWidth() && ny >= 0 && ny < getHeight()){
                        if (!visited[nx][ny] && getPixel(new Index2D(nx, ny)) == old_v) {
                            visited[nx][ny] = true;
                        }
                    }
                }
            }
        }

		return count;
	}
    public int fill(Pixel2D xy, int new_v, boolean cyclic) {
        int old_v = getPixel(xy);
        if (old_v == new_v)
            return 0;

    ArrayList<Pixel2D> q= new ArrayList<Pixel2D>();
    boolean[][] visited = new boolean[getWidth()][getHeight()];
    q.add(xy); visited[xy.getX()][xy.getY()]=true;
    int count= 0;
    while (!q.isEmpty()){
        Pixel2D c=q.remove(0);
        if( getPixel(c)== old_v){
            setPixel(c,new_v);
            count++
            int [][] dir= {{1,0},{-1,0},{0,1},{0,-1}};
            for (int []step:dir){
                int nx = c.getX() + step[0]
                int ny = c.getY() + step[1]
                if (cyclic) {
                    nx =(nx + getWidth()) % getWidth();
                    ny =(ny + getHeight()) % getHeight();
                }
                if (nx >= 0 && nx < getWidth() && ny >= 0 && ny < getHeight()) {
                    if (!visited[nx][ny] && getPixel(new Index2D(nx, ny)) == old_v) {
                        visited[nx][ny] = true;
                        q.add(new Index2D(nx, ny));
                    }
                }
            }
        }
    }
        return count;
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
                }
