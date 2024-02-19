package chaterpillar.commands;

import chaterpillar.storage.Storage;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;

/**
 * <code>Command</code> to print out the help message.
 */
public class HelpCommand extends Command {

    private static final String HELP_MESSAGE = """
            Hi! Here are the list of commands I recognise:\s

            'list' - lists the tasks registered in the list
            'today' - lists the tasks for today
            'find' - finds the tasks containing the keyword specified
            'todo' - adds an item that has no due date
            'deadline' - adds an item with a due date
            'event' - adds an item that has a duration
            'mark' - marks the task as completed
            'unmark' - marks the task as not completed
            'update' - updates the task details
            'help' - opens the list of commands available
            'bye' - exits the chatbot""";

    /**
     * Prints out the commands that the application accepts.
     *
     * @param tasks the list of tasks.
     * @param ui object that handles the UI of this application.
     * @param storage object that is used for storage.
     * @return help message from the ChatBot.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.echo(HELP_MESSAGE);
        return HELP_MESSAGE;
    }
}
