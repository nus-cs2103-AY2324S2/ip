public class ToDoTask extends Task {

  public ToDoTask(String name) {
    super(name);
  }

  @Override
  public String toString() {
    return String.format("[T]%s", super.toString());
  }

}
