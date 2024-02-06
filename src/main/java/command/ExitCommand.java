package command;
import duke.Ui;
import task.TaskList;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {
    /**
     * Exits the program.
     * @param tasks Task list to execute the command on.
     * @param ui Ui to display the result of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.sayBye();
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
