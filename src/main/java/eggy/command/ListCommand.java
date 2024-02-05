package eggy.command;

import eggy.storage.Storage;
import eggy.task.TaskList;
import eggy.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Prints all tasks in the task list.
     *
     * @param tasks   The task list of the chatbot.
     * @param ui      The user interface of the chatbot.
     * @param storage The storage of the chatbot.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.printTaskListEmpty();
        } else {
            ui.printTaskList(tasks);
        }
    }
}
