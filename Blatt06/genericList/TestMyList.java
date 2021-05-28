package genericList;

import util.TestSuite;

/**
 * Test class for the clone-method of MyList
 *
 * @author Per Starke
 */
public class TestMyList {

    /**
     * Start and lead through the test
     * @param args
     */
    public static void main(String[] args){
        System.out.println("Start tests");
        startTests();
        System.out.println(TestSuite.getErrors() + " errors found in " + TestSuite.getTests() + " tests.");
    }

    /**
     * Start the tests
     */
    public static void startTests(){
        testCloneIsNotSameObject();
        testCloneIsSameClass();
        testCloneEqualsOriginal();

        testEqualLists();
        testDifferentListsAreUnequal();
        testEqualsReflexivity();
        testEqualsSymmetry();
        testEqualsTransitivity();
        testEqualsConsistency();
        testEqualsNull();
    }

    /**
     * Test whether x.clone() != x
     */
    public static void testCloneIsNotSameObject(){
        // Test with String-List
        MyList<String> myStringList = new MyList<String>();
        MyList<String> myClonedStringList = (MyList<String>)myStringList.clone();

        boolean isSameObject = myStringList == myClonedStringList;
        TestSuite.assertFalse(isSameObject, "Cloned String List is same object as original");

        // Test with Integer-List
        MyList<Integer> myIntegerList = new MyList<Integer>();
        MyList<Integer> myClonedIntegerList = (MyList<Integer>)myIntegerList.clone();

        isSameObject = myIntegerList == myClonedIntegerList;
        TestSuite.assertFalse(isSameObject, "Cloned Integer List is same object as original");
    }

    /**
     * Test whether x.clone().getClass() == x.getClass()
     */
    public static void testCloneIsSameClass(){
        // Test with String List
        MyList<String> myStringList = new MyList<String>();
        MyList<String> myCloneStringList = (MyList<String>)myStringList.clone();

        boolean isSameClass = myStringList.getClass() == myCloneStringList.getClass();
        TestSuite.assertTrue(isSameClass, "Cloned String List is not of the same class as original");

        // Test with Float List
        MyList<Float> myFloatList = new MyList<Float>();
        MyList<Float> myClonedFloatList = (MyList<Float>)myFloatList.clone();

        isSameClass = myFloatList.getClass() == myClonedFloatList.getClass();
        TestSuite.assertTrue(isSameClass, "Cloned Float List is not of the same class as original");
    }

    /**
     * Test whether x.clone().equals(x)
     */
    public static void testCloneEqualsOriginal(){
        // Test with String List
        MyList<String> myStringList = new MyList<String>();
        MyList<String> myClonedStringList = (MyList<String>)myStringList.clone();

        TestSuite.assertEquals(myStringList, myClonedStringList, "Cloned empty String List does not equal original");

        // Test with String List with added String
        myStringList.add("Hallo");
        myClonedStringList = (MyList<String>)myStringList.clone();

     //   TestSuite.assertEquals(myStringList, myClonedStringList, "Non-empty cloned String List does not equal original");


        // Test with Integer List
        MyList<Integer> myIntegerList = new MyList<Integer>();
        MyList<Integer> myClonedIntegerList = (MyList<Integer>)myIntegerList.clone();

        TestSuite.assertEquals(myIntegerList, myClonedIntegerList, "Cloned empty Integer List does not equal original");
    }

    /*
    Equals needs to be tested as well, because we check if x.clone().equals(x),
    for which equals needs to work!
     */

    /**
     * Test whether two equal lists are asserted as equal
     */
    public static void testEqualLists(){
        MyList<String> myStringList = new MyList<String>();
        MyList<String> mySecondStringList = new MyList<String>();

       TestSuite.assertEquals(myStringList, mySecondStringList, "Two empty (=equal) String Lists not asserted equal");

        myStringList.add("Hallo");
        mySecondStringList.add("Hallo");

        TestSuite.assertEquals(myStringList, mySecondStringList, "Two String Lists with same content not asserted equal");
    }

    /**
     * Test whether two obviously different lists are not equal
     */
    public static void testDifferentListsAreUnequal(){
        MyList<Integer> myIntegerList = new MyList<Integer>();
        myIntegerList.add(-5);
        myIntegerList.add(10000);
        myIntegerList.add(42);

        MyList<Integer> mySecondIntegerList = new MyList<Integer>();
        mySecondIntegerList.add(12);
        mySecondIntegerList.add(1010101);
        mySecondIntegerList.add(21);

        boolean equal = myIntegerList.equals(mySecondIntegerList);

        TestSuite.assertFalse(equal, "Two different Integer Lists are asserted as equal");
    }

    /**
     * Test equals() for reflexivity: x.equals(x)
     */
    public static void testEqualsReflexivity(){
        MyList<String> myStringList = new MyList<String>();
        myStringList.add("Hello ma friendz");

        TestSuite.assertEquals(myStringList, myStringList, "A String List does not equal itself");
    }

    /**
     * Test equals() for symmetry: x.equals(y) == y.equals(x)
     */
    public static void testEqualsSymmetry(){
        MyList<Integer> myIntegerList = new MyList<Integer>();
        MyList<Integer> mySecondIntegerList = new MyList<Integer>();

        boolean xEqualsY = myIntegerList.equals(mySecondIntegerList);
        boolean yEqualsX = mySecondIntegerList.equals(myIntegerList);
        boolean symmetric = xEqualsY == yEqualsX;

        TestSuite.assertTrue(symmetric, "Symmetry does not hold with two empty and therefore equal Integer Lists");

        myIntegerList.add(42);

        xEqualsY = myIntegerList.equals(mySecondIntegerList);
        yEqualsX = mySecondIntegerList.equals(myIntegerList);
        symmetric = xEqualsY == yEqualsX;

        TestSuite.assertTrue(symmetric, "Symmetry does not hold with two unequal Integer Lists");
    }

    /**
     * Test equals() for transitivity: if x.equals(y) and y.equals(z), then x.equals(z)
     */
    public static void testEqualsTransitivity(){
        MyList<String> myStringList = new MyList<String>();
        MyList<String> mySecondStringList = new MyList<String>();
        MyList<String> myThirdStringList = new MyList<String>();

        boolean xEqualsY = myStringList.equals(mySecondStringList);
        boolean yEqualsZ = mySecondStringList.equals(myThirdStringList);
        boolean xEqualsZ = myStringList.equals(myThirdStringList);

        boolean antecedent = xEqualsY && yEqualsZ;
        boolean consequent = xEqualsZ;

        boolean transitive = !antecedent || consequent; // Implication is true if: antecedent is false or consequent is true

        TestSuite.assertTrue(transitive, "Transitivity does not hold with three empty (=equal) String Lists");
    }

    /**
     * Test equals() for consistency - multiple times calling equals() should give same result
     */
    public static void testEqualsConsistency(){
        MyList<String> myStringList = new MyList<String>();
        MyList<String> mySecondStringList = new MyList<String>();

        boolean equal1 = myStringList.equals(mySecondStringList);
        boolean equal2 = myStringList.equals(mySecondStringList);
        boolean equal3 = myStringList.equals(mySecondStringList);

        boolean allEqual = (equal1 == equal2) && (equal2 == equal3);

        TestSuite.assertTrue(allEqual, "equals() not consistent for equal String Lists");

        myStringList.add("Hallo");

        equal1 = myStringList.equals(mySecondStringList);
        equal2 = myStringList.equals(mySecondStringList);
        equal3 = myStringList.equals(mySecondStringList);

        allEqual = (equal1 == equal2) && (equal2 == equal3);

        TestSuite.assertTrue(allEqual, "equals() not consistent for unequal String Lists");
    }

    /**
     * Test whether x.equals(null) == false
     */
    public static void testEqualsNull(){
        MyList<Integer> myIntegerList = new MyList<Integer>();

        boolean equalsNull = myIntegerList.equals(null);

        TestSuite.assertFalse(equalsNull, "Empty Integer List equals null-object");
    }
}
