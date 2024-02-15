package thecount.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import thecount.ui.AddToListReply;
import thecount.ui.Reply;

/**
 * Represents a task with a deadline in the to-do list.
 */
public class Deadline extends Task {
    private LocalDate deadlineTime;

    /**
     * Constructs a deadline task with the given description and deadline time.
     *
     * @param description The description of the deadline task.
     * @param deadlineTime The deadline time of the task.
     * @throws DateTimeParseException If the deadline time cannot be parsed.
     */
    public Deadline(String description, String deadlineTime) throws DateTimeParseException {
        super(description);
        try {
            this.deadlineTime = convertStringToDate(deadlineTime);
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    /**
     * Converts the string representation of the deadline time to a LocalDate object.
     *
     * @param deadlineTime The string representation of the deadline time.
     * @return The LocalDate object representing the deadline time.
     * @throws DateTimeParseException If the deadline time cannot be parsed.
     */
    private LocalDate convertStringToDate(String deadlineTime) throws DateTimeParseException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(deadlineTime, formatter);
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    /**
     * Converts the deadline time to a string representation.
     *
     * @param deadlineTime The deadline time.
     * @return The string representation of the deadline time.
     */
    private String convertDateToString(LocalDate deadlineTime) {
        return deadlineTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Displays a message related to the deadline task.
     *
     * @param currSize The current size of the list.
     */
    public String displayMessage(int currSize) {
        Reply replyToUser = new AddToListReply(this.toString(), currSize);
        return replyToUser.displayMessage();
    }

    /**
     * Gets the deadline time as a string.
     *
     * @return The deadline time as a string.
     */
    public String getDeadlineTime() {
        return this.deadlineTime.toString();
    }

    /**
     * Gets the type of the task.
     *
     * @return The type of the task.
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Gets the description of the task, including the deadline time.
     * (More so for writing to file)
     *
     * @return The description of the task.
     */
    @Override
    public String getDesc() {
        return super.getDesc() + " | " + this.deadlineTime;
    }

    /**
     * Converts the deadline task to a string representation.
     *
     * @return The string representation of the deadline task.
     */
    public String toString() {
        return "[D]" + super.toString() + " (by: " + convertDateToString(this.deadlineTime) + ")";
    }
}
