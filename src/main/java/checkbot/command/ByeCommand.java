package checkbot.command;

import checkbot.Storage;
import checkbot.Ui;
import checkbot.task.TodoList;

/**
 * Represents a command to say goodbye to the user.
 */
public class ByeCommand extends Command {

    @Override
    public boolean isBye() {
        return true;
    }

    @Override
    public void executeCommand(TodoList todoList, Storage storage, Ui ui) {
        ui.showGoodbye();
    }
}
