package asher.commands;

import asher.tasks.TaskList;
import asher.ui.Ui;

/**
 * Represents the abstract base class for all command types in the Asher Bot.
 */
public abstract class Command {
    protected String input;

    /**
     * Constructs a new Command object with the given input string.
     *
     * @param input The input string provided by the user.
     */
    public Command(String input) {
        this.input = input;
    }

    /**
     * Executes the command with the given ui, taskList and storage.
     *
     * @param ui The UI object to interact with the user.
     * @param taskList The list of tasks.
     * @param storage The storage object for reading and writing tasks from a file.
     * @return A String representing the result of executing the command.
     */
    public abstract String execute(Ui ui, TaskList taskList, Storage storage);
}
