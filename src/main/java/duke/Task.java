package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Task class that encapsulates an entry in the tasklist.
 * @author Cedric
 */
public class Task {
    private String action;
    private Boolean isDone;


    /**
     * Constructor specifying action and isDone
     * @param action String as described by user
     * @param isDone if action is marked as done or not
     */
    public Task(String action, Boolean isDone) {
        this.action = action;
        this.isDone = isDone;
    }
    /**
     * Custom toString() method to display isDone status
     * @return Description of the return value.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X]" + action;
        } else {
            return "[ ]" + action;
        }
    }
    /**
     * Marks task as done
     */
    public void mark() {
        isDone = true;
    }
    /**
     * Marks task as not done
     */
    public void unmark() {
        isDone = false;
    }
    /**
     * Dummy function to be overridden by subclasses, specifically for writing to the storage
     * @return String to be written to storage
     */

    public String export() {
        return toString();
    }
}
/**
 * Event task that has additional time entries from and to
 * @author Cedric
 */
class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    /**
     * Constructor which also includes time from and to
     * @param input Description of the user input task
     * @param isDone whether task is done or not
     * @param from time from as described by user
     * @param to time to as described by user
     */
    public Event(String input, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(input, isDone);
        this.from = from;
        this.to = to;
    }
    /**
     * Custom toString() including task type, from and to
     * @return String to be displayed to user
     */
    @Override
    public String toString() {
        String s = super.toString();
        return "[E]" + s + "(from " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:ss")).replace("T", " ") + " to " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:ss")).replace("T", " ") + ")";
    }
    /**
     * different string to be generated and written into storage
     * @return String to be written into storage
     */
    @Override
    public String export() {
        String s = super.toString();
        return "[E]" + s + "/from" + from.toString().replace("T", " ") + "/to" + to.toString().replace("T" , " ") ;
    }
}
/**
 * Todo task with no time specifications
 * @author Cedric
 */
class Todo extends Task {
    public Todo(String input, boolean isDone) {
        super(input, isDone);
    }
    /**
     * Adds task type to the string
     * @return returns String displaying task type
     */
    @Override
    public String toString() {
        String s = super.toString();
        return "[T]" + s;
    }
    /**
     * Doesnt change anything as todo has no extra requirements
     * @return String to be written to storage
     */
    @Override
    public String export() {
        return toString();
    }
}
/**
 * Deadline task with a specific deadline by
 * @author Cedric
 */
class Deadline extends Task {
    private LocalDateTime by;
    /**
     * Constructor with time by for the deadline task
     * @param input User input task
     * @param isDone if the task is done
     * @param by deadline due date/time
     */
    public Deadline(String input, boolean isDone, LocalDateTime by) {
        super(input, isDone);
        this.by = by;
    }
    /**
     * Custom toString() including task type and deadline due
     * @return String to be displayed to user
     */
    @Override
    public String toString() {
        String s = super.toString();
        return "[D]" + s + "(by:" + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:ss")).replace("T", " ") +")";
    }
    /**
     * Rewrites string to be exported to storage
     * @return String to be written to storage
     */
    @Override
    public String export() {
        String s = super.toString();
        return "[D]" + s + "/by" + by.toString().replace("T", " ") ;
    }
}
