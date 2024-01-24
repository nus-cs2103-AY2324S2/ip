public class Event extends Task{

    protected String fromDate;
    protected String byDate;

    public Event(String description, String fromDate, String byDate) {
        super(description);
        this.fromDate = fromDate;
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        return String.format("[E]" + super.toString() + " (from: %s to: %s)", this.fromDate, this.byDate);
    }
}
