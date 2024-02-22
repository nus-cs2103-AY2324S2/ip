package emisCommand;

import emis.TaskList;
import emis.Storage;

/**
 * The PrintCommand class represents a command to print the list of tasks in the EMIS application.
 * When executed, it displays the list of tasks to the user.
 */
public class HelpCommand extends Command {

    /**
     * Constructs a new PrintCommand object.
     */
    public HelpCommand() {
    }

    /**
     * Executes the print command by displaying the list of tasks to the user.
     *
     * @param tasklist The TaskList object representing the list of tasks.
     * @param ui The Ui object handling interactions with the user.
     * @param storage The Storage object handling loading and saving of tasks.
     */
    @Override
    public String execute(TaskList tasklist, Storage storage) {
        String response = "";
        response += "Emis is happy to help with printing a list of tasks with the command 'list'.";
        response += "\nAdd todos with the command 'todo (insert task here)'.";
        response += "\nAdd deadlines with the command 'deadline (insert deadline name) /by (insert deadline here in the format yyyy-mm-dd hhmm)'.";
        response += "\nAdd events with the command 'event (insert event name) /from (insert start time) /to (insert end time)'.";
        response += "\nMark tasks as done with the command 'mark (task no)'.";
        response += "\nMark tasks as undone with the command 'unmark (task no)'.";
        response += "\nDelete tasks  with the command 'delete (task no)'.";
        response += "\nTo stop talking to Emis, please say 'bye'.";
        return response;
    }
}
