public class Point extends Geometry implements Comparable{
    private double[] values;

    public Point(double... positions) {
        super(positions.length);
        values = positions;
    }


    @Override
    public double volume() {
        return 0;
    }

    @Override
    public int compareTo(Object o) {
        // Does not really make sense to compare points by volume, right?
        return 0;
    }

    @Override
    public Geometry encapsulate(Geometry other) {
        if (this.dimensions() != other.dimensions()) return null;
        
        // Create a volume out of two points
        if (other instanceof Point) {
            return new Volume(this, (Point)other);
        }

        // Create an encapsulating volume for the other volume and this point
        if (other instanceof Volume) {
            return other.encapsulate(this);
        }

        return null;
    }

    public double[] values() {
        return values;
    }
    
}
