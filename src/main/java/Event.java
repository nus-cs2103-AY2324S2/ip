public class Event extends Task {

    protected String startDate;
    protected String endDate;


    public Event (String description, String newStartDate, String newEndDate) {
        super(description);
        this.startDate = newStartDate;
        this.endDate = newEndDate;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.startDate, this.endDate);
    }

    @Override
    public String toFileString() {
        return String.format("E,%s,%s,%s", super.toFileString(), this.startDate, this.endDate);
    }
}
