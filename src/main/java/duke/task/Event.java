package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import duke.exception.DateFormatException;

/**
 * Represents a task with a user-defined start and end time
 */
public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * constructor for a new Event task
     * @param name       name of new Event taks
     * @param startTime  starting time of new Event task
     * @param endTime    ending time of new Event task
     * @throws DateFormatException
     */
    public Event(String name, String startTime, String endTime) throws DateFormatException {
        super(name, "E");
        String[] startString = startTime.split("/");
        try {
            String[] endString = endTime.split("/");
            int startDay = Integer.parseInt(startString[0]);
            int startMonth = Integer.parseInt(startString[1]);
            int startYear = Integer.parseInt((startString[2].split(" "))[0]);
            int startHour = Integer.parseInt((startString[2].split(" "))[1]) / 100;
            this.startTime = LocalDateTime.of(startYear, startMonth, startDay, startHour, 00);
            int endDay = Integer.parseInt(endString[0]);
            int endMonth = Integer.parseInt(endString[1]);
            int endYear = Integer.parseInt((endString[2].split(" "))[0]);
            int endHour = Integer.parseInt((endString[2].split(" "))[1]) / 100;
            this.endTime = LocalDateTime.of(endYear, endMonth, endDay, endHour, 00);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException d) {
            throw new DateFormatException();
        }
    }

    /**
     * returns String representation of current Event task
     * @return  current Event object as String
     */
    public String toString() {
        String status = this.isComplete() ? "[x]" : "[ ]";
        DateTimeFormatter returnFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return "[" + this.getType() + "] " + status + " " + this.getName() + " (" + returnFormat.format(this.startTime)
                + " to " + returnFormat.format(this.endTime) + ")";
    }
}

