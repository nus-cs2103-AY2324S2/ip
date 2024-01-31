public class Event extends Task {
    private String fromDate;
    private String toDate;
    public Event(String task, String fromDate, String toDate) {
        super(task);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String formatTask() {
        String status = getStatus() ? "1" : "0";
        return String.format("T | %s | %s | %s | %s", status, super.formatTask(), fromDate, toDate);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), fromDate, toDate);
    }
}
