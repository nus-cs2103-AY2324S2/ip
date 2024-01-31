public class ListCommand extends Command {

  /**
   * Constructs a ListCommand object with the given tokens.
   *
   * @param tokens The tokens of the user input.
   */
  public ListCommand(String[] tokens) {
    super(tokens);
  }

  /**
   * Executes the ListCommand.
   *
   * @param tasks   The list of tasks.
   * @param ui      The user interface.
   * @param storage The storage.
   */
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    ui.showTaskList(tasks);
  }
}
