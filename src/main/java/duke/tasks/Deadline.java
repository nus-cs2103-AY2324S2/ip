package duke.tasks;


import java.time.LocalDate;
import duke.parsers.DateTimeParser;

/**
 * Type of task with a deadline
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
     * To mark the task as done using the superclass's mark method.
     */
    public void mark() {
        super.mark();
    }
    /**
     * To unmark the task as done using the superclass's mark method.
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
