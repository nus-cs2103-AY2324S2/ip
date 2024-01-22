import java.lang.String;
public class Task {

  protected String description;
  protected boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }
  public void mark() {
    this.isDone = true;
  }
  public void unmark() {
    this.isDone = false;
  }

  public String getDescription() {
    return this.description;
  }

  public String getStatusIcon() {
    return (isDone ? "[X]" : "[ ]");
  }
}
