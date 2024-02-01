package duke.storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.time.format.DateTimeFormatterBuilder;
import java.util.Arrays;
import java.util.Optional;

/**
 * The Deadlines class represents a deadline task in the Duke task manager, which is a subtype of the Task class.
 * It inherits properties and methods from the Task class and provides a specific implementation for deadline tasks with date and time details.
 */
public class Deadlines extends Task {

    protected LocalDate by;
    protected LocalTime byTime;

    /**
     * Constructs a Deadlines object with the specified original command, description, and date-time details.
     *
     * @param originalCommand The original command used to create the deadline task.
     * @param description The description of the deadline task.
     * @param dateTimeBy The deadline date and time in string format.
     */
    public Deadlines(String originalCommand, String description, String dateTimeBy) {
        super(originalCommand, description);
        String[] splitBy = dateTimeBy.split("-");
        int lenBy = splitBy.length;

        if (lenBy == 3) {
            this.by = LocalDate.parse(String.join("-", splitBy));
        } else if (lenBy == 4) {
            this.by = LocalDate.parse(String.join("-", Arrays.copyOfRange(splitBy,
                    1, lenBy)));
            if (splitBy[0].length() < 5 && splitBy[0].indexOf(":") != -1) {
                splitBy[0] = "0" + splitBy[0];
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[HH:mm]" + "[HHmm]" + "[Hmm]");
            this.byTime = LocalTime.parse(splitBy[0], formatter);
        } else if (lenBy == 5) {
            this.by = LocalDate.parse(String.join("-", Arrays.copyOfRange(splitBy,
                    2, lenBy)));
            this.byTime = LocalTime.parse(splitBy[1] + " " + splitBy[0], DateTimeFormatter.ofPattern("h:mm a"));
        }
    }

    /**
     * Returns a string representation of the deadline task, including its specific type identifier, the result of the superclass's toString method, and date-time details.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(" h:mm a");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + dateFormatter.format(this.by) +
                Optional.ofNullable(byTime).map(timeFormatter::format).orElse("") + ")";
    }
}
