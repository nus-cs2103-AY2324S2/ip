package task;

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
    private static final String REGEX_PATTERN = "\\[(?<status>.)\\](?<name>.*)";

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
     * @throws IllegalStateException If the regex doesn't match the pattern
     */
    public Task(Matcher matcher) throws IllegalStateException {
        matcher = Pattern
                .compile(REGEX_PATTERN)
                .matcher(matcher.group("task"));

        matcher.find();
        this.isCompleted = isStatusIconCompleted(matcher.group("status"));
        this.name = matcher.group("name").trim();
    }

    /**
     * Parse a task from a human-readable string.
     *
     * @param readableString the task as a human-readable string
     * @return the task
     * @throws IllegalStateException If the regex doesn't match the pattern
     */
    static Task parseTask(String readableString) throws IllegalStateException {
        // determine the type of task
        String taskType = readableString.substring(1, 2);
        if (taskType.equals(Deadline.TASK_TYPE_ICON)) {
            return Deadline.parseDeadline(readableString);
        } else if (taskType.equals(Event.TASK_TYPE_ICON)) {
            return Event.parseEvent(readableString);
        } else if (taskType.equals(ToDo.TASK_TYPE_ICON)) {
            return ToDo.parseToDo(readableString);
        }

        throw new IllegalStateException("The string doesn't match any task type!");
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
