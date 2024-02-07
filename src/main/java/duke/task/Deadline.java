package duke.task;

import duke.exception.DateFormatException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents a task with a user-defined deadline
 */
public class Deadline extends Task {
    LocalDateTime dueDate;
    public Deadline(String name, String dueDate) throws DateFormatException {
        super(name, "D");
        String[] dateString = dueDate.split("/");
        try {
            int day = Integer.parseInt(dateString[0]);
            int month = Integer.parseInt(dateString[1]);
            int year = Integer.parseInt((dateString[2].split(" "))[0]);
            int time = Integer.parseInt((dateString[2].split(" "))[1]) / 100;
            this.dueDate = LocalDateTime.of(year, month, day, time, 00);
        } catch (ArrayIndexOutOfBoundsException d) {
            throw new DateFormatException();
        }
    }
    public String toString() {
        String status = this.isComplete ? "[x]" : "[ ]";
        DateTimeFormatter returnFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return "[D] " + status + " " + this.name + " (" + returnFormat.format(this.dueDate) + ")";
    }
}
