package gandalf.tasktypes;

import gandalf.GandalfException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Class for Deadline-type tasks
 */
public class Deadlines extends Task {
    private String deadlineDate;
    private LocalDateTime formattedDate;
    private transient DateTimeFormatter formatter;
    /**
     * Constructor for Deadlines. Takes in a deadlineDate to be added as additional information to the ArrayList
     * @param nameOfTask
     * @param deadlineDate
     */
    public Deadlines(String nameOfTask, String deadlineDate) throws GandalfException {
        super(nameOfTask);
        try {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.deadlineDate = deadlineDate;
            this.formattedDate = LocalDateTime.parse(deadlineDate, formatter);
        } catch (IllegalArgumentException | DateTimeParseException e) {
            throw new GandalfException("The format is wrong, I recognize: yyyy-MM-dd HHmm");
        }
    }

    @Override
    public String toString() {
        if (getStatus()) {
            return "[D][X] " + getNameOfTask() + " " + "("
                    + formattedDate.format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy h:mma")) + ")";
        } else {
            return "[D][ ] " + getNameOfTask() + " " + "("
                    + formattedDate.format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy h:mma")) + ")";
        }
    }
}
