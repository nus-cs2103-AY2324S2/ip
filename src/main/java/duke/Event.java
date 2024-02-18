package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task, a subclass of Task.
 * Event tasks have a description and a specified start and end date.
 */
public class Event extends Task {

    /**
     * The start date of the event.
     */
    protected LocalDate from;

    /**
     * The end date of the event.
     */
    protected LocalDate to;

    /**
     * Constructs a new Event task with the given description, start date, and end date.
     *
     * @param description The description of the event task.
     * @param from        The start date of the event.
     * @param to          The end date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the Event task.
     * The string includes the type of task (represented by "[E]"), the status (done or not done),
     * the task's description, and the start and end dates of the event.
     *
     * @return The string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.getStatus() + " " + super.toString() + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns the string representation of the Event task in a format suitable for saving to a file.
     * The format includes the task type, status, description, start date, and end date.
     *
     * @return The string representation of the Event task in a save format.
     */
    @Override
    public String toSaveFormat() {
        return "E | " + (super.isDone ? "1" : "0") + " | " + super.description + " | " + this.from + " | " + this.to;
    }

    /**
     * Static method to create an Event task from its saved format.
     * The method parses a string array containing the saved information of the task and
     * reconstructs the Event task from this information.
     *
     * @param info The array of strings containing the task's saved data.
     * @return An Event task reconstructed from the saved data.
     */
    public static Event fromSaveFormat(String[] info) {
        Event loadedTask = new Event(info[2], LocalDate.parse(info[3]), LocalDate.parse(info[4]));
        boolean isSavedTaskDone = info[1].equals("1");
        if (isSavedTaskDone) {
            loadedTask.markAsDone();
        }
        return loadedTask;
    }
}
