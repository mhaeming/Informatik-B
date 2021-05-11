package library;

public abstract class LibraryItem {

    /**
     * is the item currently borrowed, or still in the library available?
     */
    private boolean isBorrowed;



    /**
     * Constructor without any given arguments
     */
    public LibraryItem(){
        this.isBorrowed = false;
    }



    /**
     * Is the item currently borrowed?
     * @return a boolean, false for not currently borrowed, true for currently borrowed
     */
    public boolean isBorrowed(){
        return this.isBorrowed;
    }

    /**
     * Set isBorrowed to a given value (true/false)
     * @param isBorrowed boolean, the value that isBorrowed will be set to
     */
    public void setBorrowed(boolean isBorrowed){
        this.isBorrowed = isBorrowed;
    }

    /**
     * Abstract method
     * Get the description of the item
     * @return a String, the description of the item
     */
    public abstract String getDescription();

}
