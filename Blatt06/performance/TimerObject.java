package performance;

import java.util.Collection;

public class TimerObject {
    private int tests;
    private int size;

    private Object[] objs;
    private Collection colobj;

    public TimerObject(Collection colobj, int tests, int size) {
        this.colobj = colobj;
        this.tests = tests;
        this.size = size;

        // Create a pool of objects to test add functionality
        objs = new Object[size];
        for (int i = 0; i < size; i++) {
            objs[i] = new Object();
        }

    }

    /**
     * 
     * @param tests How many times should the test be performed?
     */
    public String timeAll() {
        long addTimes = 0;
        long containTimes = 0;
        long removeTimes = 0;


        for (int i = 0; i < tests; i++) {
            addTimes += timeAdd();
            containTimes += timeContains();
            removeTimes += timeRemoves();
        }

        String result = colobj.getClass() + " | Ø add(): " + (addTimes / tests) + "ns | Ø contains(): " + (containTimes / tests) + "ns | Ø remove(): " + (removeTimes / tests) + "ns";
        return result;

    }

    public long timeAdd() {
        long start = System.nanoTime();

        for (int i = 0; i < size; i++) {
            colobj.add(objs[i]);
        }

        long end = System.nanoTime();

        return (end - start) / tests;
    }

    public long timeContains() {
        long start = System.nanoTime();

        for (int i = 0; i < size; i++) {
            colobj.contains(objs[i]);
        }

        long end = System.nanoTime();

        return (end - start) / tests;
    }

    public long timeRemoves() {
        long start = System.nanoTime();

        for (int i = 0; i < size; i++) {
            colobj.remove(objs[i]);
        }

        long end = System.nanoTime();

        return (end - start) / tests;
    }

}
