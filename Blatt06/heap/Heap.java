package heap;

import java.util.Arrays;
import java.util.Comparator;


/**
 * Implements a typesafe min-heap.
 *
 * @author Per Starke
 */
public class Heap<T extends Comparable<T>> {

    /**
     * The current capacity for how many Objects can be stored in the Heap. Will automatically be updated, if needed.
     */
    private int capacity = 21;

    /**
     * The current size of the Heap.
     */
    private int size = 0;

    /**
     * The array that represents the Heap.
     */
    Object itemArray[] = new Object[capacity];

    /**
     * Did the heap get a comparator?
     */
    private boolean hasComparator = false;

    /**
     * The given java.util.Comparator. If null, use compareTo of comparable interface
     */
    private Comparator comparator = null;

    /**
     * Default constructor
     */
    public Heap(){

    }

    /**
     * Constructor with given comparator
     * @param comparator the given comparator
     */
    public Heap(Comparator comparator){
        this.comparator = comparator;
    }




    /**
     * Get the index of the left child.
     * @param indexParent the index of the parent who's left child we are looking for.
     * @return index of left child, as Integer.
     */
    private int getIndexLeftChild(int indexParent){
        return (2 * indexParent) + 1;
    }

    /**
     * Get the index of the right child.
     * @param indexParent the parents index.
     * @return the right childs index, as Integer.
     */
    private int getIndexRightChild(int indexParent){
        return (2* indexParent) + 2;
    }

    /**
     * Get the index of the parent.
     * @param indexChild the childs index.
     * @return the parents index, as Integer.
     */
    private int getIndexParent(int indexChild){
        return (indexChild - 1) / 2;
    }

    /**
     * Is there a left child at the given index?
     * @param index the given index.
     * @return True if there is a left child, false otherwise.
     */
    private boolean hasLeftChild(int index){
        return getIndexLeftChild(index) < size;
    }

    /**
     * Is there a right child at the given index?
     * @param index the given index.
     * @return True if there is a right child, false otherwise.
     */
    private boolean hasRightChild(int index){
        return getIndexRightChild(index) < size;
    }

    /**
     * Is there a parent at the given index?
     * @param index the given index.
     * @return True if there is a parent, false otherwise.
     */
    private boolean hasParent(int index){
        return getIndexParent(index) >= 0;
    }

    /**
     * Get the left child of the given index.
     * @param index the given index.
     * @return The left child, of type T.
     */
    private T leftChild(int index){
        return (T) itemArray[getIndexLeftChild(index)];
    }

    /**
     * Get the right child of the given index.
     * @param index the given index.
     * @return The right child, of type T.
     */
    private T rightChild(int index){
        return (T) itemArray[getIndexRightChild(index)];
    }

    /**
     * Get the parent of the given index.
     * @param index the given index.
     * @return The parent, of type T.
     */
    private T parent(int index){
        return (T) itemArray[getIndexParent(index)];
    }

    /**
     * Swap the positions of two items in the Heap at two given indices.
     * @param index1 the first item.
     * @param index2 the second item.
     */
    private void swap(int index1, int index2){
        T buffer = (T) itemArray[index1];
        itemArray[index1] = itemArray[index2];
        itemArray[index2] = buffer;
    }

    /**
     * Check if there is still capacity left to add new items.
     * If not, double the capacity.
     */
    private void assureCapacity(){
        if (size == capacity) {
            itemArray = Arrays.copyOf(itemArray, capacity*2);
            capacity *= 2;
        }
    }


    /**
     * Get the first and therefore smallest element of the Heap.
     * Throws an exception if the Heap is empty.
     * @return the first element of the Heap, of Type T.
     */
    public T getFirstElememt(){
        if(size == 0){
            throw new IllegalStateException("There is no first element, because the Heap is empty!");
        }
        return (T) itemArray[0];
    }

    /**
     * Get the first and therefore smallest element of the Heap, and then remove it.
     * Throws an exception if the Heap is empty.
     * @return the first element of the Heap, of Type T.
     */
    public T getAndRemoveFirstElement(){
        if(size == 0){
            throw new IllegalStateException("There is no first element, because the Heap is empty!");
        }
        T elem = (T) itemArray[0];
        itemArray[0] = itemArray[size-1];
        itemArray[size-1] = null;
        size--;
        heapifyRemove();
        return elem;
    }

    /**
     * Adds a new element to the Heap
     * @param elem the element to add to the heap
     */
    public void add(T elem){
        assureCapacity();
        itemArray[size] = elem;
        size++;
        heapifyAdd();
    }

    /**
     * Repair the Heap after removing an object, so that the Heap-rules are obeyed.
     */
    public void heapifyRemove(){
        int index = 0;
        while(hasLeftChild(index)){
            int indexOfSmallerChild = getIndexLeftChild(index);
            if (hasRightChild(index) && myCompare(rightChild(index), leftChild(index)) < 0){
                indexOfSmallerChild = getIndexRightChild(index);
            }

            T elem = (T) itemArray[index];
            T smallerChild = (T) itemArray[indexOfSmallerChild];
            if(myCompare(elem, smallerChild) > 0){
                swap(index, indexOfSmallerChild);
            }
            index = indexOfSmallerChild;
        }
    }

    /**
     * Repair the Heap after adding an object, so that the Heap-rules are obeyed
     */
    public void heapifyAdd(){
        int index = size-1;
        while(hasParent(index) && myCompare(parent(index),(T)itemArray[index]) > 0){
            swap(getIndexParent(index), index);
            index = getIndexParent(index);
        }
    }


    /**
     * Compare two elements in the Heap.
     * Use comparator, if one was provided during construction, else use compareTo of the elements
     * @param elemOne the first element
     * @param elemTwo the second element
     * @return the result of the comparator, or: number < 0 if elemOne is smaller, 0 if both are equal, a number > 0 otherwise
     */
    private int myCompare(T elemOne, T elemTwo){
        if (this.hasComparator){ // Comparator is given
            return comparator.compare(elemOne, elemTwo);
        }
        else{ // Elements are comparable
            return elemOne.compareTo(elemTwo);
        }
    }



    public void printHeap(){
        for(Object elem: itemArray){
            if (elem != null){
                System.out.println(elem);
            }
        }
    }

}
