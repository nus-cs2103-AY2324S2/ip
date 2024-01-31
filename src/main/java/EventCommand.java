import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {

  /**
   * Constructs a EventCommand object with the given tokens.
   *
   * @param tokens The tokens of the user input.
   */
  public EventCommand(String[] tokens) {
    super(tokens);
  }

  /**
   * Executes the EventCommand.
   *
   * @param tasks   The list of tasks.
   * @param ui      The user interface.
   * @param storage The storage.
   */
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    try {
      String[] eventTokens = tokens[1].split(" /at ");
      String description = eventTokens[0];
      LocalDate at = LocalDate.parse(eventTokens[1]);
      Task newTask = new Event(description, at);
      tasks.add(newTask);
      ui.showTaskAdded(newTask, tasks.getSize());
      storage.save(tasks);
    } catch (ArrayIndexOutOfBoundsException e) {
      ui.showEventUsage();
    } catch (DateTimeParseException e) {
      ui.showEventUsage();
    }
  }
}
