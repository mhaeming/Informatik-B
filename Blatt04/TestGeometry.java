public class TestGeometry {
    public static void main(String[] args) {
        Point2D p1 = new Point2D(new double[]{0,0});
        Point2D p2 = new Point2D(new double[]{5,4});
        Rectangle rect = new Rectangle(p1, p2);
        System.out.println(rect.volume()); 


        Point2D p3 = new Point2D(new double[]{9,0});
        Geometry rect2 = rect.encapsulate(p3);
        System.out.println(rect2.volume());

        Point2D p4 = new Point2D(new double[]{0,8});
        Rectangle rect3 = new Rectangle(p3, p4);
        Geometry rect4 = rect.encapsulate(rect3);
        System.out.println(rect4.volume());
    }

    // TODO: Test Point2D

    // TODO: Test Rectangle

    // TODO: Test Point

    // TODO: Test Volume
}
