public class MarkCommand extends Command{

  @Override
  public void execute(TaskList taskList, Ui ui, String userInput) throws DukeException {
    int taskIndex = Parser.extractTaskIndex(userInput);
    Task task = taskList.markTaskAsDone(taskIndex); // Use TaskList to mark task as done
    if (task != null) {
        ui.showTaskDone(task);
    } else {
        throw new DukeException("Invalid task number. Please try again.");
    }
  }

}