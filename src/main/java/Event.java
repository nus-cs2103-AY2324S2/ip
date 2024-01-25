public class Event extends Task {

  protected String at;
  protected String by;

  public static Event createFromInput(String input) throws DukeException {
    try {
      String[] parts = input.split("/from | /to ");
      String description = parts[0].split("event ")[1];
      String at = parts[1];
      String by = parts[2];
      return new Event(description, at, by);
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new DukeException("Invalid event format. Please use 'event description /from start /to end'.");
    }
  }

  public Event(String description, String at, String by) {
    super(description);
    this.at = at;
    this.by = by;
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (from: " + at + " to: " + by + ")";
  }
}
