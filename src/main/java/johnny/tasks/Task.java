package johnny.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task.
 */
public class Task {

    /** Format of DateTime in Tasks for storing in Storage. */
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
    /** Format of DateTime in Tasks for Ui to print. */
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
    /** Name of Task. */
    private String name;
    /** Boolean to check if Task is done. */
    private boolean isDone = false;

    /**
     * Constructor for Task.
     *
     * @param name Name of Task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Marks Task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Unmarks Task to not done.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Formats Task for Ui to print.
     *
     * @return String representation of Task for printing.
     */
    @Override
    public String toString() {
        String x = isDone ? "X" : " ";
        return "[" + x + "] " + name;
    }

    /**
     * Formats Task for storing in Storage.
     *
     * @return String representation of Task to store.
     */
    public String addToFile() {
        String isDone = this.isDone ? "1" : "0";
        return isDone + " | " + name;
    }

    /**
     * Formats DateTime in Tasks for storing in Storage.
     *
     * @param dateTime DateTime to be formatted.
     * @return String representation of formatted DateTime.
     */
    public String formatInputDate(LocalDateTime dateTime) {
        return dateTime.format(INPUT_DATE_FORMAT);
    }

    /**
     * Formats DateTime in Tasks for Ui to print.
     *
     * @param dateTime DateTime to be formatted.
     * @return String representation of formatted DateTime.
     */
    public String formatOutputDate(LocalDateTime dateTime) {
        return dateTime.format(OUTPUT_DATE_FORMAT);
    }

    /**
     * Checks if Task name contains keyword.
     *
     * @param keyword String to be matched to Task name.
     * @return True if name contains else false;
     */
    public boolean contains(String keyword) {
        return name.contains(keyword);
    }

}
