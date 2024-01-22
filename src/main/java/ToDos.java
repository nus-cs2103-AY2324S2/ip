public class ToDos extends Task {
  public ToDos(String description) {
    super(description, "[T]");
  }

  @Override
  public String getTimeData() {return "";}

  @Override
  public String getFullStatus() {
    return this.getStatusIcon() + " " + this.getDescription();
  }
}
