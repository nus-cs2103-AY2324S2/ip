package toothless.commands;

import java.util.ArrayList;
import toothless.Storage;
import toothless.TaskList;
import toothless.ToothlessException;
import toothless.Ui;
import toothless.tasks.Task;

/**
 * Represents a command to find and display tasks in the task list that match a given keyword.
 * The detail is used to search through task descriptions to find matches.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword for searching.
     * @param keyword The search criteria used to find matching tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find and display tasks that match the given detail.
     * Searches through the task list and displays tasks with descriptions that contain the specified keyword.
     * @param ui The user interface to interact with.
     * @param taskList The task list to search through.
     * @param storage The storage system, not directly used by this command.
     * @return false to indicate the application should continue running.
     * @throws ToothlessException If the task list is empty or no tasks match the search criteria.
     */
    @Override
    public String handle(Ui ui, TaskList taskList, Storage storage) throws ToothlessException {
        if (taskList.size() == 0) {
            throw new ToothlessException(ui.showEmptyListWarning());
        }

        ArrayList<Task> list = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.getTask(i);
            if (task.contains(keyword)) {
                list.add(task);
            }
        }

        if (list.size() == 0) {
            throw new ToothlessException(ui.showNoMatchingTaskWarning());
        }

        return ui.showFoundTasks(list);
    }

    /**
     * Indicates whether the command is an exit command.
     * @return False, as the command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
