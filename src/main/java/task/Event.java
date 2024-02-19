package task;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** This class represents an event which is a task that has start time and end time */
public class Event extends Task{
    /** Field for what time the event starts */
    private LocalDateTime from;
    /** Field for what time the evnet ends */
    private LocalDateTime to;

    /**
     * Constructs an event object
     *
     * @param description
     * @param from
     * @param to
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs event object and is marked if task is done
     * @param description
     * @param isDone
     * @param from
     * @param to
     */
    public Event(String description, Boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns string text of the task name, when it is from and when it ends and the [E] tag
     *
     */
    @Override
    public String toString() {
        return
                "[E]" +
                super.toString() +
                " (from: " +
                from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) +
                " to: " +
                to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) +
                ")";
    }

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public String toDataFormat() {
        String isDone = this.isDone ? "1 | " : "0 | ";
        return
                "E | " +
                isDone +
                description +
                " | " +
                from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) +
                " | " +
                to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Event)) {
            return false;
        }

        Event other = (Event) obj;
        return
                this.description.equals(other.description) &&
                        this.from.equals(other.from) &&
                        this.to.equals(other.to);
    }
}
