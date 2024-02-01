package duke.storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;


/**
 * The Events class represents an event task in the Duke task manager, which is a subtype of the Task class.
 * It inherits properties and methods from the Task class and provides a specific implementation for event tasks with date and time details.
 */
public class Events extends Task{

    protected LocalDate from;
    protected LocalTime fromTime;
    protected LocalDate to;
    protected LocalTime toTime;

    /**
     * Constructs an Events object with the specified original command, description, and date-time details.
     *
     * @param originalCommand The original command used to create the event task.
     * @param description The description of the event task.
     * @param dateTimeFrom The starting date and time of the event in string format.
     * @param dateTimeTo The ending date and time of the event in string format.
     */
    public Events(String originalCommand, String description, String dateTimeFrom, String dateTimeTo) {
        super(originalCommand, description);
        String[] splitFrom = dateTimeFrom.split("-");
        String[] splitTo = dateTimeTo.split("-");
        int lenFrom = splitFrom.length;
        int lenTo = splitTo.length;

        if (lenFrom == 3) {
            this.from = LocalDate.parse(String.join("-", splitFrom));
        } else if (lenFrom == 4) {
            this.from = LocalDate.parse(String.join("-", Arrays.copyOfRange(splitFrom,
                    1, lenFrom)));
            if (splitFrom[0].length() < 5 && splitTo[0].indexOf(":") != -1) {
                splitFrom[0] = "0" + splitFrom[0];
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[HH:mm]" + "[HHmm]" + "[Hmm]");
            this.toTime = LocalTime.parse(splitFrom[0], formatter);
        } else if (lenFrom == 5) {
            this.from = LocalDate.parse(String.join("-", Arrays.copyOfRange(splitFrom,
                    2, lenFrom)));
            this.fromTime = LocalTime.parse(splitFrom[1] + " " + splitFrom[0], DateTimeFormatter.ofPattern("h:mm a"));
        }

        if (lenTo == 3) {
            this.to = LocalDate.parse(String.join("-", splitTo));
        } else if (lenTo == 4) {
            this.to = LocalDate.parse(String.join("-", Arrays.copyOfRange(splitTo,
                    1, lenTo)));
            if (splitTo[0].length() < 5 && splitTo[0].indexOf(":") != -1) {
                splitTo[0] = "0" + splitTo[0];
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[HH:mm]" + "[HHmm]" + "[Hmm]");
            this.toTime = LocalTime.parse(splitTo[0], formatter);
        } else if (lenTo == 5) {
            this.to = LocalDate.parse(String.join("-", Arrays.copyOfRange(splitTo,
                    2, lenTo)));
            this.toTime = LocalTime.parse(splitFrom[1] + " " + splitFrom[0],DateTimeFormatter.ofPattern("h:mm a"));
        }
    }


    /**
     * Returns a string representation of the event task, including its specific type identifier, the result of the superclass's toString method, and date-time details.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(" h:mm a");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (from: " + dateFormatter.format(this.from) +
                Optional.ofNullable(fromTime).map(timeFormatter::format).orElse("") +
                " to: " + dateFormatter.format(this.to) +
                Optional.ofNullable(toTime).map(timeFormatter::format).orElse("") + ")";
    }
}
