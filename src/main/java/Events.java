public class Events extends Task {

  String start;

  String end;

  public Events(String description, String start, String end) {
    super(description, "[E]");
    this.start = start;
    this.end = end;
  }

  public String getStart() {return this.start;}

  public String getEnd() {return this.end;}

  @Override
  public String getLogRepresentation() {
    String complete_status = "F";
    if (this.isDone) {complete_status = "T";}
    return "E" + "," + complete_status + "," +
      this.description + "," + this.getStart() + "," + this.getEnd();
  }

  @Override
  public String getTimeData() {
    return "(from: " + this.getStart() + " to: " + this.getEnd() + ")";
  }
}
