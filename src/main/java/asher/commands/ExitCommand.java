package asher.commands;

import asher.tasks.TaskList;
import asher.ui.Ui;

/**
 * Represents a command for exiting.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an Exit Command object with the given input string.
     *
     * @param input The input string for the Exit Command.
     */
    public ExitCommand(String input) {
        super(input);
    }

    /**
     * Executes the Exit Command.
     *
     * @param ui The UI object to interact with the user.
     * @param taskList The list of tasks.
     * @param storage The storage object for reading and writing tasks from a file.
     * @return The string input for Exit Command.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        storage.writeToFile(taskList);
        return ui.showExitMessage();
    }
}
