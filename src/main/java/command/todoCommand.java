package command;
import duke.Ui;
import duke.TaskList;
import exception.EmptyInputException;
import task.Todo;

/**
 * Command to add a todo into the task list.
 */
public class todoCommand extends Command {

    private TaskList taskList;
    private Ui ui;

    /**
     * The constructor of todo Command.
     *
     * @param taskList The task list which the command will modify.
     * @param ui The ui to get the input of the user.
     */
    public todoCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

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
