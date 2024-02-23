package signal.task;

import signal.SignalException;
import signal.util.TimeManager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;


public class Event extends Task {
    private TimeManager timeManager = new TimeManager();
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;

    /**
     * Constructor for a new Event task.
     *
     * @param description The description of the task.
     * @param start The start timing of the event.
     * @param end The end timing of the event.
     */
    public Event(String description, String start, String end) throws SignalException {
        super(description);
        try {
            String[] parseStart = start.split(" ");
            String[] parseEnd = end.split(" ");
            if (parseStart.length > 1) {
                this.startTime = timeManager.convertTime(parseStart[1]);
            }
            this.startDate = timeManager.convertDate(parseStart[0]);
            if (parseEnd.length > 1) {
                this.endTime = timeManager.convertTime(parseEnd[1]);
            }
            this.endDate = timeManager.convertDate(parseEnd[0]);
        } catch (DateTimeParseException e) {
            throw new SignalException("Oops, I can't read the date or time like that! Please use dd-mm-yyyy for date " +
                    "(year is optional) and hhmm for time (time is optional).");
        }
    }

    @Override
    public LocalDate getDue() {
        return this.startDate;
    }

    /**
     *
     * @return The string representation of the Event task.
     */
    @Override
    public String toString() {
        return "E" + super.toString()
                + "| from: " + timeManager.formatDate(startDate) + (startTime != null ? " " + timeManager.formatTime(startTime) : "")
                +  " | to: " + timeManager.formatDate(endDate) + (endTime != null ? " " + timeManager.formatTime(endTime) : "");
    }

    @Override
    public String checkType() {
        return "Event";
    }

}
