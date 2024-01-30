package duke.Tasks;

import duke.Parsers.DateTimeParser;

import java.time.LocalDate;

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

    @Override
    public LocalDate getStart() {
        return this.start;
    }

    @Override
    public LocalDate getEnd() {
        return this.end;
    }
}
