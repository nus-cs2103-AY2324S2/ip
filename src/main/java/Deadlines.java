public class Deadlines extends Task {

  String deadline;

  public Deadlines (String description, String deadline) {
    super(description, "[D]");
    this.deadline = deadline;
  }

  public String getDeadline() {
    return this.deadline;
  }

  @Override
  public String getTimeData() {
    return "(by: " + this.getDeadline() + ")";
  }

}
