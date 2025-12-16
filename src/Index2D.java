public class Index2D implements Pixel2D {
    private int x;
    private int y;

    public Index2D(int w, int h) {
        this.x=w;
        this.y=h;
    }
    // copy constructor
    public Index2D(Pixel2D other) {
        if (other == null) {
            throw new IllegalArgumentException("Pixel2D is null");
        }
        this.x = other.getX();
        this.y = other.getY();
    }

    @Override
    public int getX() {

        return this.x;
    }

    @Override
    public int getY() {

        return this.y;
    }
    // checks the distance between two pixels using the Pythagorean theorem.
    // Throws runtime exception if null
    @Override
    public double distance2D(Pixel2D p2) {
        double dx = this.x - p2.getX();
        double dy = this.y - p2.getY();
        if (p2==null) {
            throw new RuntimeException("p2 is null");
        }
        double t = (dx * dx + dy * dy);

        return Math.sqrt(t);
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
