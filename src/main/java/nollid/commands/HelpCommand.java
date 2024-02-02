package nollid.commands;

import nollid.Storage;
import nollid.TaskList;

/**
 * HelpCommand class represents a command for displaying a help message with available commands.
 * It extends the Command class and implements the execute method to perform the command logic.
 */
public class HelpCommand extends Command {
    /**
     * Overrides the execute method from the Command class.
     * Executes the command to display information about available commands.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String message = "Available commands:\n"
                + "list \t\t- Lists all your tasks\n"
                + "todo \t- Create a new todo task.\n"
                + "deadline \t- Create a new task with a deadline.\n"
                + "event \t- Create a new task with a starting and ending time.\n"
                + "mark \t- Mark a task as completed.\n"
                + "unmark \t- Mark a task as not completed.\n"
                + "delete \t- Delete a task.\n"
                + "find \t\t- Look for task containing a given keyword.\n"
                + "bye \t\t- Closes the application.";

        return message;
    }
}
