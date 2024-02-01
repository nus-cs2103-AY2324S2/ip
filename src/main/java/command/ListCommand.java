package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A ListCommand to list down the current list of tasks.
 * A subclass of Command class.
 */
public class ListCommand extends Command {

    /**
     * Lists down the current list of tasks (from the file).
     *
     * @param tasks the Task Object that contains a List of Task.
     * @param ui The Ui Object that interact with the user.
     * @param storage Storage Manager to writing to the file.
     * @throws DukeException
     */
    @Override
    public void excuteCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printAnyStatement("Here are the tasks in your list:");
        tasks.setTasks(storage.loadTasksFromFile());
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            System.out.println("      " + (i + 1) + "." + tasks.getTasks().get(i).toString());
        }
    }
}
