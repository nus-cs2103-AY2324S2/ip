public class Events extends Task {

  protected String from;
  protected String to;

  public Events(String description, String from, String to) {
    super(description);
    this.from = from;
    this.to = to;
  }

  @Override
  public String toString() {
    return "[E][" + getStatusIcon() + "] " + description + "(from: " + from + "to: " + to + ")";
  }

  @Override
  public String toFileString() {
    // Include the status in the output string
    // For example: "E | 0 | project meeting | Aug 6th 2-4pm"
    return String.format("E | %d | %s | %s | %s", isDone ? 1 : 0, description, from, to);
  }
}
