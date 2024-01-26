public class Event extends Task {
    private String fromDate;
    private String toDate;

    public Event(String description, String fromDate, String toDate) {
        super(description);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toFileLine() {
        String fileLine = super.toFileLine();
        return String.format("E | %s | %s | %s", fileLine.substring(5),
                this.fromDate, this.toDate);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                this.fromDate, this.toDate);
    }
}