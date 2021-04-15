public class TestFraction {
    
    private int errors;

    public static void main(String[] args) {
        System.out.println("Starte Tests...");

        TestFraction test = new TestFraction();
        test.startTest();


        System.out.println("Tests beendet.");
        System.out.println(test.getErrors() + " Fehler gefunden");
    }

    public void startTest() {
        testInitialization();
        testNegativeValues();
        testSimplification();
        testDivide();
        testMultiplyInt();
        testMultiplyFraction();
        testMultiplyFractions();
    }


    public int getErrors(){
        return this.errors;
    }

    public void assertVals(int expectedNumerator, int expectedDenominator, Fraction fraction) {
        if (fraction.getNumerator() != expectedNumerator || fraction.getDenominator() != expectedDenominator) {
            System.out.println("FEHLER: Erwartete " + expectedNumerator + "/" + expectedDenominator + " Erhielt: " + fraction);
            errors++;
        }
    }


    public void testInitialization() {
        Fraction f1 = new Fraction(2, 3);
        assertVals(2, 3, f1);

        Fraction f2 = new Fraction(5);
        assertVals(5, 1, f2);

        Fraction f3 = new Fraction(0);
        assertVals(0, 1, f3);
    }

    public void testNegativeValues() {
        Fraction f1 = new Fraction(-4);
        assertVals(-4, 1, f1);

        Fraction f2 = new Fraction(-3, 2);
        assertVals(-3, 2, f2);

        Fraction f3 = new Fraction(3, -2);
        assertVals(-3, 2, f3);
    }

    public void testSimplification() {
        Fraction f1 = new Fraction(5, 10);
        assertVals(1, 2, f1);

        Fraction f2 = new Fraction(-5, 10);
        assertVals(-1, 2, f2);
    }


    public void testDivide() {
        Fraction f1 = new Fraction(1, 8);
        Fraction f2 = new Fraction(2, 3);
        assertVals(3, 16, f1.divide(f2));

        Fraction f3 = new Fraction(-1, 8);
        Fraction f4 = new Fraction(2, 3);
        assertVals(-3, 16, f3.divide(f4));
    }

    public void testMultiplyInt() {
        Fraction f1 = new Fraction(2, 4);
        assertVals(1, 1, f1.multiply(2));

        Fraction f2 = new Fraction(3, 5);
        assertVals(0, 1, f2.multiply(0));
    }

    public void testMultiplyFraction() {
        Fraction f1 = new Fraction(1, 8);
        Fraction f2 = new Fraction(2, 3);
        assertVals(1, 12, f1.multiply(f2));

        Fraction f3 = new Fraction(-2, 5);
        Fraction f4 = new Fraction(3, 4);
        assertVals(-3, 10, f3.multiply(f4));
    }

    public void testMultiplyFractions() {
        Fraction f1 = new Fraction(3, 5);

        Fraction[] testList = new Fraction[3];
        testList[0] = new Fraction(2, 7);
        testList[1] = new Fraction(4, 3);
        testList[2] = new Fraction(9, 11);
        assertVals(72, 385, f1.multiply(testList));
    }
}
