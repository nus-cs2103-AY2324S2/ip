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
        switch (expense[0].trim().toUpperCase().substring(4)) {
        case "FOOD":
            list.setFood(amount + list.getFood());
            break;
        case "GROCERY":
            list.setGrocery(amount + list.getGrocery());
            break;
        case "BOOKS":
            list.setBooks(amount + list.getBooks());
            break;
        case "TRANSPORT":
            list.setTransport(amount + list.getTransport());
            break;
        case "CLOTHES":
            list.setClothes(amount + list.getClothes());
            break;
        case "ENTERTAINMENT":
            list.setEntertainment(amount + list.getEntertainment());
            break;
        case "OTHER":
            list.setOther(amount + list.getOther());
            break;
        default:
            throw new JavAssistException("Invalid category. "
                    + "Try: food, grocery, transport, books, clothes, entertainment, others");
        }
        storage.writeExpensesToFile(list);
        return ui.showAddedExpense(list);
    }

    @Override
    public boolean isExpenseCommand() {
        return true;
    }
}
