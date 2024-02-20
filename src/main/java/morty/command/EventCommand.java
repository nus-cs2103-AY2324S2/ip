package morty.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import morty.Storage;
import morty.TaskList;
import morty.Response;
import morty.task.Event;
import morty.task.Task;

/**
 * Represents a command to add a deadline task.
 */
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
   * @param tasks The list of tasks.
   * @param ui The user interface.
   * @param storage The storage.
   * @return The response after executing the EventCommand.
   */
  @Override
  public String execute(TaskList tasks, Response ui, Storage storage) {
    try {
      String[] eventTokens = tokens[1].split(" /at ");
      String description = eventTokens[0];
      LocalDate at = LocalDate.parse(eventTokens[1]);
      Task newTask = new Event(description, at);
      tasks.add(newTask);
      storage.save(tasks);
      return ui.showTaskAdded(newTask, tasks.getSize());
    } catch (ArrayIndexOutOfBoundsException e) {
      return ui.showEventUsage();
    } catch (DateTimeParseException e) {
      return ui.showEventUsage();
    }
  }
}
