package nollid.commands;

import nollid.Storage;
import nollid.TaskList;
import nollid.Ui;

/**
 * HelpCommand class represents a command for displaying a help message with available commands.
 * It extends the Command class and implements the execute method to perform the command logic.
 */
public class HelpCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String message = "Available commands:\n"
                + "list \t\t- Lists all your tasks\n"
                + "todo \t\t- Create a new todo task.\n"
                + "deadline \t- Create a new task with a deadline.\n"
                + "event \t\t- Create a new task with a starting and ending time.\n"
                + "mark \t\t- Mark a task as completed.\n"
                + "unmark \t\t- Mark a task as not completed.\n"
                + "delete \t\t- Delete a task.\n"
                + "find \t\t- Look for task containing a given keyword.";

        ui.sendMessage(message);
    }
}
