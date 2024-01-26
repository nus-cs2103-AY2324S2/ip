public class Events extends Task {
    private String startTime;
    private String endTime;

    public Events(String taskName, String start, String end) {
        super(taskName, "E");
        this.startTime = start;
        this.endTime = end;
    }

    public Events(String taskName, String start, String end, int isTaskDone) {
        super(taskName, "E");
        this.startTime = start;
        this.endTime = end;
        super.changeStatus(isTaskDone);
        super.setTime(new String[] {start, end});
    }

    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + super.getTaskName() + " (from: " +
                this.startTime + " to: " + this.endTime + ")";
    }
}
