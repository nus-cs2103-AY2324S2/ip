package javassist.command;

import javassist.exception.JavAssistException;
import javassist.util.ExpenseList;
import javassist.util.JavAssistList;
import javassist.util.Storage;
import javassist.util.Ui;

/**
 * Represents a command to add expense.
 */
public class AddExpenseCommand implements Command {
    private String input;

    public AddExpenseCommand(String input) {
        this.input = input;
    }

    /**
     * Increments the expense of the given category.
     *
     * @param javAssistList Holds the expenses added.
     * @param ui Displays messages about executed operation.
     * @param storage Handles IO storage operation.
     * @return String of response of chatbot.
     * @throws JavAssistException If float format or category is not valid.
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

        ExpenseList.ExpenseCategory category = parseExpenseCategory(expense[0]);
        list.addExpense(category, amount);
        storage.writeExpensesToFile(list);
        return ui.showAddedExpense(list);
    }

    private ExpenseList.ExpenseCategory parseExpenseCategory(String categoryString) throws JavAssistException {
        String category = categoryString.trim().toUpperCase().substring(4).trim();
        try {
            return ExpenseList.ExpenseCategory.valueOf(category);
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
        AddExpenseCommand aec = (AddExpenseCommand) a;
        return this.input.equals(aec.input);
    }
}
