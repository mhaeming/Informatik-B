/**
 * A class for testing the arena
 */
public class TestArena {

    /**
     * amount of found errors
     */
    private int errors;

    /**
     * the main method
     * @param args not used here
     */
    public static void main(String[] args){
        System.out.println("Start tests");
        TestArena tester = new TestArena();
        tester.startTests();
        System.out.println(tester.errors + " Errors found");

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
    }
    /**
     * Is called in the separate tests to see if an error was found, and if yes print that and add it to the number of errors
     * @param correctArea The area that would be correct
     * @param x,y the position of the tribute
     */
    public void assertVals(int correctArea, double x, double y){
        Arena arena = new Arena();
        int area = arena.getArea(x,y);
        if(correctArea != area){
            errors++;
            System.out.println("Error found, was supposed to be Area " + correctArea + " with coordinates " + x + "|" + y + ", but was calculated as Area " + area);
        }
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

    /**
     * test positions in area 7
     */

    /**
     * test positions in area 8
     */

    /**
     * test positions in area 9
     */

    /**
     * test positions in area 10
     */

    /**
     * test positions in area 11
     */

    /**
     * test positions in area 12
     */

    /**
     * test positions outside of the arena
     */
}
