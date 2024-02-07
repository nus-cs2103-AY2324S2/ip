package duke.task;

import duke.exception.DateFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents a task with a user-defined start and end time
 */
public class Event extends Task {
    LocalDateTime startTime;
    LocalDateTime endTime;
    public Event(String name, String startTime, String endTime) throws DateFormatException {
        super(name, "E");
        String[] startString = startTime.split("/");
        try {
            String[] endString = endTime.split("/");
            int startDay = Integer.parseInt(startString[0]);
            int startMonth = Integer.parseInt(startString[1]);
            int startYear = Integer.parseInt((startString[2].split(" "))[0]);
            int startHour = Integer.parseInt((startString[2].split(" "))[1]) / 100;
            this.startTime = LocalDateTime.of(startYear, startMonth, startDay, startHour,00);
            int endDay = Integer.parseInt(endString[0]);
            int endMonth = Integer.parseInt(endString[1]);
            int endYear = Integer.parseInt((endString[2].split(" "))[0]);
            int endHour = Integer.parseInt((endString[2].split(" "))[1]) / 100;
            this.endTime = LocalDateTime.of(endYear, endMonth, endDay, endHour,00);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException d) {
            throw new DateFormatException();
        }
    }
    public String toString() {
        String status = this.isComplete ? "[x]" : "[ ]";
        DateTimeFormatter returnFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return "[E] " + status + " " + this.name + " (" + returnFormat.format(this.startTime) + " to "
                 + returnFormat.format(this.endTime) + ")";
    }
}

