package bytebuddy.commands;

import bytebuddy.exceptions.DukeException;
import bytebuddy.storage.Storage;
import bytebuddy.tasks.TaskList;
import bytebuddy.ui.Ui;

/**
 * Command to find tasks in the task list that match a specified keyword.
 * The keyword is used to search for tasks containing the specified text in their descriptions.
 * Executes the 'findInTaskList' method in the provided 'TaskList' instance.
 */
public class FindCommand implements Command {

    private String info;

    /**
     * Constructs a new FindCommand with the specified keyword.
     *
     * @param info The keyword or text to search for among all the tasks in the task list.
     */
    public FindCommand(String info) {
        this.info = info;
    }

    /**
     * Executes the 'findInTaskList' method in the provided 'TaskList' instance to find tasks
     * containing the specified keyword in their descriptions.
     *
     * @param taskList The TaskList instance containing the list of tasks.
     * @param ui       The Ui instance for user interaction.
     * @param storage  The Storage instance for file storage operations.
     * @throws DukeException If there is an issue executing the 'findInTaskList' method.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.findTaskWithKeywordInTaskList(info);
    }

    /**
     * Checks if the DeleteCommand is an exit command, indicating that the chatbot should not exit.
     *
     * @return {@code false} as FindCommand is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
