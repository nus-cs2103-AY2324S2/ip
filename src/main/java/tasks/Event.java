package tasks;

public class Event extends Task {
    private String startDate;
    private String endDate;
    public Event(String name, String startDate, String endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), this.startDate, this.endDate);
    }
}
