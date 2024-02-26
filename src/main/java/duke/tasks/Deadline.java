package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents deadline tasks.
 */
public class Deadline extends Task {
    private static String TASK_TYPE = "[D] ";
    private static String DEADLINE = "deadline";
    private LocalDate date;
    static String COMPLETED_MESSAGE_END = " is complete!";
    static String INCOMPLETE_MESSAGE_END = " by ";
    
    /**
     * Constructor for new deadlines.
     * @param name Description or name of the given task.
     * @param date Date that the task needs to be completed by.
     */
    public Deadline(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    /**
     * Constructor for existing deadlines.
     * @param name Description or name of the given task.
     * @param isDone Completion status of task.
     * @param date Date that the task needs to be completed by.
     */
    public Deadline(String name, String isDone, LocalDate date) {
        super(name, isDone);
        this.date = date;
    }    

    @Override
    public String checkStatus() {
        if (this.checkDone()) {
            return TASK_TYPE + this.getName() + COMPLETED_MESSAGE_END;
        } else {
            return TASK_TYPE + this.getName() + INCOMPLETE_MESSAGE_END + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }

    /**
     * Returns string representing the current attributes of the deadline.
     * @return String representing the current attributes of the deadline.
     */
    public String getAttributes() {
        String isDoneString = "";
        if (this.isDone) {
            isDoneString = "true";
        } else {
            isDoneString = "false";
        }

        return DEADLINE + " " + isDoneString + " " + this.date + " " + this.name;
    }
}