package alpa.commands;

import java.util.List;

import alpa.tasks.Task;
import alpa.tasks.TaskList;
import alpa.utils.Storage;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand implements Command {

    /**
     * Executes the list command by displaying all tasks in the task list.
     *
     * @param taskList The task list containing the tasks.
     * @param storage The storage to save the task list.
     * @return A string representation of the tasks in the task list.
     */
    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        if (taskList.getSize() == 0) {
            return "Your list is empty, human!";
        }
        List<Task> tasks = taskList.getTasks(); // Use getTasks() to get the list of tasks
        StringBuilder listOutput = new StringBuilder("Your list, human!\n");
        for (int i = 0; i < tasks.size(); i++) {
            listOutput.append("  ").append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return listOutput.toString().trim(); // Trim to remove the last newline character
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return False, as the list command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
