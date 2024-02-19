package duke;

/**
 * Represents a todo task in the task list
 */
public class Todos extends Task {

  /**
   * Constructs a Todos object with the specified description.
   *
   * @param description The description of the todo task.
   */
  public Todos(String description) {
    super(description);
  }

  @Override
  public String toString() {
    assert description != null : "Description must not be null";
    return "[T][" + getStatusIcon() + "] " + description;
  }

  @Override
  public String toFileString() {
    assert description != null : "Description must not be null";
    return String.format("T | %d | %s", isDone ? 1 : 0, description);
  }
}