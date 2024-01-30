package chatbot.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ToDos: tasks without any date/time attached to it.
 *
 * @author Titus Chew
 */
public class ToDo extends Task {
    /**
     * The icon for the task type.
     */
    static final String TASK_TYPE_ICON = "T";

    /**
     * The format that a {@link ToDo} takes.
     */
    private static final String FORMAT = String.format("[%s]%s", TASK_TYPE_ICON, "%s");

    /**
     * The regex pattern that a {@link Deadline} takes.
     */
    private static final String REGEX_PATTERN =
            String.format("\\[%s\\](?<task>.*)", TASK_TYPE_ICON);

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
     * @throws IllegalStateException If the regex doesn't match the pattern
     */
    public ToDo(Matcher matcher) throws IllegalStateException {
        super(matcher);
    }

    /**
     * Parse a to-do from a human-readable string.
     *
     * @param readableString the to-do as a human-readable string
     * @return the to-do
     */
    public static ToDo parseToDo(String readableString) {
        Matcher matcher = Pattern
                .compile(REGEX_PATTERN)
                .matcher(readableString);

        matcher.find();
        return new ToDo(matcher);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format(FORMAT, super.toString());
    }
}
