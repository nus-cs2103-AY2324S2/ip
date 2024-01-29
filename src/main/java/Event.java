public class Event extends Task {
    private static final String TYPE = "Event";
    protected String startDate;
    protected String endDate;

    Event(String description, String startDate, String endDate) {
        super(description, TYPE);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private String getStartDate() {
        return startDate;
    }

    private String getEndDate() {
        return endDate;
    }

    @Override
    public String getFileEncoding() {
        return super.getFileEncoding() + "," + getStartDate() + "," + getEndDate();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + getStartDate() + " to:" +  getEndDate() + ")";
    }
}
