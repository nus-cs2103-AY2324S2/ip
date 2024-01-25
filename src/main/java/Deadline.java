public class Deadline extends Task {

  protected String by;

  public static Deadline createFromInput(String input) throws DukeException {
    try {
      String[] parts = input.split("/by ");
      String description = parts[0].split("deadline ")[1];
      String by = parts[1];
      return new Deadline(description, by);
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new DukeException("Invalid deadline format. Please use 'deadline description /by date'.");
    }
  }

  public Deadline(String description, String by) {
    super(description);
    this.by = by;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + by + ")";
  }
}
