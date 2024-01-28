package Tasks;


import Tasks.Task;

import java.time.LocalDateTime;
import java.time.LocalDate;
import Parsers.DateTimeParser;

public class Deadline extends Task {

    String taskType;
    String taskName;
    LocalDate deadline;
    Boolean isDone;

    public Deadline(String taskName, LocalDate deadline, Boolean isDone, String taskType) {
        super(taskName, isDone, taskType);
        this.deadline = deadline;
    }

    public void mark() {
        super.mark();
    }

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
