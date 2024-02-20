package blu.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a title and the due date/time.
 * Inherits from the {@link Task} class.
 */
public class Deadline extends Task {
    private static final String TASK_TYPE = "D";
    private LocalDateTime by;

    /**
     * Constructs a new Deadline task with the specified title and due date/time.
     *
     * @param title The title of the deadline task.
     * @param by The due date and time of the task.
     */
    public Deadline(String title, LocalDateTime by) {
        super(title);
        this.by = by;
    }

    /**
     * Formats the due date and time of the deadline task for display.
     *
     * @param by The LocalDateTime object to be formatted.
     * @return A string representation of the due date and time in "MMM dd yyyy, HH:mm" format.
     */
    private String displayDate(LocalDateTime by) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return by.format(formatter);
    }

    @Override
    public String toCsv() {
        return String.format("%s,%s,%s,%s", TASK_TYPE, this.isCompleted() ? "T" : "F", this.getTitle(), by);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (By: %s)", TASK_TYPE, super.toString(), displayDate(by));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Deadline)) {
            return false;
        }
        Deadline other = (Deadline) obj;
        boolean isSameDateTime = by.equals(other.by);
        return isSameDateTime && super.equals(other);
    }
}
