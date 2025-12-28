public class Index2D implements Pixel2D {
    private int x;
    private int y;

    public Index2D(int w, int h) {
        this.x=w;
        this.y=h;
    }

    /** copy constructor
     *Creates a new Index2D with the same coordinates as another Pixel2D.
     * @param other
     */
    public Index2D(Pixel2D other) {
        if (other == null) {
            throw new IllegalArgumentException("Pixel2D is null");
        }
        this.x = other.getX();
        this.y = other.getY();
    }

    /**
     *Returns the x coordinate of this pixel.
     * @return
     */

    @Override
    public int getX() {

        return this.x;
    }

    /**
     * Returns the y coordinate of this pixel.
     * @return
     */
    @Override
    public int getY() {

        return this.y;
    }

    /**
     * checks the distance between two pixels using the Pythagorean theorem.
     *  Throws runtime exception if null
     * @param p2
     * @return
     */
    @Override
    public double distance2D(Pixel2D p2) {
        double dx = this.x- p2.getX();
        double dy = this.y - p2.getY();
        if (p2==null) {
            throw new RuntimeException("p2 is null");
        }
        double t = (dx * dx + dy * dy);

        return Math.sqrt(t);
    }

    /**
     * Returns a string representation of this pixel in the form "x,y".
     * @return
     */
    @Override
    public String toString() {
        //String ans = null;
      String ans= this.x+","+this.y;
        return ans;
    }

    /**
     * Checks whether this pixel is equal to another object.
     * Two pixels are equal if they have the same x and y coordinates.
     * @param p the reference object with which to compare.
     * @return
     */
    @Override
    public boolean equals(Object p) {
        if (this == p){

            return true;
    }
            if(p==null || !(p instanceof Pixel2D)) {
                return false;
            }
            Pixel2D p2 = (Pixel2D)p;
            return ( (this.x==p2.getX()) && (this.y==p2.getY()));
        }

}
