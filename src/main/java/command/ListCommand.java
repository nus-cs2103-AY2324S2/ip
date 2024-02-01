package command;

import andelu.AndeluException;
import andelu.Storage;
import andelu.TaskList;
import andelu.Ui;

/**
 * A ListCommand to list down the current list of tasks.
 * A subclass of Command class.
 */
public class ListCommand extends Command {

    /**
     * Lists down the current list of tasks (from the file).
     *
     * @param tasks The TaskList Object that contains a List of Task.
     * @param ui The Ui Object that interact with the user.
     * @param storage Storage Manager to writing to the file.
     * @throws AndeluException
     */
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) throws AndeluException {
        ui.printAnyStatement("Here are the tasks in your list:");

        tasks.setTasks(storage.loadTasksFromFile());

        for (int i = 0; i < tasks.getTasks().size(); i++) {
            System.out.println("      " + (i + 1) + "." + tasks.getTasks().get(i).toString());
        }
    }
}
