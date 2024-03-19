package louie;

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
        assert str != null : "input string cannot be null";
        this.strs = str.split(" ");
    }

    /**
     * consumes a token from the string and returns it.
     *
     * @return The next string.
     * @throws LouieOptionParsingException If all tokens have been read from the input
     */
    public String next() throws LouieOptionParsingException {
        String token = this.peek();
        cursor++;
        return token;
    }

    /**
     * consumes whatever is left of the string and returns it.
     *
     * @return all the test after the cursor.
     * @throws LouieOptionParsingException If all tokens have been read from the input
     */
    public String rest() throws LouieOptionParsingException {
        if (!this.hasNext()) {
            throw new LouieOptionParsingException("command ended when an argument was expected");
        }
        String collected = Stream.iterate(this.cursor, i -> i < this.strs.length, i -> i + 1)
                .map(i -> this.strs[i])
                .collect(Collectors.joining(" "));
        this.cursor = this.strs.length;
        assert(!this.hasNext());
        return collected;
    }

    /**
     * Returns the next token without consuming it.
     *
     * @return The next token.
     * @throws LouieOptionParsingException If all tokens have been read from the input
     */
    public String peek() throws LouieOptionParsingException {
        if (!this.hasNext()) {
            throw new LouieOptionParsingException("command ended when an argument was expected");
        }
        return this.strs[this.cursor];
    }

    /**
     * Reads until an option or the end of input is encountered. an option starts with '/'. The option itself
     * is not consumed.
     * @return the String before the option or end of input was encountered.
     * @throws LouieOptionParsingException if the end of input has already been reached.
     */
    public String nextUntilOption() throws LouieOptionParsingException {
        StringBuilder name = new StringBuilder();
        if (!this.hasNext()) {
            throw new LouieOptionParsingException("command ended when an argument was expected");
        }
        while (this.hasNext() && !this.peek().startsWith("/")) {
            // check if not-empty
            if (name.length() > 0) {
                name.append(" ");
            }
            name.append(this.next());
        }
        assert(!this.hasNext() || this.peek().startsWith("/"));
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
     * @throws LouieOptionParsingException If the end of input is reached or !expected.equals(actual)
     */
    public void assertNext(String expected) throws LouieOptionParsingException {
        assert(expected != null);
        String actual = this.next();

        if (!actual.equals(expected)) {
            throw new LouieOptionParsingException
                    (String.format("expected '%s' but '%s' was given instead", expected, actual));
        }

    }

    /**
     * Asserts that the end of input has been reached. Throws if there's any input available.
     *
     * @throws LouieOptionParsingException If there's any input available.
     */
    public void assertEnd() throws LouieOptionParsingException {
        if (this.hasNext()) {
            throw new LouieOptionParsingException
                    ("option was not expected but was given: " + this.strs[this.cursor]);
        }
    }
}
