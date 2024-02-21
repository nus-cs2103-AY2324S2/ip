package zoe;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Subclass of task
 * Creates a deadline when keyed in the form: deadline XYZ /by yyyy-mm-dd
 */
public class Deadline extends Task {
    private static DateTimeFormatter DATE_TIME_PARSER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
    private static int DESCRIPTION = 0;
    private static int DATE = 1;
    protected String dateTime;
    protected String formattedDate;
    public Deadline(String desc) {
        String[] str = desc.split("/by");
        this.description = str[DESCRIPTION].trim();
        this.dateTime = str[DATE].trim();
        LocalDateTime inputDate = LocalDateTime.parse(dateTime, DATE_TIME_PARSER);
        this.formattedDate = inputDate.format(FORMATTER);
        this.type = "D";
        this.isDone = false;
        this.priority = TaskPriority.DEADLINE.getPriority();
    }

    public Deadline(String desc, String isDoneNumber) {
        String[] str = desc.split("/by");
        this.description = str[DESCRIPTION].trim();
        this.dateTime = str[DATE].trim();
        LocalDateTime inputDate = LocalDateTime.parse(dateTime, DATE_TIME_PARSER);
        this.formattedDate = inputDate.format(FORMATTER);
        this.type = "D";
        int doneState = Integer.parseInt(isDoneNumber);
        assert doneState < 2 : "Data file corrupted, invalid state";
        assert doneState >= 0 : "Data file corrupted, invalid state";
        this.isDone = doneState == DONE_STATE;
        this.priority = TaskPriority.DEADLINE.getPriority();
    }

    @Override
    public String getStatus() {
        return String.format("[%s][%s] %s (by: %s)", type, getStatusIcon(),
                description, formattedDate);
    }

    @Override
    public String saveTask() {
        return String.format("deadline_%s/by %s_%d", description, dateTime, isDoneNumerical());
    }

    /**
     * Provides date to the comparator to sort deadlines
     */
    public LocalDateTime getDateTime() {
        return LocalDateTime.parse(dateTime, DATE_TIME_PARSER);
    }
}
