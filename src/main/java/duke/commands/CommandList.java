package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the command for listing all tasks in the task list in the Duke application.
 */
public class CommandList extends Command {
    /**
     * Constructs a new CommandList object.
     */
    public CommandList() {}

    /**
     * Executes the "list" command, which displays all tasks in the task list to the user.
     *
     * @param tasks   The task list containing the tasks to be listed.
     * @param ui      The user interface component for displaying messages to the user.
     * @param storage The storage component for saving the updated task list.
     * @throws DukeException If an error occurs while executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] outputs = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            outputs[i] = String.format("%d. %s", i + 1, task);
        }

        String output = String.join("\n", outputs);
        ui.showMessage(output);
    }
}
