package Models;
public class Task {
  private String name;
  private boolean isDone;

  public Task(String name) {
    this.name = name;
    this.isDone = false;
  }

  public Task(String name, boolean isDone) {
    this.name = name;
    this.isDone = isDone;
  }

  public String getName() {
    return name;
  }

  public boolean isDone() {
    return isDone;
  }

  public void markAsDone() {
    isDone = true;
  }

  public void markAsUndone() {
    isDone = false;
  }

  @Override
  public String toString() {
    String done = isDone ? "X" : " ";
    return "[" + done + "] " + name;
  }
}
