package commands;

import util.Ui;
import util.TaskList;
import util.Storage;

/**
 * The InvalidCommand class represents a command that is invalid or not recognized by the chatbot.
 * It extends the Command class and implements the execute method to handle the invalid command.
 */
public class InvalidCommand extends Command {

    public String message;

    /**
     * Constructs an InvalidCommand object with the specified error message.
     *
     * @param message The error message indicating the reason for the invalid command.
     */
    public InvalidCommand(String message) {
        this.message = message;
    }

    /**
     * Executes the invalid command by printing the error message to the user interface.
     *
     * @param taskList The TaskList containing the current tasks.
     * @param ui       The Ui instance for user interaction and output.
     * @param storage  The Storage instance for saving tasks or loading data.
     */
    @Override
    public UserCommand execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printMessage(this.message);
        return new UserCommand("\t" + this.message);
    }
}
