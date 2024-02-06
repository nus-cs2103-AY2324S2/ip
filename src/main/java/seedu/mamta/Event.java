package seedu.mamta;

import java.time.LocalDate;

/**
 * Represents a task with a scheduled start and end time.
 * Inherits properties and methods from the Task class.
 */
public class Event extends Task {

    /**
     * The start time of the event.
     */
    private final String startTime;

    /**
     * The end time of the event.
     */
    private final String endTime;

    /**
     * Constructs an Event object with the given content, start time, and end time.
     * @param content The content of the event task.
     * @param startTime The start time of the event.
     * @param endTime The end time of the event.
     */
    Event(String content, String startTime, String endTime) {
        super(content);
        if (startTime.isEmpty() || endTime.isEmpty()) {
            this.startTime = String.valueOf(MamtaException.invalidDates());
            this.endTime = "";
        } else {
            this.startTime = transformDates(startTime);
            this.endTime = transformDates(endTime);
        }
    }

    /**
     * Constructs an Event object with the given completion status, content, start time, and end time.
     * @param isComplete The completion status of the event task.
     * @param content The content of the event task.
     * @param startTime The start time of the event.
     * @param endTime The end time of the event.
     */
    Event(boolean isComplete, String content, String startTime, String endTime) {
        super(isComplete, content);
        this.startTime = transformDates(startTime);
        this.endTime = transformDates(endTime);
    }

/**
 * Transforms the input deadline into a standardized format.
 * @param deadline The input deadline.
 * @return The transformed deadline in a standardized format.
 */
    public String transformDates(String deadline) {
        String year = "";
        String month = "";
        String day = "";
        String time = "";
        try {
            String[] splitOutput = deadline.split("-");
            for (String s : splitOutput) {
                year = splitOutput[0];
                month = splitOutput[1];
                day = splitOutput[2].split(" ")[0];
                time = splitOutput[2].split(" ")[1];
            }
            LocalDate date = LocalDate.parse(String.format("%s-%s-%s", year, month, day));
            year = String.valueOf(date.getYear());
            month = String.valueOf(date.getMonth());
            day = String.valueOf(date.getDayOfMonth());
            return String.format("%s %s %s %s", month, day, year, time);
        } catch (Exception e) {
            return deadline;
        }
    }

    /**
     * Marks the event task as done.
     * @return A new Event object with the event task marked as done.
     */
    @Override
    public Event markDone() {
        return new Event(true, this.content, this.startTime, this.endTime);
    }

    /**
     * Marks the event task as not done.
     * @return A new Event object with the event task marked as not done.
     */
    @Override
    public Event unmarkTask() {
        return new Event(false, this.content, this.startTime, this.endTime);
    }

    /**
     * Returns a string representation of the Event object.
     * @return A string representation of the Event object.
     */
    public String toString() {
        if (this.isComplete) {
            return String.format("E|X|%s|%s|%s", this.content, this.startTime, this.endTime);
        } else {
            return String.format("E| |%s|%s|%s", this.content, this.startTime, this.endTime);
        }
    }

}
