package duke.task;

import duke.utility.DukeException;
import duke.utility.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that represents a Event Task.
 */
public class Event extends Task {
    /** LocalDateTime Object of when the Event Task is stated to begin. */
    private LocalDateTime fromDate;
    /** LocalDateTime Object of when the Event Task is stated to end. */
    private LocalDateTime toDate;

    /**
     * Constructs a Event Object.
     *
     * @param description String containing the description of the Task.
     * @param fromDate LocalDateTime Object of when the Event Task is stated to begin.
     * @param toDate LocalDateTime Object of when the Event Task is stated to end.
     */
    public Event(String description, LocalDateTime fromDate, LocalDateTime toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     * Returns the Event Object as a String
     *
     * @return String Representation of the Event Object.
     */
    @Override
    public String toString() {
        DateTimeFormatter dTFormatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm");
        String fromDateString = fromDate.format(dTFormatter);
        String toDateString = toDate.format(dTFormatter);
        return String.format("[E]" + super.toString() + " (from: %s to: %s)", fromDateString, toDateString);
    }

    /**
     * Updates one of the value/description of the fields in this task.
     *
     * @param field field to be updated.
     * @param updatedDescription description to be updated to.
     * @throws DukeException
     */
    @Override
    public void updateTaskDescription(String field, String updatedDescription) throws DukeException {
        String fieldInput = field.toLowerCase();
        switch (fieldInput) {
        case "info":
            this.description = updatedDescription;
            break;
        case "from":
            this.fromDate = Parser.checkDates(updatedDescription);
            break;
        case "to":
            this.toDate = Parser.checkDates(updatedDescription);
            break;
        default:
            throw new DukeException("*Honk!* Pengu can only update the 'info', 'from' or 'to' of this task");
        }
    }
}
