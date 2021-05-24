package genericList;


/**
 * Bitte löschen, wenn es die Testklasse gibt!
 */
public class Main {
    public static void main(String[] args) {
        MyList<Integer> origial = new MyList<Integer>();
        origial.add(5);

        @SuppressWarnings("unchecked") MyList<Integer> klon = (MyList<Integer>) origial.clone();

        assert(origial != klon);
        assert(origial.equals(klon));

    }
}
