package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class representing event tasks.
 */
public class Event extends Task {
    private static String TASK_TYPE = "[E] ";
    private static String EVENT = "event";
    private LocalDate date;
    static String COMPLETED_MESSAGE_END = " is complete!";
    static String INCOMPLETE_MESSAGE_END = " by ";

    /**
     * Constructor for new events.
     * @param name Description or name of the given event.
     * @param date Date that the event occurs.
     */
    public Event(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    /**
     * Constructor for existing events.
     * @param name Description or name of the given event.
     * @param isDone Completion status of event.
     * @param date Date that the event occurs.
     */
    public Event(String name, String isDone, LocalDate date) {
        super(name, isDone);
        this.date = date;
    }    

    @Override
    public String checkStatus() {
        if (this.checkDone()) {
            return TASK_TYPE + this.getName() + COMPLETED_MESSAGE_END;
        } else {
            return TASK_TYPE + this.getName() + INCOMPLETE_MESSAGE_END + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }

    /**
     * Returns string representing the current attributes of the event.
     * @return String representing the current attributes of the event.
     */
    public String getAttributes() {
        String isDoneString = "";
        if (this.isDone) {
            isDoneString = "true";
        } else {
            isDoneString = "false";
        }

        return EVENT + " " + isDoneString + " " + this.name;
    }
}
