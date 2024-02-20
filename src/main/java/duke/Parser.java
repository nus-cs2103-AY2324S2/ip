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
     * Returns the next token without consuming it.
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
     * Reads until an option or the end of input is encountered. an option starts with '/'. The option itself
     * is not consumed.
     * @return the String before the option or end of input was encountered.
     * @throws DukeOptionParsingException if the end of input has already been reached.
     */
    public String nextUntilOption() throws DukeOptionParsingException {
        StringBuilder name = new StringBuilder();
        if (!this.hasNext()) {
            throw new DukeOptionParsingException("command ended when an argument was expected");
        }
        while (this.hasNext() && !this.peek().startsWith("/")) {
            if (!name.isEmpty()) {
                name.append(" ");
            }
            name.append(this.next());
        }
        return name.toString();
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
     * Asserts that the next token read is the same as the passed string. consumes the token.
     *
     * @param expected The what the next token read is expected to be
     *
     * @throws DukeOptionParsingException If the end of input is reached or !expected.equals(actual)
     */
    public void assertNext(String expected) throws DukeOptionParsingException {
        String actual = this.next();

        if (!actual.equals(expected)) {
            throw new DukeOptionParsingException
                    (String.format("expected '%s' but '%s' was given instead", expected, actual));
        }

    }

    /**
     * Asserts that the end of input has been reached. Throws if there's any input available.
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
