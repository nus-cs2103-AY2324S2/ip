import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline implements Task {
    private final String name;
    private final boolean done;
    private final LocalDateTime deadline;
    static DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    Deadline(String name, LocalDateTime deadline) {
        this.name = name;
        this.done = false;
        this.deadline = deadline;
    }

    private Deadline(String name, boolean done, LocalDateTime deadline) {
        this.name = name;
        this.done = done;
        this.deadline = deadline;
    }

    public Deadline mark() {
        return new Deadline(this.name, true, this.deadline);
    }

    public Deadline unmark() {
        return new Deadline(this.name, false, this.deadline);
    }

    @Override
    public String toString() {
        return "[D][" + (this.done?"X":" ") + "] " + this.name + " (by: " + this.deadline.format(dtf) + ")";
    }
}
