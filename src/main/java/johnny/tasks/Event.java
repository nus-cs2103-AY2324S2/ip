package johnny.tasks;

import java.time.LocalDateTime;

/**
 * Represents an event.
 */
public class Event extends Task {

    /** When the Event starts from. */
    private LocalDateTime from;
    /** When the Event lasts to. */
    private LocalDateTime to;

    /**
     * Constructor for Deadline.
     * Calls super class constructor to store name of task.
     *
     * @param name Name of the Event.
     * @param from When the Event starts from.
     * @param to When the Event lasts to.
     */
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    /**
     * Formats Event for Ui to print.
     *
     * @return String representation of Event for printing.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + super.formatOutputDate(from)
                + " to: " + super.formatOutputDate(to) + ")";
    }

    /**
     * Formats Event for storing in Storage.
     *
     * @return String representation of Event to store.
     */
    @Override
    public String addToFile() {
        return "E | " + super.addToFile() + " | " + super.formatInputDate(from)
                + " | " + super.formatInputDate(to) + "\n";
    }

}
