public class Event extends Task {
    String fromDate = "";
    String toDate = "";

    public Event(String description, String fromDate, String toDate) {
        super(description, "E");
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getFromDate() {
        return this.fromDate;
    }

    public String getToDate() {
        return this.toDate;
    }

    public String getTaskDetails() {
        String codeBox = "[" + this.getTaskCode() + "]";
        String statusBox = "[" + this.getStatusIcon() + "]";
        String description = this.getDescription();
        String fromTo = "(from: " + this.fromDate + " to: " + this.toDate + ")";
        return codeBox + statusBox + " " + description + " " + fromTo;
    }
}
