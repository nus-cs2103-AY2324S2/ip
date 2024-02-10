package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task, a subclass of Task.
 * Deadline tasks have a description and a specified deadline date.
 */
public class Deadline extends Task {

    /**
     * The deadline date of the task.
     */
    protected LocalDate by;

    /**
     * Constructs a new Deadline task with the given description and deadline date.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline date of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the Deadline task.
     * The string includes the type of task (represented by "[D]"), the status (done or not done),
     * the task's description, and the deadline date.
     *
     * @return The string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.getStatus() + " " + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns the string representation of the Deadline task in a format suitable for saving to a file.
     * The format includes the task type, status, description, and deadline date.
     *
     * @return The string representation of the Deadline task in a save format.
     */
    @Override
    public String toSaveFormat() {
        return "D | " + (super.isDone ? "1" : "0") + " | " + super.description + " | " + this.by;
    }

    /**
     * Static method to create a Deadline task from its saved format.
     * The method parses a string array containing the saved information of the task and
     * reconstructs the Deadline task from this information.
     *
     * @param info The array of strings containing the task's saved data.
     * @return A Deadline task reconstructed from the saved data.
     */
    public static Deadline fromSaveFormat(String[] info) {
        Deadline loadedTask = new Deadline(info[2], LocalDate.parse(info[3]));
        boolean isSavedTaskDone = info[1].equals("1");
        if (isSavedTaskDone) {
            loadedTask.markAsDone();
        }
        return loadedTask;
    }
}
