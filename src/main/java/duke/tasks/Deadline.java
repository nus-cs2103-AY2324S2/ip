package duke.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task with a specific due date and time.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;
    private DateTimeFormatter printFormatter;

    /**
     * Constructs a new Deadline object with the given description, deadline, and print formatter.
     *
     * @param description    The description of the deadline task.
     * @param deadline       The due date and time of the deadline task.
     * @param printFormatter The formatter used to format the deadline for printing.
     */
    public Deadline(String description, LocalDateTime deadline, DateTimeFormatter printFormatter) {
        super(description);
        this.deadline = deadline;
        this.printFormatter = printFormatter;
    }

    /**
     * Constructs a new Deadline object with the given description, status, deadline, and print formatter.
     *
     * @param description    The description of the deadline task.
     * @param status         The completion status of the deadline task.
     * @param deadline       The due date and time of the deadline task.
     * @param printFormatter The formatter used to format the deadline for printing.
     */
    public Deadline(String description, Boolean status, LocalDateTime deadline, DateTimeFormatter printFormatter) {
        super(description, status);
        this.deadline = deadline;
        this.printFormatter = printFormatter;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(this.printFormatter) + ")";
    }

    @Override
    public String convertToFileFormat(DateTimeFormatter storeFormatter) {
        return "D | " + super.convertToFileFormat(storeFormatter) + " | " + this.deadline.format(storeFormatter);
    }

    /**
     * Checks if the specified LocalDateTime is equal to the deadline of this task.
     *
     * @param checkDate The LocalDateTime to compare with the deadline of this task.
     * @return {@code true} if the checkDate is equal to the deadline of this task, {@code false} otherwise.
     */
    public boolean search(LocalDate checkDate) {
        LocalDate deadlineDate = deadline.toLocalDate();
        return deadlineDate.equals(checkDate);
    }

}
