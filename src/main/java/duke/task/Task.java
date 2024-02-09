package duke.task;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Stores information regarding tasks to be recorded.
 *
 * @author KohGuanZeh
 */
public abstract class Task {
    // Input format for all datetime inputs.
    public static final DateTimeFormatter INPUT_DATETIME_FORMAT = DateTimeFormatter.ofPattern(
            "dd-MM-yyyy HH:mm");
    // Output format for all datetime outputs.
    public static final DateTimeFormatter OUTPUT_DATETIME_FORMAT = DateTimeFormatter.ofLocalizedDateTime(
            FormatStyle.FULL, FormatStyle.SHORT);
    // Task description.
    private String description;
    // Task completion status.
    private boolean isDone;
    // Priority of task.
    private Priority priority;

    /**
     * Creates a new task with given description.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = Priority.NONE;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Sets task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets task as not done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Returns priority of task to be displayed in chatbot.
     *
     * @return Priority of task for user.
     */
    public String getPriorityPostfix() {
        switch (this.priority) {
        case HIGH:
            return " (HIGH PRIORITY)";
        case LOW:
            return " (LOW PRIORITY)";
        case NONE:
            // Fallthrough intended.
        default:
            return "";
        }
    }

    /**
     * Returns the task and its completion status.
     * Tasks that are done are marked with "[X]" whereas tasks that are not done are marked with "[ ]".
     *
     * @return Task completion status and description.
     */
    public String getTaskInformation() {
        String marker = this.isDone ? "[X]" : "[ ]";
        return marker + " " + this.description + this.getPriorityPostfix();
    }

    /**
     * Returns true if description of task contains given keyword.
     *
     * @param keyword Keyword to match.
     * @return True if task description contains given keyword, else false.
     */
    public boolean descriptionContains(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Returns String to be saved in data file and loaded for future use.
     *
     * @return String data of task.
     */
    public abstract String toDataString();

    /**
     * Returns isDone status in task saving format.
     *
     * @return String indicator of whether task is done.
     */
    public String getIsDoneDataString() {
        return this.isDone ? "1" : "0";
    }

    /**
     * Returns priority status in task saving format.
     *
     * @return String indicator of task priority.
     */
    public String getPriorityDataString() {
        switch (this.priority) {
        case HIGH:
            return "high";
        case LOW:
            return "low";
        case NONE:
            return "none";
        default:
            return "";
        }
    }
}
