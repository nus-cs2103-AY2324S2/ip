package checkbot.command;

import checkbot.Storage;
import checkbot.Ui;
import checkbot.exception.CheckbotException;
import checkbot.task.TodoList;

/**
 * Represents a command that can be executed.
 */
public abstract class Command {
    public boolean isBye() {
        return false;
    }

    /**
     * Executes the command. Implementations of this method
     * should modify the todoList, storage and ui as necessary.
     *
     * @param todoList The list of tasks.
     * @param storage  The storage object.
     * @param ui       The user interface.
     * @throws CheckbotException If an error occurs during execution.
     */
    public abstract void executeCommand(TodoList todoList, Storage storage, Ui ui) throws CheckbotException;
}
