package arc.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import arc.Arc;
import arc.exceptions.tasks.EmptyDescriptionException;

/**
 * Represents an Event task in the Arc application.
 * An Event task is a task that starts and ends at specific times.
 */
public class Event extends Task {
    /**
     * The formatter for displaying the event dates in a human-readable form.
     */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * The start date of the event.
     */
    private final LocalDate from;

    /**
     * The end date of the event.
     */
    private final LocalDate to;

    /**
     * Constructs a new Event task with the specified description and time range (start and end dates).
     * The task is initially not completed.
     *
     * @param description The description of the event task.
     * @param from The start date of the event.
     * @param to The end date of the event.
     * @throws EmptyDescriptionException If the description is empty.
     */
    public Event(String description, LocalDate from, LocalDate to) throws EmptyDescriptionException {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs a new Event task with the
     * specified description, completion status, and time range (start and end dates).
     *
     * @param description The description of the event task.
     * @param isDone The completion status of the task.
     * @param from The start date of the event.
     * @param to The end date of the event.
     * @throws EmptyDescriptionException If the description is empty.
     */
    public Event(String description, boolean isDone, LocalDate from, LocalDate to) throws EmptyDescriptionException {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start date of the event.
     *
     * @return The start date.
     */
    public LocalDate getFrom() {
        return this.from;
    }

    /**
     * Returns the end date of the event.
     *
     * @return The end date.
     */
    public LocalDate getTo() {
        return this.to;
    }

    /**
     * Serializes the Event task to a string format for storage.
     * This includes the task type, description, start and end dates, and completion status.
     *
     * @return A string representing the serialized form of the Event task.
     */
    @Override
    public String serialize() {
        ArrayList<String> taskArgs = new ArrayList<>();

        taskArgs.add("event");
        taskArgs.add(this.getDescription());
        taskArgs.add(this.getFrom().toString());
        taskArgs.add(this.getTo().toString());
        taskArgs.add(this.isDone() ? "1" : "0");

        return String.join(Arc.ARG_DELIMITER, taskArgs);
    }

    /**
     * Returns a string representation of the Event task, including its status icon, description, and time range.
     *
     * @return The string representation of the Event task.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
            this.from.format(DATE_TIME_FORMATTER), this.to.format(DATE_TIME_FORMATTER));
    }
}
