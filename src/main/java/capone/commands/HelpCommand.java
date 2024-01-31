package capone.commands;

import capone.Storage;
import capone.TaskList;
import capone.Ui;
import capone.exceptions.CaponeException;

public class HelpCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CaponeException {
        ui.sendMessage("Commands I understand:\n"
                + "1. list - Lists the tasks entered.\n"
                + "2. todo [description] - Creates a new capone.tasks.ToDo task. Remember to enter the description!\n"
                + "3. deadline [description] /by [date] - Creates a new capone.tasks.Deadline task.\n"
                + "   Remember to enter the description and date!\n"
                + "   Dates are recognised in the following format - 'yyyy-mm-dd HHmm' (24-hour).\n"
                + "4. event [description] /from [date] /to [date] - Creates a new capone.tasks.Event task.\n"
                + "   Remember to enter the description, as well as the start and end date!\n"
                + "   Dates are recognised in the following format - 'yyyy-mm-dd HHmm' (24-hour).\n"
                + "5. mark [index] - Marks a task as completed. Use this in conjunction with the 'list' command!\n"
                + "6. unmark [index] - Unmarks a task. Use this in conjunction with the 'list' command!\n"
                + "7. delete [index] - Deletes a task. Use this in conjunction with the 'list' command!\n"
                + "8. find [keyword] - Finds all tasks that matches the given input keyword.\n");
    }
}
