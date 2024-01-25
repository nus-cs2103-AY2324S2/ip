public class ToDo extends Task {

  public static ToDo createFromInput(String input) {
    String description = input.split("todo ")[1];
    return new ToDo(description);
  }

  public ToDo(String description) {
    super(description);
  }

  @Override
  public String toString() {
    return "[T]" + super.toString();
  }
}
