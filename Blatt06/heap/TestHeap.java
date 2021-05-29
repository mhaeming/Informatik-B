package heap;

import util.TestSuite;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Test class for the Heap
 * @author Per Starke
 */
public class TestHeap {

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
        testAdd();
        testGetFirstElemet();
        testGetAndRemoveFirstElement();
        testComparator();
        //testMitMaddad();
    }

    private static void testComparator(){
        Heap<Integer> heap = new Heap<Integer>(new MyIntComparator());
        initalizeMyHeapWithSomeValues(heap);
        int number = heap.getAndRemoveFirstElement();
        heap.printHeap();

    }

    private static void testMitMaddad() {
        Heap<Integer> heap = new Heap<Integer>();
        initalizeMyHeapWithSomeValues(heap);
        int number = heap.getAndRemoveFirstElement();
    }


    /**
     * Test whether after adding a few elements, the Heap correctly obeyes the Heap-rules
     */
    public static void testAdd(){
        Heap<Integer> heap = new Heap<Integer>();

        heap.add(5);
        Object[] correctArray = new Object[21];
        correctArray[0] = 5;
        boolean equal = Arrays.equals(heap.itemArray, correctArray);
        TestSuite.assertTrue(equal, "Heap with item '5' does not equal correct array");

        heap.add(1);
        correctArray[0] = 1;
        correctArray[1] = 5;
        equal = Arrays.equals(heap.itemArray, correctArray);
        TestSuite.assertTrue(equal, "Heap with items '1' and '5' does not equal correct array");

        heap.add(21);
        correctArray[2] = 21;
        equal = Arrays.equals(heap.itemArray, correctArray);
        TestSuite.assertTrue(equal, "Heap with items '1', '5' and '21' does not equal correct array");
    }

    /**
     * Test whether after adding a few elements, the Heap correctly returns the first element
     */
    public static void testGetFirstElemet(){
        Heap<Integer> heap = new Heap<Integer>();

        heap.add(42);
        int firstElem = heap.getFirstElememt();
        String err = ("The first element should be 42, but is " + firstElem);
        TestSuite.assertEquals(firstElem, 42, err);

        heap.add(3);
        firstElem = heap.getFirstElememt();
        err = ("The first element should be 3, but is " + firstElem);
        TestSuite.assertEquals(firstElem, 3, err);

        heap.add(21);
        firstElem = heap.getFirstElememt();
        err = ("The first element should be 3, but is " + firstElem);
        TestSuite.assertEquals(firstElem, 3, err);
    }

    /**
     * Test whether after adding a few elements, the Heap correctly returns and removes the first element
     */
    public static void testGetAndRemoveFirstElement(){
        Heap<Integer> heap = new Heap<Integer>();

        heap.add(16);
        heap.add(42);
        heap.add(21);
        heap.add(59);
        heap.add(1);

        int firstElem = heap.getAndRemoveFirstElement();
        String err = ("After getting and removing first element, the first element should be 1, but is " + firstElem);
        TestSuite.assertEquals(firstElem, 1, err);

        Object[] correctArray = new Object[21];
        correctArray[0] = 16;
        correctArray[1] = 42;
        correctArray[2] = 21;
        correctArray[3] = 59;

        err = "After getting and removing the first element, the array should be [16, 42, 21, 59]";
        boolean equal = Arrays.equals(heap.itemArray, correctArray);
        TestSuite.assertTrue(equal, err);

        firstElem = heap.getAndRemoveFirstElement();
        err = ("After getting and removing the first element a second time, the first elem should be 16, but is " + firstElem);
        TestSuite.assertEquals(firstElem, 16, err);

        correctArray[0] = 21;
        correctArray[1] = 42;
        correctArray[2] = 59;
        correctArray[3] = null;

        err = "After getting and removing the first element a second time, the array should be [21, 42, 59]";
        equal = Arrays.equals(heap.itemArray, correctArray);
        TestSuite.assertTrue(equal, err);

    }

    private static void initalizeMyHeapWithSomeValues(Heap<Integer> heap) {
        heap.add(10);
        heap.add(2);
        heap.add(7);
        heap.add(9);
        heap.add(1);
        heap.add(4);
        heap.add(5);
        heap.add(12);
        heap.add(8);
    }
}
