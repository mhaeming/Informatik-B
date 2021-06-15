package io;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A decorator extention for LineNumberReader, allowing to count matches of a regular expression
 * 
 * @author Maximilian Häming
 */
public class SearchLineReader extends LineNumberReader{

    private Pattern match;
    private int matchCount;

    public SearchLineReader(Reader r, String match) {
        super(r);

        this.match = Pattern.compile(match);
        this.matchCount = 0;
    }


    /**
     * 
     * @return The read in line
     */
    @Override
    public String readLine() throws IOException{
        String line = super.readLine();
        matchCount = 0;

        if (line != null) {
            Matcher mat = this.match.matcher(line);
            while (mat.find()) {
                matchCount++;
            }
        }
        return line;
    }


    /**
     * 
     * @return How many matches were made
     */
    public int getAmountOfMatches() {
        return matchCount;
    }

}
