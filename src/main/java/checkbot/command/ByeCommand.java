package checkbot.command;

import checkbot.Storage;
import checkbot.task.TodoList;
import checkbot.Ui;

/**
 * Represents a command to say goodbye to the user.
 */
public class ByeCommand extends Command {

    @Override
    public boolean isBye() {
        return true;
    }

    @Override
    public void execute(TodoList todoList, Storage storage, Ui ui) {
        ui.showGoodbye();
    }
}
