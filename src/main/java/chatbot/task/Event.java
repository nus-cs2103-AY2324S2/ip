package chatbot.task;

import chatbot.task.exception.InvalidTaskStringException;
import chatbot.value.DateStringValue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Events: tasks that start at a specific date/time and ends at a specific data/time.
 *
 * @author Titus Chew
 */
public final class Event extends Task {
    /**
     * Stores the start time of this.
     */
    private final DateStringValue startDateTime;
    /**
     * Stores the end time of this.
     */
    private final DateStringValue endDateTime;

    /**
     * The icon for the task type.
     */
    private static final String TASK_TYPE_ICON = "E";

    /**
     * The format/pattern that an {@link Event} takes.
     */
    private static final String FORMAT = String.format("[%s]%s (from: %s to: %s)", TASK_TYPE_ICON, "%s", "%s", "%s");

    /**
     * The regex pattern that a {@link Deadline} takes.
     */
    private static final Pattern REGEX_PATTERN = Pattern.compile(
            String.format("\\[%s\\](?<task>.*)\\(from:(?<from>.*)to:(?<to>.*)\\)", TASK_TYPE_ICON));

    /**
     * Constructor for this event.
     *
     * @param name the name of this event
     * @param startDateTime the starting date/time of this event
     * @param endDateTime the ending date/time of this event
     */
    public Event(String name, DateStringValue startDateTime, DateStringValue endDateTime) {
        super(name);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Constructor for this event.
     *
     * @param matcher the matcher that has the relevant captured groups
     * @throws InvalidTaskStringException If the regex doesn't match the pattern
     */
    public Event(Matcher matcher) throws InvalidTaskStringException {
        super(matcher);
        this.startDateTime = new DateStringValue(matcher.group("from"));
        this.endDateTime = new DateStringValue(matcher.group("to"));
    }

    /**
     * Parse an event from a human-readable string.
     *
     * @param readableString the event as a human-readable string
     * @return the event
     * @throws InvalidTaskStringException If the regex doesn't match the pattern
     */
    public static Event parseEvent(String readableString) throws InvalidTaskStringException {
        Matcher matcher = REGEX_PATTERN.matcher(readableString);

        if (matcher.find()) {
            return new Event(matcher);
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
    public static boolean matchesEvent(String matchingString) {
        return REGEX_PATTERN.matcher(matchingString).find();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(FORMAT, super.toString(), startDateTime, endDateTime);
    }
}
