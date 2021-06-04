package visitor;

/**
 * A visitor with termination condition
 * @param <E>
 */
public class PrintListVisitor2<E> implements Visitor<E>{

    /**
     * Visits the elements of the list it is called on, stopping if one element is 42
     * @param o
     *           the element that has just been visited by
     *           {@link Visitable#accept(Visitor)}
     * @return
     */
    @Override
    public boolean visit(Object o) {
        System.out.println(o);

        // termination condition
        if(o.equals(42)){
            return false;
        }

        return true;
    }
}