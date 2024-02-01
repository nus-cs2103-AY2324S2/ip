package checkbot.command;

import checkbot.Storage;
import checkbot.task.TodoList;
import checkbot.Ui;

/**
 * Represents a command to list all tasks in the list.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TodoList todoList, Storage storage, Ui ui) {
        ui.showList(todoList);
    }
}
