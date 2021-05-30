package heap;

import util.MyIntComparator;
import util.Student;
import util.StudentComparator;
import util.TestSuite;
import java.util.Arrays;

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
        testCreateIntComparator();
        testRemoveFromIntComparator();
        testAssureCapacity();
        testStudentHeap();
        testStudentHeapComparator();
    }

    /**
     * Test if the assure capacity method works if many items are added to a heap
     */
    private static void testAssureCapacity(){
        Heap<Integer> heap = new Heap<Integer>();
        for (int i = 0; i<40; i++){
            heap.add(i);
        }

        TestSuite.assertEquals(heap.getCapacity(), 42, "Capacity should be 42 but is " +
                heap.getCapacity());

        for (int i=0; i<1000; i++){
            heap.add(i);
        }

        TestSuite.assertEquals(heap.getCapacity(), 1344, "Capacity should be 1344 but is " +
                heap.getCapacity());
    }

    /**
     * Test whether after adding a few elements, the Heap correctly obeyes the Heap-rules
     */
    private static void testAdd(){
        Heap<Integer> heap = new Heap<Integer>();

        heap.add(5);
        Object[] correctArray = new Object[21];
        correctArray[0] = 5;
        boolean equal = Arrays.equals(heap.getItemArray(), correctArray);
        TestSuite.assertTrue(equal, "Heap with item '5' does not equal correct array");

        heap.add(1);
        correctArray[0] = 1;
        correctArray[1] = 5;
        equal = Arrays.equals(heap.getItemArray(), correctArray);
        TestSuite.assertTrue(equal, "Heap with items '1' and '5' does not equal correct array");

        heap.add(21);
        correctArray[2] = 21;
        equal = Arrays.equals(heap.getItemArray(), correctArray);
        TestSuite.assertTrue(equal, "Heap with items '1', '5' and '21' does not equal correct array");
    }

    /**
     * Test whether after adding a few elements, the Heap correctly returns the first element
     */
    private static void testGetFirstElemet(){
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
    private static void testGetAndRemoveFirstElement(){
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
        boolean equal = Arrays.equals(heap.getItemArray(), correctArray);
        TestSuite.assertTrue(equal, err);

        firstElem = heap.getAndRemoveFirstElement();
        err = ("After getting and removing the first element a second time, the first elem should be 16, but is " + firstElem);
        TestSuite.assertEquals(firstElem, 16, err);

        correctArray[0] = 21;
        correctArray[1] = 42;
        correctArray[2] = 59;
        correctArray[3] = null;

        err = "After getting and removing the first element a second time, the array should be [21, 42, 59]";
        equal = Arrays.equals(heap.getItemArray(), correctArray);
        TestSuite.assertTrue(equal, err);

    }


    /**
     * Test creating the heap with a given comparator for Integers
     */
    private static void testCreateIntComparator(){
        Heap<Integer> heap = new Heap<Integer>(new MyIntComparator());
        Object[] correctArray = initalizeMyHeapWithSomeValues(heap);


        boolean equal = Arrays.equals(heap.getItemArray(), correctArray);
        TestSuite.assertTrue(equal, "Integer heap with comparator is not correctly ordered after adding elements");
    }

    /**
     * Test removing from a heap with a given Integer comparator
     */
    private static void testRemoveFromIntComparator(){
        Heap<Integer> heap = new Heap<Integer>(new MyIntComparator());
        Object[] correctArray = initalizeMyHeapWithSomeValues(heap);

        int number = heap.getAndRemoveFirstElement();
        number = heap.getAndRemoveFirstElement();

        correctArray[0] = 4;
        correctArray[1] = 8;
        correctArray[2] = 5;
        correctArray[3] = 10;
        correctArray[4] = 9;
        correctArray[5] = 7;
        correctArray[6] = 12;
        correctArray[7] = null;
        correctArray[8] = null;

        boolean equal = Arrays.equals(heap.getItemArray(), correctArray);
        TestSuite.assertTrue(equal, "Integer heap with comparator is not correctly ordered after removing elements");
    }

    /**
     * Quick-test the heap with students, without comparator
     */
    private static void testStudentHeap(){
        Heap<Student> heap = new Heap<Student>();

        // Test adding students
        Student stud1 = new Student(1, "Per", "Starke");
        Student stud2 = new Student(2, "Gernot", "Starke");
        Student stud3 = new Student(3, "Maximilian", "Häming");

        heap.add(stud1);
        heap.add(stud2);
        heap.add(stud3);

        Student[] correctArray = new Student[21];
        correctArray[0] = stud1;
        correctArray[1] = stud2;
        correctArray[2] = stud3;

        boolean equal = Arrays.equals(heap.getItemArray(), correctArray);
        TestSuite.assertTrue(equal, "Student heap without comp. after adding three students not in " +
                "correct order");

        // Test removing one
        heap.add(new Student(0, "Kevin", "Schmidt"));
        Student first = heap.getAndRemoveFirstElement();
        equal =  Arrays.equals(heap.getItemArray(), correctArray);
        TestSuite.assertTrue(equal, "Student heap without comp. after adding 4 and removing 1 student not " +
                "in correct order");

        // Test getting the first
        first = heap.getFirstElememt();
        TestSuite.assertEquals(stud1, first, "First student in heap without comp. is not the correct first one");
    }

    /**
     * Quick-test the heap with students, with comparator
     */
    private static void testStudentHeapComparator(){
        StudentComparator studComp = new StudentComparator();
        Heap<Student> heap = new Heap<Student>(studComp);

        // Test adding students
        Student stud1 = new Student(1, "Per", "Starke");
        Student stud2 = new Student(2, "Gernot", "Starke");
        Student stud3 = new Student(3, "Maximilian", "Häming");

        heap.add(stud1);
        heap.add(stud2);
        heap.add(stud3);

        Student[] correctArray = new Student[21];
        correctArray[0] = stud3;
        correctArray[1] = stud1;
        correctArray[2] = stud2;

        boolean equal = Arrays.equals(heap.getItemArray(), correctArray);
        TestSuite.assertTrue(equal, "Student heap with comp. after adding three students not in " +
                "correct order");

        // Test removing one
        heap.add(new Student(0, "Kevin", "Arson"));
        Student first = heap.getAndRemoveFirstElement();
        equal =  Arrays.equals(heap.getItemArray(), correctArray);
        TestSuite.assertTrue(equal, "Student heap comp. after adding 4 and removing 1 student not " +
                "in correct order");

        // Test getting the first
        first = heap.getFirstElememt();
        TestSuite.assertEquals(stud3, first, "First student in heap with comp. is not the correct first one");
    }






    /**
     * Helper-method: Adds a few values to the given heap and returns an array in the correct ordering.
     * @param heap the heap that the values should be added to
     * @return an array of int, in the correct ordering that the heap-array should also have
     */
    private static Object[] initalizeMyHeapWithSomeValues(Heap<Integer> heap) {
        heap.add(10);
        heap.add(2);
        heap.add(7);
        heap.add(9);
        heap.add(1);
        heap.add(4);
        heap.add(5);
        heap.add(12);
        heap.add(8);

        Object[] array = new Object[21];
        array[0] = 1;
        array[1] = 2;
        array[2] = 4;
        array[3] = 8;
        array[4] = 9;
        array[5] = 7;
        array[6] = 5;
        array[7] = 12;
        array[8] = 10;

        return array;
    }
}
