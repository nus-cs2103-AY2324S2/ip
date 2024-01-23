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

    /**
     * Constructor for a Event instance,
     * @param description to be used to identify a task
     * @param from to be used to identify the start of said task
     * @param to to be used to identify the end of said task
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from =from;
        this.to = to;
    }

    /**
     * Prints Event description in Task Array or when task is marked/unmarked/added
     * @return a string representing the task description
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + this.from + " to:" + this.to + ")";
    }
}
