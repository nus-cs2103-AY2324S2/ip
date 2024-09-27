package gandalf.tasktypes;

import gandalf.GandalfException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Events class that extends Task
 */
public class Events extends Task {
    private String startDate;
    private String endDate;
    private LocalDateTime formattedStartDate;
    private LocalDateTime formattedEndDate;
    transient DateTimeFormatter formatter;

    /**
     * Constructor for Event task, also throws an exception if any date inputs is in the wrong format
     * @param nameOfTask
     * @param startDate
     * @param endDate
     * @throws GandalfException
     */
    public Events(String nameOfTask, String startDate, String endDate) throws GandalfException {
        super(nameOfTask);
        try {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.startDate = startDate;
            this.endDate = endDate;
            this.formattedStartDate = LocalDateTime.parse(startDate.trim(), formatter);
            this.formattedEndDate = LocalDateTime.parse(endDate.trim(), formatter);
        } catch (IllegalArgumentException | DateTimeParseException e) {
            throw new GandalfException("The format is wrong, I recognize: yyyy-MM-dd HHmm");
        }
    }
    @Override
    public String toString() {
        if (getStatus()) {
            return "[E][X] " + getNameOfTask() + " " + "(from: "
                    + formattedStartDate.format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy h:mma"))
                    + " to: " + formattedEndDate.format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy h:mma")) + ")";
        } else {
            return "[E][ ] " + getNameOfTask() + " " + "(from: "
                    + formattedStartDate.format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy h:mma"))
                    + " to: " + formattedEndDate.format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy h:mma")) + ")";
        }
    }
}
