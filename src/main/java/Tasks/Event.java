package Tasks;

import Tasks.Task;

public class Event extends Task {

    String taskType;
    String taskName;
    Boolean isDone;
    String start;
    String end;

    public Event(String taskName, String start, String end, Boolean isDone, String taskType) {
        super(taskName, isDone, taskType);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + this.start + "to:" + this.end + ")";
    }

    @Override
    public String getStart() {
        return this.start;
    }

    @Override
    public String getEnd() {
        return this.end;
    }
}
