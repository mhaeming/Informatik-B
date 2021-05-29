package heap;
import java.util.Comparator;

/**
 * Simple Integer Comparator to test whether heap can be sorted with a given Comparator
 */
public class MyIntComparator implements Comparator<Integer>{

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }
}
