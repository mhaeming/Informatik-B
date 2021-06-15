package io;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.PatternSyntaxException;

public class SearchLines {
    public static void main(String[] args) {

        // Inform user about correct usage
        if (args.length != 1) {
            System.out.println("How to use: 'java io/SearchLines Expression < filename'");
            System.out.println("Or: 'java io/SearchLines Experssion' and enter the text afterwards");
            return;
        }

        String expr = args[0];

        try (SearchLineReader slreader = new SearchLineReader(new InputStreamReader(System.in), expr)) {
            boolean matchFound = false;
            String line;
            while ((line = slreader.readLine()) != null) {

                // Found a single match in a line
                if (slreader.getAmountOfMatches() == 1) {
                    matchFound = true;
                    System.out.println("1 match for '" + expr + "' in line " + slreader.getLineNumber() + ": " + line);
                }

                // Found multiple matches in a line
                if (slreader.getAmountOfMatches() > 1) {
                    matchFound = true;
                    System.out.println(slreader.getAmountOfMatches() + " matches for '" + expr + "' in line " + slreader.getLineNumber() + ": " + line); 
                }
            }

            if (!matchFound) {
                System.out.println("No match found!");
            }
        } catch (PatternSyntaxException e) {
            System.err.println(expr + " is not a valid regular expression!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
