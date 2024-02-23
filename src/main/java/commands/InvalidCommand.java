package commands;

import services.Storage;
import services.TaskList;
import services.UI;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends AbstractCommand {
    private String message;
    public InvalidCommand(String message) {
        this.message = message;
    }

    @Override
    public UserCommand execute(TaskList taskList, UI ui, Storage storage) {
        ui.printMessage(this.message);
        return new UserCommand("\t" + this.message);
    }
}
