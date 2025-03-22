package objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadlines is a class representing tasks with a specific deadline.
 * It extends the Task class and implements the Serializable interface for object serialization.
 */
public class Deadline extends Task implements Serializable {
    private LocalDateTime by;

    /**
     * Constructs a Deadlines object with a name and a specific deadline.
     *
     * @param name The name of the deadline task.
     * @param by   The deadline date and time.
     */
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    /**
     * Returns a formatted string representation of the Deadlines object.
     *
     * @return A string containing the task type, name, and deadline information.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("[D]%s (by: %s)", super.toString(), this.by.format(formatter));
    }

    @Override
    public void snooze() {
        this.by = this.by.plusHours(1);
    }
}
