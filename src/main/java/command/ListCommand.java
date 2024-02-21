package command;

import andelu.AndeluException;
import andelu.Storage;
import andelu.TaskList;
import andelu.Ui;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * A ListCommand to list down the current list of tasks.
 * A subclass of Command class.
 */
public class ListCommand extends Command {

    /**
     * Lists down the current list of tasks (from the file).
     * Returns a response from Andelu.
     *
     * @param tasks The TaskList Object that contains a List of Task.
     * @param ui The Ui Object that interact with the user.
     * @param storage Storage Manager to writing to the file.
     * @return The response from Andelu.
     * @throws AndeluException
     */
    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) throws AndeluException {

        String tasksString = IntStream.range(0, tasks.getTasks().size()).mapToObj(i -> String.format("%d. %s", (i + 1), tasks.getTasks().get(i).toString())).collect(Collectors.joining("\n"));
        String result = "Here are the tasks in your list:\n" + tasksString;

        return result;
    }
}
