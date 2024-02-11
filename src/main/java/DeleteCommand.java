public class DeleteCommand implements Command {
  private final int index;

  public DeleteCommand(String indexStr) throws AlpaException {
    try {
      this.index = Integer.parseInt(indexStr) - 1;
    } catch (NumberFormatException e) {
      throw new AlpaException("\nInvalid input for delete, human!!");
    }
  }

  @Override
  public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws AlpaException {
    if (index < 0 || index >= taskList.getSize()) {
      throw new AlpaException("Invalid task number, human!!");
    }
    Task removedTask = taskList.deleteTask(index);
    ui.showDeletedTask(removedTask, taskList.getSize());
    storage.saveTasks(taskList.getTasks());
  }

  @Override
  public boolean isExit() {
    return false;
  }
}
