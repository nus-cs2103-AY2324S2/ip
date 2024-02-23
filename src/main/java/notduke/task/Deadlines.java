package notduke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Represents a Deadline which is a type of Task
 */
public class Deadlines extends Task {

    /** The date the Deadline is due */
    private LocalDateTime by;

    /**
     * Constructs a Deadline object with the specified name, the due date of the Deadline and mark whether it is done.
     * @param name The name of the Deadline
     * @param by The date in which the Deadline is due
     * @param status The status of the Deadline
     */
    public Deadlines(String name, LocalDateTime by, Boolean status) {
        super(name, status);
        this.by = by;
    }

    @Override
    public String happenOn(LocalDate date) {
        boolean isOnBy = date.isEqual(by.toLocalDate());
        if (isOnBy) {
            return taskInfo();
        }
        return "";
    }
    @Override
    public String printMatch(String match) {
        if (super.contains(match)) {
            return taskInfo();
        }
        return "";
    }

    /**
     * @InheritDoc Includes task type Deadlines to string.
     */
    @Override
    public String saveOutput() {
        return "D " + super.saveOutput() + String.format(" | %s", by);
    }

    /**
     * @InheritDoc Includes task type Deadlines to string.
     */
    @Override
    public String taskInfo() {
        String output = "";
        String formattedBy = by.format(DateTimeFormatter.ofPattern("MMM d yyyy, HHmm"));

        output += "[D]";
        output += super.taskInfo();
        return output + " (by: " + formattedBy + "hrs)\n";
    }

    @Override
    public String happenSoon() {
        LocalDate now = LocalDate.now();
        LocalDate nowPlusSeven = now.plus(7, ChronoUnit.DAYS);
        LocalDate deadline = by.toLocalDate();

        boolean isToday = deadline.isEqual(now);
        boolean isInSevenDays = deadline.isEqual(nowPlusSeven);
        boolean isBetweenSevenDays = deadline.isAfter(now) && deadline.isBefore(nowPlusSeven);
        boolean isWithinSevenDays = isBetweenSevenDays || isToday || isInSevenDays;

        if (isWithinSevenDays) {
            return taskInfo();
        } else {
            return "";
        }
    }
}
