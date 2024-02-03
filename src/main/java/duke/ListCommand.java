/**
 * The ListCommand class represents a command to list all tasks in the task list.
 * It is a subclass of the Command class.
 */
package duke;

import duke.task.Task;
import duke.task.TaskList;
import java.util.List;

public class ListCommand extends Command {

    /**
     * Executes the list command to display all tasks in the task list.
     *
     * @param taskList   The task list to retrieve tasks from.
     * @param ui         The user interface for displaying messages.
     * @param userInput  The user input specifying the list command.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, String userInput) {
        List<Task> tasks = taskList.getTasks(); // Use duke.task.TaskList to get the list of tasks
        ui.showTasks(tasks);
    }
}
