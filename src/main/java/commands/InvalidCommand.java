package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 *  Handles any InvalidCommand that a user inputs. Error message
 *  will be displayed to the user on execute
 */
public class InvalidCommand extends Command {
    public final String result;

    public InvalidCommand(String result) {
        this.result = result;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return result;
    }

    @Override
    public String showUsage() {
        return "";
    }
}
