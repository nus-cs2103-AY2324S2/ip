package lemona.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Locale;


/**
 * Represents an event task in the task manager application.
 * An event task has a description, start time, and end time.
 */
public class Event extends Task{
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm", Locale.ENGLISH);

    /**
     * Constructs an Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param startTime The start time of the event in the format "dd/MM/yyyy HHmm".
     * @param endTime The end time of the event in the format "dd/MM/yyyy HHmm".
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = LocalDateTime.parse(startTime, inputFormat);
        this.endTime = LocalDateTime.parse(endTime, inputFormat);
    }

    @Override
    public String print() {
        String str = "";
        try {
            String start = startTime.format(outputFormat);
            String end = endTime.format(outputFormat);
            str = "[E]" + super.print() + "(from: " +
                    start + ") (to: " + end + ")";
        } catch (DateTimeParseException e) {
            str = "I think you haven't had enough vitamin C."
                    + "\nYour time format should be :"
                    + "\n\t{ dd/MM/yyyy HHmm }"
                    + "\nI suggest you take some LEMONA.";
        }
        return str;
    }

    @Override
    public String getDescription() {
        String start = startTime.format(outputFormat);
        String end = endTime.format(outputFormat);
        String str = "[E] " + super.getDescription() + " " + start + " " + end;
        return str;
    }

    @Override
    public String getTaskInfo() {
        String start = startTime.format(outputFormat);
        String end = endTime.format(outputFormat);
        return "[E] " + "/ [" + super.getStatusIcon() + "] / " + super.getTaskInfo() + "/ " + start
                + "/ " + end;
    }
}
