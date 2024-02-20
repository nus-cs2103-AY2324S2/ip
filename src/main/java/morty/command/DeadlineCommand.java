package morty.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import morty.Storage;
import morty.TaskList;
import morty.Response;
import morty.task.Deadline;
import morty.task.Task;

/**
 * Represents a command to add a deadline task.
 */
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
   * @param tasks The list of tasks.
   * @param ui The user interface.
   * @param storage The storage.
   * @return The response after executing the DeadlineCommand.
   */
  @Override
  public String execute(TaskList tasks, Response ui, Storage storage) {
    try {
      String[] deadlineTokens = tokens[1].split(" /by ");
      String description = deadlineTokens[0];
      LocalDate by = LocalDate.parse(deadlineTokens[1]);
      Task newTask = new Deadline(description, by);
      tasks.add(newTask);
      storage.save(tasks);
      return ui.showTaskAdded(newTask, tasks.getSize());
    } catch (ArrayIndexOutOfBoundsException e) {
      return ui.showDeadlineUsage();
    } catch (DateTimeParseException e) {
      return ui.showDeadlineUsage();
    }
  }
}
