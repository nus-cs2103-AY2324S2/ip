package commands;

import exceptions.InvalidFormatException;
import exceptions.LeluException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * This class represents a command for finding tasks, using a keyword, from the task list managed
 * by the chatbot. When executed, this command lists out tasks containing keyword.
 */
public class FindCommand extends Command {
    private static final String COMMAND = " find ";

    /**
     * Executes the command to search for tasks containing the keyword entered by the user.
     *
     * @param tasks   Recorded list of tasks.
     * @param ui      Format of output shown.
     * @param storage To save and load tasks recorded.
     * @param message Input of user.
     * @throws LeluException If the input is in the wrong format.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, String message) throws LeluException {
        checkEmptyDescription(message, COMMAND, LeluException.ErrorType.FIND);
        if (message.replaceFirst(COMMAND, "").split(" ").length > 1) {
            throw new InvalidFormatException("   Enter only ONE keyword to search for your task:\n" +
                    "   find <keyword>\n");
        }
        TaskList res = tasks.filter(message.replaceFirst("find ", ""));
        assert message.length() >= "find #".length() : "Input not handled properly";
        return "   These are matching tasks in your list:\n" + res.toString();
    }


}
