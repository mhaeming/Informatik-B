public class TestGeometry {
    public static void main(String[] args) {
        Point2D p1 = new Point2D(new double[]{0,0});
        Point2D p2 = new Point2D(new double[]{5,4});
        Rectangle rect = new Rectangle(p1, p2);
        System.out.println(rect.volume()); 

    }
}
