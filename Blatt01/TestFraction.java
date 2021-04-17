/**
 * Eine Klasse zum Testen der Klasse Fraction
 */
public class TestFraction {

    /**
     * Anzahl der gefundenen Fehler
     */
    private int errors;

    /**
     * Die Main-Methode, hier wird über den Start und Schluss und das Ergebnis der Tests informiert und die Methode zum Aufrufen der einzelnen Tests aufgerufen.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Starte Tests...");

        TestFraction test = new TestFraction();
        test.startTest();


        System.out.println("Tests beendet.");
        System.out.println(test.getErrors() + " Fehler gefunden");
    }

    /**
     * Startet die verschiedenen Tests
     */
    public void startTest() {
        testInitialization();
        testNegativeValues();
        testSimplification();
        testDivide();
        testMultiplyInt();
        testMultiplyFraction();
        testMultiplyFractions();
        testFractionDenominatorIsZero();
    }

    /**
     * Gibt die Anzahl der gefundenen Fehler zurück
     * @return die Anzahl der gefundenen Fehler als Integer
     */
    public int getErrors(){
        return this.errors;
    }

    /**
     * Wird in den einzelnen Tests aufgerufen um zu gucken ob ein Fehler gefunden wurde, und wenn ja dies auszugeben und die Anzahl Fehler zu erhöhen
     * @param expectedNumerator Der erwartete Zähler
     * @param expectedDenominator Der erwartete Nenner
     * @param fraction Der Bruch der geprüft werden soll (gegebenenfalls besteht dieser aus einem Methodenaufruf der einen Bruch zurückgibt)
     */
    public void assertVals(int expectedNumerator, int expectedDenominator, Fraction fraction) {
        if (fraction.getNumerator() != expectedNumerator || fraction.getDenominator() != expectedDenominator) {
            System.out.println("FEHLER: Erwartete " + expectedNumerator + "/" + expectedDenominator + " Erhielt: " + fraction);
            errors++;
        }
    }

    /**
     * Teste die Initialisierung des Bruchs
     */
    public void testInitialization() {
        Fraction f1 = new Fraction(2, 3);
        assertVals(2, 3, f1);

        Fraction f2 = new Fraction(5);
        assertVals(5, 1, f2);

        Fraction f3 = new Fraction(0);
        assertVals(0, 1, f3);
    }

    /**
     * Teste die Initialisierung mit negativen Zahlen
     */
    public void testNegativeValues() {
        Fraction f1 = new Fraction(-4);
        assertVals(-4, 1, f1);

        Fraction f2 = new Fraction(-3, 2);
        assertVals(-3, 2, f2);

        Fraction f3 = new Fraction(3, -2);
        assertVals(-3, 2, f3);
    }

    /**
     * Teste das Kürzen von Brüchen
     */
    public void testSimplification() {
        Fraction f1 = new Fraction(5, 10);
        assertVals(1, 2, f1);

        Fraction f2 = new Fraction(-5, 10);
        assertVals(-1, 2, f2);
    }


    /**
     * Teste das Teilen von Brüchen
     */
    public void testDivide() {
        Fraction f1 = new Fraction(1, 8);
        Fraction f2 = new Fraction(2, 3);
        assertVals(3, 16, f1.divide(f2));

        Fraction f3 = new Fraction(-1, 8);
        Fraction f4 = new Fraction(2, 3);
        assertVals(-3, 16, f3.divide(f4));
    }

    /**
     * Teste das Multiplizieren eines Bruchs mit einer Zahl
     */
    public void testMultiplyInt() {
        Fraction f1 = new Fraction(2, 4);
        assertVals(1, 1, f1.multiply(2));

        Fraction f2 = new Fraction(3, 5);
        assertVals(0, 1, f2.multiply(0));
    }

    /**
     * Teste das Multiplizieren eines Bruchs mit einem anderen Bruch
     */
    public void testMultiplyFraction() {
        Fraction f1 = new Fraction(1, 8);
        Fraction f2 = new Fraction(2, 3);
        assertVals(1, 12, f1.multiply(f2));

        Fraction f3 = new Fraction(-2, 5);
        Fraction f4 = new Fraction(3, 4);
        assertVals(-3, 10, f3.multiply(f4));
    }

    /**
     * Teste das Multiplizieren eines Bruchs mit mehreren anderen Brüchen
     */
    public void testMultiplyFractions() {
        Fraction f1 = new Fraction(3, 5);

        Fraction[] testList = new Fraction[3];
        testList[0] = new Fraction(2, 7);
        testList[1] = new Fraction(4, 3);
        testList[2] = new Fraction(9, 11);
        assertVals(72, 385, f1.multiply(testList));
    }

    /**
     * Teste das Erstellen eines Bruchs mit einem Nenner von 0 (sollte nicht möglich sein, da geteilt durch null = error)
     */
    public void testFractionDenominatorIsZero(){
        boolean exceptionThrown = false;
        try {
            Fraction f1 = new Fraction(10, 0);
        } catch (Exception e){
            exceptionThrown = true;
        }
        if (!exceptionThrown){ // Es sollte eine Exception auftreten, falls nicht wurde ein Fehler gefunden, da ein Bruch mit Nenner=0 erstellt wurde
            System.out.println("FEHLER: Bruch mit Nenner von 0 erstellt");
            errors++;
        }
    }
}
