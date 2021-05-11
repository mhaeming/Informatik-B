package tests;
import geometry.Point;
import geometry.Point2D;
import geometry.Rectangle;


public class TestPoint2D {
    public TestPoint2D() {
        TestVolume();
        TestEncapsulation();
        TestComparison();
    }

    public void TestVolume() {
        Point2D p1 = new Point2D(2,5);
        Point2D p2 = new Point2D(-3,2);

        TestSuite.assertEquals(0, p1.volume(), 0.000001, "Non-zero volume for Point");
        TestSuite.assertEquals(0, p2.volume(), 0.000001, "Non-zero volume for Point");
    }

    public void TestEncapsulation() {
        Point2D p1 = new Point2D(2,5);
        Rectangle r1 = new Rectangle(new Point2D(0, 0), new Point2D(6, 6));
        TestSuite.assertEquals(r1, p1.encapsulate(r1), "encapsulation error");
        Point p2 = new Point(new double[]{1,2,3,4});
        TestSuite.assertEquals(null, p1.encapsulate(p2), "Allowed Point2D encapsulation with higher dimensional point");
    }

    public void TestComparison() {
        Point2D p1 = new Point2D(2,5);
        Point2D p2 = new Point2D(4,1);
        TestSuite.assertEquals(p1, p1, "Points with equal values not regarded equal");

        TestSuite.assertTrue(p1.compareTo(p2) == 0, "Error in comparisson of same volume points");
        Rectangle r1 = new Rectangle(new Point2D(0, 0), new Point2D(6, 6));
        TestSuite.assertTrue(p1.compareTo(r1) < 0, "Error in comparisson with rectangle");

    }
}
