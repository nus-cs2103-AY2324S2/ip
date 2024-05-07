package command;
import sky.Ui;
import task.TaskList;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * Exits the program.
     * @param tasks Task list to exit the program from.
     * @param ui Ui to display the exit message to the user.
     * @return Exit message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        return ui.sayBye();
    }

    /**
     * Returns true if the command is an exit command.
     * @return True if the command is an exit command, false otherwise.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
