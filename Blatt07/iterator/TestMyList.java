package iterator;

import java.util.Iterator;

public class TestMyList {
    public static void main(String[] args) {
        MyList<Integer> mylist = new MyList<Integer>();
        mylist.add(1);
        mylist.add(2);
        mylist.add(3);
        Iterator<Integer> iter = mylist.iterator();
        iter.next(); // Wichtig, da man sonst etwas VOR dem ersten Element löschen würde. Sollte natürlich nicht sein
        iter.remove();

        // Sollte 2 1 ausgeben, da die Elemente VOR der aktuellen Position eingefügt werden.
        // Find ich zwar irgendwie verwirrend, ist aber in der Musterimplementierung so vorgegeben.
        for (Integer i : mylist) {
            System.out.println(i);
        }
    }
}
