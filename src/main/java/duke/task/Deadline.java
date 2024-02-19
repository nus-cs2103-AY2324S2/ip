package duke.task;

import duke.DateFormatter;

/**
 * Represents the deadline task.
 */
public class Deadline extends Task {
    /**
     * Represents the deadline of this task.
     */
    protected String by;

    /**
     * Instantiates a deadline task.
     *
     * @param description Represents the string describing what the task is.
     * @param by Represent the deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        DateFormatter dateFormat = new DateFormatter();
        this.by = dateFormat.convertedDate(by);
    }

    /**
     * Instantiates a deadline task with a specific checkmark.
     *
     * @param description Represents the string describing what the task is.
     * @param by Represent the deadline of the task.
     * @param isDone Represents the boolean describing whether the task is checked.
     */
    public Deadline(String description, String by, Boolean isDone) {
        super(description, isDone);
        DateFormatter dateFormat = new DateFormatter();
        this.by = dateFormat.convertedDate(by);
    }

    /**
     * Returns a string formatting of the deadline task.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns a special string formmating of the deadline task to write into local file.
     *
     * @return A special string representation of the deadline task used in the local file.
     */
    @Override
    public String toSave() {
        String strDone = this.isDone ? "1" : "0";
        return "D|" + strDone + "|" + this.description + "|" + this.by;
    }

    /**
     * Updates the by parameter of this class.
     *
     * @param newBy
     *
     * @return A string representation of the work done.
     */
    public String updateBy(String newBy) {
        String response = "Updated " + this.description + "'s deadline from: " + this.by + "to: " + newBy;
        this.by = newBy;
        return response;
    }
}
