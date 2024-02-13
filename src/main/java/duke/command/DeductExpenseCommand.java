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
            if (checkDeductable(amount, list.getFood())) {
                list.setFood(list.getFood() - amount);
            } else {
                throw new DukeException("Amount to deduct is greater than initial expense!");
            }
            break;
        case "GROCERY":
            if (checkDeductable(amount, list.getGrocery())) {
                list.setGrocery(list.getGrocery() - amount);
            } else {
                throw new DukeException("Amount to deduct is greater than initial expense");
            }
            break;
        case "BOOKS":
            if (checkDeductable(amount, list.getBooks())) {
                list.setBooks(list.getBooks() - amount);
            } else {
                throw new DukeException("Amount to deduct is greater than initial expense");
            }
            break;
        case "TRANSPORT":
            if (checkDeductable(amount, list.getTransport())) {
                list.setTransport(list.getTransport() - amount);
            } else {
                throw new DukeException("Amount to deduct is greater than initial expense");
            }
            break;
        case "CLOTHES":
            if (checkDeductable(amount, list.getClothes())) {
                list.setClothes(list.getClothes() - amount);
            } else {
                throw new DukeException("Amount to deduct is greater than initial expense");
            }
            break;
        case "ENTERTAINMENT":
            if (checkDeductable(amount, list.getClothes())) {
                list.setEntertainment(list.getEntertainment() - amount);
            } else {
                throw new DukeException("Amount to deduct is greater than initial expense");
            }
            break;
        case "OTHER":
            if (checkDeductable(amount, list.getOther())) {
                list.setOther(list.getOther() - amount);
            } else {
                throw new DukeException("Amount to deduct is greater than initial expense");
            }
            break;
        default:
            throw new DukeException("Invalid category. "
                    + "Try: food, grocery, transport, books, clothes, entertainment, others");
        }
        storage.writeExpensesToFile(list);
        return ui.showDeductedExpense(list);
    }
    private boolean checkDeductable(float amount, float initial) {
        return amount <= initial;
    }

    @Override
    public boolean isExpenseCommand() {
        return true;
    }
}
