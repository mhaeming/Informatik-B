package tests;

import geometry.*;


public class TestPoint {

    public TestPoint() {
        TestVolume();
        TestEncapsulation();
        TestComparison();
    }

    public void TestVolume() {
        Point p1 = new Point(new double[]{1,2,3});
        Point p2 = new Point(new double[]{-1,2,-3});

        TestSuite.assertEquals(0, p1.volume(), 0.000001, "Non-zero volume for Point");
        TestSuite.assertEquals(0, p2.volume(), 0.000001, "Non-zero volume for Point");
    }

    public void TestEncapsulation() {
        Point p1 = new Point(new double[]{1,2,3});
        Point p2 = new Point(new double[]{-1,2,-3});
        Point p3 = new Point(new double[]{0,0,0,0});

        Volume v1 = new Volume(p1, p2);
        TestSuite.assertEquals(v1, p1.encapsulate(p2), "Error in Point encapsulation");
        TestSuite.assertEquals(null, p1.encapsulate(p3), "Allowed higher dimensional encapsulation for point");
    }

    public void TestComparison() {
        Point p1 = new Point(new double[]{1,2,3});
        Point p2 = new Point(new double[]{-1,2,-3});
        TestSuite.assertEquals(p1, p1, "Points with equal values not regarded equal");

        TestSuite.assertTrue(p1.compareTo(p2) == 0, "Error in comparisson of same volume points");
        Volume v1 = new Volume(new Point(new double[]{0,0,0}), p1);
        TestSuite.assertTrue(p1.compareTo(v1) < 0, "Error in comparisson with volume");
    }
    
}
