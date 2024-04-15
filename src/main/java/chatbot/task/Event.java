package chatbot.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chatbot.task.exception.InvalidTaskStringException;
import chatbot.value.DateStringValue;

/**
 * This encapsulates tasks that start at a specific date/time and ends at a specific data/time.
 *
 * @author Titus Chew
 */
public final class Event extends Task {
    /** The icon for the task type. */
    private static final String TASK_TYPE_ICON = "E";

    /** The format/pattern that an {@link Event} takes. */
    private static final String FORMAT = String.format("[%s]%s (from: %s to: %s)", TASK_TYPE_ICON, "%s", "%s", "%s");

    /** The regex pattern that a {@link Event} takes. */
    private static final Pattern REGEX_PATTERN = Pattern.compile(
            String.format("\\[%s\\](?<task>.*)\\(from:(?<from>.*)to:(?<to>.*)\\)", TASK_TYPE_ICON));

    /** Stores the start time of this. */
    private final DateStringValue startDateTime;

    /** Stores the end time of this. */
    private final DateStringValue endDateTime;

    /**
     * Constructor for this event.
     *
     * @param name The name of this event.
     * @param startDateTime The starting date/time of this event.
     * @param endDateTime The ending date/time of this event.
     */
    public Event(String name, DateStringValue startDateTime, DateStringValue endDateTime) {
        super(name);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Constructor for this event.
     *
     * @param matcher The matcher that has the relevant captured groups.
     * @throws InvalidTaskStringException If the regex doesn't match the pattern.
     */
    public Event(Matcher matcher) throws InvalidTaskStringException {
        super(matcher);
        this.startDateTime = new DateStringValue(matcher.group("from"));
        this.endDateTime = new DateStringValue(matcher.group("to"));
    }

    /**
     * Copy constructor for this event.
     *
     * @param event The event to copy.
     */
    public Event(Event event) {
        super(event);
        this.startDateTime = event.startDateTime;
        this.endDateTime = event.endDateTime;
    }

    /**
     * Parses an event from a human-readable string.
     *
     * @param readableString The event as a human-readable string.
     * @return The event.
     * @throws InvalidTaskStringException If the regex doesn't match the pattern.
     */
    public static Event parseEvent(String readableString) throws InvalidTaskStringException {
        Matcher matcher = REGEX_PATTERN.matcher(readableString);

        if (!matcher.find()) {
            throw new InvalidTaskStringException();
        }

        return new Event(matcher);
    }

    /**
     * Checks if the format of a string matches with the pattern.
     *
     * @return True if it matches, otherwise false.
     */
    public static boolean isMatchingEvent(String matchingString) {
        return REGEX_PATTERN.matcher(matchingString).find();
    }

    @Override
    public String toString() {
        return String.format(FORMAT, super.toString(), startDateTime, endDateTime);
    }
}
