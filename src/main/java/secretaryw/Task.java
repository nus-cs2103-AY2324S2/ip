package secretaryw;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with various attributes such as type, description, and completion status.
 */
class Task {
    protected TaskType type;
    protected String description;
    protected boolean isDone;

    protected LocalDate deadline;
    protected LocalDate startTime;
    protected LocalDate endTime;

    /**
     * Constructs a Task object for a TO DO task with the given type and description.
     * The task is initially marked as not done.
     * @param type The type of the task.
     * @param description The description of the task.
     */
    public Task(TaskType type, String description) {
        this.type = type;
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task object for a DEADLINE task with the given type, description, and deadline.
     * The task is initially marked as not done.
     * @param type The type of the task.
     * @param description The description of the task.
     * @param deadline The deadline of the task.
     */
    public Task(TaskType type, String description, String deadline) {
        this.type = type;
        this.description = description;
        this.isDone = false;
        this.deadline = LocalDate.parse(deadline, DateTimeFormatter.ofPattern("d/M/yyyy"));
    }

    /**
     * Constructs a Task object for an EVENT task with the given type, description, start time, and end time.
     * The task is initially marked as not done.
     * @param type The type of the task.
     * @param description The description of the task.
     * @param startTime The start time of the task.
     * @param endTime The end time of the task.
     */
    public Task(TaskType type, String description, String startTime, String endTime) {
        this.type = type;
        this.description = description;
        this.isDone = false;
        this.startTime = LocalDate.parse(startTime, DateTimeFormatter.ofPattern("d/M/yyyy"));
        this.endTime = LocalDate.parse(endTime, DateTimeFormatter.ofPattern("d/M/yyyy"));
    }

    /**
     * Constructs a Task object from file data with the given type, description, and completion status.
     * @param type The type of the task.
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     */
    public Task(TaskType type, String description, boolean isDone) {
        this.type = type;
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Constructs a Task object from file data for a DEADLINE task with the given type, description, deadline, and completion status.
     * @param type The type of the task.
     * @param description The description of the task.
     * @param deadline The deadline of the task.
     * @param isDone The completion status of the task.
     */
    public Task(TaskType type, String description, String deadline, boolean isDone) {
        this.type = type;
        this.description = description;
        this.isDone = isDone;
        this.deadline = LocalDate.parse(deadline);
    }


    /**
     * Constructs a Task object from file data for an EVENT task with the given type, description, start time, end time, and completion status.
     * @param type The type of the task.
     * @param description The description of the task.
     * @param startTime The start time of the task.
     * @param endTime The end time of the task.
     * @param isDone The completion status of the task.
     */
    public Task(TaskType type, String description, String startTime, String endTime, boolean isDone) {
        this.type = type;
        this.description = description;
        this.startTime = LocalDate.parse(startTime);
        this.endTime = LocalDate.parse(endTime);
        this.isDone = isDone;
    }

    /**
     * Retrieves the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Retrieves the type of the task.
     * @return The type of the task.
     */
    public TaskType getType() {
        return this.type;
    }

    /**
     * Checks if the task is marked as done.
     * @return True if the task is marked as done, otherwise false.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Retrieves the status icon of the task.
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Converts the task to a string representation suitable for writing to a file.
     * @return The string representation of the task for file storage.
     */
    public String toFileString() {
        switch (type) {
            case TODO:
                return "T | " + (isDone ? "1" : "0") + " | " + description;
            case DEADLINE:
                return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + deadline;
            case EVENT:
                return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + startTime + " | " + endTime;
            default:
                return "";
        }
    }

    /**
     * Converts the task to a string representation.
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        switch (type) {
        case TODO:
            return "[T]" + getStatusIcon() + " " + description;
        case DEADLINE:
            String formattedDeadline = deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            return "[D]" + getStatusIcon() + " " + description + " (by: " + formattedDeadline + ")";
        case EVENT:
            String formattedStart = startTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            String formattedEnd = endTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            return "[E]" + getStatusIcon() + " " + description + " (from: " + formattedStart + " to: " + formattedEnd + ")";
        default:
            return "";
        }
    }
}

enum TaskType {
    TODO, DEADLINE, EVENT
}