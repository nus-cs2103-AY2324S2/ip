package adam.command;

import java.util.ArrayList;

import adam.AdamException;
import adam.Storage;
import adam.task.Task;
import adam.task.TaskList;
import adam.ui.Ui;

/**
 * {@inheritDoc}
 * Represents a command to print the list of tasks.
 */
public class ListCommand extends Command {
    /**
     * Returns a command to print the list of tasks.
     */
    public ListCommand() {
    }

    /**
     * Prints the list of tasks.
     * {@inheritDoc}
     *
     * @param taskList Current TaskList of program.
     * @param ui Ui used by the program.
     * @param storage Storage used by the program.
     * @return The result of the command executed to be printed as the program's response.
     * @throws AdamException If command cannot be executed.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws AdamException {
        ArrayList<Task> tasks = taskList.getTasks();
        String[] results = new String[taskList.size() + 1];

        results[0] = "Here are your tasks:";
        // Copy the tasks into results
        for (int i = 1; i < results.length; i++) {
            results[i] = i + ". " + tasks.get(i - 1).toString();
        }

        return ui.showResult(results);
    }

    /**
     * {@inheritDoc}
     *
     * @return True if program will exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
