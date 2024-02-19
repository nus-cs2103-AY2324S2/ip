package judy.commands;

import judy.storage.Storage;
import judy.task.TaskList;
import judy.ui.Ui;

/**
 * The ListTaskCommand class represents a command to display the list of tasks.
 */
public class ListTaskCommand extends Command {
    public static final String COMMAND_WORD = "list";
    private final TaskList taskList; // The task list to be displayed

    /**
     * Constructs a ListTaskCommand with the specified task list.
     *
     * @param taskList The task list to be displayed.
     */
    public ListTaskCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Executes the command by displaying the list of tasks in the user interface.
     *
     * @param storage The storage component (not used in this implementation).
     * @param ui      The user interface component for displaying messages to the user.
     */
    @Override
    public void execute(Storage storage, Ui ui) {
        ui.showTaskList(this.taskList);
    }

    /**
     * Indicates whether this command represents an exit command.
     * In this case, always returns false as adding a task does not trigger an exit.
     *
     * @return Always returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
