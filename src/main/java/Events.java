/**
 * Events class
 */
public class Events extends Task {
  protected String at;

  public Events(String description, String from, String to) {
      super(description);
      this.at = from + ", to: " + to;
  }

  @Override
  protected String taskType() {
      return "E";
  }

  @Override
  public String toString() {
      return super.toString() + " (from: " + at + ")";
  }
}