package chatbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates the data and behaviour of an chatbot.task.Event task.
 *
 * @author Huang Zhuoyan, Celeste
 * @version CS2103T AY24/25 Semester 1, G07
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma");

    /**
     * Constructs a new chatbot.task.Event task with the given name, start date and end date.
     *
     * @param name  The name of the chatbot.task.Deadline task.
     * @param start The LocalDateTime object containing the start date.
     * @param end   The LocalDateTime object containing the end date.
     */
    public Event(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
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
        return "[E]" + super.printTask() + " (from: " + formatDeadline(this.start)
                + " to: " + formatDeadline(this.end) + ")";
    }

    @Override
    public String saveTask() {
        return "E | " + super.saveTask() + " | " + formatDeadline(this.start) + " | " + formatDeadline(this.end);
    }
}
