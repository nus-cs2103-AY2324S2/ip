package whisper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Task class represents a task in the Whisper application.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected TaskCategory category;
    protected String from;
    protected String to;
    protected String by;
    protected LocalDateTime deadline;
    protected LocalDateTime eventFromDateTime;
    protected LocalDateTime eventToDateTime;

    /**
     * Constructs a task with the specified description and category.
     *
     * @param description The description of the task.
     * @param cat         The category of the task.
     */
    public Task(String description, TaskCategory cat) {
        this.description = description;
        this.isDone = false;
        this.category = cat;
    }

    /**
     * Constructs a deadline task with the specified description, category, and deadline.
     *
     * @param description The description of the deadline task.
     * @param cat         The category of the task (deadline).
     * @param deadline    The deadline of the task.
     */
    public Task(String description, TaskCategory cat, LocalDateTime deadline) {
        this(description, cat);
        this.deadline = deadline;
    }

    /**
     * Constructs an event task with the specified description, category, start date and time, and end date and time.
     *
     * @param description      The description of the event task.
     * @param cat              The category of the task (event).
     * @param fromDateTime     The start date and time of the event.
     * @param toDateTime       The end date and time of the event.
     */
    public Task(String description, TaskCategory cat, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        this(description, cat);
        this.eventFromDateTime = fromDateTime;
        this.eventToDateTime = toDateTime;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return The status icon, indicating whether the task is done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    /**
     * Gets the category of the task.
     *
     * @return The category of the task.
     */
    public TaskCategory getTaskCat() {
        return category;
    }

    // setter event
    public Task setFrom(String from) {
        this.from = from;
        return this;
    }

    // setter event
    public Task setTo(String to) {
        this.to = to;
        return this;
    }

    // setter deadline
    public Task setBy(String by) {
        this.by = by;
        return this;
    }

    // getter for date and time
    public LocalDateTime getDeadline() {
        return deadline;
    }

    public LocalDateTime getEventFromDateTime() {
        return eventFromDateTime;
    }

    public LocalDateTime getEventToDateTime() {
        return eventToDateTime;
    }

    // getter event
    public String getFrom() {
        return from;
    }

    // getter event
    public String getTo() {
        return to;
    }

    // getter deadline
    public String getBy() {
        return by;
    }

    // format datetime details for all task categories
    private String formatDateTime() {
        if (category == TaskCategory.D && deadline != null) {
            return " (by: " + deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm")) + ")";
        } else if (category == TaskCategory.E && eventFromDateTime != null && eventToDateTime != null) {
            return " (from: " + eventFromDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm")) +
                    " to: " + eventToDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm")) + ")";
        }
        return "";
    }

    @Override
    public String toString() {
        return "[" + category + "]" + getStatusIcon() + getDescription()
                + category.getDetails(this);
    }

    /**
     * The TaskCategory enum represents different categories of tasks.
     */
    public enum TaskCategory {
        T {
            @Override
            public String getDetails(Task task) {
                return "";
            }
        },
        E {
            @Override
            public String getDetails(Task task) {
                return " (from: " + task.getEventFromDateTime().format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                        + " to: " + task.getEventToDateTime().format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                        + ")";
            }
        },
        D {
            @Override
            public String getDetails(Task task) {
                if (task.getDeadline() != null) {
                    return " (by: " + task.getDeadline().format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
                } else {
                    return "";
                }
            }
        };

        /**
         * Returns additional details specific to the task category.
         *
         * @param task The task for which details are requested.
         * @return Additional details for the task category.
         */
        public abstract String getDetails(Task task);
    }
}
