package chatbot.task;

import chatbot.task.exception.InvalidTaskStringException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Encapsulates the behaviour of a task.
 *
 * @author Titus Chew
 */
public abstract class Task {
    /**
     * The name of this task.
     */
    private final String name;

    /**
     * Whether this task is completed.
     */
    private boolean isCompleted = false;

    /**
     * The format that a {@link Task} takes.
     */
    private static final String FORMAT = "[%s] %s";


    /**
     * The regex pattern that a {@link Task} takes.
     */
    private static final Pattern REGEX_PATTERN = Pattern.compile(
            "\\[(?<status>.)\\](?<name>.*)");

    /**
     * The text icon to indicate that a task is completed.
     */
    private static final String COMPLETED_ICON = "X";

    /**
     * Constructor for this task.
     *
     * @param name the name of this task
     */
    public Task(String name) {
        this.name = name.trim();
    }

    /**
     * Constructor for this task.
     *
     * @param matcher the matcher that has the relevant captured groups
     * @throws InvalidTaskStringException If the regex doesn't match the pattern
     */
    public Task(Matcher matcher) throws InvalidTaskStringException {
        matcher = REGEX_PATTERN.matcher(matcher.group("task"));

        if (matcher.find()) {
            this.isCompleted = isStatusIconCompleted(matcher.group("status"));
            this.name = matcher.group("name").trim();
        } else {
            throw new InvalidTaskStringException();
        }
    }

    /**
     * Gets a human-readable description of this task.
     *
     * @return this task in a human-readable string
     */
    @Override
    public String toString() {
        return String.format(FORMAT, getStatusIcon(), name);
    }

    /**
     * Mark this task as done.
     */
    public void mark() {
        isCompleted = true;
    }

    /**
     * Change the status of this task back to not done.
     */
    public void unmark() {
        isCompleted = false;
    }

    /**
     * Gets the icon of the text that depends on the task completion status.
     *
     * @return the icon
     */
    private String getStatusIcon() {
        return (isCompleted) ? COMPLETED_ICON : " ";
    }

    /**
     * Checks if the status icon is completed
     *
     * @return true if the icon is {@value COMPLETED_ICON}, otherwise false
     */
    private boolean isStatusIconCompleted(String statusIcon) {
        return COMPLETED_ICON.equals(statusIcon);
    }
}
