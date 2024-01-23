public class Event extends Task {
    private String start;
    private String end;
    public Event(String Task, String taskType, String start, String end) {
        super(Task, taskType);
        this.start = start;
        this.end = end;
    }
    public String toString() {
        return this.getTaskType() + this.getStatus() + " " + this.getTask() + getPeriod();
    }

    public String getPeriod() {
        return "(from: " + this.start + "to: " + this.end + ")";
    }

    public String announcement() {
        return "New Event created!";
    }
}
