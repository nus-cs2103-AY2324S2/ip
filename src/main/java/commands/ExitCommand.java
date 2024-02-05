package commands;

import storage.Storage;
import task.TaskList;
/**
 * Represents the command used to exit the application.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";
    private static final String EXIT_MESSAGE = "Bye! Hope to see you again soon!";

    /**
     * Executes the ExitCommand, exiting the application
     * @param tasks   The TaskList representing the collection of tasks.
     * @param storage The Storage object handling storage operations.
     * @return
     */
    public String execute(TaskList tasks, Storage storage) {
        System.exit(0);
        return EXIT_MESSAGE;
    }
}
