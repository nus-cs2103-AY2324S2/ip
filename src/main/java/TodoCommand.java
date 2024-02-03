public class TodoCommand extends Command {
  private final String description;

  public TodoCommand(String description) {
    this.description = description;
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    Task newTodo = new Todo(false, description);
    tasks.addTask(newTodo);

    ui.showAddedTaskMessage();
    ui.show(newTodo.toString());
    ui.showCurrNoOfTasks(tasks);
  }
}
