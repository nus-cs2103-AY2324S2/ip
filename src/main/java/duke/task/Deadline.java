package duke.task;

import java.time.LocalDateTime;

/**
 * A deadline with an event and a due date.
 */
public class Deadline extends Task {

    private final LocalDateTime dueBy;

    /**
     * Constructor.
     *
     * @param event The event itself.
     * @param dueBy When the event is due by.
     */
    public Deadline(String event, LocalDateTime dueBy) {
        super();
        assert event != null;
        assert dueBy != null;
        this.event = event;
        this.dueBy = dueBy;
    }

    @Override
    public String toString() {
        String done = this.isDone ? "X" : " ";
        return String.format("[D][%s] %s (by: %s)", done, this.event, this.dateFormat(this.dueBy));
    }

    @Override
    public int compare(Task left, Task right) {
        if (left instanceof Deadline) {
            if (right instanceof Deadline) {
                return ((Deadline) left).dueBy.compareTo(((Deadline) right).dueBy);
            }
            return -1;
        }
        if (right instanceof Deadline) {
            return 1;
        }
        return 0;
    }

}
