package duke;

import java.time.LocalDate;

public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a Deadline with a description and due date.
     *
     * @param description The description of the deadline task.
     * @param by          The due date of the deadline.
     * @throws DukeException If the date string is not in a valid format.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = DateChecker.parseDate(by);
    }

    /**
     * Returns the description of the saved task.
     *
     * @return The description of the saved task.
     */
    @Override
    public String saveData() {
        return "D | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + this.by;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateChecker.formatDate(this.by) + ")";
    }
}
