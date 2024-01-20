public class Events extends Task {
    private String startTime;
    private String endTime;

    public Events(String taskName, String start, String end) {
        super(taskName);
        this.startTime = start;
        this.endTime = end;
    }

    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + super.getTaskName() + "(from: " +
                this.startTime + " to: " + this.endTime + ")";
    }
}
