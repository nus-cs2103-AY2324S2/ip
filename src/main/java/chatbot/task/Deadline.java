package chatbot.task;

import chatbot.task.exception.InvalidTaskStringException;
import chatbot.value.DateStringValue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Deadlines: tasks that need to be done before a specific date/time.
 *
 * @author Titus Chew
 */
public final class Deadline extends Task {
    /**
     * Stores the deadline of this.
     */
    private final DateStringValue deadline;

    /**
     * The icon for the task type.
     */
    static String TASK_TYPE_ICON = "D";

    /**
     * The format that a {@link Deadline} takes.
     */
    private static final String FORMAT = String.format("[%s]%s (by: %s)", TASK_TYPE_ICON, "%s", "%s");

    /**
     * The regex pattern that a {@link Deadline} takes.
     */
    private static final String REGEX_PATTERN =
            String.format("\\[%s\\](?<task>.*)\\(by:(?<by>.*)\\)", TASK_TYPE_ICON);

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
        this.deadline = new DateStringValue(matcher.group("by"));
    }

    /**
     * Parse a deadline from a human-readable string.
     *
     * @param readableString the deadline as a human-readable string
     * @return the deadline
     * @throws InvalidTaskStringException If the regex doesn't match the pattern
     */
    public static Deadline parseDeadline(String readableString) throws InvalidTaskStringException {
        Matcher matcher = Pattern
                .compile(REGEX_PATTERN)
                .matcher(readableString);

        if (matcher.find()) {
            return new Deadline(matcher);
        } else {
            throw new InvalidTaskStringException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(FORMAT, super.toString(), deadline);
    }
}
