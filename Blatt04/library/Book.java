package library;

public class Book extends LibraryItem{

    /**
     * The title of the book
     */
    private String title;

    /**
     * The author of the book
     */
    private String author;


    /**
     * The constructor
     * @param title
     * @param author
     */
    public Book(String title, String author){
        super();
        this.title = title;
        this.author = author;
    }


    /**
     * Get the title of the book
     * @return a String, the title of the book
     */
    public String getTitle(){
        return this.title;
    }

    /**
     * Get the author of the book
     * @return a String, the author of the book
     */
    public String getAuthor(){
        return this.author;
    }

    /**
     * Get the description of the item, which is constructed out of the title and the author
     * @return a String, the description of the item
     */
    public String getDescription(){
        return "This book named '" + this.title + "' was written by " + this.author + ".";
    }
}
