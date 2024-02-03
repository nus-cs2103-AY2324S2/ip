package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Locale;

/**
 * Task class
 */
public class Task {
    protected String task;
    protected boolean isDone;
    protected DateTimeFormatter f1 = DateTimeFormatter.ofPattern("d/M/yyyy", Locale.ENGLISH);
    protected DateTimeFormatter f2 = DateTimeFormatter.ofPattern("yyyy-M-d", Locale.ENGLISH);
    protected DateTimeFormatter f3 = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);
    protected DateTimeFormatter f4 = DateTimeFormatter.ofPattern("d-M-yyyy", Locale.ENGLISH);

    /**
     * Constructor for Task
     * 
     * @param task
     */
    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    /**
     * Mark task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark task as undone
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Check if task is done
     * 
     * @return boolean
     */
    public boolean isDone() {
        return this.isDone;
    }

    /** 
     * Returns task type
     */
    protected String taskType() {
        return "";
    }

    /**
     * Returns task status
     * 
     * @return String task status
     */
    public String taskStatus() {
        return this.isDone ? "done" : "not done";
    }

    /** 
     * Parse the date string and return a LocalDate object
     * 
     * @param by
     * @param formatters
     * @return LocalDate
     * @throws IllegalArgumentException
     * @throws Exception
     */
    protected static LocalDate parseDate(String by, DateTimeFormatter... formatters) {
        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDate.parse(by, formatter);
            } catch (Exception e) {
                // Try the next format
            }
        }
        // If none of the formats match, you may want to handle this case
        throw new IllegalArgumentException("Date could not be parsed with any of the provided formats");
    }

    /**
     * toString method for Task
     * 
     * @return String
     */
    @Override
    public String toString() {
        return taskType() + " | " + taskStatus() + " | " + this.task;
    }
}

/**
 * TaskType enum
 */
enum TaskType {
    TODO,
    DEADLINE,
    EVENT,
    HELP,
    DELETE,
    UNKNOWN
}