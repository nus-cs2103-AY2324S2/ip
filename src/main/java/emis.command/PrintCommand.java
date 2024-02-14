package main.java.emis.command;
import main.java.emis.*;

/**
 * The PrintCommand class represents a command to print the list of tasks in the EMIS application.
 * When executed, it displays the list of tasks to the user.
 */
public class PrintCommand extends Command {

    /**
     * Constructs a new PrintCommand object.
     */
    public PrintCommand() {
    }

    /**
     * Executes the print command by displaying the list of tasks to the user.
     *
     * @param t The TaskList object representing the list of tasks.
     * @param ui The Ui object handling interactions with the user.
     * @param s The Storage object handling loading and saving of tasks.
     */
    @Override
    public void execute(TaskList t, Ui ui, Storage s) {
        t.printList();
    }

    /**
     * Indicates whether the command is an exit command.
     *
     * @return false, as the print command does not represent an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
