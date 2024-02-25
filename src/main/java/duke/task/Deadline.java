package duke.task;

import duke.utility.DukeException;
import duke.utility.Parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class that represents a Deadline Task.
 */
public class Deadline extends Task {
    /** LocalDateTime Object of when the Deadline Tasks is marked to be done by. */
    private LocalDateTime byDate;

    /**
     * Constructs a Deadline Object.
     *
     * @param description String containing the description of the Task.
     * @param byDate LocalDateTime Object of when the Deadline Tasks is marked to be done by.
     */
    public Deadline(String description, LocalDateTime byDate) {
        super(description);
        this.byDate = byDate;
    }

    /**
     * Returns the Deadline Object as a String.
     *
     * @return String Representation of the Task Object.
     */
    @Override
    public String toString() {
        DateTimeFormatter dTFormatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm");
        String byDateString = byDate.format(dTFormatter);
        return String.format("[D]" + super.toString() + " (by: %s)", byDateString);
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
        case "by":
            this.byDate = Parser.checkDates(updatedDescription);
            break;
        default:
            throw new DukeException("*Honk!* Pengu can only update the 'info' or 'by' of this task");
        }
    }
}
