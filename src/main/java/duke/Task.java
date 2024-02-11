package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Task class represents a generic task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the completion status of the task.
     *
     * @param done The completion status.
     */
    public void setDone(boolean done) {
        isDone = done;
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
     * Checks if the task is marked as done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + "[" + (isDone ? "X" : " ") + "] " + description;
    }

    /**
     * Gets the status icon for the task.
     *
     * @return The status icon.
     */
    public String getStatusIcon() {
        return " "; // Default to empty space
    }
}

/**
 * The Todo class represents a specific type of task called Todo.
 */
class Todo extends Task {
    /**
     * Constructs a Todo task with the given description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Gets the status icon for the Todo task.
     *
     * @return The status icon.
     */
    @Override
    public String getStatusIcon() {
        return "T"; // Commands.Todo tasks have "T" as their status icon
    }
}

class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a Deadline task with the given description and deadline.
     *
     * @param description The description of the task.
     * @param by          The deadline of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets the status icon for the Deadline task.
     *
     * @return The status icon.
     */
    @Override
    public String getStatusIcon() {
        return "D"; // Commands.Deadline tasks have "D" as their status icon
    }

    /**
     * Gets the deadline of the task.
     *
     * @return The deadline of the task.
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Returns a string representation of the Deadline task with the deadline.
     *
     * @return A string representation of the task with the deadline.
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ")";
    }
}

/**
 * The Event class represents a specific type of task called Event, with a specified start and end date.
 */
class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs an Event task with the given description, start date, and end date.
     *
     * @param description The description of the task.
     * @param from        The start date of the task.
     * @param to          The end date of the task.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the status icon for the Event task.
     *
     * @return The status icon.
     */
    @Override
    public String getStatusIcon() {
        return "E"; // Commands.Event tasks have "E" as their status icon
    }

    /**
     * Gets the start date of the task.
     *
     * @return The start date of the task.
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * Gets the end date of the task.
     *
     * @return The end date of the task.
     */
    public LocalDate getTo() {
        return to;
    }

    /**
     * Returns a string representation of the Event task with the start and end dates.
     *
     * @return A string representation of the task with the start and end dates.
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ")";
    }
}
