package alpa.commands;

import alpa.tasks.TaskList;
import alpa.utils.Storage;

/**
 * Represents a command that displays a help message to the user.
 */
public class HelpCommand implements Command {

    /**
     * Executes the help command and returns the help message.
     *
     * @param tasks   The task list.
     * @param storage The storage.
     * @return The help message.
     */
    @Override
    public String executeCommand(TaskList tasks, Storage storage) {
        String helpMessage = getHelpMessage();
        return helpMessage;
    }

    /**
     * Retrieves the help message.
     *
     * @return The help message.
     */
    private String getHelpMessage() {
        return "I am Alpa - Your Fluffy Task Manager!\n"
            + "Here are some commands you can use:\n"
            + "1. list - lists all the tasks you have!\n"
            + "2. todo <description> - Adds a todo task\n"
            + "3. deadline <description> /by <date/time> - Adds a new deadline task\n"
            + "4. event <description> /at <date/time> - Adds a new event task\n"
            + "5. mark <task number> - Marks a task as done\n"
            + "6. unmark <task number> - Marks a task as not done\n"
            + "7. delete <task number> - Deletes a task\n"
            + "8. bye - Exits the application\n"
            + "9. help - Displays this help message\n";
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return True if the command is an exit command, false otherwise.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
