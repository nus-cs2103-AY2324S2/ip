package duke.commands;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;

/**
 * ListCommand class represents a command to list all tasks in the task list.
 * It extends the Command class and provides methods to execute the command.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command by displaying all tasks in the task list.
     * If the task list is empty, it displays a message indicating no tasks.
     *
     * @param taskList The list of tasks to display.
     * @param ui       The user interface (not used in this command).
     * @param storage  The storage handler (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.size() == 0) {
            System.out.println("Yay! You have no tasks!");
        } else {
            System.out.println("Here are the tasks in your list:");
            System.out.println(taskList);
        }
    }
}
