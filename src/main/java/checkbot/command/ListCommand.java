package checkbot.command;

import checkbot.Storage;
import checkbot.Ui;
import checkbot.task.TodoList;

/**
 * Represents a command to list all tasks in the list.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TodoList todoList, Storage storage, Ui ui) {
        ui.printList(todoList);
    }
}
