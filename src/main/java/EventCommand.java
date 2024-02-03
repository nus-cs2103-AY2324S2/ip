public class EventCommand extends Command {
  private final String description;
  private final String startDate;
  private final String endDate;

  public EventCommand(String description, String startDate, String endDate) {
    this.description = description;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    Task newEvent = new Event(false, description, startDate, endDate);
    tasks.addTask(newEvent);

    ui.showAddedTaskMessage();
    ui.show(newEvent.toString());
    ui.showCurrNoOfTasks(tasks);
  }
}