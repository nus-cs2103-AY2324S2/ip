public class Task {
  protected String description;
  protected boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  public String getStatusIcon() {
    return (isDone ? "X" : " ");
  }

  public void markAsDone() {
    this.isDone = true;
  }

  public void unmarkAsDone() {
    this.isDone = false;
  }

  @Override
  public String toString() {
    return "[" + this.getStatusIcon() + "] " + this.description;
  }

  public String toFileString() {
    return (this.isDone ? "1" : "0") + " | " + this.description;
  }

}
