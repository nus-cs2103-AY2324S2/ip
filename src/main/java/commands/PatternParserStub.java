package commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.RyanGoslingException;

/**
 * Tests the {@link PatternParser} class.
 */
public class PatternParserStub {

    // Regular expression pattern for "todo" command
    private static final Pattern todoPattern = Pattern.compile("todo (.*?)");

    /**
     * Parses a "todo" command and returns the parsed task string for testing purposes.
     *
     * @param taskToParse The input string containing the "todo" command.
     * @return The parsed task string.
     * @throws RyanGoslingException If the input string does not match the expected "todo" pattern.
     */
    public static String todoParser(String taskToParse) throws RyanGoslingException {
        Matcher matcher = todoPattern.matcher(taskToParse);
        if (!matcher.matches()) {
            throw new RyanGoslingException("Incomplete todo command, todo <event>");
        }
        return taskToParse;
    }
}
