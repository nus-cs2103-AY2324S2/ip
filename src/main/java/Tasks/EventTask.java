package Tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The EventTask class represents a task with a start and end time.
 */
public class EventTask extends Task {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM-dd");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
    private static final String YEAR = "24-";
    private final String startTime;
    private final String endTime;
    private final String formattedEndTime;
    private final String formattedStartTime;

    /**
     * Constructs an EventTask object with the specified start time, end time, and task description.
     *
     * @param startTime The start time of the event.
     * @param endTime   The end time of the event.
     * @param task      The description of the event.
     */
    public EventTask(String startTime, String endTime, String task) {
        super(task);
        this.startTime = startTime;
        this.endTime = endTime;
        LocalTime sTime;
        LocalTime eTime;
        LocalDate startDate;

        eTime = LocalTime.parse(endTime.toUpperCase().trim(), timeFormatter);
        formattedEndTime = eTime.format(timeFormatter);
        if (startTime.trim().length() > 8) {
            sTime = LocalTime.parse(startTime.substring(6).toUpperCase().trim(), timeFormatter);
            startDate = LocalDate.parse(YEAR + startTime.substring(0, 5), DateTimeFormatter.ofPattern("yy-MM-dd"));
            formattedStartTime = startDate.format(dateFormatter) + " " + sTime.format(timeFormatter);
        } else {
            sTime = LocalTime.parse(startTime.toUpperCase().trim(), timeFormatter);
            formattedStartTime = sTime.format(timeFormatter);
        }
    }

    /**
     * Returns a string representation of the EventTask object.
     *
     * @return A string representation of the EventTask object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from " + formattedStartTime + " to " + formattedEndTime + ")";
    }

    /**
     * Returns a string representation of the EventTask object for logging purposes.
     *
     * @return A string representation of the EventTask object for logging purposes.
     */
    @Override
    public String logString() {
        return 'E' + super.logString() + '|' + startTime + '|' + endTime;
    }
}
