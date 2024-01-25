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
}
