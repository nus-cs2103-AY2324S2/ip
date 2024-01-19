public class Task {
  protected final String description;
  protected boolean isDone = false;

  public Task(String description) {
    this.description = description;
  }

  public String getDetails() {
    return this.description;
  }

  public Task toggleDone() {
    this.isDone = !this.isDone;
    return this;
  }

  public boolean isDone() {
    return this.isDone;
  }

  public String getStatusIcon() {
    return this.isDone ? "X" : " ";
  }

  @Override
  public String toString() {
    return String.format("[%s] %s", this.getStatusIcon(), this.getDetails());
  }
}
