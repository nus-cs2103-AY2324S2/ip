package morty.task;

/**
 * Class representing a Todo task.
 * Inherits from the Task class and adds specific formatting to the toString
 * method.
 */
public class Todo extends Task {
  /**
   * Constructor to create a new Todo task.
   * Calls the constructor of the parent Task class.
   *
   * @param title The title of the todo task.
   */
  public Todo(String title) {
    super(title);
  }

  /**
   * Constructor to create a new Todo task.
   * Calls the constructor of the parent Task class.
   *
   * @param title The title of the todo task.
   */
  public Todo(String title, boolean isDone) {
    super(title, isDone);
  }

  /**
   * Provides a string representation of the Todo task.
   * Format: "[T][\u2713 or \u2718] Title" for completed or pending tasks
   * respectively.
   *
   * @return The formatted string representation of the Todo task.
   */
  @Override
  public String toString() {
    return "[T]" + super.toString();
  }

  /**
   * Provides a string representation of the Todo task for saving to disk.
   * Format: "T | 0 or 1 | Title" for pending or completed tasks respectively.
   *
   * @return The formatted string representation of the Todo task for saving to
   *         disk.
   */
  @Override
  public String serialize() {
    return "T | " + super.serialize();
  }

  /**
   * Deserializes a Todo task from a string representation.
   * Format: "T | 0 or 1 | Title" for pending or completed tasks respectively.
   *
   * @param text The string representation of the Todo task.
   * @return The Todo task represented by the string.
   */
  public static Todo deserialize(String text) {
    String[] parts = text.split(" \\| ");
    return new Todo(parts[2], parts[1].equals("1"));
  }
}
