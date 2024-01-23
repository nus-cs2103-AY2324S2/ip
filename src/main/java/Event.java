public class Event extends Task {

    String taskType;
    String taskName;
    Boolean isDone;
    String start;
    String end;

    public Event(String taskName, String start, String end) {
        super(taskName);
        this.taskType = "E";
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from:" + this.start + "to:" + this.end + ")";
    }
}
