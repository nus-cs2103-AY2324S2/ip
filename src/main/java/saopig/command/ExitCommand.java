package saopig.command;

import saopig.Storage;
import saopig.Ui;
import saopig.task.TaskList;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command to exit the program.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage.
     * @return response to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ("\n"
                + "As our time together comes to a close, "
                + "I just want to say it's been an absolute delight!\n "
                + "Remember, every day is a new adventure waiting to happen.\n "
                + "Bye for now, and take care! ");
    }

    /**
     * Returns true if the command is an exit command.
     * Program should exit after return.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
