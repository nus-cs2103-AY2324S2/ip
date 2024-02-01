package checkbot.command;

import checkbot.exception.CheckbotException;
import checkbot.Storage;
import checkbot.task.TodoList;
import checkbot.Ui;

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
    public abstract void execute(TodoList todoList, Storage storage, Ui ui) throws CheckbotException;
}
