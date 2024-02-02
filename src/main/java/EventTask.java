import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;

public class EventTask extends Task {
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructs an event task with the given name, start date/time, and end date/time.
     *
     * @param name The name of the event task.
     * @param from The start date/time of the event task.
     * @param to   The end date/time of the event task.
     * @throws DukeException If the name, start date/time, or end date/time of the task is an empty string.
     */
    public EventTask(String name, String from, String to) throws DukeException {
        this(name, from, to, false);
    }

    /**
     * Constructs an event task with the given name, start date/time, end date/time, and done status.
     *
     * @param name   The name of the event task.
     * @param from   The start date/time of the event task.
     * @param to     The end date/time of the event task.
     * @param isDone The done status of the event task.
     * @throws DukeException If the name, start date/time, or end date/time of the task is an empty string.
     */
    public EventTask(String name, String from, String to, boolean isDone) throws DukeException {
        super(name);
        if (name.isEmpty()) {
            throw new DukeException("oi the task needs a name la \uD83D\uDE21\uD83D\uDE21");
        }
        if (from.isEmpty()) {
            throw new DukeException("eh the event needs a start date/time");
        }
        if (to.isEmpty()) {
            throw new DukeException("eh the event needs a end date/time");
        }
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeException e) {
            throw new DukeException("can you follow the format yyyy-mm-dd pls");
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    /**
     * Returns a string representation of the task to be written to the file.
     *
     * @return A string representation of the task to be written to the file.
     */
    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s | %s", isDone() ? 1 : 0, getName(), from, to);
    }
}
