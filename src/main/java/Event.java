public class Event extends Task {
  private String startToEnd;

  public Event(String description, String startToEnd) {
    super(description);
    this.startToEnd = startToEnd;
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (from: " + this.startToEnd + ")";
  }
}
