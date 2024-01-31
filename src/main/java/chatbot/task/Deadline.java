package chatbot.task;

import chatbot.task.exception.InvalidTaskStringException;
import chatbot.value.DateStringValue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This encapsulates tasks that need to be done before a specific date/time.
 *
 * @author Titus Chew
 */
public final class Deadline extends Task {
    /** Stores the deadline time of this. */
    private final DateStringValue deadline;

    /** The icon for the task type. */
    private static final String TASK_TYPE_ICON = "D";

    /** The format that a {@link Deadline} takes. */
    private static final String FORMAT = String.format("[%s]%s (by: %s)", TASK_TYPE_ICON, "%s", "%s");

    /** The regex pattern that a {@link Deadline} takes. */
    private static final Pattern REGEX_PATTERN = Pattern.compile(
            String.format("\\[%s\\](?<task>.*)\\(by:(?<by>.*)\\)", TASK_TYPE_ICON));

    /**
     * Constructor for this deadline.
     *
     * @param name the name of this deadline
     * @param deadline the deadline (possibly date/time) of this task
     */
    public Deadline(String name, DateStringValue deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Constructor for this deadline.
     *
     * @param matcher the matcher that has the relevant captured groups
     * @throws InvalidTaskStringException If the regex doesn't match the pattern
     */
    public Deadline(Matcher matcher) throws InvalidTaskStringException {
        super(matcher);
        this.deadline = DateStringValue.of(matcher.group("by"));
    }

    /**
     * Parses a deadline from a human-readable string.
     *
     * @param readableString the deadline as a human-readable string
     * @return the deadline
     * @throws InvalidTaskStringException If the regex doesn't match the pattern
     */
    public static Deadline parseDeadline(String readableString) throws InvalidTaskStringException {
        Matcher matcher = REGEX_PATTERN
                .matcher(readableString);

        if (matcher.find()) {
            return new Deadline(matcher);
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
    public static boolean isMatchingDeadline(String matchingString) {
        return REGEX_PATTERN.matcher(matchingString).find();
    }

    @Override
    public String toString() {
        return String.format(FORMAT, super.toString(), deadline);
    }
}
