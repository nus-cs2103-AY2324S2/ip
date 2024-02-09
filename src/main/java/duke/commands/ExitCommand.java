package duke.commands;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;

/**
 * ExitCommand class represents a command to exit the application.
 * It extends the Command class and provides methods to execute the command.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command by displaying a farewell message.
     *
     * @param taskList The list of tasks (not used in this command).
     * @param ui       The user interface (not used in this command).
     * @param storage  The storage handler (not used in this command).
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Indicates that this command is an exit command.
     *
     * @return true since this command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
