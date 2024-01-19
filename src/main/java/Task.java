public class Task {
  private final String description;
  private boolean isDone = false;

  public Task(String description) {
    this.description = description;
  }

  public String getDetails() {
    return description;
  }

  public Task toggleDone() {
    this.isDone = !this.isDone;
    return this;
  }

  public boolean isDone() {
    return isDone;
  }

  public String getStatusIcon() {
    return isDone ? "X" : " ";
  }

  @Override
  public String toString() {
    return "[" + getStatusIcon() + "] " + getDetails();
  }
}
