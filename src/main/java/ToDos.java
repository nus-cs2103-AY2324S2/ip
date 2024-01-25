public class ToDos extends Task {
  public ToDos(String description) {
    super(description, "[T]");
  }

  @Override
  public String getTimeData() {return "";}

  @Override
  public String getLogRepresentation() {
    String complete_status = "F";
    if (this.isDone) {complete_status = "T";}
    return "T" + "," + complete_status + "," + this.description;
  }

  @Override
  public String getFullStatus() {
    return this.getStatusIcon() + " " + this.getDescription();
  }
}
