import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates the data and behaviour of a Deadline task.
 *
 * @author Huang Zhuoyan, Celeste
 * @version CS2103T AY24/25 Semester 1, G07
 */
public class Deadline extends Task {
    private LocalDateTime deadline;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");

    /**
     * Constructs a new Deadline task with the given name and deadline.
     *
     * @param name The name of the Deadline task.
     * @param time The LocalDateTime object containing the deadline.
     */
    public Deadline(String name, LocalDateTime time) {
        super(name);
        this.deadline = time;
    }

    /**
     * Formats the deadline of the task according to a standard format.
     *
     * @param time The LocalDateTime object to be formatted.
     * @return The formatted deadline, as a String.
     */
    public String deadlineFormatter(LocalDateTime time) {
        return time.format(formatter);
    }

    @Override
    public String printTask() {
        return "[D]" + super.printTask() + " (by: " + deadlineFormatter(this.deadline) + ")";
    }

    @Override
    public String saveTask() {
        return "D | " + super.saveTask() + " | " + deadlineFormatter(this.deadline);
    }
}
