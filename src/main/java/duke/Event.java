package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represent a subclass that stores information and 2 timings/dates to be used with a Chatbot
 * CS2103T
 * AY23/24 Semester 2
 * Author: Chua Zen Khoon
 */
public class Event extends Task {

    /**
     * A Event instance contains a start time/date
     */
    private String from;

    /**
     * A Event instance contains a end time/date
     */
    private String to;

    private LocalDateTime convertedFrom;
    private LocalDateTime convertedTo;

    private DateTimeFormatter accepted = DateTimeFormatter.ofPattern(" yyyy-MM-dd HH:mm ");
    private DateTimeFormatter accepted1 = DateTimeFormatter.ofPattern(" yyyy-MM-dd HH:mm");

    /**
     * Constructor for an Event instance
     *
     * @param description to be used to identify a task
     * @param from to be used to identify the start of said task
     * @param to to be used to identify the end of said task
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.convertedFrom = LocalDateTime.parse(from, accepted);
        this.convertedTo = LocalDateTime.parse(to, accepted1);
    }

    /**
     * Prints Event description in Task Array
     *
     * @return a string representing the task description
     */
    @Override
    public String toString() {
        DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

        return "[E]" + super.toString() + "(from:" + this.convertedFrom.format(output)
                + " to:" + this.convertedTo.format(output) + ")";
    }

    /**
     * Returns the string representation of the Task to be updated in the data file
     *
     * @return a string representing the task description
     */
    @Override
    public String toString(boolean update) {
        return "E@" + super.toString(update) + "@" + this.from + "@" + this.to;
    }

    @Override
    public String snoozeTask() throws DukeException {
        convertedFrom = convertedFrom.plusMinutes(5);
        convertedTo = convertedTo.plusMinutes(5);
        from = convertedFrom.format(accepted);
        to = convertedTo.format(accepted1);
        return super.snoozeTask();
    }
}
