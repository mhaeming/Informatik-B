/**
 * Every instance of <code>Fraction</code> represents a fraction with numerator
 * and denominator.
 *
 * @author Lars Huning 
 */
public class Fraction {

   /**
    * Creates greatest common divisor for a and b.
    *
    * @param a
    * @param b
    * @return the greatest common divisor for a and b.
    */
   public static int gcd(int a, int b) {
      return b == 0 ? a : gcd(b, a % b);
   }
   
   /**
    * Numerator of the Fraction
    */
   private final int numerator;
   
   /**
    * Denominator of the Fraction
    */
   private final int denominator;

   /**
    * Creates a Fraction object with numerator and denominator 1, reduces the
    * fraction with creation.
    *
    * @param numerator
    */
   public Fraction(int numerator) {
      this(numerator, 1);
   }

   /**
    * Creates a Fraction object with numerator and denominator. Reduces the 
    * fraction in the constructor. 
    * Denominator == 0 is not allowed. In such a case, the program terminates
    * with an error message
    *
    * @param numerator   the numerator
    * @param denominator the denominator, must not be 0.
    */
   public Fraction(int numerator, int denominator) {
      if (denominator == 0) {
          System.out.println("denominator == 0 is not possible");
          System.out.println("Terminating program");
          System.exit(-1);
      }

      /*
       * creates greatest common divisior.
       */
      int gcd = Fraction.gcd(numerator, denominator);
      
      /*
       * check sign, make denominator positive --> the sign of the fraction
       * is always stored only in the numerator. 
       */
      if (denominator / gcd < 0) {
         gcd *= -1;
      }

      this.numerator = numerator / gcd;

      this.denominator = denominator / gcd;
   }

   /**
    * Divides this Fraction with another Fraction. Terminates the program in 
    * case numerator of another is zero (via constructor of the newly created
    * Fraction).
    *
    * @param another Fraction to divide this Fraction by
    * @return Quotient as a new Fraction
    *
    */
   public Fraction divide(Fraction another) {
      return new Fraction(this.numerator * another.denominator,
            this.denominator * another.numerator);
   }
   
   /**
    * Note: "Default" getters and setters do not always require JavaDoc, 
    * as their purpose is obvious 
    */
   public int getDenominator() {
      return this.denominator;
   }

   public int getNumerator() {
      return this.numerator;
   }

   /**
    * Multiplies this Fraction with another Fraction
    *
    * @param factor Fraction to multiply this Fraction with
    * @return The product as a new Fraction
    */
   public Fraction multiply(Fraction factor) {
      return new Fraction(this.numerator * factor.numerator, this.denominator
            * factor.denominator);
   }

   /**
    * Multiplies this Fraction with all other Fraction instances given by
    * the parameter factors
    *
    * @param factors Fraction instances to multiply this Fraction with
    * @return The product as a new Fraction
    */
   public Fraction multiply(Fraction... factors) {
      Fraction result = this;
      
      //variable parameters may be treated like an array inside the method
      for (int i = 0; i < factors.length; i++) {
         result = result.multiply(factors[i]);
      }
      return result;
   }

   /**
    * Multiplies this Fraction with int n.
    *
    * @param n factor to multiply with
    * @return The product as a new Fraction
    */
   public Fraction multiply(int n) {
      return new Fraction(this.numerator * n, this.denominator);
   }

   /**
    * Returns a string representation of this Fraction such as
    * numerator/denominator. As the constructor has already made sure that 
    * a negative Fraction is represented by a negative numerator and a positive
    * denominator, negative fractions are always displayed with the minus sign
    * at the numerator (Advantage: consistent notation, for example if anyone
    * decides to parse the results of this method with a string parser)
    *
    * @return This Fraction as a String
    */
   public String toString() {
      return numerator + "/" + denominator;
   }


   /**
    * Adds the Fraction addend to this Fraction
    * @param addend
    * @return the sum as a new Fraction
    */
   public Fraction add(Fraction addend){
      int[] extended = this.extend(addend);
      return new Fraction(extended[0] + extended[2], extended[1]);
   }

   /**
    * Subtracts the Fraction subtrahend from this Fraction
    * @param subtrahend
    * @return the result of the subtraction as a new Fraction
    */
   public Fraction subtract(Fraction subtrahend){
      int[] extended = this.extend(subtrahend);
      return new Fraction(extended[0] - extended[2], extended[1]);
   }

   /**
    * Extends this Fraction and the given Fraction such that they have the same denominator
    * @param fraction the second fraction
    * @return a list of: extended num. of this fraction, extended denom. of this fraction, then of given
    */
   public int[] extend(Fraction fraction){
      int extendedNumerator = this.numerator * fraction.getDenominator();
      int extendedDenominator = this.denominator * fraction.getDenominator();
      int extendedNumeratorGiven = fraction.getNumerator() * this.denominator;
      int extendedDenominatorGiven = fraction.getDenominator() * this.denominator;
      int[] result = {extendedNumerator, extendedDenominator, extendedNumeratorGiven, extendedDenominatorGiven};
      return result;
   }

   /**
    * Takes a Fraction as a String (written in the way the ToString methods shows)
    * and returns it as a fraction of type Fraction
    * @param fractionString the Fraction as a string that we want to parse into type Fraction
    * @return the given Fraction-String as a fraction of type Fraction
    */
   public static Fraction parseFraction(String fractionString){
      /*
      The regex for parseFraction:
      Number of digits (not starting with 0), division sign, number of digits not starting with 0
      ^[1-9] [0-9]* [\\/] [1-9] [0-9]*
      */
      boolean isCorrectFraction = fractionString.matches("^[1-9][0-9]*[\\/][1-9][0-9]*");
      if (!isCorrectFraction) {
         System.err.println("Not a valid format for a fraction!");
         return null;
      }
      else{
         String[] splittedFraction = fractionString.split("\\/");
         int numerator = Integer.parseInt(splittedFraction[0]);
         int denominator = Integer.parseInt(splittedFraction[1]);
         return new Fraction(numerator, denominator);
      }
   }

   }


