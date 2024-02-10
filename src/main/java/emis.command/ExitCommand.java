package main.java.emis.command;
import main.java.emis.*;

/**
 * The ExitCommand class represents a command to exit the EMIS application.
 * When executed, it terminates the application.
 */
public class ExitCommand extends Command {

    /**
     * Constructs a new ExitCommand object.
     */
    public ExitCommand() {
    }

    /**
     * Executes the exit command by terminating the application.
     *
     * @param t The TaskList object representing the list of tasks.
     * @param ui The Ui object handling interactions with the user.
     * @param s The Storage object handling loading and saving of tasks.
     */
    @Override
    public void execute(TaskList t, Ui ui, Storage s) {
        Ui.exit();
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return true, as the exit command represents an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
