public class Volume extends Geometry implements Comparable{
    private double[] point1vals;
    private double[] point2vals;
    private double[] minPointvals;
    private double[] maxPointvals;


    public Volume(Point p1, Point p2) {
        super(p1.dimensions());

        if (p1.dimensions() != p2.dimensions()) throw new RuntimeException("Point dimensions for volume must be equal!");

        this.point1vals = p1.values();
        this.point2vals = p2.values();
    }

    @Override
    public double volume() {
        // Check whether the points are identical. Saves computational power since the check is O(1)
        if (point1vals.equals(point2vals)) return 0;

        double volume = 1;
        for (int i = 0; i < this.dimensions(); i++) {
            volume *= Math.abs(point1vals[i] - point2vals[i]);
        }
        return volume;
    }

    @Override
    public Geometry encapsulate(Geometry other) {
        if (dimensions() != other.dimensions()) throw new RuntimeException("Geometry for encapsulation must be of same dimensionality!");
        
        double[] absMinPointVals = new double[this.dimensions()];
        double[] absMaxPointVals = new double[this.dimensions()];


        if (other instanceof Point) {
            for (int i = 0; i < this.dimensions(); i++) {
                absMinPointVals[i] = Double.min(minPointvals[i], ((Point)other).values()[i]);
                absMaxPointVals[i] = Double.max(maxPointvals[i], ((Point)other).values()[i]);
            }
        }

        if (other instanceof Volume) {
            for (int i = 0; i < this.dimensions(); i++) {
                absMinPointVals[i] = Double.min(minPointvals[i], ((Volume)other).getMin().values()[i]);
                absMaxPointVals[i] = Double.max(maxPointvals[i], ((Volume)other).getMax().values()[i]);
            }
        }
        Point absMinPoint = new Point(absMinPointVals);
        Point absMaxPoint = new Point(absMaxPointVals);

        return new Volume(absMinPoint, absMaxPoint);
    }

    @Override
    public int compareTo(Object o) {
        // Confirm that it's compared to a volume
        if (!(o instanceof Volume)) throw new RuntimeException("You have to compare the same data types");

        // Ensure same dimensions
        if (this.dimensions() != ((Volume)o).dimensions()) throw new RuntimeException("Geometry for encapsulation must be of same dimensionality!");

        if (volume() > ((Volume)o).volume()) return 1;
        if (volume() < ((Volume)o).volume()) return -1;
        return 0;
    }

    public Point getMin() {
        for (int i = 0; i < this.dimensions(); i++) {
            minPointvals[i] = Double.min(point1vals[i], point2vals[i]);
        }
        return new Point(minPointvals);
    }

    public Point getMax() {
        for (int i = 0; i < this.dimensions(); i++) {
            maxPointvals[i] = Double.max(point1vals[i], point2vals[i]);
        }
        return new Point(maxPointvals);
    }
    
}
