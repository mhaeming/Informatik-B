package library;
import util.List;

public class Library {

/*    public static void main(String[] args){
        Library library = new Library();
        Book book = new Book("Der Hering", "Per Starke");
        BluRay bluRay = new BluRay("Hering - der Film", "Max");
        library.addItem(book);
        library.addItem(bluRay);
        List list = library.search("Hering");
        list.reset();
        LibraryItem elem = (LibraryItem)list.elem();
        System.out.println(elem.getDescription());
        list.advance();
        elem = (LibraryItem)list.elem();
        System.out.println(elem.getDescription());
    }*/


    /**
     * A list of all items in the library. Can be empty, and can contain as many items as desired.
     */
    private List inventory = new List();

    /**
     * Adds an item to the library
     * @param item the item to add to the library
     */
    public void addItem(LibraryItem item){
        inventory.reset();
        inventory.add(item);
    }

    /**
     * Deletes an item from the library
     * @param item the item in the library to be deleted
     */
    public void deleteItem(LibraryItem item){
        inventory.reset();
        while(!inventory.endpos()){
            if(inventory.elem() == item){
                inventory.delete();
            }
            if(!inventory.endpos()){
                inventory.advance();
            }
        }
    }

    /**
     * Searches in all items in the library whether the respective description contains the given text,
     * and returns all items where this is the case
     * @param text The text to search for in the descriptions of the LibraryItems
     * @return a list of all items where the description contains the given text
     */
    public List search(String text){
        List found = new List();
        inventory.reset();
        while(!inventory.endpos()){
            LibraryItem elem = (LibraryItem)inventory.elem();
            if(elem.getDescription().contains(text)){
                found.add(elem);
            }
            inventory.advance();
        }
        return found;
    }

}