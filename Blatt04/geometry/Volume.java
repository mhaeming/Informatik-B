package geometry;
public class Volume extends Geometry implements Comparable{
    private double[] point1vals;
    private double[] point2vals;

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
        if (dimensions() != other.dimensions()) return null;
        
        double[] absMinPointVals = new double[this.dimensions()];
        double[] absMaxPointVals = new double[this.dimensions()];

        //TODO: I think finding the min/max could be abstracted into a helper function


        if (other instanceof Point2D){
            for (int i = 0; i < this.dimensions(); i++) {
                absMinPointVals[i] = Double.min(this.getMin().valueAt(i), ((Point2D)other).valueAt(i));
                absMaxPointVals[i] = Double.max(this.getMax().valueAt(i), ((Point2D)other).valueAt(i));
            }
            Point2D min = new Point2D(absMinPointVals[0], absMinPointVals[1]);
            Point2D max = new Point2D(absMaxPointVals[0], absMaxPointVals[1]);
            return new Rectangle(min, max);
        }

        if (other instanceof Rectangle){
            for (int i = 0; i < this.dimensions(); i++) {
                absMinPointVals[i] = Double.min(this.getMin().valueAt(i), ((Rectangle)other).getMin().valueAt(i));
                absMaxPointVals[i] = Double.max(this.getMax().valueAt(i), ((Rectangle)other).getMax().valueAt(i));
            }
            Point2D min = new Point2D(absMinPointVals[0], absMinPointVals[1]);
            Point2D max = new Point2D(absMaxPointVals[0], absMaxPointVals[1]);
            return new Rectangle(min, max);
        }

        if (other instanceof Point) {
            for (int i = 0; i < this.dimensions(); i++) {
                absMinPointVals[i] = Double.min(this.getMin().valueAt(i), ((Point)other).valueAt(i));
                absMaxPointVals[i] = Double.max(this.getMax().valueAt(i), ((Point)other).valueAt(i));
            }

            Point absMinPoint = new Point(absMinPointVals);
            Point absMaxPoint = new Point(absMaxPointVals);
    
            return new Volume(absMinPoint, absMaxPoint);
        }

        if (other instanceof Volume) {
            for (int i = 0; i < this.dimensions(); i++) {
                absMinPointVals[i] = Double.min(this.getMin().valueAt(i), ((Volume)other).getMin().valueAt(i));
                absMaxPointVals[i] = Double.max(this.getMax().valueAt(i), ((Volume)other).getMax().valueAt(i));
            }

            Point absMinPoint = new Point(absMinPointVals);
            Point absMaxPoint = new Point(absMaxPointVals);
    
            return new Volume(absMinPoint, absMaxPoint);
        }

        return null;

    }

    @Override
    public int compareTo(Object o) {
        // Confirm that it's compared to a volume
        if (!(o instanceof Geometry)) throw new RuntimeException("You have to compare the same data types");

        // Ensure same dimensions
        if (this.dimensions() != ((Geometry)o).dimensions()) throw new RuntimeException("Geometry for comparisson must be of same dimensionality!");

        return (int)(this.volume() - ((Geometry) o).volume());
    }

    /**
     * 
     * @return Point - the smallest point of the volume
     */
    public Point getMin() {
        double[] minPointvals = new double[this.dimensions()];
        for (int i = 0; i < this.dimensions(); i++) {
            minPointvals[i] = Double.min(point1vals[i], point2vals[i]);
        }
        return new Point(minPointvals);
    }

    /**
     * 
     * @return Point - the largest point of the volume
     */
    public Point getMax() {
        double[] maxPointvals = new double[this.dimensions()];
        for (int i = 0; i < this.dimensions(); i++) {
            maxPointvals[i] = Double.max(point1vals[i], point2vals[i]);
        }
        return new Point(maxPointvals);
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
        Volume other = (Volume) obj;
        if (!(this.getMin().equals(other.getMin()) && this.getMax().equals(other.getMax()))) {
            return false;
        }
        return true;
    }
}
