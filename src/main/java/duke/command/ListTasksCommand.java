package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to list all tasks currently in the task list.
 * This command is responsible for triggering the display of all tasks to the user.
 */
public class ListTasksCommand extends Command {

    /**
     * Executes the command by displaying the list of all tasks using the user interface.
     * The method leverages the provided UI component to show tasks currently managed within the task list.
     *
     * @param tasks   The task list containing the tasks to be displayed.
     * @param ui      The user interface responsible for displaying the list of tasks.
     * @param storage The storage component, not used in this command but included for interface consistency.
     * @return A string representation of all tasks, formatted for user display.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks);
    }
}
