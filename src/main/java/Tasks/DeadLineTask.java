package Tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The DeadLineTask class represents a task with a deadline.
 */
public class DeadLineTask extends Task {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM-dd");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
    private static final String YEAR = "24-";
    private final String formattedDeadline;
    private final String deadLineLog;


    /**
     * Constructs a DeadLineTask object with the specified deadline and task description.
     *
     * @param dl    The deadline for the task.
     * @param task  The description of the task.
     */
    public DeadLineTask(String dl, String task) {
        super(task);
        deadLineLog = dl;
        LocalTime time;
        LocalDate deadline;
        if (dl.length() != 5) {
            time = LocalTime.parse(dl.substring(6).toUpperCase(), timeFormatter);
            deadline = LocalDate.parse(YEAR + dl.substring(0, 5), DateTimeFormatter.ofPattern("yy-MM-dd"));
            formattedDeadline = deadline.format(dateFormatter) + " " + time.format(timeFormatter);
        } else {
            deadline = LocalDate.parse(YEAR + dl, DateTimeFormatter.ofPattern("yy-MM-dd"));
            formattedDeadline = deadline.format(dateFormatter);
        }
    }

    /**
     * Returns a string representation of the DeadLineTask object.
     *
     * @return A string representation of the DeadLineTask object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by " + formattedDeadline + ")";
    }

    /**
     * Returns a string representation of the DeadLineTask object for logging purposes.
     *
     * @return A string representation of the DeadLineTask object for logging purposes.
     */
    @Override
    public String logString() {
        return "D" + super.logString() + "|" + deadLineLog;
    }



}
