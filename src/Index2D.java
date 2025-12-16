public class Index2D implements Pixel2D {
    private int x;
    private int y;

    public Index2D(int w, int h) {
        this.x=w;
        this.y=h;
    }
    // copy constructor
    public Index2D(Pixel2D other) {
        ;
    }
    @Override
    public int getX() {

        return this.x;
    }

    @Override
    public int getY() {

        return this.y;
    }
    // throw exception
    @Override
    public double distance2D(Pixel2D p2) {

        return 0;
    }

    @Override
    public String toString() {
        String ans = null;

        return ans;
    }

    @Override
    public boolean equals(Object p) {
        boolean ans = true;

        return ans;
    }
}
