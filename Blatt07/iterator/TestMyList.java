package iterator;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import test.TestSuite;

/**
 * Test class for testing MyListIterator on MyList
 */
public class TestMyList {

    /**
     * Lead through the test process
     * @param args Not used here
     */
    public static void main(String[] args) {
        System.out.println("Start tests");
        startTests();
        System.out.println(TestSuite.getErrors() + " errors found in " + TestSuite.getTests() + " tests.");
    }

    /**
     * Start the tests
     */
    private static void startTests(){
        testIterateThroughAll();
        testDeleteElements();
        testConcurrentModificationException();
        testNoSuchElementException();
    }

    /**
     * Test whether the iterator can iterate through all elements of the list
     * in the correct order
     */
    private static void testIterateThroughAll(){
        MyList<Integer> myList = new MyList<Integer>();
        fillMyList(myList);

        Iterator<Integer> myListIterator = myList.iterator();
        MyList<Integer> mySecondList = new MyList<Integer>();

        // Iterate through the list using the iterator and add all elements to a second list
        while(myListIterator.hasNext()){
            mySecondList.add(myListIterator.next());
            mySecondList.advance();
        }

        // The second list should now be a copy of the first, and therefore equal to it
        TestSuite.assertEquals(myList, mySecondList, "Iterator did not run " +
                "through all elements in the correct order");
    }

    /**
     * Test whether the iterator can correctly delete elements
     */
    private static void testDeleteElements(){
        MyList<Integer> myList = new MyList<Integer>();
        fillMyList(myList);

        MyList<Integer> mySecondList = new MyList<Integer>();
        fillMyList(mySecondList);

        Iterator<Integer> myListIterator = myList.iterator();

        // Removing first element with the iterator
        myListIterator.next();
        myListIterator.remove();

        mySecondList.delete();

        TestSuite.assertEquals(myList, mySecondList, "Iterator did not correctly" +
                "remove the first element");


        // Remove an element a few steps later
        myListIterator.next();
        for(int i=0; i<5; i++){
            myListIterator.next();
            mySecondList.advance();
        }

        myListIterator.remove();
        mySecondList.delete();

        TestSuite.assertEquals(myList, mySecondList, "Iterator did not correctly" +
                "remove an element after iterating a few steps");


        // Remove all elements
        myListIterator = myList.iterator();
        myListIterator.next();
        while(myListIterator.hasNext()){
            myListIterator.remove();
            myListIterator.next();
        }
        myListIterator.remove();

        TestSuite.assertEquals(myList, new MyList<Integer>(), "Iterator did not remove all elements");
    }

    /**
     * Test whether the ConcurrentModificationException is thrown correctly
     */
    private static void testConcurrentModificationException(){
        MyList<Integer> myList = new MyList<Integer>();

        // Test ConcurrentModificationException for: element is added
        Iterator<Integer> myListIterator = myList.iterator();
        myList.add(5);

        boolean exceptionThrown = false;
        try{
            myListIterator.next();
        } catch(ConcurrentModificationException e){
            exceptionThrown = true;
        }

        TestSuite.assertTrue(exceptionThrown, "ConcurrentModificationException" +
                "not thrown when element is added after iterator is created");


        // Test ConcurrentModificationException for: element is removed
        fillMyList(myList);
        myListIterator = myList.iterator();
        myList.delete();

        exceptionThrown = false;
        try{
            myListIterator.next();
        } catch(ConcurrentModificationException e){
            exceptionThrown = true;
        }

        TestSuite.assertTrue(exceptionThrown, "ConcurrentModificationException" +
                "not thrown when element is removed after iterator is created");
    }

    /**
     * Test whether the NoSuchElementException is thrown correctly
     */
    private static void testNoSuchElementException(){
        MyList<Integer> myList = new MyList<Integer>();
        Iterator<Integer> myListIterator = myList.iterator();

        // Test for empty list
        boolean exceptionThrown = false;
        try{
            myListIterator.next();
        } catch(NoSuchElementException e){
            exceptionThrown = true;
        }

        TestSuite.assertTrue(exceptionThrown, "NoSuchElementException" +
                "not thrown when trying to go to next element in empty list");

        // Test for non-empty list
        myList.add(4);
        myListIterator = myList.iterator();
        myListIterator.next();
        exceptionThrown = false;

        try{
            myListIterator.next();
        } catch(NoSuchElementException e){
            exceptionThrown = true;
        }

        TestSuite.assertTrue(exceptionThrown, "NoSuchElementException" +
                "not thrown when trying to go to next element at last position of non-empty list");
    }




    /**
     * Helper-method.
     * Fills the given list with values from 0 to 20, in ascending order
     * @param myList the list to add the values to
     */
    private static void fillMyList(MyList<Integer> myList){
        for (int i=0; i<=20; i++){
            myList.add(i);
            myList.advance();
        }
        myList.reset();
    }

}

