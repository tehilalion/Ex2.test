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


    /**
     *
     * @param arr a 2D int array.
     */
    @Override
	public void init(int[][] arr) {
        if   (arr==null || arr.length==0)
            throw new RuntimeException("Invalid input array");
        int h = arr.length;
        int c= arr[0].length;
        if (c==0)
            throw new RuntimeException("Invalid Row Input");

        for (int i=1; i<h; i=i+1) {
            if (arr[i].length != c)
                throw new RuntimeException("Ragged array");
        }
                map = new int[h][c];
                for (int y = 0; y < h; y++){
                    for (int x = 0; x < c; x++) {
                        map[y][x] = arr[y][x];
                    }
                }
        }

    /**
     *
     * @return
     */
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
    public boolean isInside(int x, int y) {
        return x >= 0 && y >= 0 && x < getWidth() && y < getHeight();
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
        return this.getWidth() == p.getWidth()
                && this.getHeight() == p.getHeight();

    }

    @Override // check the sameDimesions
    public void addMap2D(Map2D p) {
        if (!sameDimensions(p))
            throw new RuntimeException("Invalid dimensions");

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
        int newW= (int)(sx*getWidth());
          int newH= (int)(sy*getHeight());

          int [][] temp= new int [newW][newH];
        for (int i=0; i<newW; i=i+1) {
            for (int j=0; j<newH; j=j+1) {
                int oldx=(int)(i/sx);
                int oldy=(int)(j/sy);
                if (oldx<getWidth()&&oldy<getHeight()) {
                temp [i][j]= getPixel(oldx, oldy);
                }
            }
        }
        this.init(temp);
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

private void drawHorizontalLine(Pixel2D p1, Pixel2D p2, int color) {
    int x1 = p1.getX(), y1 = p1.getY();
    int x2 = p2.getX(), y2 = p2.getY();

    int dx = x2 - x1;
    int dy = y2 - y1;
    double m = (double) dy / dx;

    for (int x = x1; x <= x2; x++) {
        double exactY = y1 + m * (x - x1);
        int roundedY = (int) Math.round(exactY);

        if (isInside(x, roundedY)) {
            setPixel(new Index2D(x, roundedY), color);
        }
    }
}

    private void drawVerticalLine(Pixel2D p1, Pixel2D p2, int color) {
        int x1 = p1.getX(), y1 = p1.getY();
        int x2 = p2.getX(), y2 = p2.getY();


        int dx = x2 - x1;
        int dy = y2 - y1;
        double m_inv = (double) dx / dy;

        for (int y = y1; y <= y2; y++) {
            double exactX = x1 + m_inv * (y - y1);
            int roundedX = (int) Math.round(exactX);

            if (isInside(roundedX, y)) {
                setPixel(new Index2D(roundedX, y), color);
            }
        }
    }

    @Override
    public void drawLine(Pixel2D p1, Pixel2D p2, int newcolor) {

        if (p1.equals(p2)) {
            if (isInside(p1.getX(), p1.getY())) {
                setPixel(p1, newcolor);
            }
            return;
        }

        int dx = Math.abs(p2.getX() - p1.getX());
        int dy = Math.abs(p2.getY() - p1.getY());

        if (dx >= dy) {
            if (p1.getX() <= p2.getX()) {
                drawHorizontalLine(p1, p2, newcolor);
            } else {
                drawHorizontalLine(p2, p1, newcolor);
            }
        } else {
            if (p1.getY() <= p2.getY()) {
                drawVerticalLine(p1, p2, newcolor);
            } else {
                drawVerticalLine(p2, p1, newcolor);
            }
        }
    }



    @Override
    public void drawRect(Pixel2D p1, Pixel2D p2, int color) {
        int x1 = Math.min(p1.getX(), p2.getX());
        int x2 = Math.max(p1.getX(), p2.getX());
        int y1 = Math.min(p1.getY(), p2.getY());
        int y2 = Math.max(p1.getY(), p2.getY());
        for ( int x=x1; x<=x2; x=x+1 ) {
            for ( int y=y1; y<=y2; y=y+1 ) {
                setPixel(new Index2D(x, y), color);
            }
        }

    }

    @Override
    public boolean equals(Object ob) {
        if (this == ob)
            return true;
        if(ob==null || !(ob instanceof  Map2D))
            return false;
        Map2D other = (Map2D) ob;
        if (this.getWidth()!=other.getWidth()|| this.getHeight()!= other.getHeight())
    return false;
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                if (this.getPixel(i, j) != other.getPixel(i, j))
                    return false;
                }
        }
        return true;
    }




	@Override
	/** 
	 * Fills this map with the new color (new_v) starting from p.
	 * https://en.wikipedia.org/wiki/Flood_fill
	 */
    public int fill(Pixel2D xy, int new_v, boolean cyclic) {
        int old_v = getPixel(xy);
        if (old_v == new_v)
            return 0;
    ArrayList<Pixel2D> q= new ArrayList<>();
    boolean[][] visited = new boolean[getWidth()][getHeight()];
    q.add(xy); visited[xy.getX()][xy.getY()]=true;
    int count= 0;
        int [][] dir= {{1,0},{-1,0},{0,1},{0,-1}};
        while (!q.isEmpty()){
        Pixel2D c=q.remove(0);
            setPixel(c,new_v);
            count++;
            for (int []step:dir){
                int nx = c.getX() + step[0];
                int ny = c.getY() + step[1];
                if (cyclic) {
                    nx =(nx + getWidth()) % getWidth();
                    ny =(ny + getHeight()) % getHeight();
                }
                    if (isInside(nx,ny) && !visited[nx][ny] && getPixel(nx, ny) == old_v) {
                        visited[nx][ny] = true;
                        q.add(new Index2D(nx, ny));
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
       Map2D distMap= allDistance(p1,obsColor,cyclic);
       int dist = distMap.getPixel(p2.getX(), p2.getY());
       if (dist == -1) return null;
       Pixel2D[] shortest = new Pixel2D[dist+1];
       Pixel2D temp=p2;
       int[] dx={1,-1,0,0};
       int[] dy={0,0,1,-1};
               for (int i=dist; i>=0; i--){
                   shortest[i]= temp;
               if (i>0) {
                   for (int j = 0; j < dx.length; j++) {
                       int nX = temp.getX() + dx[j];
                       int nY = temp.getY() + dy[j];
                       if (cyclic) {
                           nX = (nX + getWidth()) % getWidth();
                           nY = (nY + getHeight()) % getHeight();
                       }
                       if (isInside(nX, nY) && distMap.getPixel(nX, nY) == i - 1) {
                           temp = new Index2D(nX, nY);
                           break;
                       }
                   }

               }
               }
               return shortest;

    }

    @Override
    public Map2D allDistance(Pixel2D start, int obsColor, boolean cyclic) {
        Map2D distMap = new Map(this.getWidth(),this.getHeight(),-1);
        if (start == null) {
            throw new RuntimeException("invalid start");
        }
        distMap.setPixel(start.getX(), start.getY(), 0);
        ArrayList<Pixel2D> q= new ArrayList<Pixel2D>();
        q.add(start);
        int [] dx={1,-1,0,0}; //changes in x
        int [] dy= {0,0,1,-1};// changes in y

        while (!q.isEmpty()){
            Pixel2D c =q.remove(0);
            int currentDis= distMap.getPixel(c.getX(), c.getY());

            for (int i=0;i< dx.length;i=i+1) {
                int nX = c.getX() + dx[i];
                int nY = c.getY() + dy[i];
                if (cyclic) {
                    nX = (nX + getWidth()) % getWidth();
                    nY = (nY + getHeight()) % getHeight();
                }
                if (isInside(nX, nY)) {
                    if (this.getPixel(nX, nY) != obsColor && distMap.getPixel(nX, nY) == -1) {
                        distMap.setPixel(nX, nY, currentDis+1);
                        q.add(new Index2D(nX, nY));

                    }
                }
            }

            }
        return distMap;
    }

}
