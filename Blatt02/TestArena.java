/**
 * A class for testing the arena
 */
public class TestArena {

    /**
     * the main method
     * @param args not used here
     */
    public static void main(String[] args){
        System.out.println("Start tests");
        TestArena tester = new TestArena();
        tester.startTests();
        System.out.println(TestSuite.getErrors() + " Errors found at " + TestSuite.getTests() + " tests.");

    }
    /**
     * Starts the separate tests
     */
    public void startTests(){
        testArea1();
        testArea2();
        testArea3();
        testArea4();
        testArea5();
        testArea6();
        testArea7();
        testArea8();
        testArea9();
        testArea10();
        testArea11();
        testArea12();
        testOutside();
    }
    /**
     * Is called in the separate tests to see if an error was found, and if yes print that and add it to the number of errors
     * @param correctArea The area that would be correct
     * @param x,y the position of the tribute
     */
    public void assertVals(int correctArea, double x, double y){
        TestSuite.countTest();
        Arena arena = new Arena();
        int area = arena.getArea(x,y);
        String errMsg = "Was supposed to be Area " + correctArea + " with coordinates " + x + "|" + y + ", but was calculated as Area " + area;
        TestSuite.assertEquals(correctArea, area, errMsg);
    }




    /**
     * test positions in area 1
     */
    public void testArea1(){
        // Position 0|0, special case because edge case, I assigned this to area 1
        assertVals(1, 0, 0);

        assertVals(1, 0.2, 1);
    }

    /**
     * test positions in area 2
     */
    public void testArea2(){
        assertVals(2, 0.6, 0.6);
    }

    /**
     * test positions in area 3
     */
    public void testArea3(){
        assertVals(3, 1.3, 0.2);
        assertVals(3, 1, 0);
    }

    /**
     * test positions in area 4
     */
    public void testArea4(){
        assertVals(4, 1.3, -0.2);
    }
    /**
     * test positions in area 5
     */
    public  void testArea5(){
        assertVals(5, 0.6, -0.6);
    }

    /**
     * test positions in area 6
     */
    public void testArea6(){
        assertVals(6, 0, -1);
        assertVals(6, 0.2, -1.1);
    }

    /**
     * test positions in area 7
     */
    public void testArea7(){
        assertVals(7, -0.2, -1);
    }

    /**
     * test positions in area 8
     */
    public void testArea8(){
        assertVals(8, -0.6, -0.6);
    }

    /**
     * test positions in area 9
     */
    public void testArea9(){
        assertVals(9, -1.3, -0.2);
    }

    /**
     * test positions in area 10
     */
    public void testArea10(){
        assertVals(10, -1.3, 0.2);
        assertVals(10, -1, 0);
    }

    /**
     * test positions in area 11
     */
    public void testArea11(){
        assertVals(11, -0.6, 0.6);
    }

    /**
     * test positions in area 12
     */
    public void testArea12(){
        assertVals(12, -0.1, 1);
    }

    /**
     * test positions outside of the arena
     */
    public void testOutside(){
        assertVals(-1, 1, 2);
        assertVals(-1, -1, 2);
        assertVals(-1, 2, -0.1);
        assertVals(-1, -2, -2);
    }
}
