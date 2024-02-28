package bmo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that is an event.
 */
public class Events extends Task implements Comparable<Task> {

    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructor for the Events class.
     *
     * @param task  The task to be done.
     * @param start The start time of the event.
     * @param end   The end time of the event.
     */
    public Events(String task, LocalDateTime start, LocalDateTime end) {
        super(task);
        assert start != null : "Start time cannot be null";
        assert end != null : "End time cannot be null";
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return this.start.format(DateTimeFormatter.ofPattern("d' 'MMMM' 'yyyy', 'h:mma"));
    }

    public String getEnd() {
        return this.end.format(DateTimeFormatter.ofPattern("d' 'MMMM' 'yyyy', 'h:mma"));
    }

    /**
     * Returns the formatted string representation of the task to be saved in the file.
     *
     * @return String representation of the task to be saved in the file.
     */
    @Override
    public String toSaveData() {
        String done = super.getStatus() ? "1" : "0";
        String startStr = this.start.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        String endStr = this.end.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));

        return "E | " + done + " | " + super.toString() + " | "
                + startStr + " | " + endStr + "\n";
    }

    /**
     * Returns the type priority of the task.
     *
     * @return Integer representing the type priority of the task.
     */
    @Override
    public int getTypePriority() {
        return 0;
    }

    /**
     * Returns the comparison of the start time of the events, used for sorting.
     *
     * @return Integer representing the comparison of the start time of the events.
     */
    @Override
    public int compareTo(Task t) {
        if (t instanceof Events) {
            Events e = (Events) t;
            return this.start.compareTo(e.start);
        } else {
            return this.getTypePriority() - t.getTypePriority();
        }
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + getStart() + " to: " + getEnd() + ")";
    }
}
