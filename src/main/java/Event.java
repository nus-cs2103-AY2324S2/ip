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

  @Override
  public void mark() {
    super.setComplete();
    System.out.println("\tNice! I've marked this task as done:\n\t" + this.toString());
  }

  @Override
  public void unmark() {
    this.setIncomplete();
    System.out.println("\tOK, I've marked this task as not done yet:\n\t" + this.toString());
  }
}
