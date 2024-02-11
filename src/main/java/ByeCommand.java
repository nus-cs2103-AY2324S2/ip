public class ByeCommand implements Command {
  
  @Override
  public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
    ui.showGoodbye();
    System.exit(0);
  }

  @Override
  public boolean isExit() {
    return true;
  }
}
