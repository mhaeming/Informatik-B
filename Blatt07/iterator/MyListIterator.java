package iterator;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyListIterator<E> implements Iterator<E> {
    private MyList<E> list;
    private MyEntry<E> penultimate;
    private MyEntry<E> last;
    private MyEntry<E> next;
    private int expModCount;


    public MyListIterator(MyList<E> list, MyEntry<E> begin) {
        this.list = list;
        this.last = begin;
        this.next = begin.next;
        this.expModCount = list.getModCount();
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

    @Override
    public E next() {
        if (this.expModCount != list.getModCount()) {
            throw new ConcurrentModificationException();
        }

        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        penultimate = last;
        last = next;
        next = next.next;

        return last.o;
    }

    @Override
    public void remove() {
        if (this.expModCount != list.getModCount()) {
            throw new ConcurrentModificationException();
        }

        list.increaseModCount();

        // Same behavior as for the MyList modCount
        if (expModCount == Integer.MAX_VALUE) {
            expModCount = 0;
        } else {
            expModCount++;
        }
        

        // Remove an element by reassigning the references to exclude that element
        penultimate.next = next;
        last = penultimate;
        penultimate = null;

    }

}
