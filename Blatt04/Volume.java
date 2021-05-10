public class Volume extends Geometry{
    private Point x;
    private Point y;

    public Volume(Point x, Point y) {
        super(x.dimensions());

        if (x.dimensions() != y.dimensions()) throw new RuntimeException("Point dimensions for volume must be equal!");

        this.x = x;
        this.y = y;
    }

    @Override
    public double volume() {
        double[] xval = x.values();
        double[] yval = y.values();

        // Check whether the points are identical. Saves computational power since the check is O(1)
        if (xval.equals(yval)) return 0;

        double volume = 1;
        for (int i = 0; i < this.dimensions(); i++) {
            volume *= Math.abs(xval[i] - yval[i]);
        }
        return volume;
    }

    @Override
    public Geometry encapsulate(Geometry other) {
        // TODO: Not sure how the method has to look here
        return null;
    }
    
}
