public abstract class Command {
  public abstract void execute(TaskList taskList, Ui ui, String userInput) throws DukeException;
}