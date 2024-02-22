package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event is a subclass of task.Task. It stores information of a task including
 * the start and end date.
 * @author Koo Zhuo Hui
 */
public class Event extends Task {
    private static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    private static final String MMM_DD_YYYY_HH_MM = "MMM dd yyyy HH:mm";

    private String from;
    private String to;
    private LocalDateTime end;

    /**
     * Constructor for Event with a task name, start and end date.
     * @param s Name of the event.
     * @param from The start timing of the event.
     * @param to The end of the event.
     */
    public Event(String s, String from, String to) {
        super(s);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM);
            LocalDateTime start = LocalDateTime.parse(from, formatter);
            end = LocalDateTime.parse(to, formatter);
            this.from = start.format(DateTimeFormatter.ofPattern(MMM_DD_YYYY_HH_MM));
            this.to = end.format(DateTimeFormatter.ofPattern(MMM_DD_YYYY_HH_MM));
        } catch (DateTimeParseException e) {
            this.from = from;
            this.to = to;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode() {
        String taskType = "E|";
        String taskStatus = (super.getStatus() ? 1 : 0) + "|";
        String taskName = super.getTask() + "|";
        String taskRange = from + "|" + to;
        return taskType + taskStatus + taskName + taskRange;
    }

    @Override
    public String toString() {
        String taskType = "[E][";
        String taskStatus = (super.getStatus() ? "X" : " ") + "] ";
        String taskName = super.getTask();
        String taskRange = "(From: " + from + " To: " + to + ")";
        return taskType + taskStatus + taskName + taskRange;
    }
}
