package tests;

import geometry.*;


public class TestVolume {

    public TestVolume() {
        TestInstantiation();
        TestEncapsulation();
        TestComparison();
    }

    public void TestInstantiation() {
        Point p1 = new Point(new double[] {0,0,0});
        Point p2 = new Point(new double[] {1,2,3});
        Point p3 = new Point(new double[] {-1,-2,-3});

        Volume v1 = new Volume(p1, p2);
        Volume v2 = new Volume(p1, p3);
        Volume v3 = new Volume(p2, p2);

        TestSuite.assertEquals(6.0, v1.volume(), 0.000001, "Testing Rect 1");
        TestSuite.assertEquals(6.0, v2.volume(), 0.000001, "Testing Rect 2");
        TestSuite.assertEquals(0.0, v3.volume(), 0.000001, "Testing Rect 2");
    }

    public void TestEncapsulation() {
        Point p1 = new Point(new double[] {0,0,0});
        Point p2 = new Point(new double[] {1,2,3});
        Point p3 = new Point(new double[] {-1,-2,-3});
        Point p4 = new Point(new double[] {1,2,3,4});

        Volume v1 = new Volume(p2, p3);
        Volume v2 = new Volume(p1, p3);

        TestSuite.assertEquals(v1, v1.encapsulate(p1), "Error in encapsulating a point in the Volume");
        TestSuite.assertEquals(v1, v1.encapsulate(v2), "Error in encapsulating a volume in the rectangle");
        TestSuite.assertEquals(null, v1.encapsulate(p4), "Higher dimension encapsulate did not return null");
    }

    public void TestComparison() {
        Point p1 = new Point(new double[] {0,0,0});
        Point p2 = new Point(new double[] {1,2,3});
        Point p3 = new Point(new double[] {-1,-2,-3});

        Volume v1 = new Volume(p1, p2);
        Volume v2 = new Volume(p1, p3);
        Volume v3 = new Volume(p2, p3);

        TestSuite.assertTrue(v3.compareTo(v1) > 0, "Error in Volume Compare Method 1");
        TestSuite.assertTrue(v1.compareTo(v3) < 0, "Error in Volume Compare Method 2");
        TestSuite.assertTrue(v1.compareTo(v2) == 0, "Error in Volume Compare Method 3");
    }
}