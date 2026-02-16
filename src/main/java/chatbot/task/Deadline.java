package chatbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates the data and behaviour of a chatbot.task.Deadline task.
 *
 * @author Huang Zhuoyan, Celeste
 * @version CS2103T AY24/25 Semester 1, G07
 */
public class Deadline extends Task {
    private LocalDateTime deadline;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");

    /**
     * Constructs a new chatbot.task.Deadline task with the given name and deadline.
     *
     * @param name The name of the chatbot.task.Deadline task.
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
    public String formatDeadline(LocalDateTime time) {
        return time.format(FORMATTER);
    }

    @Override
    public String printTask() {
        return "[D]" + super.printTask() + " (by: " + formatDeadline(this.deadline) + ")";
    }

    @Override
    public String saveTask() {
        return "D | " + super.saveTask() + " | " + formatDeadline(this.deadline);
    }
}
