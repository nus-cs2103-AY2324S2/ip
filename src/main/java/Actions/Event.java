import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Actions.Action {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for Actions1.Event class
     * @param description action to be made
     * @param from
     * @param to
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     *
     * @return get event starting date
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     *
     * @return get event ending date
     */
    public LocalDateTime getTo() {
        return this.to;
    }

    /**
     * @return task output
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:"
                + this.from.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma"))
                + " to: "
                + this.to.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma"));
    }
}
