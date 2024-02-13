package duke.command;

import duke.exception.DukeException;
import duke.util.DukeList;
import duke.util.ExpenseList;
import duke.util.Storage;
import duke.util.Ui;

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
     * @param dukeList Holds the expenses added.
     * @param ui Displays messages about executed operation.
     * @param storage Handles IO storage operation.
     * @return String of response of chatbot.
     * @throws DukeException If float format or category is not valid.
     */
    @Override
    public String execute(DukeList dukeList, Ui ui, Storage storage) throws DukeException {
        ExpenseList list = (ExpenseList) dukeList;
        String[] expense = input.split("/amount");
        float amount;
        try {
            amount = Float.parseFloat(expense[1].trim());
        } catch (NumberFormatException e) {
            throw new DukeException("Amount is in invalid format.");
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
            throw new DukeException("Invalid category. "
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
