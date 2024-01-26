package commands;

import commands.Command;
import services.Storage;
import services.TaskList;
import services.UI;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {
    public String message;
    public InvalidCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.printMessage(this.message);
    }
}
