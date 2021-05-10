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

    public Geometry encapsulate(Geometry other) {
        if (this.dimensions() != other.dimensions()) return null;
        // TODO: Create a Rectangle to encapsulate the point
        return null;
    }

}
