package checkbot.command;

import checkbot.exception.InvalidIndexException;
import checkbot.Storage;
import checkbot.task.Task;
import checkbot.task.TodoList;
import checkbot.Ui;

/**
 * Represents a command to mark a task as completed given an index.
 */
public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TodoList todoList, Storage storage, Ui ui) throws InvalidIndexException {
        Task task = todoList.markTask(index);
        ui.showMarkedTaskMessage(task);
    }
}
