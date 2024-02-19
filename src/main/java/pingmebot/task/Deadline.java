package pingmebot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A category of task that has a task description and a datetime to complete it by.
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    protected String description;

    /**
     * Creates a Deadline object with a specified task description and a datetime to complete the task by.
     *
     * @param description Task's description.
     * @param by Datetime to finish the task by.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.description = description;
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm")) + ")";
    }

    /**
     * Returns a string representation that is saved to the local file whenever a deadline task is completed.
     *
     * @param isCompleted Integer boolean flag to indicate if the task is completed.
     * @return A string representation when a deadline task is completed.
     */
    public String updateDeadlineText(int isCompleted) {
        String text = "";
        text += "deadline | " + isCompleted + " | " + this.description + " | "
                + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
        return text;
    }

    /**
     * Returns true only when 2 objects have the same description and the date time to complete the task by.
     *
     * @param obj The object that is being compared to.
     * @return A boolean to indicate if 2 objects have the same description and the date time to complete the task by.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Deadline otherDeadline = (Deadline) obj;
        return this.description.equals(otherDeadline.description)
                && this.by.equals(otherDeadline.by);
    }
}
