package genericList;

/**
 * An Entry holds an Object <code>o</code> and a reference <code>next</code> to
 * the next Entry such that a linked List of Entry elements is generated.
 * 
 * @author Maximilian Häming, based on work by Mathias Menninghaus (mathias.menninghaus@uos.de)
 */
class GenericEntry<T> {

   T o;
   GenericEntry<T> next;

   GenericEntry() {
      this(null, null);
   }

   GenericEntry(T o) {
      this(o, null);
   }

   GenericEntry(T o, GenericEntry<T> e) {
      this.o = o;
      this.next = e;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      @SuppressWarnings("unchecked") GenericEntry<T> other = (GenericEntry<T>) obj;
      if (o == null) {
         if (other.o != null)
            return false;
      } else if (!o.equals(other.o))
         return false;
      if (next == null) {
         if (other.next != null)
            return false;
      } else if (!next.equals(other.next))
         return false;

      return true;
   }

   @Override
   public int hashCode() {
      int prime = 13;
      int result = 1;
      result *= prime + o.hashCode();
      result *= prime + next.hashCode();
      return result;
   }
}