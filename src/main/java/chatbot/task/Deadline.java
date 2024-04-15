package chatbot.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chatbot.task.exception.InvalidTaskStringException;
import chatbot.value.DateStringValue;

/**
 * This encapsulates tasks that need to be done before a specific date/time.
 *
 * @author Titus Chew
 */
public final class Deadline extends Task {
    /** The icon for the task type. */
    private static final String TASK_TYPE_ICON = "D";

    /** The format that a {@link Deadline} takes. */
    private static final String FORMAT = String.format("[%s]%s (by: %s)", TASK_TYPE_ICON, "%s", "%s");

    /** The regex pattern that a {@link Deadline} takes. */
    private static final Pattern REGEX_PATTERN = Pattern.compile(
            String.format("\\[%s\\](?<task>.*)\\(by:(?<by>.*)\\)", TASK_TYPE_ICON));

    /** Stores the deadline time of this. */
    private final DateStringValue deadline;

    /**
     * Constructor for this deadline.
     *
     * @param name The name of this deadline.
     * @param deadline The deadline (possibly date/time) of this task.
     */
    public Deadline(String name, DateStringValue deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Constructor for this deadline.
     *
     * @param matcher The matcher that has the relevant capture groups.
     * @throws InvalidTaskStringException If the regex doesn't match the pattern.
     */
    public Deadline(Matcher matcher) throws InvalidTaskStringException {
        super(matcher);
        this.deadline = new DateStringValue(matcher.group("by"));
    }

    /**
     * Copy constructor for this deadline.
     *
     * @param deadline The deadline to copy.
     */
    public Deadline(Deadline deadline) {
        super(deadline);
        this.deadline = deadline.deadline;
    }

    /**
     * Parses a deadline from a human-readable string.
     *
     * @param readableString The deadline as a human-readable string.
     * @return The deadline.
     * @throws InvalidTaskStringException If the regex doesn't match the pattern.
     */
    public static Deadline parseDeadline(String readableString) throws InvalidTaskStringException {
        Matcher matcher = REGEX_PATTERN
                .matcher(readableString);

        if (!matcher.find()) {
            throw new InvalidTaskStringException();
        }

        return new Deadline(matcher);
    }

    /**
     * Checks if the format of a string matches with the pattern.
     *
     * @return True if it matches, otherwise false.
     */
    public static boolean isMatchingDeadline(String matchingString) {
        return REGEX_PATTERN.matcher(matchingString).find();
    }

    @Override
    public String toString() {
        return String.format(FORMAT, super.toString(), deadline);
    }
}
