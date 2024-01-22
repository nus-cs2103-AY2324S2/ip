package taskList;


public class Event extends Task {

    protected String fromDate;
    protected String toDate;

    public Event(String item, String fromDate, String toDate) {
        super(item);
        String[] fromDateString = fromDate.split(" ",2);
        this.fromDate = fromDateString[1].trim();
        String[] toDateString = toDate.split(" ",2);
        this.toDate = toDateString[1].trim();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()  + " (from: " + fromDate + " to: " + toDate + ")";
    }
}
