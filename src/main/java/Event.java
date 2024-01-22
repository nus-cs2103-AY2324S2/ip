public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String listItem, String startTime, String endTime) {
        super(listItem);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "[E]" +
                (this.taskDone ? "[X] " : "[ ] ") +
                this.listItem +
                " (from: " +
                this.startTime +
                " to: " +
                this.endTime +
                ")"
                ;
    }
}
