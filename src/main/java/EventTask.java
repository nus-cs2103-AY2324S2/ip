import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DateTimeException;

public class EventTask extends Task {
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Represents an event task with a name, start date/time, and end date/time.
     *
     * @param name The name of the event task.
     * @param from The start date/time of the event task.
     * @param to   The end date/time of the event task.
     * @throws DukeException If the name, start date/time, or end date/time is an empty string.
     */
    public EventTask(String name, String from, String to) throws DukeException {
        super(name);
        if (name.equals("")) {
            throw new DukeException("oi the task needs a name la \uD83D\uDE21\uD83D\uDE21");
        }
        if (from.equals("")) {
            throw new DukeException("eh the event needs a start date/time");
        }
        if (to.equals("")) {
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
}
