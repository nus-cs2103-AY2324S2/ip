package duke;

import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * Parser class extracts tokens from a String like a Scanner, intended for used for parsing arguments given to a
 * command.
 */
public class Parser {
    private final String[] strs;
    private int cursor = 0;

    /**
     * Constructs a Parser object with the provided input string. read operations on this parser reads tokens from the
     * String as if it were an array of space-separated strings.
     *
     * @param str The input string.
     */
    public Parser(String str) {
        this.strs = str.split(" ");
    }

    /**
     * consumes a token from the string and returns it.
     *
     * @return The next string.
     * @throws DukeOptionParsingException If all tokens have been read from the input
     */
    public String next() throws DukeOptionParsingException {
        String token = this.peek();
        cursor++;
        return token;
    }

    /**
     * consumes whatever is left of the string and returns it.
     *
     * @return all the test after the cursor.
     * @throws DukeOptionParsingException If all tokens have been read from the input
     */
    public String rest() throws DukeOptionParsingException {
        if (!this.hasNext()) {
            throw new DukeOptionParsingException("command ended when an argument was expected");
        } else {
            return Stream.iterate(this.cursor, i -> i < this.strs.length, i -> i + 1)
                    .map(i -> this.strs[i])
                    .collect(Collectors.joining(" "));
        }
    }

    /**
     * consumes the next token without returning it.
     *
     * @return The next token.
     * @throws DukeOptionParsingException If all tokens have been read from the input
     */
    public String peek() throws DukeOptionParsingException {
        if (!this.hasNext()) {
            throw new DukeOptionParsingException("command ended when an argument was expected");
        } else {
            return this.strs[this.cursor];
        }
    }

    /**
     * Checks if there's any input available.
     *
     * @return false if all the text has been consumed, false otherwise.
     */
    public boolean hasNext() {
        return this.cursor < this.strs.length;
    }

    /**
     * Throws if there's any input available.
     *
     * @throws DukeOptionParsingException If there's any input available.
     */
    public void assertEnd() throws DukeOptionParsingException {
        if (this.hasNext()) {
            throw new DukeOptionParsingException
                    ("option was not expected but was given: " + this.strs[this.cursor]);
        }
    }
}
