public class ToDo extends Task {

  public static ToDo createFromInput(String input) throws DukeException {
    try {
      String description = input.split("todo ")[1];
      return new ToDo(description);
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new DukeException("Invalid todo format. Please use 'todo description'.");
    }
  }

  public ToDo(String description) {
    super(description);
  }

  @Override
  public String toString() {
    return "[T]" + super.toString();
  }
}
