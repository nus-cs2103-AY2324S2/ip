package baron.Models;
public class Todo extends Task {

  public Todo(String name) {
    super(name);
  }

  public Todo(int id, String name, boolean done) {
    super(id, name, done);
  }

  @Override
  public String toString() {
    return "[T]" + super.toString();
  }


  @Override
  public String toDataString() {
    return super.toDataString();
  }

  /**
   * Parses data string to create a todo object.
   * @param data Todo line
   * @return Created Todo object
   */
  public static Todo fromDataString(String data) {
    String[] segments = data.split("\\s*\\|\\s*");
    int id = Integer.parseInt(segments[0]);
    boolean done = Long.parseLong(segments[1]) == 1;
    // Strong assumption that there is no | in the data
    String name = segments[2];
    return new Todo(id, name, done);
  }
}
