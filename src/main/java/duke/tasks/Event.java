package duke.tasks;

import duke.parsers.DateTimeParser;

import java.time.LocalDate;

/**
 * Type of task with two LocalDates, a start and an end.
 */
public class Event extends Task {

    String taskType;
    String taskName;
    Boolean isDone;
    LocalDate start;
    LocalDate end;

    public Event(String taskName, LocalDate start, LocalDate end, Boolean isDone, String taskType) {
        super(taskName, isDone, taskType);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + DateTimeParser.dtToString(this.start) +
                "to:" + DateTimeParser.dtToString(this.end) + ")";
    }
    /**
     * Queries the task's start date.
     */
    @Override
    public LocalDate getStart() {
        return this.start;
    }
    /**
     * Queries the task's end date.
     */
    @Override
    public LocalDate getEnd() {
        return this.end;
    }
}
