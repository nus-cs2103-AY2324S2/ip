package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Todo;

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
   * @param tasks   The list of tasks.
   * @param ui      The user interface.
   * @param storage The storage.
   */
  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    try {
      String title = tokens[1];
      Todo newTodo = new Todo(title);
      tasks.add(newTodo);
      ui.showTaskAdded(newTodo, tasks.getSize());
      storage.save(tasks);
    } catch (ArrayIndexOutOfBoundsException e) {
      ui.showTodoUsage();
    }
  }
}
