package Task;

public class EventCommand extends Command {
  private String taskDescription;
  private String startTime;
  private String endTime;

  public EventCommand(String body) {
    super(body);
    String[] parts = body.split("/from", 2);
    String[] parts2 = parts[1].split("/to", 2);
    this.taskDescription = parts[0].trim();
    this.startTime = parts2[0].trim();
    this.endTime = parts2[1].trim();
  }

  String execute(TaskList list) {
    Task task = new Event(taskDescription, startTime, endTime);
    list.addTask(task);
    return "Added: " + task + "\nYou now have " + list.size() + " tasks in the list.";
  }
}
