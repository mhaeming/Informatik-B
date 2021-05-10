public class Rectangle extends Geometry {
    private Point2D corner1;
    private Point2D corner2;

    public Rectangle(Point2D x, Point2D y) {
        super(2);
        corner1 = x;
        corner2 = y;
    }

    @Override
    public double volume() {
        Volume v = new Volume(corner1, corner2);
        return v.volume();
    }

    @Override
    public Geometry encapsulate(Geometry other) {
        // TODO: Need to create new Rectangle to encompass old rectangle + new Point or Rectangle
        return null;
    }
}
