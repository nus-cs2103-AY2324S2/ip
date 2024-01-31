public class DoneCommand extends Command {

  /**
   * Constructs a DoneCommand object with the given tokens.
   *
   * @param tokens The tokens of the user input.
   */
  public DoneCommand(String[] tokens) {
    super(tokens);
  }

  /**
   * Executes the DoneCommand.
   *
   * @param tasks   The list of tasks.
   * @param ui      The user interface.
   * @param storage The storage.
   */
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    try {
      int index = Integer.parseInt(tokens[1]) - 1;
      Task task = tasks.get(index);
      tasks.markDone(index);
      ui.showTaskMarkedDone(task);
      storage.save(tasks);
    } catch (NumberFormatException e) {
      ui.showInvalidDone();
    } catch (IndexOutOfBoundsException e) {
      ui.showInvalidDone();
    }
  }
}
