package command;

import roland.Storage;
import roland.TaskList;
import roland.Ui;

/**
 * The HelpCommand class represents a command to display a help message.
 * This command is executed by calling the execute method, which returns a string containing a list of available commands.
 */
public class HelpCommand extends Command {

    /**
     * Executes the HelpCommand by returning a string containing a list of available commands.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The user interface to display the help message.
     * @param storage The storage object (not used in this command).
     * @return A string containing a list of available commands.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        return "list (all tasks)\n" +
                "find [KEYWORD] \n" +
                "mark [INDEX]: \n" +
                "unmark [INDEX] \n" +
                "note [INDEX] /[NOTES] \n" +
                "delete [INDEX] \n" +
                "clear (all tasks) \n" +
                "todo [DESCRIPTION]\n" +
                "deadline [DESCRIPTION] /by [YYYY-MM-DD] \n" +
                "event [DESCRIPTION] /from [START] /to [END] \n";
    }

}
