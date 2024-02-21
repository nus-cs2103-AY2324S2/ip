package capone.commands;

import java.util.Scanner;

import capone.Storage;
import capone.TaskList;
import capone.exceptions.CaponeException;
import capone.ui.Ui;

/**
 * Represents a command to terminate the application. Extends the abstract class Command.
 *
 * @author Tay Rui-Jie
 */
public class ByeCommand extends Command {
    /** Scanner used for handling user input. */
    private final Scanner scanner;

    /**
     * Constructs a ByeCommand with the specified scanner.
     *
     * @param scanner The scanner for user input.
     */
    public ByeCommand(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Executes the ByeCommand, displaying a farewell message and terminating the application.
     *
     * @param taskList The TaskList to be updated (not used in this command).
     * @param ui       The Ui to interact with the user.
     * @param storage  The Storage for saving data (not used in this command).
     * @throws CaponeException If any Capone-related exception occurs.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws CaponeException {
        scanner.close();
        System.exit(0);
        return ui.sendGoodbye();
    }
}
