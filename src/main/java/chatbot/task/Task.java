package chatbot.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chatbot.task.exception.InvalidTaskStringException;

/**
 * Encapsulates the behaviour of a task.
 *
 * @author Titus Chew
 */
public abstract class Task {
    /** The format that a {@link Task} takes. */
    private static final String FORMAT = "[%s] %s";


    /** The regex pattern that a {@link Task} takes. */
    private static final Pattern REGEX_PATTERN = Pattern.compile(
            "\\[(?<status>.)](?<name>.*)");

    /** The text icon to indicate that a task is completed. */
    private static final String COMPLETED_ICON = "X";

    /** The name of this task.*/
    private final String name;

    /** Whether this task is completed. */
    private boolean isCompleted = false;

    /**
     * Constructor for this task.
     *
     * @param name The name of this task.
     */
    Task(String name) {
        this.name = name.trim();
    }

    /**
     * Constructor for this task.
     *
     * @param matcher The matcher that has the relevant captured groups.
     * @throws InvalidTaskStringException If the regex doesn't match the pattern
     */
    Task(Matcher matcher) throws InvalidTaskStringException {
        Matcher taskPatternMatcher = REGEX_PATTERN.matcher(matcher.group("task"));

        if (!taskPatternMatcher.find()) {
            throw new InvalidTaskStringException();
        }

        this.isCompleted = isStatusIconCompleted(taskPatternMatcher.group("status"));
        this.name = taskPatternMatcher.group("name").trim();
    }

    /**
     * Copy constructor for this task.
     *
     * @param task The task to copy.
     */
    Task(Task task) {
        this.name = task.name;
        this.isCompleted = task.isCompleted;
    }

    /**
     * Copies the task.
     *
     * @param task The non-null task to copy.
     */
    static Task copy(Task task) {
        assert task != null;
        if (task instanceof Event) {
            return new Event((Event) task);
        } else if (task instanceof Deadline) {
            return new Deadline((Deadline) task);
        } else if (task instanceof ToDo) {
            return new ToDo((ToDo) task);
        } else {
            throw new AssertionError("Invalid task type!");
        }
    }

    /**
     * Gets a human-readable description of this task.
     *
     * @return This task as a human-readable string.
     */
    @Override
    public String toString() {
        return String.format(FORMAT, getStatusIcon(), name);
    }

    /**
     * Marks this task as done.
     */
    public void mark() {
        isCompleted = true;
    }

    /**
     * Changes the status of this task back to not done.
     */
    public void unmark() {
        isCompleted = false;
    }

    /**
     * Checks if this task's name contains the pattern.
     *
     * @param pattern The string to find in this task's name.
     * @return True, if the name matches the pattern, otherwise false.
     */
    public boolean isContainingPattern(String pattern) {
        return Pattern
                .compile(Pattern.quote(pattern))
                .matcher(name)
                .find();
    }

    /**
     * Gets the {@link #COMPLETED_ICON} of the text that depends on the task completion status.
     */
    private String getStatusIcon() {
        return (isCompleted) ? COMPLETED_ICON : " ";
    }

    /**
     * Checks if the status icon is completed.
     *
     * @return True if the icon is {@value COMPLETED_ICON}, otherwise false.
     */
    private boolean isStatusIconCompleted(String statusIcon) {
        return COMPLETED_ICON.equals(statusIcon);
    }
}
