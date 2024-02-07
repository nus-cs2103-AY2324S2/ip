package yippee.tasks;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import yippee.exceptions.InvalidCommandException;

/**
 * Represents tasks of type Deadline.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Constructor for tasks of type Deadline.
     * @param name Name of Deadline task.
     * @param deadline Date that task needs to be completed by.
     * @throws InvalidCommandException If date format is incorrect.
     */
    public Deadline(String name, String deadline) throws InvalidCommandException {
        super(name);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeException e) {
            throw new InvalidCommandException(
                    "Invalid input format for date :( Please use the format yyyy-mm-dd instead!");
        }
    }

    @Override
    public String toString() {
        String formattedDate = this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return String.format("%s %s (by: %s)", "[D]", super.toString(), formattedDate);
    }
    @Override
    public String dataString() {
        return String.format("%s|%s|%s\n", "D", super.dataString(), deadline);
    }
}
