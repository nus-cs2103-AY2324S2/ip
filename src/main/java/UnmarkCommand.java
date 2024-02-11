public class UnmarkCommand implements Command {
  private int index;

  public UnmarkCommand(String index) {
    this.index = Integer.parseInt(index.trim()) - 1;
  }

  @Override
  public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws AlpaException {
    Task task = taskList.markTaskAsNotDone(index);
    ui.showMarkedAsNotDone(task);
    storage.saveTasks(taskList.getTasks());
  }
  
  @Override
  public boolean isExit() {
    return false;
  }
}
