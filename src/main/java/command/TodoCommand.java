package command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.EmptyInputException;
import task.Todo;

/**
 * Command to add a todo into the task list.
 */
public class TodoCommand extends Command {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * The constructor for TodoCommand
     *
     * @param taskList The task list which the command will modify.
     * @param ui The ui to get the input of the user.
     * @param storage The storage to write task into.
     */
    public TodoCommand(TaskList taskList, Ui ui, Storage storage) {

        super(taskList, ui, storage);
    }

    /**
     * Execute to add new todo task into the list
     *
     * @param taskList The task list which the command will modify.
     * @param ui The ui to get the input of the user.
     * @param storage The storage to write task into.
     * @throws EmptyInputException If user did not input description.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws EmptyInputException {
        String input = ui.getInput();
        if (input.split(" ").length > 1) {
            String description = input.substring(4).trim();
            Todo t = new Todo(description);
            String str = taskList.todo(t);
            storage.writeTasks(taskList);
            return str;
        } else {
            throw new EmptyInputException("todo");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
