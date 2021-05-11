package geometry;
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
        // Confirm that it's compared to a volume
        if (!(o instanceof Geometry)) throw new RuntimeException("You have to compare the same data types");

        // Ensure same dimensions
        if (this.dimensions() != ((Geometry)o).dimensions()) throw new RuntimeException("Geometry for comparisson must be of same dimensionality!");

        return (int)(this.volume() - ((Geometry) o).volume());
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

    public double valueAt(int i) {
        return values[i];
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Point other = (Point) obj;
        for (int i = 0; i < values.length; i++) {
            if (valueAt(i) != other.valueAt(i)) return false;
        }
        return true;
    }
    
}
