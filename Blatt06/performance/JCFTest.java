package performance;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;

public class JCFTest {
    public static void main(String[] args) {
        // Number of tests
        int tests = 1000;

        // How many objects should be put in the structure?
        int size = 10000;


        /**
         * Feel free to test as many objects as you like
         * Keep in mind that only Collection or Collection interface conform objects will work
         * 
         * How to add a collection:
         * 1. Instantiate the Collection
         * 2. Create a new TimerObject and give it the appropriate Collection, you can leave the test and size as variables
         * 3. Output via System.out.println(<TimerObject>.timeAll());
         * 
         * Depending on the number of tests and objects grab a coffee or tea unless running on a supercomputer.
         */


        // ArrayList
        ArrayList al = new ArrayList<>();
        TimerObject alTime = new TimerObject(al, tests, size);

        // LinkedList
        LinkedList ll = new LinkedList<>();
        TimerObject llTime = new TimerObject(ll, tests, size);

        // HashSet
        HashSet hs = new HashSet<>();
        TimerObject hsTime = new TimerObject(hs, tests, size);


        System.out.println();
        System.out.println("Performed " + tests + " tests with " + size + " objects.");
        System.out.println("-----------------------------------");
        System.out.println(alTime.timeAll());
        System.out.println(llTime.timeAll());
        System.out.println(hsTime.timeAll());
    }



}
