package command;
import duke.Ui;
import task.TaskList;

/**
 * Represents a command to be executed.
 */
public abstract class Command {
    /**
     * Executes the command.
     * @param tasks Task list to execute the command on.
     * @param ui Ui to display the result of the command.
     * @throws Exception If an error occurs during the execution of the command.
     */
    public void execute(TaskList tasks, Ui ui) throws Exception {
    }

    /**
     * Returns true if the command is an exit command.
     * @return True if the command is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
