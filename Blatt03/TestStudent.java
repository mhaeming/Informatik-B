public class TestStudent {
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
        Student peter = new Student("Peter", 123456);
        TestSuite.assertFalse(peter.equals(null), "Null comparison returned true");
    }

    // Test reflexiveness
    public void testIdentity() {
        Student peter = new Student("Peter", 123456);
        TestSuite.assertTrue(peter.equals(peter), "Identity check returned false");
    }

    public void testSymmetry() {
        Student student = new Student("Peter", 123456);
        Student student2 = new Student("Peter", 123456);
        Student student3 = new Student("Peter", 654321);
        Student student4 = new Student("John", 123456);

        TestSuite.assertFalse(student == student2, "");

        // Check cases which should be symmetric
        TestSuite.assertTrue(student.equals(student2), "");
        TestSuite.assertTrue(student2.equals(student), "");

        // Check cases in which symmetry should not hold
        TestSuite.assertFalse(student.equals(student3), "");
        TestSuite.assertFalse(student.equals(student4), "");
        TestSuite.assertFalse(student3.equals(student4), "");
    }

    public void testTransivity() {
        Student student = new Student("Peter", 123456);
        Student student2 = new Student("Peter", 123456);
        Student student3 = new Student("Peter", 123456);
        Student student4 = new Student("John", 123456);

        // Check for different references
        TestSuite.assertFalse(student == student2 || student == student3 || student2 == student3, "");

        // Check transitivity
        TestSuite.assertTrue(student.equals(student2) && student2.equals(student3) && student.equals(student3), "");
        TestSuite.assertTrue(student.equals(student2) && student2.equals(student4) && student.equals(student4), "");

    }
}
