package lucky.tasks;

import java.time.LocalDateTime;

import lucky.common.Utils;

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
     * @return A formatted string that represents the object's data in a storage-friendly format.
     */
    @Override
    public String toStorageString() {
        int statusValue = this.getIsMarked() ? 1 : 0;
        assert statusValue == 0 || statusValue == 1 : "Status value must be equal to 0 or 1";

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("deadline~%d~%s~%s", statusValue, this.description,
                Utils.inputFormat(this.by)));

        for (String tag : this.getTags()) {
            sb.append("~").append(tag);
        }

        return sb.toString();
    }

    /**
     * Returns a string representation of a Deadline with its status and description.
     *
     * @return A string representation of a Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Utils.dateTimeToString(this.by) + ")";
    }

}
