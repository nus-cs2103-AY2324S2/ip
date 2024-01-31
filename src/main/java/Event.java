import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * This class represents a Task with a start and end duration
 */
public class Event extends Task {

    private static final String TYPE_SYMBOL = "[E]";
    private LocalDate fromDate;
    private LocalDate toDate;
    private LocalTime fromTime;
    private LocalTime toTime;

    /**
     * Constructs a new Event with the specified description, start, and end period.
     *
     * @param description Description of the Event
     * @param from        Starting period of the Event in the format "YYYY-MM-DD (optional: HH:MM)"
     * @param to          Ending period of the Event in the format "YYYY-MM-DD (optional: HH:MM)"
     * @throws MeanDukeException if starting or ending period is invalid
     */
    public Event(String description, String from, String to) throws MeanDukeException {
        super(description, TYPE_SYMBOL);
        if (from.isEmpty() || to.isEmpty()) {
            throw new MeanDukeException("Usage: \"add event <description> /from <start> /to <end>\"");
        }
        //Parses from and to
        String[] parsedFrom = from.split(" ");
        String[] parsedTo = from.split(" ");
        try {
            this.fromDate = LocalDate.parse(parsedFrom[0]);
            if (parsedFrom.length > 1) {
                this.fromTime = LocalTime.parse(parsedFrom[1]);
            }
            this.toDate = LocalDate.parse(parsedTo[0]);
            if (parsedTo.length > 1) {
                this.toTime = LocalTime.parse(parsedTo[1]);
            }
        } catch (DateTimeParseException e) {
            throw new MeanDukeException("Usage: \"add event <description> /from yyyy-mm-dd (hh:mm) /to yyyy-mm-dd (hh:mm)\"");
        }

    }

    /**
     * Constructs a new Event with the specified description, completion state, start, and end period.
     *
     * @param description Description of the Event
     * @param isDone      boolean value that determines if the initialised Event is completed or not
     * @param fromDate    start date of the Event in the format "YYYY-MM-DD"
     * @param fromTime    start time of the Event in the format "HH:MM", or null
     * @param toDate      end date of the Event in the format "YYYY-MM-DD"
     * @param toTime      end time of the Event in the format "HH:MM", or null
     */
    public Event(String description, boolean isDone, LocalDate fromDate,
                 LocalTime fromTime, LocalDate toDate, LocalTime toTime) {
        super(description, TYPE_SYMBOL, isDone);
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
    }

    public String saveString() {
        return "EVENT" + "\n"
                + super.saveString() + "\n"
                + this.fromDate
                + (this.fromTime == null ? "" : ";" + this.fromTime) + "\n"
                + this.toDate
                + (this.toTime == null ? "" : ";" + this.toTime);
    }

    @Override
    public String toString() {
        String fromString = this.fromDate.getDayOfMonth() + " "
                + this.fromDate.getMonth().toString() + " "
                + this.fromDate.getYear()
                + (this.fromTime == null ? "" : " " + this.fromTime);
        String toString = this.toDate.getDayOfMonth() + " "
                + this.toDate.getMonth().toString() + " "
                + this.toDate.getYear()
                + (this.toTime == null ? "" : " " + this.toTime);
        return super.toString() + " (" + fromString + " - " + toString + ")";
    }
}
