package checkbot.command;

import checkbot.Storage;
import checkbot.task.TodoList;
import checkbot.Ui;

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
