package objects;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Events is a class representing tasks with a specific start and end date and time.
 * It extends the Task class and implements the Serializable interface for object serialization.
 */
public class Event extends Task implements Serializable {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an Events object with a name, start date and time, and end date and time.
     *
     * @param name The name of the event task.
     * @param from The start date and time of the event.
     * @param to   The end date and time of the event.
     */
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a formatted string representation of the Events object.
     *
     * @return A string containing the task type, name, and event duration information.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from.format(formatter),
                this.to.format(formatter));
    }

    @Override
    public void snooze() {
        this.from = this.from.plusHours(1);
        this.to = this.to.plusHours(1);
    }
}
