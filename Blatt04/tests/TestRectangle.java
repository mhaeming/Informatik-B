package tests;

import geometry.*;


public class TestRectangle {

    public TestRectangle() {
        TestInstantiation();
        TestEncapsulation();
        TestComparison();
    }

    public void TestInstantiation() {
        Rectangle r1 = new Rectangle(new Point2D(-1, 5), new Point2D(5, -1));
        Rectangle r2 = new Rectangle(new Point2D(4, 3), new Point2D(0, 0)); 
        Rectangle r3 = new Rectangle(new Point2D(4, 3), new Point2D(4, 3)); 

        TestSuite.assertEquals(36.0, r1.volume(), 0.000001, "Testing Rect 1");
        TestSuite.assertEquals(12.0, r2.volume(), 0.000001, "Testing Rect 2");
        TestSuite.assertEquals(0.0, r3.volume(), 0.000001, "Testing Rect 2");
    }

    public void TestEncapsulation() {
        Rectangle r1 = new Rectangle(new Point2D(-1, 5), new Point2D(5, -1));
        Rectangle r2 = new Rectangle(new Point2D(4, 3), new Point2D(0, 0));
        Point2D p1 = new Point2D(1, 1);
        Point p2 = new Point(new double[]{1,2,3,4});

        TestSuite.assertEquals(r1, r1.encapsulate(p1), "Error in encapsulating a point in the rectangle");
        Rectangle r3 = new Rectangle(new Point2D(-1, -1), new Point2D(5, 5));
        TestSuite.assertEquals(r3, r1.encapsulate(r2), "Error in encapsulating a rectangle in the rectangle");
        TestSuite.assertEquals(null, r1.encapsulate(p2), "Higher dimension encapsulate did not return null");
    }

    public void TestComparison() {
        Rectangle r1 = new Rectangle(new Point2D(-1, 5), new Point2D(5, -1));
        Rectangle r2 = new Rectangle(new Point2D(4, 3), new Point2D(0, 0));
        Point2D p1 = new Point2D(1, 1);

        TestSuite.assertTrue(r1.compareTo(r2) > 0, "Error in Rectangle Compare Method");
        TestSuite.assertTrue(r2.compareTo(r1) < 0, "Error in Rectangle Compare Method");
        TestSuite.assertTrue(r1.compareTo(r1) == 0, "Error in Rectangle Compare Method");
        TestSuite.assertTrue(r1.compareTo(p1) > 0, "Error in Rectangle Compare Method");
    }
    
}
