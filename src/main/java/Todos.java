public class Todos extends Task {

  public Todos(String description) {
    super(description);
  }

  @Override
  public String toString() {
    return "[T][" + getStatusIcon() + "] " + description;
  }
}
