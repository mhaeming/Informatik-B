/**
 * Company
 */
public class Company {

    /**
     * The company name
     */
    private String name;

    /**
     * Create a company with a given name
     * @param name The company name
     */
    public Company(String name) {
        this.name = name;
    }

    /**
     * 
     * @param d new stock price
     */
    public void changeStockPrice(double d) {
        String text = this.name + " " + d;
        Ticker.getInstance().print(text);
    }

    /**
     * Announces when the company goes bankcrupt and is removed from the stock market
     */
    protected void finalize() {
        String text = this.name + " is insolvent";
        Ticker.getInstance().print(text);
    }
    
}