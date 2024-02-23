package asher.commands;

import asher.tasks.TaskList;
import asher.ui.Ui;

/**
 * Represents a command to list out the task.
 */
public class ListCommand extends Command {

    /**
     * Constructs a List Command object with the given input string.
     *
     * @param input The input string for the List Command.
     */
    public ListCommand(String input) {
        super(input);
    }

    /**
     * Executes the List Command.
     *
     * @param ui The UI object to interact with the user.
     * @param taskList The list of tasks.
     * @param storage The storage object for reading and writing tasks from a file.
     * @return The string input for List Command.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        taskList.sortByDeadline();
        return ui.showTaskList(taskList);
    }
}
