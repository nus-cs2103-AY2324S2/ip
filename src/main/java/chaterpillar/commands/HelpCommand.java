package chaterpillar.commands;

import chaterpillar.storage.Storage;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;

/**
 * <code>Command</code> to print out the help message.
 *
 * @author marclamp
 */
public class HelpCommand extends Command {

    private static final String HELP_MESSAGE = "Hi! Here are the list of commands I recognise: \n\n"
                                               + "'list' - lists the tasks registered in the list\n"
                                               + "'today' - lists the tasks for today\n"
                                               + "'find' - finds the tasks containing the keyword specified\n"
                                               + "'todo' - adds an item that has no due date\n"
                                               + "'deadline' - adds an item with a due date\n"
                                               + "'event' - adds an item that has a duration\n"
                                               + "'mark' - marks the task as completed\n"
                                               + "'unmark' - marks the task as not completed\n"
                                               + "'update' - updates the task details\n"
                                               + "'help' - opens the list of commands available\n"
                                               + "'bye' - exits the chatbot";

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
