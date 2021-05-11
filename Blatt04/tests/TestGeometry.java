package tests;
public class TestGeometry {
    public static void main(String[] args) {
        System.out.println("Starting Tests...");
        TestGeometry tester = new TestGeometry();
        tester.runTests();
        System.out.println(TestSuite.getTests() + " Tests finished with " + TestSuite.getErrors() + " Errors.");
    }

    public void runTests() {
        TestRectangle testerRect = new TestRectangle();
        TestPoint2D testerPoint2D = new TestPoint2D();
        TestVolume testerVolume = new TestVolume();
        TestPoint testerPoint = new TestPoint();
    }
}
