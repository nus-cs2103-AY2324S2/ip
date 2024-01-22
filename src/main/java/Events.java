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
  public String getTimeData() {
    return "(from: " + this.getStart() + "to: " + this.getEnd() + ")";
  }
}
