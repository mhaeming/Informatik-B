public class TestSuite {

    private static int errors;

    public static int getErrors() {
        return TestSuite.errors;
    }

    public static void countError() {
        TestSuite.errors++;
    }

    // Integers
    public static void assertEquals(int expected, int actual, String errMsg) {
        if (expected != actual) {
            System.out.println("ASSERTION ERROR: expected " + expected + " but received " + actual + " Details: " + errMsg);
            errors++;
        }
    }

    // Doubles
    public static void assertEquals(double expected, double actual, double epsilon, String errMsg) {
        if (expected != actual && Math.abs(expected - actual) > epsilon) {
            System.out.println("ASSERTION ERROR: expected " + expected + " but received " + actual + " Details: " + errMsg);
            errors++;
        }
    }

    // Strings
    public static void assertEquals(String expected, String actual, String errMsg) {
        if (!expected.equals(actual)) {
            System.out.println("ASSERTION ERROR: expected " + expected + " but received " + actual + " Details: " + errMsg);
            errors++;
        }
    }

    // True
    public static void assertTrue(boolean actual, String errMsg) {
        if (!actual) {
            System.out.println("ASSERTION ERROR: This statement should be true. Details: " + errMsg);
            errors++;
        }
    }

    // False
    public static void assertFalse(boolean actual, String errMsg) {
        if (actual) {
            System.out.println("ASSERTION ERROR: This statement should be true. Details: " + errMsg);
            errors++;
        }
    }
}
