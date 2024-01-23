package Models;
public class Event extends Task {

  private String startDate;
  private String endDate;
  public Event(String name, String startdate, String endDate) {
    super(name);
    this.startDate = startdate;
    this.endDate = endDate;
  }
  
  @Override
  public String toString() {
    return "[E] " + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
  }
}
