package checkbot.command;

import checkbot.exception.InvalidIndexException;
import checkbot.exception.SaveFileException;
import checkbot.Storage;
import checkbot.task.Task;
import checkbot.task.TodoList;
import checkbot.Ui;

/**
 * Represents a command to mark a task as incomplete given an index.
 */
public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TodoList todoList, Storage storage, Ui ui) throws InvalidIndexException, SaveFileException {
        Task task = todoList.unmarkTask(index);
        storage.saveTasks(todoList);
        ui.showUnmarkedTaskMessage(task);
    }
}
