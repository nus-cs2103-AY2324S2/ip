package duke.command;

import duke.exception.DukeException;
import duke.util.DukeList;
import duke.util.ExpenseList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents a command to sum expenses.
 */
public class SumExpenseCommand implements Command {
    /**
     * Sums up expenses in all categories.
     *
     * @param dukeList Holds the expenses added.
     * @param ui Displays messages about executed operation.
     * @param storage Handles IO storage operation.
     * @return String of response of chatbot.
     * @throws DukeException Not thrown.
     */
    @Override
    public String execute(DukeList dukeList, Ui ui, Storage storage) throws DukeException {
        ExpenseList list = (ExpenseList) dukeList;
        return ui.showSumExpenses(list);
    }

    @Override
    public boolean isExpenseCommand() {
        return true;
    }
}
