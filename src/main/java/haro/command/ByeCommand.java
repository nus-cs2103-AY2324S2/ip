package haro.command;

import haro.Storage;
import haro.TaskList;
import haro.Ui;

/**
 * The ByeCommand class represents a command to exit the application.
 * It extends the base Command class and implements the execute method to save Tasks to the save file before
 * exiting the application.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a ByeCommand instance indicating that it signifies an exit of the application.
     */
    public ByeCommand() {
        super(true);
    }

    /**
     * Executes the command by saving the TaskList to disk before exiting the application.
     *
     * @param taskList TaskList to be modified by the command
     * @param ui       Ui for displaying messages related to command execution
     * @param storage  Storage for saving and loading task data
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        storage.saveToDisk(taskList);
        return ui.bye();
    }
}
