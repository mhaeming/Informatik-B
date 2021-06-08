package ioarray;

import test.TestSuite;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Test class for the persistent filearray
 */
public class TestFilearray {

    /**
     * An integer-array, can be used to initialize the filearray with
     */
    private static final int[] INTS = {1,2,3,4,5,6,7,8,9};

    /**
     * Lead through the tests
     * @param args not used here
     */
    public static void main(String[] args){
        System.out.println("Start tests");
        startTests();
        System.out.println(TestSuite.getErrors() + " errors found in " + TestSuite.getTests() + " tests.");
    }

    /**
     * Start all tests
     */
    private static void startTests(){
        testCreateNew();
        testGet();
        testSet();
        testArrayFromExistingFile();
        testGetLength();
        testClose();
    }

    /**
     * Tests creating a new filearray and checks if the file exists
     */
    private static void testCreateNew(){
        try (Filearray fa = new Filearray("Test", INTS)){
            // Check if file is created
            File f = new File(fa.getName() + ".txt");
            TestSuite.assertTrue(f.exists(), "There exists no file for the filearray");
        } catch (IOException e){}
    }

    /**
     * Tests getting values from the filearray
     */
    private static void testGet(){
        try (Filearray fa = new Filearray("Test", INTS)){
            // Check if correct integers are returned when using get()
            TestSuite.assertEquals(1, fa.get(0), "Wrong element returned for first position");
            TestSuite.assertEquals(5, fa.get(4), "Wrong element returned for fifths position");

            // Check if correctly exception is thrown when accessing index out of bound
            boolean exceptionThrown = false;
            try{
                fa.get(100);
            } catch(ArrayIndexOutOfBoundsException e){
                exceptionThrown = true;
            } finally{
                TestSuite.assertTrue(exceptionThrown, "No exception thrown for get() with index too large");
            }

            exceptionThrown = false;
            try{
                fa.get(-1);
            } catch(ArrayIndexOutOfBoundsException e){
                exceptionThrown = true;
            } finally{
                TestSuite.assertTrue(exceptionThrown, "No exception thrown for get() with index <0");
            }

        } catch (IOException e){}
    }

    /**
     * Test setting values in the filearray
     */
    private static void testSet(){
        try (Filearray fa = new Filearray("Test", INTS)){
            // Check if integers are correct set to a new value at the correct position
            fa.set(0, 5);
            TestSuite.assertEquals(5, fa.get(0), "Wrong element at first position " +
                    "after setting first position to a new value");
            fa.set(5, 4);
            TestSuite.assertEquals(4, fa.get(5), "Wrong element at sixth position " +
                    "after setting sixth position to a new value");

            // Check if correctly exception is thrown when accessing index out of bound
            boolean exceptionThrown = false;
            try{
                fa.set(100, 0);
            } catch(ArrayIndexOutOfBoundsException e){
                exceptionThrown = true;
            } finally{
                TestSuite.assertTrue(exceptionThrown, "No exception thrown for set() with index too large");
            }

            exceptionThrown = false;
            try{
                fa.set(-1, 0);
            } catch(ArrayIndexOutOfBoundsException e){
                exceptionThrown = true;
            } finally{
                TestSuite.assertTrue(exceptionThrown, "No exception thrown for set() with index <0");
            }

        } catch (IOException e){}
    }

    /**
     * Test accessing an existing filearray with the filename
     */
    private static void testArrayFromExistingFile(){
        try (Filearray fa = new Filearray("Test", INTS)){

            try (Filearray fa2 = new Filearray("Test")){
                boolean equal = Arrays.equals(fa.data(), fa2.data());
                TestSuite.assertTrue(equal, "Access to existing filearray did not yield " +
                        "the same array as accessing the originally creating one");
            } catch (IOException e) {}

        } catch (IOException e){}
    }

    /**
     * Test getting the array length
     */
    private static void testGetLength(){
        try (Filearray fa = new Filearray("Test", INTS)){
            // Check if length is correct
            TestSuite.assertEquals(9, fa.getLength(), "Incorrect length returned for array " +
                    "of size 9");
        } catch (IOException e){}
    }

    /**
     * Test whether the file is closed after closing it.
     */
    private static void testClose(){
        try (Filearray fa = new Filearray("Test", INTS)){
            TestSuite.assertFalse(fa.isClosed(), "File is closed before closing it");
            fa.close();
            TestSuite.assertTrue(fa.isClosed(), "file is not closed after closing it");
        } catch (IOException e){}
    }

}
