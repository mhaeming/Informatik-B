package visitor;

public class PrintListVisitor<E> implements Visitor<E>{

    @Override
    public boolean visit(Object o) {
        System.out.println(o);

        // Optional: termination condition (without this, all elements are visited
        if(o.equals(42)){
            return false;
        }

        return true;
    }
}
