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
}