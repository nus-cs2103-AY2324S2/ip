package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class represents a deadline task by encapsulating information about a specific deadline,
 * including the description, due date and time.
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    /**
     * Returns a Deadline object as the public constructor for this class.
     *
     * @param taskName Description of task.
     * @param deadline Deadline of the task.
     */
    public Deadline(String taskName, String deadline) throws DateTimeParseException {
        super(taskName);
        this.deadline = LocalDateTime.parse(deadline, formatter);

    }

    /**
     * Formats the details of the Deadline object as a String to be written to a text file.
     *
     * @return A String containing the description and due date and time of a Deadline object.
     */
    @Override
    public String saveFormat() {
        int check = this.isCompleted ? 1 : 0;
        assert check == 1 || check == 0 : "check should return 1 or 0";
        return String.format("D | %d | %s | %s\n", check, this.taskName,
                this.deadline.format(formatter));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy hh:mma")));
    }
}
