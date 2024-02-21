package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Event extends Task{

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Creates an event task.
     *
     * @param description Description of the task.
     * @param from Start date of the task.
     * @param to End date of the task.
     */
    public Event(String description, String from, String to) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.from = LocalDate.parse(from, formatter);
        this.to = LocalDate.parse(to, formatter);
    }

    /**
     * getter for task icon.
     *
     */
    @Override
    public String getTaskIcon() {
        return "E";
    }

    /**
     * returns string representation of event.
     *
     */
    @Override
    public String ToString() {
        return "[" + getTaskIcon() + "] " + "[" + getStatusIcon() + "] "+ description  + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")" + " (" + getPriorityDataString() + ")";
    }
    /**
     * returns a string representation of event used to store the event on hard drive.
     *
     */
    @Override
    public String toStore() {
        if (isDone) {
            return getTaskIcon() + "/" + "1" + "/" + description + "/" + priority + "/" + from + "/" + to;
        } else {
            return getTaskIcon() + "/" + "0" + "/" + description + "/" + priority + "/" + from + "/" + to;
        }
    }
}
