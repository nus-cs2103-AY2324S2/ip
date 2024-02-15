package duke.task.events;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.task.Task;

/**
 * This class contains the functions for event tasks.
 * @author Tang Hao Liang
 */
public class Events extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor that updates description and duration for the task.
     *
     * @param description Event's description.
     * @param from Event's Start
     * @param to Event's End
     */
    public Events(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String fromText = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        String toText = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));

        return "  [E]" + super.toString() + " (from: " + fromText + " to: " + toText + ")";
    }

    @Override
    public String toFile() {
        LocalDateTime.parse("2012-12-12 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String fromText = from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String toText = to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        if (isDone) {
            return "E|1|" + description + "|" + fromText + "|" + toText;
        } else {
            return "E|0|" + description + "|" + fromText + "|" + toText;
        }
    }
}
