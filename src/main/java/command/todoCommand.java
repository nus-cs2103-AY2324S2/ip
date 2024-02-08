package command;
import duke.TaskList;
import duke.Ui;
import exception.EmptyInputException;
import task.Todo;

/**
 * Command to add a todo into the task list.
 */
public class TodoCommand extends Command {

    private TaskList taskList;
    private Ui ui;
    public TodoCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    /**
     * Execute to add new todo task into the list
     *
     * @param taskList The task list which the command will modify.
     * @param ui The ui to get the input of the user.
     * @throws EmptyInputException If user did not input description.
     */
    public void execute(TaskList taskList, Ui ui) throws EmptyInputException {
        String input = ui.getInput();
        if (input.split(" ").length > 1) {
            String description = input.substring(4).trim();
            Todo t = new Todo(description);
            taskList.todo(t);
        } else {
            throw new EmptyInputException("todo");
        }
    }

    public boolean isExit() {
        return false;
    }
}
