package commands;

import storage.Storage;
import task.TaskList;
/**
 * Represents the command used to exit the application.
 */
public class HelpCommand extends Command {
    private static final String HELP_MESSAGE = "Sorry, Uncle only understands the following commands:\n"
            + "1. todo <description>: Adds a new todo task.\n"
            + "2. deadline <description> /by <date>: Adds a new task with a deadline.\n"
            + "3. event <description> /from <date> /to <date>: Adds a new event task.\n"
            + "4. mark <task number>: Marks a task as done.\n"
            + "5. unmark <task number>: Marks a task as undone.\n"
            + "6. list: Lists all tasks.\n"
            + "7. find <keyword>: Finds tasks containing the keyword.\n"
            + "8. remind: Shows upcoming tasks for today.";
    /**
     * Executes the ExitCommand, exiting the application
     * @param tasks   The TaskList representing the collection of tasks.
     * @param storage The Storage object handling storage operations.
     * @return
     */
    public String execute(TaskList tasks, Storage storage) {
        return HELP_MESSAGE;
    }
}
