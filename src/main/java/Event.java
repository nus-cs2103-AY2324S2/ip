public class Event extends Task {
    protected String startDateTime;
    protected String endDateTime;

    public Event(String taskName, String startDateTime, String endDateTime) {
        super(taskName);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "[E] " + super.taskTitle + " (from: " + startDateTime + " to: " + endDateTime + ")" + super.getStatusIcon();
    }
}
