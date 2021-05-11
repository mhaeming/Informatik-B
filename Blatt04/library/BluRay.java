package library;

public class BluRay extends LibraryItem{

    /**
     * The title of the movie on the BluRay
     */
    private String title;

    /**
     * The director of the movie on the BluRay
     */
    private String director;


    /**
     * The constructor
     * @param title
     * @param director
     */
    public BluRay(String title, String director){
        super();
        this.title = title;
        this.director = director;
    }


    /**
     * Get the title of the BluRay
     * @return a String, the title of the BluRay
     */
    public String getTitle(){
        return this.title;
    }

    /**
     * Get the director of the BluRay
     * @return a String, the director of the BluRay
     */
    public String getDirector(){
        return this.director;
    }

    /**
     * Get the description of the item, which is constructed out of the title and the director
     * @return a String, the description of the item
     */
    public String getDescription(){
        return "The movie on this BluRay is named '" + this.title + "' and was directed by " + this.director + ".";
    }
}
