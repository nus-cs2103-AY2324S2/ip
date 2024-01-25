package Task;

public class Deadline extends Task {
  private String dueTime;

  public Deadline(String description, String due) {
    super(description);
    this.dueTime = due;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (due: " + dueTime + ")";
  }
}
