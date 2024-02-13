package duke.command;

import duke.exception.DukeException;
import duke.util.DukeList;
import duke.util.ExpenseList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents a command to list expenses.
 */
public class ListExpenseCommand implements Command {

    /**
     * List the expenses of all categories.
     *
     * @param dukeList Holds the expenses added.
     * @param ui Displays messages about executed operation.
     * @param storage Handles IO storage operation.
     * @return String of response of chatbot.
     * @throws DukeException If float format or category is not valid, or amount given is more than current expense.
     */
    @Override
    public String execute(DukeList dukeList, Ui ui, Storage storage) throws DukeException {
        ExpenseList list = (ExpenseList) dukeList;
        return ui.printExpenses(list);
    }

    @Override
    public boolean isExpenseCommand() {
        return true;
    }
}
