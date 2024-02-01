public class Deadline extends Task {
  protected String by;

  public Deadline(boolean isDone, String description, String by) {
    super(isDone, description);
    this.by = by;
  }

  @Override
  public String saveTask() {
    return "D | " + super.saveTask() + " | " + this.by;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + this.by + ")";
  }
}
