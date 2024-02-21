package seedu.chatteroo.tasks;

import seedu.chatteroo.ChatterooException;
import seedu.chatteroo.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;

/**
 * Represents a deadline task with a description, a status of whether it is done and a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for the Deadline class.
     * @param description The description of the task.
     * @param by The deadline of the task.
     * @throws DateTimeException If the date and time is not in the correct format.
     */
    public Deadline(String description, String by) throws ChatterooException {
        super(description, "D");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            this.by = LocalDateTime.parse(by.trim(), dateFormat);
        } catch (DateTimeException e) {
            throw new ChatterooException("Please enter a valid date and time in the format:\n"
                    + "YYYY-MM-DD HHmm!\n"
                    + "For example, 2024-01-31 1800");
        }
    }
    //Overridden toString method to print type of task, description and time
    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        String formattedBy = this.by.format(dateFormat);
        return "[D]" + super.toString() + "(by: " + formattedBy + ")";
    }

    public LocalDateTime getBy() {
        return this.by;
    }
}
