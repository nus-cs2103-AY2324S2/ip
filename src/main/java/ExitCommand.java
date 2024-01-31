public class ExitCommand extends Command {

  /**
   * Constructs a ExitCommand object with the given tokens.
   *
   * @param tokens The tokens of the user input.
   */
  public ExitCommand(String[] tokens) {
    super(tokens);
    this.exit = true;
  }

  /**
   * Executes the ExitCommand.
   *
   * @param tasks   The list of tasks.
   * @param ui      The user interface.
   * @param storage The storage.
   */
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    ui.showGoodbye();
  }
}
