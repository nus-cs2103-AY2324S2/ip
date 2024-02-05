package duke;

public class Todos extends Task {

  public Todos(String description) {
    super(description);
  }

  @Override
  public String toString() {
    return "[T][" + getStatusIcon() + "] " + description;
  }

  @Override
  public String toFileString() {
    return String.format("T | %d | %s", isDone ? 1 : 0, description);
  }
}