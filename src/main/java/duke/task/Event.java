package duke.task;

import java.time.LocalDateTime;

/**
 * An event, with a *from* and *to* date.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructor.
     *
     * @param event The event itself.
     * @param from  When the event starts.
     * @param to    When the event ends.
     */
    public Event(String event, LocalDateTime from, LocalDateTime to) {
        super();
        assert event != null;
        assert from != null;
        assert to != null;
        this.event = event;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String done = this.isDone ? "X" : " ";
        return String.format(
            "[E][%s] %s (from: %s to: %s)",
            done,
            this.event,
            this.dateFormat(this.from),
            this.dateFormat(this.to)
        );
    }
}
