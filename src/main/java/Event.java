/**
 * Class representing an Event task with a location or time.
 * Inherits from the Task class and adds event details.
 */
class Event extends Task {
  private String at;

  /**
   * Constructor to create a new Event task.
   * Initializes the task with a title and event details.
   *
   * @param title The title of the event task.
   * @param at    The location or time of the event.
   */
  public Event(String title, String at) {
    super(title);
    this.at = at;
  }

  /**
   * Constructor to create a new Event task.
   * Initializes the task with a title, event details and a completion status.
   *
   * @param title  The title of the event task.
   * @param at     The location or time of the event.
   * @param isDone The completion status of the task.
   */
  public Event(String title, String at, boolean isDone) {
    super(title, isDone);
    this.at = at;
  }

  /**
   * Provides a string representation of the Event task.
   * Format: "[E][\u2713 or \u2718] Title (at: event details)" for completed or
   * pending tasks respectively.
   *
   * @return The formatted string representation of the Event task.
   */
  @Override
  public String toString() {
    return "[E]" + super.toString() + " (at: " + at + ")";
  }

  /**
   * Provides a string representation of the Event task for saving to disk.
   * Format: "E | 0 or 1 | Title | event details" for pending or completed tasks
   * respectively.
   *
   * @return The formatted string representation of the Event task for saving
   *         to disk.
   */
  @Override
  public String serialize() {
    return "E | " + super.serialize() + " | " + at;
  }

  /**
   * Deserializes an Event task from a string representation.
   * Format: "E | 0 or 1 | Title | event details" for pending or completed tasks
   * respectively.
   *
   * @return The formatted string representation of the Event task for saving
   *         to disk.
   */
  public static Event deserialize(String text) {
    String[] parts = text.split(" \\| ");
    return new Event(parts[2], parts[3], parts[1].equals("1"));
  }
}
