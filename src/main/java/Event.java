public class Event extends Task {

    public String fromDate;
    public String toDate;

    public Event(String name, String fromDate, String toDate) {
        super(name);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Event(String name, String fromDate, String toDate, boolean isDone) {
        super(name, isDone);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromDate + " to: " + toDate + ")";
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String[] getTimes() {
        return new String[] { fromDate, toDate };
    }
}
