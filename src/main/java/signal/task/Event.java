package signal.task;

import signal.util.TimeManager;

import java.time.LocalDate;
import java.time.LocalTime;


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
    public Event(String description, String start, String end) {
        super(description);
        String[] parseStart = start.split(" ");
        String[] parseEnd = end.split(" ");
        if (parseStart.length > 1) {
            this.startTime = LocalTime.parse(parseStart[1]);
        }
        this.startDate = LocalDate.parse(parseStart[0]);
        if (parseEnd.length > 1) {
            this.endTime = LocalTime.parse(parseEnd[1]);
        }
        this.endDate = LocalDate.parse(parseEnd[0]);
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
                + " | from: " + timeManager.formatDate(startDate) + (startTime != null ? " " + timeManager.formatTime(startTime) : "")
                +  " | to: " + timeManager.formatDate(endDate) + (endTime != null ? " " + timeManager.formatTime(endTime) : "");
    }

    @Override
    public String checkType() {
        return "Event";
    }

}
