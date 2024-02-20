package duke.command;

import duke.exception.DukeException;
import duke.util.DukeList;
import duke.util.ExpenseList;
import duke.util.Storage;
import duke.util.Ui;

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
     * @param dukeList Holds the expenses added.
     * @param ui Displays messages about executed operation.
     * @param storage Handles IO storage operation.
     * @return String of response of chatbot.
     * @throws DukeException Not thrown.
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
        switch (expense[0].trim().toUpperCase().substring(7)) {
        case "FOOD":
            deductFood(list, amount);
            break;
        case "GROCERY":
            deductGrocery(list, amount);
            break;
        case "BOOKS":
            deductBooks(list, amount);
            break;
        case "TRANSPORT":
            deductTransport(list, amount);
            break;
        case "CLOTHES":
            deductClothes(list, amount);
            break;
        case "ENTERTAINMENT":
            deductEntertainment(list, amount);
            break;
        case "OTHER":
            deductOther(list, amount);
            break;
        default:
            throw new DukeException("Invalid category. "
                    + "Try: food, grocery, transport, books, clothes, entertainment, others");
        }
        storage.writeExpensesToFile(list);
        return ui.showDeductedExpense(list);
    }

    private void deductFood(ExpenseList list, float amount) throws DukeException {
        if (checkNotDeductable(amount, list.getFood())) {
            throw new DukeException("Amount to deduct is greater than initial expense!");
        }
        list.setFood(list.getFood() - amount);
    }

    private void deductGrocery(ExpenseList list, float amount) throws DukeException {
        if (checkNotDeductable(amount, list.getGrocery())) {
            throw new DukeException("Amount to deduct is greater than initial expense!");
        }
        list.setGrocery(list.getGrocery() - amount);
    }
    private void deductBooks(ExpenseList list, float amount) throws DukeException {
        if (checkNotDeductable(amount, list.getBooks())) {
            throw new DukeException("Amount to deduct is greater than initial expense!");
        }
        list.setBooks(list.getBooks() - amount);
    }
    private void deductTransport(ExpenseList list, float amount) throws DukeException {
        if (checkNotDeductable(amount, list.getTransport())) {
            throw new DukeException("Amount to deduct is greater than initial expense!");
        }
        list.setTransport(list.getTransport() - amount);
    }
    private void deductClothes(ExpenseList list, float amount) throws DukeException {
        if (checkNotDeductable(amount, list.getClothes())) {
            throw new DukeException("Amount to deduct is greater than initial expense!");
        }
        list.setClothes(list.getClothes() - amount);
    }

    private void deductEntertainment(ExpenseList list, float amount) throws DukeException {
        if (checkNotDeductable(amount, list.getEntertainment())) {
            throw new DukeException("Amount to deduct is greater than initial expense!");
        }
        list.setEntertainment(list.getEntertainment() - amount);
    }

    private void deductOther(ExpenseList list, float amount) throws DukeException {
        if (checkNotDeductable(amount, list.getOther())) {
            throw new DukeException("Amount to deduct is greater than initial expense!");
        }
        list.setOther(list.getOther() - amount);
    }

    private boolean checkNotDeductable(float amount, float initial) {
        return amount > initial;
    }

    @Override
    public boolean isExpenseCommand() {
        return true;
    }
}
