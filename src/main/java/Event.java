public class Event extends Task {
  protected String startDate;
  protected String endDate;

  public Event(String description, String startDate, String endDate) {
    super(description);
    this.startDate = startDate;
    this.endDate = endDate;
  }

  @Override
  public String getStatus() {
    return "[E]" + super.getStatus() + " (from: " + this.startDate + " to: " + this.endDate + ")";
  }
}
