package duke.task;

import duke.parser.DateHandler;

import java.time.LocalDateTime;

public class Event extends Task {

    //Legacy support
    protected String by = "";
    protected String from = "";

    protected LocalDateTime byDateTime = null;
    protected LocalDateTime fromDateTime = null;

    public Event(String description, String from, String by) {
        super(description);
        this.from = from;
        this.by = by;
    }

    public Event(String description, LocalDateTime from, LocalDateTime by) {
        super(description);
        fromDateTime = from;
        byDateTime = by;
    }

    @Override
    public String saveFile() {
        if (byDateTime == null) {
            return "E" + "|" + super.done() + "|" + super.description
                    + "|" + by + "|" + from + "|" + "null" + "|" + "null";
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
            return "[E]" + super.toString() + " (from: " + DateHandler.formatDate(fromDateTime) + " to "
                    + DateHandler.formatDate(byDateTime) + ")";
        }
    }
}