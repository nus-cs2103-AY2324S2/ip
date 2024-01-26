import java.lang.String;
import java.time.LocalDateTime;

public abstract class Task {

  protected String description;
  protected boolean isDone;

  protected String icon;

  public Task(String description, String icon) {
    this.description = description;
    this.isDone = false;
    this.icon = icon;
  }
  public String getIcon() {return this.icon;}
  public void mark() {
    this.isDone = true;
  }
  public void unmark() {
    this.isDone = false;
  }

  public String decomposeDateTime(LocalDateTime dt) {
    String minute = ((Integer) dt.getMinute()).toString();
    if (minute.length() < 2) {
      minute = "0" + minute;
    }
    return dt.getDayOfMonth() + " " + dt.getMonth() + " " + dt.getYear() + " " +
      dt.getHour() + ":" + minute + "H";
  }

  public void setCompletion(boolean state) {this.isDone = state;}

  public String getDescription() {
    return this.description;
  }

  public String getStatusIcon() {
    return this.getIcon() + (isDone ? "[X]" : "[ ]");
  }

  public abstract String getTimeData();

  public abstract String getLogRepresentation();

  public String getFullStatus() {
    return this.getStatusIcon() + " " + this.getDescription() + " " + this.getTimeData();
  }

}
