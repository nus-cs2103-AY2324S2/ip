import java.lang.String;
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
