package bytebuddy.commands;

import bytebuddy.exceptions.DukeException;
import bytebuddy.storage.Storage;
import bytebuddy.tasks.TaskList;
import bytebuddy.ui.Ui;

/**
 * Command to display the list of tasks in the task list.
 */
public class ListCommand implements Command {

    /**
     * Executes the ListCommand, displaying the list of tasks in the task list.
     *
     * @param taskList The task list whose tasks will be displayed.
     * @param ui       The user interface for displaying information.
     * @param storage  The storage for saving and loading data.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.printTaskList();
    }

    /**
     * Checks if the ListCommand is an exit command, indicating that the chatbot should not exit.
     *
     * @return {@code false} as ListCommand is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
