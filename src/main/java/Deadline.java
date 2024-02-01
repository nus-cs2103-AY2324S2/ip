import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represent a subclass that stores information and 1 timing/date to be used with a Chatbot
 * CS2103T
 * AY23/24 Semester 2
 * Author: Chua Zen Khoon
 */
public class Deadline extends Task{

    /**
     * A Deadline instance contains a do by time/date
     */
    protected String by;
    protected LocalDateTime converted;
    /**
     * Constructor for a Deadline instance,
     * @param description to be used to identify a task
     * @param by to be used to identify the deadline of said task
     */
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter accepted = DateTimeFormatter.ofPattern(" yyyy-MM-dd HH:mm");
        this.by = by;
        this.converted = LocalDateTime.parse(by, accepted);
    }

    /**
     * Prints Deadline description in Task Array or when task is marked/unmarked/added
     * @return a string representing the task description
     */
    @Override
    public String toString() {
        DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[D]" + super.toString() + " (by:" + this.converted.format(output) + ")";
    }

    @Override
    public String toString(boolean update) {

        return "D@" + super.toString(update) + "@" + this.by;
    }
}
