package duke;

import java.time.LocalDate;

/**
 * Constructs a deadline task.
 */
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
     * Returns the due date of the deadline.
     * @return
     */
    public LocalDate getBy() {
        return by;
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

    /**
     * Returns whether the task is equal to another object.
     *
     * @param obj The object to be compared.
     * @return Whether the task is equal to the object.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Deadline deadline = (Deadline) obj;
        return this.getDescription().equals(deadline.getDescription()) &&
                this.getBy().equals(deadline.getBy());
    }

}
