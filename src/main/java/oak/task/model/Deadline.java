package oak.task.model;

import java.time.LocalDateTime;

import oak.utility.DateTimeUtility;

/**
 * The Deadline Class to handle all Deadline-type Tasks
 */
public class Deadline extends Task {
    /** The icon this task is represented by */
    public static String DEADLINE_TYPEICON = "D";
    /** The datetime the task has to be completed by */
    private LocalDateTime byDateTime;

    /**
     * Constructor method for Deadline from user input (without the isCompleted)
     *
     * @param name
     * @param byDateTime
     */
    public Deadline(String name, String byDateTime) {
        super(name, DEADLINE_TYPEICON);

        this.byDateTime = DateTimeUtility.parseStringToLocalDateTime(byDateTime);
    }

    /**
     * Constructor method for Deadline from tasklist.txt
     *
     * @param name
     * @param isCompleted
     * @param byDateTime
     */
    public Deadline(String name, Boolean isCompleted, String byDateTime) {
        super(name, DEADLINE_TYPEICON);

        if (isCompleted) {
            super.markTaskCompleted();
        }

        this.byDateTime = DateTimeUtility.parseStringToLocalDateTime(byDateTime);
    }

    public LocalDateTime getByDateTime() {
        return this.byDateTime;
    }

    @Override
    public String toTaskListStringFormat() {
        return String.format("%s|%s|%s",
                DEADLINE_TYPEICON, super.toTaskListStringFormat(), this.byDateTime.toString());
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)",
                super.toString(), DateTimeUtility.parseLocalDateTimeToString(this.byDateTime));
    }
}
