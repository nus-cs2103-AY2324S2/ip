package checkbot.command;

import checkbot.Storage;
import checkbot.Ui;
import checkbot.exception.CheckbotException;
import checkbot.task.TodoList;

public class FindCommand extends Command {
    private final String substr;

    public FindCommand(String substr) {
        this.substr = substr;
    }

    @Override
    public void executeCommand(TodoList todoList, Storage storage, Ui ui) throws CheckbotException {
        ui.showSubList(todoList.find(substr));
    }
}
