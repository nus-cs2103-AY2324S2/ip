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
        setDeadline(deadline);
    }

    public void setDeadline(String deadline) throws BondException {
        String[] dateTime = deadline.split(" ");

        // A timing is also required for the deadline task.
        if (dateTime.length != 2) {
            BondException.raiseException("deadline", "INVALID_DATETIME_FORMAT");
        }

        try {
            this.dueDate = LocalDate.parse(dateTime[0]);
        } catch (java.time.format.DateTimeParseException e) {
            BondException.raiseException("deadline", "INVALID_DATE_FORMAT");
        }

        if (!Parser.isValidTiming(dateTime[1])) {
            BondException.raiseException("deadline", "INVALID_TIME_FORMAT");
        }

        this.timing = Parser.changeTimeFormat(dateTime[1]);
    }



    @Override
    public String toString() {
        return String.format("[D]%s (by: %s %s)", super.toString(),
                this.dueDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")), this.timing);
    }
}
