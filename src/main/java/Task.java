import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a description and status.
 */
abstract class Task {
    protected String description;
    protected TaskStatus status;

    private static Integer taskNum = 0;

    /**
     * Constructs a Task object with the given description.
     * The initial status is set to TaskStatus.UNDONE.
     * The task number is incremented.
     * 
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.status = TaskStatus.UNDONE;
        Task.taskNum += 1;
    }

    /**
     * Returns the status of the task.
     * 
     * @return the status of the task
     */
    public TaskStatus getStatus() {
        return status;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.status = TaskStatus.DONE;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.status = TaskStatus.UNDONE;
    }

    /**
     * Returns the description of the task.
     * 
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the number of tasks in the list.
     * 
     * @return a string representation of the number of tasks in the list
     */
    public static String getTaskNum() {
        return "Now you have " + Task.taskNum + " tasks in the list.";
    }

    /**
     * Returns a string representation of the task.
     * 
     * @return a string representation of the task
     */
    public abstract String toString();

    /**
     * Encodes a task.
     */
    public abstract String encode();
}

/**
 * Represents a todo task.
 * Inherits from the Task class.
 */
class Todo extends Task {
    /**
     * Constructs a Todo object with the given description.
     * 
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task.
     * 
     * @return A string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T][" + this.getStatus().getStatusIcon() + "] " + this.getDescription();
    }

    /**
     * Encodes the todo task into a string representation.
     * 
     * @return The encoded string representation of the todo task.
     */
    @Override
    public String encode() {
        String status = this.getStatus().isDone() ? "1" : "0";
        return "T | " + status + " | " + this.description;
    }
}

/**
 * Represents a task with a deadline.
 */
class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline object with the given description and deadline.
     *
     * @param description the description of the task
     * @param by          the deadline of the task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return a string representation of the Deadline object
     */
    @Override
    public String toString() {
        return "[D][" + this.getStatus().getStatusIcon() + "] " + this.getDescription() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + ")";
    }

    @Override
    public String encode() {
        String status = this.getStatus().isDone() ? "1" : "0";
        return "D | " + status + " | " + this.description + " | "
                + this.by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}

/**
 * Represents an event task.
 * Inherits from the Task class.
 */
class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructs an Event object with the given description and fromTo details.
     *
     * @param description The description of the event.
     * @param fromTo      The time period of the event.
     */
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return A string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E][" + this.getStatus().getStatusIcon() + "] " + this.getDescription()
                + String.format(" (from: %s to %s)",
                        this.startTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")),
                        this.endTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
    }

    @Override
    public String encode() {
        String status = this.getStatus().isDone() ? "1" : "0";
        return "E | " + status + " | " + this.description + " | " + String.format("%s | %s",
                this.startTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                this.endTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
    }
}