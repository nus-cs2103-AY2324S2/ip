package commands;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 *  Represents the command used to display the list of tasks to the user.
 *  The command checks if the task list is empty and provides appropriate feedback to the user.
 *
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    private static final String SUCCESS_MESSAGE = "Congrats, you have no more tasks! Uncle is proud of you!";
    private static final String LIST_MESSAGE = "You have %s tasks in your list!";

    /**
     * Executes the ListCommand, listing out all the tasks in the task list.
     * @param tasks   The TaskList representing the collection of tasks.
     * @param storage The Storage object handling storage operations.
     * @param ui      The Ui object responsible for user interface interactions.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        if (tasks.isEmpty()) {
            ui.showToUser(SUCCESS_MESSAGE);
        } else {
            ui.showList(String.format(LIST_MESSAGE, tasks.numTasks()),tasks);
        }

    }
}
