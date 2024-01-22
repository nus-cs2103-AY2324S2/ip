public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String input) {
        super(input.substring(0, input.indexOf("/from")).strip());
        setStartTime(input.substring(input.indexOf("/from") + 5, input.indexOf("/to")).strip());
        setEndTime(input.substring(input.indexOf("/to") + 3).strip());
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
