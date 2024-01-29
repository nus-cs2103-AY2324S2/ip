package Models;
public class Deadline extends Task {

  private String deadline;
  public Deadline(String name, String deadline) {
    super(name);
    this.deadline = deadline;
  }

  public Deadline(int id, String name, boolean done, String deadline) {
    super(id, name, done);
    this.deadline = deadline;
  }
  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + deadline + ")";
  }

  public String toDataString() {
    return super.toDataString() + " | "  + this.deadline;
  }

  public static Deadline fromDataString(String data) {
    String[] segments = data.split("\\s*\\|\\s*");
    int id = Integer.parseInt(segments[0]);
    boolean done = Long.parseLong(segments[1]) == 1;
    // Strong assumption that there is no | in the data
    String name = segments[2];
    String deadline = segments[3];
    return new Deadline(id, name, done, deadline);
  }
}
