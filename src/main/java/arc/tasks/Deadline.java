package arc.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import arc.exceptions.tasks.EmptyDescriptionException;
import arc.storage.Storage;

/**
 * Represents a Deadline task in the Arc application.
 * A Deadline task is a task that needs to be done before a specific date.
 */
public class Deadline extends Task {
    /**
     * The formatter for displaying the deadline date in a human-readable form.
     */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * The deadline date by which the task needs to be completed.
     */
    private final LocalDate by;

    /**
     * Constructs a new Deadline task with the specified description and deadline date.
     * The task is initially not completed.
     *
     * @param description The description of the deadline task.
     * @param by The deadline date by which the task needs to be completed.
     * @throws EmptyDescriptionException If the description is empty.
     */
    public Deadline(String description, LocalDate by) throws EmptyDescriptionException {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a new Deadline task with the specified description, completion status, and deadline date.
     *
     * @param description The description of the deadline task.
     * @param isDone The completion status of the task.
     * @param by The deadline date by which the task needs to be completed.
     * @throws EmptyDescriptionException If the description is empty.
     */
    public Deadline(String description, boolean isDone, LocalDate by) throws EmptyDescriptionException {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the deadline date by which the task needs to be completed.
     *
     * @return The deadline date.
     */
    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Serializes the Deadline task to a string format for storage.
     * This includes the task type, description, deadline date, and completion status.
     *
     * @return A string representing the serialized form of the Deadline task.
     */
    @Override
    public String serialize() {
        ArrayList<String> taskArgs = new ArrayList<>();

        taskArgs.add("deadline");
        taskArgs.add(this.getDescription());
        taskArgs.add(this.getBy().toString());
        taskArgs.add(this.isDone() ? "1" : "0");

        return String.join(Storage.STORAGE_DELIMITER, taskArgs);
    }

    /**
     * Returns a string representation of the Deadline task, including its status icon, description, and deadline date.
     *
     * @return The string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by.format(DATE_TIME_FORMATTER));
    }
}
