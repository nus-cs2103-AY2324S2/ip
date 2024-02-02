import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private final LocalDate deadline;

    /**
     * Represents a deadline task with a name and deadline.
     *
     * @param name     The name of the deadline task.
     * @param deadline The deadline of the deadline task.
     * @throws DukeException If the name or deadline is an empty string.
     */
    public DeadlineTask(String name, String deadline) throws DukeException {
        super(name);
        if (name.equals("")) {
            throw new DukeException("oi the task needs a name la \uD83D\uDE21\uD83D\uDE21");
        }
        if (deadline.equals("")) {
            throw new DukeException("bro this task needs a deadline bro");
        }

        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeException e) {
            throw new DukeException("can you follow the format yyyy-mm-dd pls");
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }
}
