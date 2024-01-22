package Tasks;

public class Event extends Task {
    private String startDate;
    private String endDate;

    public Event(String description, boolean status, String startDate, String endDate) {
        super(description, status, Type.EVENT);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }
}
