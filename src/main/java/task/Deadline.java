package task;

import utilities.DateTime;

public class Deadline extends Task {
    /**
     * The due date or deadline of the given task in string form.
     */
    private String by;
    /**
     * The due date or deadline of the given task as a date time object.
     */
    private DateTime deadline;

    /**
     * Deadline class constructor.
     * @param description Task description.
     * @param by The due date or deadline of the given task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.deadline = new DateTime(by);
    }

    /**
     * Deadline class constructor.
     * @param description The task description.
     * @param isDone A variable that determines whether a task has been completed.
     * @param by The due date or deadline of the given task.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.deadline = new DateTime(by);
    }

    /**
     * Outputs the task as a string to the user.
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + this.deadline.formatDate() + "hrs)";
    }

    /**
     * Outputs the task as a string to be stored in the save file.
     * @return A string representing the task to be saved.
     */
    @Override
    public String toFileString() {
        return "D" + super.toFileString() + "|" + this.by;
    }
}
