package klee.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event with a duration.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for Event class.
     *
     * @param description
     * @param from
     * @param to
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.type = "E";
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the type and current completion status of event with its start and end date.
     *
     * @return String to represent current state of class.
     */
    public String getStatus() {
        String statusIcon = (isDone ? "X" : " ");
        return "[" + type + "][" + statusIcon + "] " + description + " (from: "
                + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:ma")) + " to: "
                + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:ma")) + ")";
    }

    /**
     * Creates a String to represent this task in the txt file.
     *
     * @return String to be stored in txt file.
     */
    public String toText() {
        return super.toText() + " / " + from.format(DateTimeFormatter.ofPattern("yyyy MM dd H m")) + " / "
                + to.format(DateTimeFormatter.ofPattern("yyyy MM dd H m"));
    }

    /**
     * Given input from txt file create new instance of Event.
     *
     * @param description
     * @param done
     * @param from
     * @param to
     * @return instance of Task.
     */
    public static Task fromText(String description, String done, LocalDateTime from, LocalDateTime to) {
        Task task = new Event(description, from, to);
        task.isDone = done.equals("1");
        return task;
    }

    /**
     * Checks if obj is the same as the current instance.
     *
     * @param obj
     * @return If obj has the same fields as this.
     */
    @Override
    public boolean equals(Object obj) {
        if (Task.class.isAssignableFrom(obj.getClass())) {
            boolean hasSameFields = this.from == ((Event) obj).from && this.to == ((Event) obj).to;
            return super.equals(obj) && hasSameFields;
        } else {
            return false;
        }
    }
}
