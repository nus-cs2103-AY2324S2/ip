package judy.commands;

import judy.storage.Storage;
import judy.task.Task;
import judy.task.TaskList;
import judy.ui.Ui;

/**
 * The FindCommand class represents a command to find tasks in the task list that match a given keyword.
 */
public class FindTaskCommand extends Command{
    public static final String COMMAND_WORD = "find";
    private final TaskList taskList;
    private final String keyword;

    /**
     * Creates a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindTaskCommand(TaskList taskList, String keyword) {
        this.taskList = taskList;
        this.keyword = keyword;
    }

    /**
     * Finds tasks in the task list that contain the specified keyword.
     *
     * @return A list of tasks containing the keyword.
     */
    private TaskList findMatchingTasks() {
        TaskList matchingTasks = new TaskList();
        for (Task task : taskList.getTaskList()) {
            if (task.containsKeyword(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Executes the find command to search for tasks containing the specified keyword.
     *
     * @param storage The storage for saving tasks.
     * @param ui      The user interface for displaying messages.
     */
    @Override
    public void execute(Storage storage, Ui ui) {
        TaskList matchingTasks = findMatchingTasks();
        ui.showFindTasks(matchingTasks);
    }


    /**
     * Indicates whether this command represents an exit command.
     *
     * @return Always returns false as finding tasks does not trigger an exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
