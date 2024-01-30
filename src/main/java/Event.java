public class Event extends Task {

    protected String fromDate;
    protected  String toDate;

    public Event(String description, boolean status, String fromDate, String toDate) {
        super(TaskType.EVENT, description, status);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getToDate() {
        return this.toDate;
    }

    public String getFromDate() {
        return this.fromDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromDate + " to: " + this.toDate +")";
    }
}
