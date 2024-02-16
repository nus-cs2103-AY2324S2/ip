package duke.tasks;

import java.time.LocalDateTime;

import duke.common.Utils;

/**
 * The Deadline class represents a task with a specific deadline.
 */
public class Deadline extends Task {

    private LocalDateTime by;

    /**
     * Defines a constructor for the `Deadline` class.
     * 
     * @param description Description of the Deadline task.
     * @param dateBy The deadline date of the task.
     */
    public Deadline(String description, LocalDateTime dateBy) {
        super(description);
        this.by = dateBy;
    }

    /**
     * Converts the object's data into a formatted string for storage.
     * 
     * @return The method is returning a formatted string that represents the object's data in a
     *         storage-friendly format.
     */
    @Override
    public String toStorageString() {
        int statusValue = this.getStatus() ? 1 : 0;

        return String.format("deadline~%d~%s~%s", statusValue, this.description,
                Utils.formatInput(this.by));
    }

    /**
     * Returns a string representation of a Deadline with its status and description.
     * 
     * @return Returns a string representation of a Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Utils.dateTimeToString(this.by) + ")";
    }

}
