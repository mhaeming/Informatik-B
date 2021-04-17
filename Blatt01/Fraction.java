/**
 * Eine Klasse zur Representation von Brüchen
 * 
 * Alle Brüche werden automatisch bei ihrer Erstellung gekürzt.
 */
public class Fraction {

    /**
     * Zähler
     */
    private int numerator;

    /**
     * Nenner
     */
    private int denominator;

    /**
     * Initialisiert einen gekürzten Bruch. Wird nur eine Zahl gegeben, so wird diese als Zähler zum Nenner 1 gesetzt.
     * 
     * @param numerator Zähler des Bruches
     * @param denominator Nenner des Bruches
     */
    public Fraction(int numerator, int denominator) {

        // Nenner darf nicht 0 sein, hab die Exception hierfür mal vorweg genommen
        if (denominator == 0) {
            throw new IllegalArgumentException("Der Nenner darf nicht 0 sein!");
        }

        // Kürzen der Brüche mittels größtem gemeinsamen Teiler
        int commonDenominator = ggt(numerator, denominator);

        this.numerator = numerator / commonDenominator;
        this.denominator = denominator / commonDenominator;

        // Konsistente Formatierung bei negativen Zahlen
        if (this.denominator < 0) {
            this.numerator *= -1;
            this.denominator *= -1;
        }
    }

    /**
     * Initialisiere einen Bruch, wenn nur ein Zähler angegeben wird, mit dem gegebenen Nenner und dem Zähler 1
     * @param numerator Zähler des Bruchs
     */
    public Fraction(int numerator) {
        this(numerator, 1);
    }

    /**
     * Gibt den Bruch als String der Form "a/b" zurück
     *
     * @return Bruch als String
     */
    public String toString() {
        return this.numerator + "/" + this.denominator;
    }

    /**
     * 
     * @return Zähler
     */
    public int getNumerator() {
        return this.numerator;
    }

    /**
     * 
     * @return Nenner
     */
    public int getDenominator() {
        return this.denominator;
    }

    /**
     * Multipliziert den Zähler des Bruches mit einem Faktor
     * 
     * @param factor Faktor zur Multiplikation
     * @return Einen gekürzten Bruch aus der Multiplikation
     */
    public Fraction multiply(int factor){
        return new Fraction(this.numerator * factor, this.denominator);
    }

    /**
     * Multipliziert zwei Brüche miteinander
     * 
     * @param factor Bruch zur Multiplikation
     * @return Gekürzter Bruch der Multiplikation
     */
    public Fraction multiply(Fraction factor){
        return new Fraction(this.numerator * factor.numerator ,this.denominator * factor.denominator);
    }

    /**
     * Teilt zwei Brüche in dem der erste Bruch mit dem Kehrwert des Divisors multipliziert wird
     * 
     * @param divisor Der Bruch, durch den geteilt werden soll
     * @return Gekürzter Bruch nach der Division
     */
    public Fraction divide(Fraction divisor){
        return new Fraction(this.numerator * divisor.denominator, this.denominator * divisor.numerator);
    }

    /**
     * Multipliziert beliebig viele Brüche miteinander
     * 
     * @param factors Ein Array von Brüchen das mit dem Bruch multipliziert werden soll
     * @return Das gekürzte Ergebnis der Multiplikation
     */
    public Fraction multiply(Fraction[] factors){
        int num = this.numerator;
        int den = this.denominator; 

        for (int i = 0; i < factors.length; i++) {
            num *= factors[i].numerator;
            den *= factors[i].denominator;
        }

        return new Fraction(num, den);
    }

    /**
     * Methode zur Bestimmung des größten gemeinsamen Teilers mittels rekursiver Implementierung des Euklid Algorithmus
     * 
     * @param a,b Zahlen, deren größter gemeinsamer Teiler ermittelt werden soll
     * @return Größter gemeinsamer Teiler von a und b
     */
    private static int ggt(int a, int b) {
        if (b == 0) {
            return a;
        }
        return ggt(b, a % b);  
    }
    
}

