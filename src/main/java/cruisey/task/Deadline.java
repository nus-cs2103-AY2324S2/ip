package cruisey.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import cruisey.exception.DukeException;
import cruisey.ui.Ui;

/**
 * The Deadline class represents a task with a specific deadline.
 * It is a subclass of the Task class and includes information about the deadline.
 */
public class Deadline extends Task {

    /**
     * The LocalDateTime representing the deadline.
     */
    protected LocalDateTime by;
    /**
     * The string representation of the deadline (used when parsing or if LocalDateTime parsing fails).
     */
    protected String byString;
    private Ui ui;

    /**
     * Constructs a Deadline object with the specified description and deadline string.
     *
     * @param description The description of the deadline task.
     * @param byString    The string representation of the deadline.
     * @param priority    The priority of the task.
     * @throws DukeException If there are issues with the provided description or deadline.
     */
    public Deadline(String description, String byString, Ui ui, TaskPriority priority) throws DukeException {
        super(TaskType.D, description, priority);
        this.byString = byString.trim();
        this.ui = ui;
        this.priority = priority;
        initializeDeadline();
    }

    /**
     * Initializes the deadline for a Deadline task. Parses the input deadline string and sets
     * the corresponding LocalDateTime. Checks for errors in the input and displays appropriate
     * error messages using the Ui class.
     *
     * @throws DukeException If there is an issue with initializing the deadline.
     */
    private void initializeDeadline() throws DukeException {
        try {
            if (!this.byString.isEmpty()) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                this.by = LocalDateTime.parse(byString, dateTimeFormatter);
            }
        } catch (DateTimeParseException e) {
            this.by = null;
        }

        assert !(this.by != null && !this.byString.isEmpty()) : "Both LocalDateTime and byString should "
                + "not be non-null.";
        assert (this.by == null && this.byString.isEmpty()) : "Didnt enter correctly";

        if (this.by == null && this.byString.isEmpty()) {
            Ui.showError("by when? You forgot to enter \"/by\"");
        } else if (description.isEmpty()) {
            Ui.showError("you forgot to enter the task for which you have to complete it by");
        }
    }

    /**
     * Gets the representation of the deadline (either LocalDateTime or the original string).
     *
     * @return The deadline as an Object (either LocalDateTime or String).
     */
    public Object getBy() {
        assert (this.by != null || !this.byString.isEmpty()) : "Either LocalDateTime or byString should be non-null.";
        assert !(this.by != null && !this.byString.isEmpty()) : "Both LocalDateTime and byString should not be "
                + " non-null.";
        return (this.by != null) ? this.by : this.byString;
    }

    /**
     * Gets the LocalDateTime representation of the deadline.
     *
     * @return The deadline as a LocalDateTime object.
     */
    public LocalDateTime getByTime() {
        assert this.by == null || !this.byString.isEmpty() : "Either LocalDateTime should be null or byString should "
                + "not be empty.";
        return this.by;
    }

    /**
     * Gets the string representation of the deadline.
     *
     * @return The deadline as a string.
     */
    public String getByString() {
        assert this.by == null || !this.byString.isEmpty() : "Either LocalDateTime should be null or byString should "
               + "not be empty.";
        return this.byString;
    }

    /**
     * Overrides the toString method to provide a formatted string representation of the Deadline task.
     *
     * @return Formatted string representation of the Deadline task.
     */
    public String getMessage() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String byStringFormatted = this.by != null
                ? " (by: " + by.format(formatter) + ")"
                : (!this.byString.isEmpty() ? " (by: " + this.byString + ")" : "");

        String priorityFormatted = (priority != null) ? " [Priority: " + priority + "]" : "";

        assert (this.by != null || !this.byString.isEmpty()) : "Either LocalDateTime or byString should be non-null.";
        assert !(this.by != null && !this.byString.isEmpty()) : "Both LocalDateTime and byString should not "
                + "be non-null.";

        return ui.printMessage("Coool, I've added this task:\n [D][" + getStatusIcon() + "] "
                + getDescription() + byStringFormatted + priorityFormatted);
    }

}
