package plato.task;

import java.time.LocalDateTime;
import java.util.Optional;

import plato.parser.DateHandler;


/**
 * Event Task is a task with a from date and to date.
 */
public class Event extends Task {

    protected String by = "";
    protected String from = "";

    protected LocalDateTime byDateTime = null;
    protected LocalDateTime fromDateTime = null;

    /**
     * Creates an event task with string values.
     *
     * @param description Name of the task.
     * @param from        The from date of the task.
     * @param by          The deadline of the task.
     */
    public Event(String description, String from, String by) {
        super(description);
        this.from = from;
        this.by = by;
    }

    /**
     * Creates an event task with a LocalDateTime object.
     *
     * @param description Name of the task.
     * @param from        The from date of the task.
     * @param by          The deadline of the task.
     */
    public Event(String description, LocalDateTime from, LocalDateTime by) {
        super(description);
        fromDateTime = from;
        byDateTime = by;
    }


    public Optional<LocalDateTime> getDateTime() {
        if (byDateTime != null) {
            return Optional.of(byDateTime);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public String saveFile() {
        if (byDateTime == null) {
            return "E" + "|" + super.done() + "|" + super.description + "|" + by + "|" + from + "|" + "null" + "|"
                   + "null";
        } else {
            return "E" + "|" + super.done() + "|" + super.description + "|" + by + "|" + from + "|"
                   + byDateTime.toString() + "|" + fromDateTime.toString();
        }

    }

    @Override
    public String toString() {
        if (byDateTime == null) {
            return "[E]" + super.toString() + " (from: " + from + " to " + by + ")";
        } else {
            return "[E]" + super.toString() + " (from: " + DateHandler.formatDate(fromDateTime) + " to: "
                   + DateHandler.formatDate(byDateTime) + ")";
        }
    }
    @Override
    public Actions getType() {
        return Actions.EVENT;
    }
}
