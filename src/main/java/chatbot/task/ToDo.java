package chatbot.task;

import chatbot.task.exception.InvalidTaskStringException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This encapsulates tasks without any date/time attached to it.
 *
 * @author Titus Chew
 */
public final class ToDo extends Task {
    /** The icon for the task type. */
    private static final String TASK_TYPE_ICON = "T";

    /** The format that a {@link ToDo} takes. */
    private static final String FORMAT = String.format("[%s]%s", TASK_TYPE_ICON, "%s");

    /** The regex pattern that a {@link ToDo} takes. */
    private static final Pattern REGEX_PATTERN = Pattern.compile(
            String.format("\\[%s\\](?<task>.*)", TASK_TYPE_ICON));

    /**
     * Constructor for this to-do.
     *
     * @param name the name of this to-do
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Constructor for this to-do.
     *
     * @param matcher the matcher that has the relevant captured groups
     * @throws InvalidTaskStringException If the regex doesn't match the pattern
     */
    public ToDo(Matcher matcher) throws InvalidTaskStringException {
        super(matcher);
    }

    /**
     * Parses a to-do from a human-readable string.
     *
     * @param readableString the to-do as a human-readable string
     * @return the to-do
     * @throws InvalidTaskStringException If the regex doesn't match the pattern
     */
    public static ToDo parseToDo(String readableString) throws InvalidTaskStringException {
        Matcher matcher = REGEX_PATTERN.matcher(readableString);

        if (matcher.find()) {
            return new ToDo(matcher);
        } else {
            throw new InvalidTaskStringException();
        }
    }

    /**
     * Checks if the format of a string matches with the pattern.
     *
     * @param matchingString the string
     * @return true if it matches, otherwise false.
     */
    public static boolean isMatchingToDo(String matchingString) {
        return REGEX_PATTERN.matcher(matchingString).find();
    }

    @Override
    public String toString() {
        return String.format(FORMAT, super.toString());
    }
}
