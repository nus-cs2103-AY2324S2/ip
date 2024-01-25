public class Event extends Task {
    protected String startDate;
    protected String endDate;

    Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private String getStartDate() {
        return "from: " + startDate;
    }

    private String getEndDate() {
        return "to: " + endDate;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + getStartDate() + " " +  getEndDate() + ")";
    }
}
