package checkbot.command;

import checkbot.Storage;
import checkbot.Ui;
import checkbot.exception.CheckbotException;
import checkbot.task.TodoList;

/**
 * Represents a command to look for tasks that contain the specified substring.
 */
public class FindCommand extends Command {
    private final String substr;

    public FindCommand(String substr) {
        this.substr = substr;
    }

    @Override
    public void execute(TodoList todoList, Storage storage, Ui ui) throws CheckbotException {
        ui.showSubList(todoList.find(substr));
    }
}
