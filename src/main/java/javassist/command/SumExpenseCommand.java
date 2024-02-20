package javassist.command;

import javassist.exception.JavAssistException;
import javassist.util.ExpenseList;
import javassist.util.JavAssistList;
import javassist.util.Storage;
import javassist.util.Ui;

/**
 * Represents a command to sum expenses.
 */
public class SumExpenseCommand implements Command {
    /**
     * Sums up expenses in all categories.
     *
     * @param javAssistList Holds the expenses added.
     * @param ui Displays messages about executed operation.
     * @param storage Handles IO storage operation.
     * @return String of response of chatbot.
     * @throws JavAssistException Not thrown.
     */
    @Override
    public String execute(JavAssistList javAssistList, Ui ui, Storage storage) throws JavAssistException {
        ExpenseList list = (ExpenseList) javAssistList;
        return ui.showSumExpenses(list);
    }

    @Override
    public boolean isExpenseCommand() {
        return true;
    }
}
