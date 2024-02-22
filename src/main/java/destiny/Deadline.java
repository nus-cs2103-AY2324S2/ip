package destiny;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Extension of task that saves the deadline date of that task.
 */
public class Deadline extends Task {

    protected LocalDateTime by;
    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma");

    /**
     * Base constructor used by Destiny to generate a Deadline object.
     *
     * @param description Title of the task.
     * @param time The deadline of this Deadline task.
     * @throws DukeException If description is empty or time is not given in the correct format.
     */
    public Deadline(String description, String time) throws DukeException {
        super(description);
        time = time.trim();
        if (description.trim().length() == 0 || time.length() == 0) {
            String errorMessage = "Please enter the following:";
            if (description.trim().length() == 0) {
                errorMessage += "\na description for this Deadline task";
            }
            if (time.length() == 0) {
                errorMessage += "\na deadline after the '/by' command";
            }
            throw new DukeException(errorMessage);
        }

        try {
            this.by = LocalDateTime.parse(time, inputFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter the date and time in the following format:\n"
                    + "dd/mm/yyyy hhmm (e.g. 30/01/2024 1234");
        }
    }

    /**
     * Secondary constructor used by Destiny to generate a deadline object from a data txt file.
     *
     * @param logic Indicates if this task should be marked.
     * @param description Title of the task.
     * @param time The deadline of this Deadline task.
     * @throws DukeException If description is empty or time is not given in the correct format.
     */
    public Deadline(String logic, String description, String time) throws DukeException {
        super(description);
        time = time.trim();
        if (description.trim().length() == 0 || time.length() == 0) {
            String errorMessage = "Please enter the following:";
            if (description.trim().length() == 0) {
                errorMessage += "\na description for this Deadline task";
            }
            if (time.length() == 0) {
                errorMessage += "\na deadline after the '/by' command";
            }
            throw new DukeException(errorMessage);
        }

        try {
            this.by = LocalDateTime.parse(time, inputFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter the date and time in the following format:\n"
                    + "dd/mm/yyyy hhmm (e.g. 30/01/2024 1234");
        }

        switch (logic) {
        case "1":
            this.isDone = true;
            break;
        default:
            this.isDone = false;
            break;
        }
    }

    /**
     * Getter for by time in the input format, used to store the time in the destiny.txt data file.
     *
     * @return Reformatted string of to time.
     */
    public String getBy() {
        return by.format(inputFormatter);
    }

    private LocalDateTime getByAsLdt() {
        return by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(displayFormatter) + ")";
    }

    @Override
    public boolean equals(Task task) {
        if (!(task instanceof Deadline)) {
            return false;
        }
        Deadline deadline = (Deadline) task;
        boolean isSimilar = this.description.equals(deadline.getDescription())
                && this.by.isEqual(deadline.getByAsLdt());
        boolean isSame = this == task;
        return isSimilar || isSame;
    }
}
