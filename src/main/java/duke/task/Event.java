package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for task start with event
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * constructor
     * @param descrip the dicription of the task.
     * @param from from field.
     * @param to to field.
     */
    public Event(String descrip, LocalDate from, LocalDate to) {
        super(descrip);
        this.from = from;
        this.to = to;
    }

    /**
     * Override the abstract class
     * @return T
     */
    @Override
    public String getTaskTypeIcon() {
        return "E";
    }

    /**
     * Override the abstract class
     * @return event
     */
    @Override
    public String getTaskType() {
        return "event";
    }
    /**
     * Override the toString method
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return String.format("%s(from: %s to: %s)",
                super.toString(),
                this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
        );
    }

    /**
     * return from
     * @return from
     */
    public String getFrom() {
        return this.from.toString();
    }

    /**
     * return to
     * @return to
     */
    public String getTo() {
        return this.to.toString();
    }

    /**
     * Whether we have to start doing it
     * @param current current time
     * @return yes/no
     */
    public boolean isTimeForStart(LocalDate current) {
        if (isDone) {
            return false;
        }
        return current.compareTo(to) <= 0 && current.compareTo(from) >= 0;
    }
}
