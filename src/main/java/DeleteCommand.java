public class DeleteCommand extends Command {

  /**
   * Constructs a DeleteCommand object with the given tokens.
   *
   * @param tokens The tokens of the user input.
   */
  public DeleteCommand(String[] tokens) {
    super(tokens);
  }

  /**
   * Executes the DeleteCommand.
   *
   * @param tasks   The list of tasks.
   * @param ui      The user interface.
   * @param storage The storage.
   */
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    try {
      int index = Integer.parseInt(tokens[1]) - 1;
      Task task = tasks.get(index);
      tasks.remove(index);
      ui.showTaskRemoved(task, tasks.getSize());
      storage.save(tasks);
    } catch (NumberFormatException e) {
      ui.showInvalidDelete();
    } catch (IndexOutOfBoundsException e) {
      ui.showInvalidDelete();
    }
  }
}
