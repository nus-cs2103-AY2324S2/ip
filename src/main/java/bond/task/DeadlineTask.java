package bond.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import bond.main.BondException;
import bond.main.Parser;

/**
 * Represents a deadline task in the Bond task management program.
 *
 * @author Benny Loh
 * @version 0.2
 */
public class DeadlineTask extends Task {

    private LocalDate dueDate;
    private String timing;

    /**
     * Constructor for the DeadlineTask class.
     * Throws a BondException if the deadline is in an incorrect format.
     *
     * @param name     The name of the deadline task.
     * @param deadline The deadline of the task.
     * @throws BondException If the deadline task cannot be created.
     */
    public DeadlineTask(String name, String deadline) throws BondException {
        super(name);

        String[] dateTime = deadline.split(" ");

        try {
            this.dueDate = LocalDate.parse(dateTime[0]);
        } catch (java.time.format.DateTimeParseException e) {
            BondException.raiseException("deadline", "INVALID_DATE_FORMAT");
        }

        if (dateTime.length != 2) {
            BondException.raiseException("deadline", "INVALID_DATE_FORMAT");
        }

        if (Parser.isNumber(dateTime[1])) {
            int hours = Integer.valueOf(dateTime[1].substring(0, 2));
            int minutes = Integer.valueOf(dateTime[1].substring(2));

            if (hours == 0) {
                this.timing = "12" + (minutes > 0 ? "." + minutes : "") + "am";
            } else if (hours < 12) {
                this.timing = String.valueOf(hours) + (minutes > 0 ? "." + minutes : "") + "am";
            } else {
                this.timing = String.valueOf(hours - 12) + (minutes > 0 ? "." + minutes : "") + "pm";
            }
        } else {
            this.timing = dateTime[1];
        }
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s %s)", super.toString(),
                this.dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")), this.timing);
    }
}
