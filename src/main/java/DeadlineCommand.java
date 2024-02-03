import java.time.LocalDate;

public class DeadlineCommand extends Command {
  private final String description;
  private final LocalDate deadline;

  public DeadlineCommand(String description, LocalDate deadline) {
    this.description = description;
    this.deadline = deadline;
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    Task newDeadline = new Deadline(false, description, deadline);
    tasks.addTask(newDeadline);

    ui.showAddedTaskMessage();
    ui.show(newDeadline.toString());
    ui.showCurrNoOfTasks(tasks);
  }
}