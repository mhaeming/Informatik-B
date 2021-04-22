public class TestCompany {

    /**
     * Test Class to create a few companies. Change their stock values and explore the behavior of Java's garbage collector
     * @param args not used in this example
     */
    public static void main(String[] args) {
        Company biontech = new Company("BioNTech");
        Company apple = new Company("Apple");
        Company tui = new Company("TUI");
        Company bayer = new Company("Bayer");
        Company amazon = new Company("Amazon");

        biontech.changeStockPrice(123.2);
        apple.changeStockPrice(108.9);
        amazon.changeStockPrice(94.6);
        tui.changeStockPrice(36.7);
        bayer.changeStockPrice(62.5);
        biontech.changeStockPrice(126.4);
        tui.changeStockPrice(12.3);
        amazon.changeStockPrice(95.3);
        tui = null;
        bayer = null;
        System.gc();
        apple.changeStockPrice(106.7);
        amazon.changeStockPrice(96.2);
        // System.gc();
    }
}
