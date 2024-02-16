package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task in the Duke application. A deadline task has a specific due date and time.
 * This class extends the Task abstract class, providing implementations for the deadline specific functionalities.
 */
public class Deadline extends Task {

    private String deadline;
    private LocalDateTime deadlineDateFormat;

    /**
     * Constructs a Deadline task with the specified description, which includes the due date and time.
     * The due date and time should follow the format "yyyy-MM-dd HH:mm".
     *
     * @param description The task description including the deadline in "description /by yyyy-MM-dd HH:mm" format.
     * @throws DukeException If the deadline date and time are not in the correct format or are missing.
     */
    public Deadline(String description) throws DukeException {
        this.fullTaskDescription = description;
        try {
            String[] command = description.split(" /by ");
            if (command.length <= 1) {
                throw new DukeException(" OOPS! Your Only Friend cannot take in a deadline entry with no time :(\n");
            }
            this.deadlineDateFormat = LocalDateTime.parse(command[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            this.description = command[0];
        } catch (DateTimeParseException e) {
            throw new DukeException(" OOPS! Please enter deadline in a valid format (yyyy-mm-dd HH:mm). :(\n");
        }

    }

    /**
     * Returns a string representation of the Deadline task, including its status, description, and deadline.
     *
     * @return A formatted string representing the Deadline task.
     */
    @Override
    public String toString() {
        return " [D]" + super.toString() + this.description + " (by: "
                + deadlineDateFormat.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }

    /**
     * Returns a string representation of the Deadline task for saving to a file, including its type, status,
     * description, and deadline.
     *
     * @return A formatted string suitable for saving the Deadline task to a file.
     */
    @Override
    public String toSave() {
        return " D" + (super.getStatusIcon().equals("X") ? " | 1 | " : " | 0 | ") + this.fullTaskDescription;
    }
}
