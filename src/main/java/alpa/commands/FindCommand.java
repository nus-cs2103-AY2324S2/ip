package alpa.commands;

import java.util.List;
import java.util.stream.Collectors;

import alpa.exceptions.AlpaException;
import alpa.tasks.Task;
import alpa.tasks.TaskList;
import alpa.utils.Storage;

/**
 * Represents a command to find tasks based on a keyword.
 */
public class FindCommand implements Command {
    private String keyword;

    /**
     * Constructs a FindCommand object with the specified keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand, finding tasks that match the keyword and displaying them.
     *
     * @param taskList The task list to search for tasks.
     * @param storage The storage to save the task list.
     * @return The result of executing the command.
     * @throws AlpaException If there is an error executing the command.
     */
    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        List<Task> foundTasks = taskList.findTasksByKeyword(keyword);
        if (foundTasks.isEmpty()) {
            return "No tasks found with the keyword: " + keyword;
        } else {
            String foundTasksString = foundTasks.stream()
                .map(Task::toString)
                .collect(Collectors.joining("\n"));
            return "Here are the matching tasks in your list:\n" + foundTasksString;
        }
    }

    /**
     * Checks if the FindCommand is an exit command.
     *
     * @return False, as the FindCommand is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
