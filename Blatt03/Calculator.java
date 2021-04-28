import java.util.Scanner;

/**
 * Class calculator
 * Can perform a calculation given as User Input on the command line
 */
public class Calculator {

    public static void main(String[] args){
        Calculator calculator = new Calculator();
        calculator.calculate();
    }

    /**
     * Gets a User Input on the command line,
     * If the input can be parsed into a calculation of two fractions, the calculation is done
     * and the result is printed on the command line.
     */
    public static void calculate(){
        // Get the user input
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter your calculation: ");
        String input = myScanner.nextLine();
        String calculation = input.replace("\"", "");

        // Check if the user input is valid
        boolean isValidCalculation = input.matches(
                "^[1-9][0-9]*[\\/][1-9][0-9]*[ ]?\"[+\\-\\/\\*]\"[ ]?[1-9][0-9]*[\\/][1-9][0-9]*");

        // If the input is not valid, enter the rules on how the input must look like
        if(!isValidCalculation) {
            System.err.println("Your input is not valid!");
            System.out.println("You must enter a fraction written like '12/32' first.\n" +
                    "Then you must enter an operator of +-/*, written in \"\", before and after which you can enter" +
                    " a blank.\n" +
                    "And then you must enter a fraction again.");
        }

        // If the input is valid, split the string, construct the fractions and do the calculation
        else{
            // Split the string at the operator symbol to obtain 2 fraction-strings
            String[] splittedCalculation = input.split("\"[+\\-\\*\\/]\"");

            // extract the operator-symbol. Remove the fractions, then remove the "" around the operator
            String operator = input.replaceAll(splittedCalculation[0], "");
            operator = operator.replaceAll(splittedCalculation[1], "");
            operator = operator.replace("\"", "");

            // remove blanks in the fraction-strings, so that we can parse them with parseFraction
            splittedCalculation[0] = splittedCalculation[0].trim();
            splittedCalculation[1] = splittedCalculation[1].trim();

            // Parse the fraction-strings into fractions of Type Fraction
            Fraction first = Fraction.parseFraction(splittedCalculation[0]);
            Fraction second = Fraction.parseFraction(splittedCalculation[1]);

            // Depending on the operator-symbol, perform the respective method
            Fraction result = null;
            switch(operator){
                case "+":
                    result = first.add(second);
                    break;
                case "-":
                    result = first.subtract(second);
                    break;
                case "*":
                    result = first.multiply(second);
                    break;
                case "/":
                    result = first.divide(second);
                    break;
            }
            System.out.println("Your calculation is: " + calculation);
            System.out.println("Your result is: " + result.toString());
        }
    }

}
