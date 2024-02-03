public class UnmarkCommand extends Command {

  @Override
  public void execute(TaskList taskList, Ui ui, String userInput) throws DukeException {
    int taskIndex = Parser.extractTaskIndex(userInput);
    Task task = taskList.unmarkTask(taskIndex); // Use TaskList to unmark task
    if (task != null) {
        ui.showTaskUndone(task);
    } else {
        throw new DukeException("Invalid task number. Please try again.");
    }
  }
  
}
