package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Represent a subclass that stores information and 2 timings/dates to be used with a Chatbot
 * CS2103T
 * AY23/24 Semester 2
 * Author: Chua Zen Khoon
 */
public class Event extends Task{

    /**
     * A Event instance contains a start time/date
     */
    protected String from;

    /**
     * A Event instance contains a end time/date
     */
    protected String to;

    protected LocalDateTime convertedFrom;
    protected LocalDateTime convertedTo;

    /**
     * Constructor for a Event instance,
     * @param description to be used to identify a task
     * @param from to be used to identify the start of said task
     * @param to to be used to identify the end of said task
     */
    public Event(String description, String from, String to) {
        super(description);
        DateTimeFormatter accepted = DateTimeFormatter.ofPattern(" yyyy-MM-dd HH:mm ");
        DateTimeFormatter accepted1 = DateTimeFormatter.ofPattern(" yyyy-MM-dd HH:mm");
        this.from = from;
        this.to = to;
        this.convertedFrom = LocalDateTime.parse(from, accepted);
        this.convertedTo = LocalDateTime.parse(to, accepted1);
    }

    /**
     * Prints Event description in Task Array or when task is marked/unmarked/added
     * @return a string representing the task description
     */
    @Override
    public String toString() {
        DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + "(from:" + this.convertedFrom.format(output)
                    + " to:" + this.convertedTo.format(output) + ")";
    }

    @Override
    public String toString(boolean update) {

        return "E@" + super.toString(update) + "@" + this.from + "@" + this.to;
    }
}
