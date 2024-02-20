package javassist.command;

import javassist.exception.JavAssistException;
import javassist.util.ExpenseList;
import javassist.util.JavAssistList;
import javassist.util.Storage;
import javassist.util.Ui;

/**
 * Represents a command to list expenses.
 */
public class ListExpenseCommand implements Command {

    /**
     * List the expenses of all categories.
     *
     * @param javAssistList Holds the expenses added.
     * @param ui Displays messages about executed operation.
     * @param storage Handles IO storage operation.
     * @return String of response of chatbot.
     * @throws JavAssistException If float format or category is not valid
     *     or amount given is more than current expense.
     */
    @Override
    public String execute(JavAssistList javAssistList, Ui ui, Storage storage) throws JavAssistException {
        ExpenseList list = (ExpenseList) javAssistList;
        return ui.printExpenses(list);
    }

    @Override
    public boolean isExpenseCommand() {
        return true;
    }
}
