/**
 * Ticker
 */
public class Ticker {

    /**
     * Singleton instance of the ticker
     */
    private static Ticker ticker;

    private Ticker(){};

    /**
     * Interaction method for the singleton
     * @return Reference to the ticker instance
     */
    public static Ticker getInstance() {
        if (ticker == null)
            ticker = new Ticker();
        return ticker;
    }

    /**
     * Print a string separated by +++
     * @param text The input text element to print
     */
    public void print(String text) {
        text = text.trim().replaceAll("[\n]", " ");
        text = "+++" + text;
        System.out.print(text);
    }
}