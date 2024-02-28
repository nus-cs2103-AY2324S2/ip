package chimp.command;

import chimp.core.Storage;
import chimp.core.TaskList;
import chimp.core.Ui;

/**
 * Represents a command to exit the application.
 * When executed, it returns a farewell message and marks the command as an exit command.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     * @param tasks The task list.
     * @param ui The user interface.
     * @param storage The storage.
     * @return The farewell message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.say("bye");
    }

    /**
     * Checks if the command is an exit command.
     * @return True if the command is an exit command, false otherwise.
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
