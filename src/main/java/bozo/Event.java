package bozo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;
    protected DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Constructs a new event task with the specified description and event time.
     *
     * @param description The description of the event task.
     * @param from The start time of the event task.
     * @param to The end time of the event task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, input);
        this.to = LocalDateTime.parse(to, input);
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return string of event task
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from.format(output), to.format(output));
    }

    /**
     * Returns the string representation of the event task to be saved in the file.
     *
     * @return string of event task
     */
    @Override
    public String save() {
        return "E " + super.save() + String.format(" | %s | %s", from.format(input), to.format(input));
    }
}
