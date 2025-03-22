package felix.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import felix.exception.FelixException;

/**
 * Class representing tasks with an end date
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructor for Deadline class.
     *
     * @param description Description of task.
     * @param deadline Date and time by which the task be completed.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDateTime.parse(deadline, INPUT_FORMATTER);
    }

    /**
     * Returns a new Deadline instance with updated description and end.
     * @param paramString String containing new description and new end.
     */
    @Override
    public Deadline updateTask(String paramString) throws FelixException {
        try {
            String[] params = paramString.split(" /by ");
            return new Deadline(params[0], params[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new FelixException("Command does not follow this format: "
                    + "update {index} {description} by {end_datetime}");
        } catch (DateTimeParseException e) {
            throw new FelixException("datetime for deadline is not in the format \"yyyy-MM-dd HHmm\"");
        }
    }

    /**
     * Returns the String representation of the Deadline instance to be written to file.
     */
    @Override
    public String toFileString() {
        return String.format("D | %s | %s | %s", this.getStatusIcon(),
                this.getDescription(), this.deadline.format(INPUT_FORMATTER));
    }


    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline.format(OUTPUT_FORMATTER));
    }
}
