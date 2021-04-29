public class TestPerson {
    public static void main(String[] args) {

        System.out.println("Starting Tests...");
        TestPerson tester = new TestPerson();
        tester.runTests();
        System.out.println("Tests finished with " + TestSuite.getErrors() + " Errors.");

    }

    public void runTests() {
        testCompareNull();
        testIdentity();
        testSymmetry();
        testTransivity();
    }

    public void testCompareNull() {
        Person peter = new Person("Peter");
        TestSuite.assertFalse(peter.equals(null), "Null comparison returned true");
    }

    // Test reflexiveness
    public void testIdentity() {
        Person peter = new Person("Peter");
        TestSuite.assertTrue(peter.equals(peter), "Identity check returned false");
    }

    public void testSymmetry() {
        Person peter = new Person("Peter");
        Person peter2 = new Person("Peter");

        TestSuite.assertFalse(peter == peter2, "");

        TestSuite.assertTrue(peter.equals(peter2), "");
        TestSuite.assertTrue(peter2.equals(peter), "");
    }

    public void testTransivity() {
        Person peter = new Person("Peter");
        Person peter2 = new Person("Peter");
        Person peter3 = new Person("Peter");

        // Check for different references
        TestSuite.assertFalse(peter == peter2 || peter == peter3 || peter2 == peter3, "");

        TestSuite.assertTrue(peter.equals(peter2) && peter2.equals(peter3) && peter.equals(peter3), "");
    }
}