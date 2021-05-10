/**
 * TODO: Class description
 */
public class Point2D extends Point{
    private double x;
    private double y;

    /**
     * Creates a point with x and y value
     */
    public Point2D(double[] positions) {
        super(positions);

        if (positions.length != 2) throw new RuntimeException("2 values are required for a 2D Point!");
        // The point is always 2 dimensional
        this.x = positions[0];
        this.y = positions[1];
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public Geometry encapsulate(Geometry other) {
        if (this.dimensions() != other.dimensions()) return null;
        
        if (other instanceof Rectangle) {
            return other.encapsulate(this);
        }
        
        if (other instanceof Point2D) {
            return new Rectangle(this, (Point2D)other);
        }

        return null;
    }

}
