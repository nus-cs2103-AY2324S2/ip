package tasks;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class represents an Event task by encapsulating information about a specific event,
 * including the description, start and end timings.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yy h:mma");

    /**
     * Returns an Event object as the public constructor for this class.
     *
     * @param taskName Description of event.
     * @param from Start date or time of the event.
     * @param to end date or time of the event.
     */
    public Event(String taskName, String from, String to) throws DateTimeParseException {
        super(taskName);
        this.from = LocalDateTime.parse(from, inputFormatter);
        this.to = LocalDateTime.parse(to, inputFormatter);
    }

    /**
     * Formats the details of the Event object as a String to be written to a text file.
     *
     * @return A String containing the description and start and end timings of an Event object.
     */
    @Override
    public String saveFormat() {
        int check = this.isCompleted ? 1 : 0;
        assert check == 1 || check == 0 : "check should return 1 or 0";
        return String.format("E | %d | %s | %s | %s\n", check, this.taskName, this.from.format(inputFormatter),
                this.to.format(inputFormatter));
    }
    @Override
    public String toString() {
        return String.format("[E]%s (From: %s To: %s)", super.toString(), this.from.format(outputFormatter), this.to.format(outputFormatter));
    }
}
