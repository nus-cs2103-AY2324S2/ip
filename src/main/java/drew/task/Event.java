package drew.task;

import java.time.LocalDate;
/**
 * This class represents the Event task.
 */
public class Event extends Task {
    /**
     * Stores the event start date in "YYYY-MM-DD" format.
     */
    private LocalDate startDate;
    /**
     * Stores the event end date in "YYYY-MM-DD" format.
     */
    private LocalDate endDate;
    /**
     * Constructor for the Event task.
     * @param description Descriptions of the event.
     * @param startDate Start date of event in "YYYY-MM-DD" format.
     * @param endDate End date of event in "YYYY-MM-DD" format.
     */
    public Event(String description, LocalDate startDate, LocalDate endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    /**
     * Returns the type, status and description of the Event task.
     * @return string in task list format.
     */
    public String toStatusString() {
        return String.format("[E]%s (from: %s to: %s)", super.toStatusString(), this.startDate, this.endDate);
    }
    /**
     * Converts the task into string format for the save file.
     * @return String in save file format.
     */
    @Override
    public String toSaveFormatString() {
        String status = (super.isDone) ? "1" : "0";
        return String.format("E | %s | %s | %s | %s\n", status, super.toString(), this.startDate, this.endDate);
    }

    @Override
    public boolean isEqual(Task task) {
        if (!(task instanceof Event)) {
            return false;
        }
        Event event = (Event) task;
        if (event == this) {
            return true;
        }
        if (!event.startDate.equals(this.startDate)) {
            return false;
        }
        if (!event.endDate.equals(this.endDate)) {
            return false;
        }
        if (!event.description.equals(this.description)) {
            return false;
        }
        assert this.description.equals(event.description) && event.startDate.equals(this.startDate);
        return true;

    }
}
