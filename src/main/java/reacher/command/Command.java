package reacher.command;

import reacher.ReacherException;
import reacher.Storage;
import reacher.TaskList;
import reacher.Ui;
import reacher.ui.MainWindow;

/**
 * Represents a command that can be executed.
 */
public abstract class Command {
    /**
     * Returns whether this command is an exit command.
     */
    public boolean isExit(){
        return false;
    }

    /**
     * Executes the command.
     *
     * @param tasks   List of tasks.
     * @param ui      User interface.
     * @param storage Local file storage.
     * @return
     * @throws ReacherException.
     */
    public String execute(String input, TaskList tasks, Ui ui, Storage storage) throws ReacherException {
        return input;
    }
}
