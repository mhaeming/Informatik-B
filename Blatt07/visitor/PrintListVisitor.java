package visitor;

/**
 * A visitor that visits all elements of the list
 * @param <E>
 */
public class PrintListVisitor<E> implements Visitor<E>{

    /**
     * Visits all the elements of the list it is called on
     * @param o
     *           the element that has just been visited by
     *           {@link Visitable#accept(Visitor)}
     * @return
     */
    @Override
    public boolean visit(Object o) {
        System.out.println(o);

        return true;
    }
}
