package taskTypes;

public class Event extends Task{
    private String startDate;
    private String endDate;
    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public String statusString() {
        return String.format("[E]%s (from: %s to: %s)", super.statusString(), this.startDate, this.endDate);
    }
}
