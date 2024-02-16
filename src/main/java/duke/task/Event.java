package duke.task;


import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * Represents an event task with a start and end datetime.
 * This class extends the Task abstract class, providing implementations for the event specific functionalities.
 */
public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs a new Event instance with the specified description, including the start and end datetime.
     *
     * @param description The event description in the format "event_description /from start_datetime /to end_datetime".
     * @throws DukeException If the datetime format is invalid or the description format is incorrect.
     */
    public Event(String description) throws DukeException {
        this.fullTaskDescription = description;
        try {
            String[] command = description.split(" /from ", 2);
            if (command.length <= 1) {
                throw new DukeException(" OOPS! Your Only Friend cannot take in an event entry with no timeline :(\n");
            }

            String timeline = command[1];
            String[] eventDates = timeline.split(" /to ");
            if (eventDates.length <= 1) {
                throw new DukeException(" OOPS! Your Only Friend cannot take in an event entry with no timeline :(\n");
            }

            this.from = LocalDateTime.parse(eventDates[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            this.to = LocalDateTime.parse(eventDates[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            this.description = command[0];

        } catch (DateTimeParseException e) {
            throw new DukeException(" OOPS! Please enter deadline in a valid format (yyyy-mm-dd HH:mm). :(\n");
        }
    }

    /**
     * Returns a string representation of the event task, including its status, description, and datetime range.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return " [E]" + super.toString() + this.description + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }

    /**
     * Returns a string representation of the event task suitable for saving to a file.
     * The saved format includes the task type, status, description, and datetime range.
     *
     * @return A string suitable for saving the event task to a file.
     */
    @Override
    public String toSave() {
        return " E" + (super.getStatusIcon().equals("X") ? " | 1 | " : " | 0 | ") + this.fullTaskDescription;
    }

}
