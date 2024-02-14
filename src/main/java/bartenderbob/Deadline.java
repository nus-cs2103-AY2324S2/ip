package bartenderbob;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Deadline task that has a due date.
 */
public class Deadline extends Task {
    /** The due date for the task */
    private LocalDate dueDate;

    /**
     * Creates an instance of the Deadline task with a description and a
     * due date.
     *
     * @param description Description of the task.
     * @param by Due date of the task.
     * @throws IllegalArgumentException If 'by' parameter is not in yyyy-MM-dd format.
     */
    public Deadline(String description, String by) throws IllegalArgumentException {
        super(description);
        if (!isValidDateFormat(by)) {
            throw new IllegalArgumentException();
        }
        this.dueDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Creates an instance of the Deadline task with a description, due date
     * and whether it has been completed.
     *
     * @param description Description of the task.
     * @param by Due date of the task.
     * @param isDone Represents whether the task has been completed.
     * @throws IllegalArgumentException If 'by' parameter is not in yyyy-MM-dd format.
     */
    public Deadline(String description, String by, boolean isDone) throws IllegalArgumentException {
        super(description, isDone);
        if (!isValidDateFormat(by)) {
            throw new IllegalArgumentException();
        }
        this.dueDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Verifies whether a string is of the format yyyy-MM-dd.
     *
     * @param by Input String.
     * @return Whether the string follows the format yyyy-MM-dd.
     */
    private boolean isValidDateFormat(String by) {
        assert by != null : "String parameter 'by' cannot be null";
        try {
            LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Shows the deadline task information when the user uses the 'list' command.
     *
     * @return Complete deadline task information as a String.
     */
    @Override
    public String show() {
        super.status = isDone ? "X" : " ";
        String dateFormat = this.dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String byFormat = "(by: " + dateFormat + ")";
        return "[D]" + "[" + status + "]" + " " + this.description + " " + byFormat;
    }
}
