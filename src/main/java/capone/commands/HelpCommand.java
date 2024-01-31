package capone.commands;

import capone.exceptions.CaponeException;
import capone.TaskList;
import capone.Storage;
import capone.Ui;

/**
 * Represents a command to display a list of supported commands.
 * Extends the abstract class Command.
 *
 * @author Tay Rui-Jie
 */
public class HelpCommand extends Command {

    /**
     * Executes the HelpCommand, displaying a list of supported commands.
     *
     * @param taskList The TaskList (not used in this command).
     * @param ui       The Ui to interact with the user.
     * @param storage  The Storage for saving data (not used in this command).
     * @throws CaponeException If any Capone-related exception occurs.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CaponeException {
        ui.sendMessage("Commands I understand:\n" +
                "1. list - Lists the tasks entered.\n" +
                "2. todo [description] - Creates a new capone.tasks.ToDo task. Remember to enter the description!\n" +
                "3. deadline [description] /by [date] - Creates a new capone.tasks.Deadline task.\n" +
                "   Remember to enter the description and date!\n" +
                "   Dates are recognized in the following format - 'yyyy-mm-dd HHmm' (24-hour).\n" +
                "4. event [description] /from [date] /to [date] - Creates a new capone.tasks.Event task.\n" +
                "   Remember to enter the description, as well as the start and end date!\n" +
                "   Dates are recognized in the following format - 'yyyy-mm-dd HHmm' (24-hour).\n" +
                "5. mark [index] - Marks a task as completed. Use this in conjunction with the 'list' command!\n" +
                "6. unmark [index] - Unmarks a task. Use this in conjunction with the 'list' command!\n" +
                "7. delete [index] - Deletes a task. Use this in conjunction with the 'list' command!\n");
    }
}
