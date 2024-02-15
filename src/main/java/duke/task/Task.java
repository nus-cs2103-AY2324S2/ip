package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Task class represents a generic task with a description and completion status.
 * Subclasses (ToDo, Event, Deadline) provide specific implementations.
 */
public abstract class Task {
    /** Format style for dates of Tasks. */
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");

    /** Description of duke.command.task as a string */
    private String description;

    /** Boolean Flag of whether the duke.command.task is done */
    private boolean isDone;

    /**
     * Constructs a task.
     *
     * @param description Brief description of task.
     * @param isDone      String representing boolean value.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Creates instances of Task subclasses.
     * This factory method is used to create the appropriate subclass based on type, description, and completion status.
     *
     * @param type            The type of task to create (ToDo, Event, Deadline).
     * @param description     A brief description of the task.
     * @param isDone          The completion status of the task.
     * @param dates           Number of dates params are specific to the task type.
     *                        For EVENTS: pass two LocalDate objects representing start and end dates.
     *                        For DEADLINES: pass one LocalDate object
     *                        For TODOs, no additional data is needed.
     * @return An instance of the specified Task subclass.
     * @throws IllegalArgumentException If the provided task type is unsupported.
     */
    public static Task createTask(TaskType type, String description, boolean isDone, LocalDate... dates) {
        switch (type) {
        case TODO:
            return new ToDo(description, isDone);
        case EVENT:
            return new Event(description, isDone, dates[0], dates[1]);
        case DEADLINE:
            return new Deadline(description, isDone, dates[0]);
        default:
            throw new IllegalArgumentException("Unsupported task type: " + type);
        }
    }

    /** Sets this task as done */
    public void setDone() {
        this.isDone = true;
    }

    /** Sets this task as not done */
    public void setNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String mark = this.isDone ? "[X] " : "[ ] ";
        return mark + this.description;
    }

    /**
     * Gets the date format style from the class.
     *
     * @return DateTimeFormatter object.
     */
    public static DateTimeFormatter getDateFormat() {
        return Task.DATE_FORMATTER;
    }

    /**
     * Returns a string containing information of this task in a clean machine-readable format.
     * "{description},{isDone}"
     *
     * @return String with tokens separated by commas.
     */
    public String getTokens() {
        return String.join(",", this.description, String.valueOf(this.isDone));
    }

}





