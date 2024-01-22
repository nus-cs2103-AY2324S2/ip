import java.util.*;
import java.io.*;

/**
 * Event is a subclass of Task. It stores information of a task including
 * the start and end date.
 * @author Koo Zhuo Hui
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructor for Event with a task name, start and end date.
     * @param s Name of the event.
     * @param from The start timing of the event.
     * @param to The end of the event.
     */
    public Event(String s, String from, String to) {
        super(s);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E][" + (super.getStatus() ? "X" : " ") + "] " +
                super.getTask() + "(from: " + from + "to: " + to + ")";

    }
}
