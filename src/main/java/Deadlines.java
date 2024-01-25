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
  public String getLogRepresentation() {
    String complete_status = "F";
    if (this.isDone) {complete_status = "T";}
    return "D" + "," + complete_status + ","
      + this.description + "," + this.deadline;
  }

  @Override
  public String getTimeData() {
    return "(by: " + this.getDeadline() + ")";
  }

}
