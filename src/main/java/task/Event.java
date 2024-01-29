package task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Events: tasks that start at a specific date/time and ends at a specific data/time.
 *
 * @author Titus Chew
 */
public class Event extends Task {
    /**
     * Stores the start time of this.
     */
    private final String startDateTime;
    /**
     * Stores the end time of this.
     */
    private final String endDateTime;

    /**
     * The icon for the task type.
     */
    static final String TASK_TYPE_ICON = "E";

    /**
     * The format/pattern that an {@link Event} takes.
     */
    private static final String FORMAT = String.format("[%s]%s (from: %s to: %s)", TASK_TYPE_ICON, "%s", "%s", "%s");

    /**
     * The regex pattern that a {@link Deadline} takes.
     */
    private static final String REGEX_PATTERN =
            String.format("\\[%s\\](?<task>.*)\\(from:(?<from>.*)to:(?<to>.*)\\)", TASK_TYPE_ICON);

    /**
     * Constructor for this event.
     *
     * @param name the name of this event
     * @param startDateTime the starting date/time of this event
     * @param endDateTime the ending date/time of this event
     */
    public Event(String name, String startDateTime, String endDateTime) {
        super(name);
        this.startDateTime = startDateTime.trim();
        this.endDateTime = endDateTime.trim();
    }

    /**
     * Constructor for this event.
     *
     * @param matcher the matcher that has the relevant captured groups
     * @throws IllegalStateException If the regex doesn't match the pattern
     */
    public Event(Matcher matcher) throws IllegalStateException {
        super(matcher);
        this.startDateTime = matcher.group("from").trim();
        this.endDateTime = matcher.group("to").trim();
    }

    /**
     * Parse an event from a human-readable string.
     *
     * @param readableString the event as a human-readable string
     * @return the event
     */
    public static Event parseEvent(String readableString) {
        Matcher matcher = Pattern
                .compile(REGEX_PATTERN)
                .matcher(readableString);

        matcher.find();
        return new Event(matcher);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(FORMAT, super.toString(), startDateTime, endDateTime);
    }
}
