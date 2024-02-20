package morty.command;

import morty.Storage;
import morty.TaskList;
import morty.MortyException;
import morty.Response;
import morty.task.Todo;

/**
 * Represents a command to add a todo task.
 */
public class TodoCommand extends Command {

  /**
   * Constructs a TodoCommand object with the given tokens.
   *
   * @param tokens The tokens of the user input.
   */
  public TodoCommand(String[] tokens) {
    super(tokens);
  }

  /**
   * Executes the TodoCommand.
   *
   * @param tasks The list of tasks.
   * @param ui The user interface.
   * @param storage The storage.
   * @return The response after executing the TodoCommand.
   */
  @Override
  public String execute(TaskList tasks, Response ui, Storage storage) {
    try {
      String title = tokens[1];
      Todo newTodo = new Todo(title);
      tasks.add(newTodo);
      storage.save(tasks);
      return ui.showTaskAdded(newTodo, tasks.getSize());
    } catch (MortyException e) {
      return ui.showTodoUsage();
    }
  }
}
