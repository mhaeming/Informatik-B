package test;

import visitor.MyList;

public class TestSuite {

    private static int errors;
    private static int tests;

    public static int getErrors() {
        return TestSuite.errors;
    }

    public static int getTests(){ return TestSuite.tests;}

    // Count tests and errors - these methods are made for use outside of this class!
    public static void countError() {
        TestSuite.errors++;
    }

    public static void countTest() {TestSuite.tests++; }

    // Integers
    public static void assertEquals(int expected, int actual, String errMsg) {
        if (expected != actual) {
            System.out.println("ASSERTION ERROR: expected " + expected + " but received " + actual + " - Details: " + errMsg);
            errors++;
        }
        countTest();
    }

    // Doubles
    public static void assertEquals(double expected, double actual, double epsilon, String errMsg) {
        if (expected != actual && Math.abs(expected - actual) > epsilon) {
            System.out.println("ASSERTION ERROR: expected " + expected + " but received " + actual + " Details: " + errMsg);
            errors++;
        }
        countTest();
    }

    // Strings
    public static void assertEquals(String expected, String actual, String errMsg) {
        if (!expected.equals(actual)) {
            System.out.println("ASSERTION ERROR: expected " + expected + " but received " + actual + " Details: " + errMsg);
            errors++;
        }
        countTest();
    }

    // Objects
    public static void assertEquals(Object expected, Object actual, String errMsg) {
        if (expected == null) {
            if (actual != null) {
                System.out.println("ASSERTION ERROR: expected " + expected + " but received " + actual + " Details: " + errMsg);
                errors++;
            }
        } else if (!expected.equals(actual)) {
            System.out.println("ASSERTION ERROR: expected " + expected + " but received " + actual + " Details: " + errMsg);
            errors++;
        }
        countTest();
    }

    // MyLists
    public static <T> void assertEquals(MyList<T> expected, MyList<T> actual, String errMsg) {
        countTest();

        expected.reset();
        actual.reset();

        while (!expected.endpos()) {
            if (expected.endpos() != actual.endpos()) {
                throw new IndexOutOfBoundsException("Lists are not of equal length!");
            }

            if (!expected.elem().equals(actual.elem())) {
                System.out.println("ASSERTION ERROR: expected " + expected.elem() + " but received " + actual.elem() + " Details: " + errMsg);
                countError();
            }
            expected.advance();
            actual.advance();
        }
    }


    // True
    public static void assertTrue(boolean actual, String errMsg) {
        if (!actual) {
            System.out.println("ASSERTION ERROR: This statement should be true. Details: " + errMsg);
            errors++;
        }
        countTest();
    }

    // False
    public static void assertFalse(boolean actual, String errMsg) {
        if (actual) {
            System.out.println("ASSERTION ERROR: This statement should be false. Details: " + errMsg);
            errors++;
        }
        countTest();
    }
}
