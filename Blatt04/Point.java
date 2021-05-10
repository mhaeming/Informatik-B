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
        // TODO: Comparing method
        return 0;
    }

    @Override
    public Geometry encapsulate(Geometry other) {
        if (this.dimensions() != other.dimensions()) return null;
        // TODO: Create a volume to encompass other volumes or n-dim Points
        return null;
    }

    public double[] values() {
        return values;
    }
    
}
