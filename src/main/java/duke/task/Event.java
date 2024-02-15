package duke.task;

import java.time.LocalDate;
/**
 * Subclass of task that represents events.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs Event object.
     *
     * @param description Description of event.
     * @param isDone Boolean value symbolizing completion status.
     * @param from Starting date of event. LocalDate object.
     * @param to End date of event. LocalDate object.
     */
    public Event(String description, boolean isDone, LocalDate from, LocalDate to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(Task.getDateFormat())
                + " to: " + this.to.format(Task.getDateFormat()) + ")";
    }

    /**
     * @inheritDoc
     *
     * @return description + isDone + from + to
     */
    @Override
    public String getTokens() {
        return String.join(",", TaskType.EVENT.toString(),
                super.getTokens(),
                this.from.format(Task.getDateFormat()),
                this.to.format(Task.getDateFormat()));
    }

    /**
     * @inheritDoc
     *
     * @return Start date of event.
     */
    @Override
    public LocalDate getStartDateTime() {
        return this.from;
    }
}

