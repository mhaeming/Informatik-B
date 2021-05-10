public class Rectangle extends Geometry implements Comparable{
    private Point2D corner1;
    private Point2D corner2;

    private double minX;
    private double minY;
    private double maxX;
    private double maxY;

    public Rectangle(Point2D x, Point2D y) {
        super(2);
        corner1 = x;
        corner2 = y;

        // Gives us the left-bottom and top-right corners, helpful for the encapsulation
        minX = Double.min(corner1.getX(), corner2.getX());
        maxX = Double.max(corner1.getX(), corner2.getX());
        minY = Double.min(corner1.getY(), corner2.getY());
        maxY = Double.max(corner1.getY(), corner2.getY());
    }

    @Override
    public double volume() {
        Volume v = new Volume(corner1, corner2);
        return v.volume();
    }

    @Override
    public Geometry encapsulate(Geometry other) {
        if (this.dimensions() != other.dimensions()) throw new RuntimeException("Geometry for encapsulation must be of same dimensionality!");

        double absXmin = 0;
        double absYmin = 0;
        double absXmax = 0;
        double absYmax = 0;

        // Encapsulate a Point2D
        if (other instanceof Point2D) {
            absXmin = Double.min(((Point2D)other).getX(), minX);
            absYmin = Double.min(((Point2D)other).getY(), minY);
            absXmax = Double.max(((Point2D)other).getX(), maxX);
            absYmax = Double.max(((Point2D)other).getY(), maxY);
        }

        if (other instanceof Rectangle) {
            absXmin = Double.min(((Rectangle)other).getMin().getX(), minX);
            absYmin = Double.min(((Rectangle)other).getMin().getY(), minY);
            absXmax = Double.max(((Rectangle)other).getMax().getX(), maxX);
            absYmax = Double.max(((Rectangle)other).getMax().getY(), maxY);
        }

        // TODO: Hier könnte ja auch noch ein Point kommen, aber der hat die jeweiligen Methoden (noch) nicht

        Point2D absMin = new Point2D(new double[]{absXmin,absYmin});
        Point2D absMax = new Point2D(new double[]{absXmax,absYmax});

        return new Rectangle(absMin, absMax);
    }

    public Point2D getMin() {
        return new Point2D(new double[]{minX,minY});
    }

    public Point2D getMax() {
        return new Point2D(new double[]{maxX,maxY});
    }

    @Override
    public int compareTo(Object o) {
        if (volume() > ((Geometry)o).volume()) return 1;
        if (volume() < ((Geometry)o).volume()) return -1;
        return 0;
    }
    
}
