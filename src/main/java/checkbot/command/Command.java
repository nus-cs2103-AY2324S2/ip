package checkbot.command;

import checkbot.exception.CheckbotException;
import checkbot.Storage;
import checkbot.task.TodoList;
import checkbot.Ui;

public abstract class Command {
    public boolean isBye() {
        return false;
    }

    public abstract void execute(TodoList todoList, Storage storage, Ui ui) throws CheckbotException;
}
