import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {

  /**
   * Constructs a DeadlineCommand object with the given tokens.
   *
   * @param tokens The tokens of the user input.
   */
  public DeadlineCommand(String[] tokens) {
    super(tokens);
  }

  /**
   * Executes the DeadlineCommand.
   *
   * @param tasks   The list of tasks.
   * @param ui      The user interface.
   * @param storage The storage.
   */
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    try {
      String[] deadlineTokens = tokens[1].split(" /by ");
      String description = deadlineTokens[0];
      LocalDate by = LocalDate.parse(deadlineTokens[1]);
      Task newTask = new Deadline(description, by);
      tasks.add(newTask);
      ui.showTaskAdded(newTask, tasks.getSize());
      storage.save(tasks);
    } catch (ArrayIndexOutOfBoundsException e) {
      ui.showDeadlineUsage();
    } catch (DateTimeParseException e) {
      ui.showDeadlineUsage();
    }
  }
}
