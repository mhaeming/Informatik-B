package genericList;


/**
 * A simple linked list. One may go through this list by {@link #advance()} until
 * the last position ({@link #endpos()}) is reached. One also may
 * {@link #delete()} and {@link #add(T)} elements. After advancing it is
 * possible to go back to the beginning by {@link #reset()}.
 * 
 * @author Maximilian Häming, based on work by Mathias Menninghaus (mathias.menninghaus@uos.de)
 * 
 */
public class MyList<T> implements Cloneable{
      /**
    * Reference on the first Entry of this List
    */
   private GenericEntry<T> begin;
   /**
    * References before the actual Entry of this List
    */
   private GenericEntry<T> pos;

   /**
    * Create a new empty List.
    */
   public MyList() {
      pos = begin = new GenericEntry<T>();
   }

   /**
    * Determines if this List is empty or not.
    * 
    * @return <code>true</code>, if there are no elements in this List
    */
   public boolean empty() {
      return begin.next == null;
   }

   /**
    * Determines if it is possible to {@link #advance()} in this List. Returns
    * <code>true</code> if the last position of this List has been reached. An
    * {@link #empty()} List will alway deliver <code>true</code>
    * 
    * @return <code>true</code> if the last Entry in this List already has been
    *         reached.
    */
   public boolean endpos() { // true, wenn am Ende
      return pos.next == null;
   }

   /**
    * Returns to the beginning of this List.
    */
   public void reset() {
      pos = begin;
   }

   /**
    * Advances one step in this List.
    * 
    * @throws RuntimeExcpetion
    *            if the last Entry of this List already has been reached.
    */
   public void advance() {
      if (endpos()) {
         throw new RuntimeException("Already at the end of this List");
      }
      pos = pos.next;
   }

   /**
    * Returns the actual element of this List.
    * 
    * @return the actual element
    * 
    * @throws RuntimeException
    *            if the last Entry of this List already has been reached.
    */
   public T elem() {
      if (endpos()) {
         throw new RuntimeException("Already at the end of this List");
      }
      return pos.next.o;
   }

   /**
    * Inserts <code>o</code> in this List. It will be placed before the actual
    * element. After insertion the inserted element will become the actual
    * element.
    * 
    * @param x
    *           the element to be inserted
    */
   public void add(T x) {
      GenericEntry<T> newone = new GenericEntry<T>(x, pos.next);

      pos.next = newone;
   }

   /**
    * Deletes the actual element of this List. The element after the actual
    * element will become the new actual element.
    * 
    * @throws RuntimeExcpetion
    *            if the last Entry of this List already has been reached.
    */
   public void delete() {
      if (endpos()) {
         throw new RuntimeException("Already at the end of this List");
      }
      pos.next = pos.next.next;
   }

   @Override
   protected Object clone() {
      try {
         Object cloneObject = super.clone();
         return cloneObject;
      } catch (CloneNotSupportedException e) {
         e.printStackTrace();
         return null;
      }
   }


   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      // Supressing the warning in this case
      @SuppressWarnings("unchecked") MyList<T> other = (MyList<T>) obj;
      if (!begin.equals(other.begin))
         return false;
      while (!endpos()) {
         advance();
         other.advance();
         if (!elem().equals(other.elem())) {
            return false;
         }
      }
      return true;
   }

   @Override
   public int hashCode() {
      int result = 1;
      while(!endpos()) {
         result *= elem().hashCode();
         advance();
      }
      return result;
   }
}
