public interface Command {
  void executeCommand(TaskList taskList, Ui ui, Storage storage) throws AlpaException;
  boolean isExit();
}
