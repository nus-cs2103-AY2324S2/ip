package alpa.tasks;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {
  
  /**
   * Constructs a ToDo task with the given description.
   * @param description The description of the ToDo task.
   */
  public ToDo(String description) {
    super(description, TaskType.TODO);
  }

  /**
   * Returns a string representation of the ToDo task.
   * @return A string representation of the ToDo task.
   */
  @Override
  public String toString() {
    return "[ T ]" + super.toString();
  }
}
