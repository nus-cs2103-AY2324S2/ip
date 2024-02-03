package chatbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class EventTask extends Task {

    private LocalDateTime startTiming;
    private LocalDateTime endTiming;

    /**
     * Constructor for EventTask.
     * @param description The description of the task.
     * @param startTiming The start timing of the task.
     * @param endTiming The end timing of the task.
     */
    public EventTask(String description, LocalDateTime startTiming, LocalDateTime endTiming) {
        super(description);
        this.startTiming = startTiming;
        this.endTiming = endTiming;
    }

    /**
     * Constructor for EventTask.
     * @param description The description of the task.
     * @param startTiming The start timing of the task.
     * @param endTiming The end timing of the task.
     * @param isCompleted Whether the task is completed.
     */
    public EventTask(String description, LocalDateTime startTiming, LocalDateTime endTiming, boolean isCompleted) {
        super(description, isCompleted);
        this.startTiming = startTiming;
        this.endTiming = endTiming;
    }

    @Override
    public String toString() {
        String status = super.isCompleted() ? "[X]" : "[ ]";
        // change format of date to MMM dd yyyy HH:mm
        String startTimingString = this.startTiming.format(DateTimeFormatter.ofPattern(Task.DATETIME_FORMAT_OUTPUT));
        String endTimingString;
        // check if endTiming is on the same day as startTiming
        if (this.startTiming.toLocalDate().equals(this.endTiming.toLocalDate())) {
            endTimingString = this.endTiming.format(DateTimeFormatter.ofPattern("HH:mm"));
        } else {
            endTimingString = this.endTiming.format(DateTimeFormatter.ofPattern(Task.DATETIME_FORMAT_OUTPUT));
        }
        return "[E]" + status + " " + super.getDescription() + " (from: " + startTimingString
                + " to: " + endTimingString + ")";
    }

    @Override
    public String exportToSave() {
        String status = super.isCompleted() ? "1" : "0";
        return "E," + status + "," + super.getDescription() + "," + this.startTiming.toString()
                + "," + this.endTiming.toString();
    }

}
