package duke.task;

import java.time.LocalDate;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;
    public Event(String name, LocalDate from, LocalDate to) {
        super(name);
        this.from = from;
        this.to = to;
    }
    public Event(String name, boolean isDone, LocalDate from, LocalDate to) {
        super(name, isDone);
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
}
