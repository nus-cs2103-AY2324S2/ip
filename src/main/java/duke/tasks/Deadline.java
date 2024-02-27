package duke.tasks;


import java.time.LocalDate;
import duke.parsers.DateTimeParser;

/**
 * Subclass of task with a deadline. Has an attribute deadline that keeps track of when the task
 * needs to be completed.
 */
public class Deadline extends Task {

    String taskType;
    String taskName;
    LocalDate deadline;
    Boolean isDone;

    public Deadline(String taskName, LocalDate deadline, Boolean isDone, String taskType) {
        super(taskName, isDone, taskType);
        this.deadline = deadline;
    }

    /**
     * Marks the task in the list as done using the superclass's mark method.
     */
    public void mark() {
        super.mark();
    }
    /**
     * Unmarks the task as done using the superclass's mark method.
     */
    public void unmark() {
        super.unmark();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeParser.dtToString(this.deadline) + ")";
    }

    @Override
    public LocalDate getDeadline() {
        return this.deadline;
    }
}
