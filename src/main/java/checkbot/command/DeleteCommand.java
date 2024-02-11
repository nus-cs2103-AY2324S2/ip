package checkbot.command;

import checkbot.Storage;
import checkbot.Ui;
import checkbot.exception.InvalidIndexException;
import checkbot.exception.SaveFileException;
import checkbot.task.Task;
import checkbot.task.TodoList;

/**
 * Represents a command to delete a task from the list given an index.
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void executeCommand(TodoList todoList, Storage storage, Ui ui) throws InvalidIndexException, SaveFileException {
        Task deletedTask = todoList.deleteTask(index);
        storage.saveTasks(todoList);
        ui.showDeletedTaskMessage(deletedTask, todoList.getLength());
    }
}
