package javassist.command;

import javassist.exception.JavAssistException;
import javassist.util.ExpenseList;
import javassist.util.ExpenseList.ExpenseCategory;
import javassist.util.JavAssistList;
import javassist.util.Storage;
import javassist.util.Ui;

/**
 * Represents a command to deduct expense.
 */
public class DeductExpenseCommand implements Command {
    private String input;

    public DeductExpenseCommand(String input) {
        this.input = input;
    }

    /**
     * Decrement the expense of given category.
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
        String[] expense = input.split("/amount");
        float amount;
        try {
            amount = Float.parseFloat(expense[1].trim());
        } catch (NumberFormatException e) {
            throw new JavAssistException("Amount is in invalid format.");
        }

        ExpenseCategory category = parseExpenseCategory(expense[0]);
        list.deductExpense(category, amount);
        storage.writeExpensesToFile(list);
        return ui.showDeductedExpense(list);
    }

    private ExpenseCategory parseExpenseCategory(String categoryString) throws JavAssistException {
        String category = categoryString.trim().toUpperCase().substring(7).trim();
        try {
            return ExpenseCategory.valueOf(category);
        } catch (IllegalArgumentException e) {
            throw new JavAssistException("Invalid category. "
                    + "Try: food, grocery, transport, books, clothes, entertainment, others");
        }
    }

    @Override
    public boolean isExpenseCommand() {
        return true;
    }

    @Override
    public boolean equals(Object a) {
        DeductExpenseCommand dec = (DeductExpenseCommand) a;
        return this.input.equals(dec.input);
    }
}
