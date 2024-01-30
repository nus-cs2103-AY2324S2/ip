package commands;

import services.Storage;
import services.TaskList;
import services.UI;

/**
 * Exits the program.
 */

public class ExitCommand extends AbstractCommand {
    @Override
    public UserCommand execute(TaskList taskList, UI ui, Storage storage) {
        ui.printExitMessage();
        // monkey fix?
        return new ExitUserCommand();
    }
}
